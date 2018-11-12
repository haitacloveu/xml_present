/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuanVXM.Util;

import TuanVXM.Config.ReplaceConfig;
import TuanVXM.Config.SingleConfig;
import TuanVXM.DTO.TechOneProductDTO;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.TagName;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author TuanVXM
 */
public class HTMLParserUtil {

    public static String loadHtml(String url) {
        String content = null;
        URLConnection connection = null;
        try {
            connection = new URL(url).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
        } catch (Exception ex) {
            //Logger.getLogger(HTMLParserUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content;
    }

    public static String prepareHtml(String html, List<ReplaceConfig> replaceConfigs) {
        if (html == null) {
            return null;
        }
        for (ReplaceConfig config : replaceConfigs) {
            html = html.replace(config.getReplacement(), config.getTarget());
        }
        return html;
    }

    public static List<List<String>> parseHtml(String url, List<ReplaceConfig> replaceConfigs,
            SingleConfig startConfig, SingleConfig endConfig, List<SingleConfig> configs)
            throws XMLStreamException {
        List<List<String>> result = new ArrayList<>();

        String html = prepareHtml(loadHtml(url), replaceConfigs);
        if (html == null) {
            return result;
        }

        InputStream is = new ByteArrayInputStream(html.getBytes(StandardCharsets.UTF_8));

        XMLStreamReader reader = XmlUtil.parseFileToStAXCursor(is);
        while (reader.hasNext()) {
            try {
                int cursor = reader.next();
                if (cursor == XMLStreamConstants.START_ELEMENT) {
                    if (matchConfig(startConfig, reader)) {
                        result.add(getNextRecord(reader, configs, endConfig));
                    }
                }
            } catch (XMLStreamException ex) {
                //Logger.getLogger(HTMLParserUtil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException ex) {
                //Logger.getLogger(HTMLParser.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }

        return result;
    }

    public static List<String> getNextRecord(XMLStreamReader reader,
            List<SingleConfig> configs, SingleConfig endConfig)
            throws XMLStreamException {
        List<String> result = new ArrayList<>();

        for (SingleConfig config : configs) {
            config.setValue(null);
        }

        while (reader.hasNext()) {
            try {
                int cursor = reader.next();
                if (cursor == XMLStreamConstants.START_ELEMENT) {
                    boolean flagEnd = false;

                    if (matchConfig(endConfig, reader)) {
                        flagEnd = true;
                    }

                    for (SingleConfig config : configs) {
                        if (matchConfig(config, reader)) {
                            config.setValue(getConfigValue(config, reader));
                            if (config.getWantedAttr() == null) {
                                break;
                            }
                        }
                    }

                    if (flagEnd) {
                        break;
                    }
                }
            } catch (XMLStreamException ex) {
                //Logger.getLogger(HTMLParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (SingleConfig config : configs) {
            result.add(config.getValue());
            //System.out.println(config.getValue());
        }

        //System.out.println("----");
        return result;
    }

    private static boolean matchConfig(SingleConfig config, XMLStreamReader reader) {
        String tagName = reader.getLocalName();
        if (tagName.equals(config.getTagName())) {
            if (config.getAttrName() == null) {
                return true;
            }

            String attrVal = reader.getAttributeValue("", config.getAttrName());
            if (attrVal != null && attrVal.contains(config.getAttrValue())) {
                return true;
            }
        }
        return false;
    }

    private static String getConfigValue(SingleConfig config, XMLStreamReader reader) throws XMLStreamException {
        String result;
        if (config.getWantedAttr() != null) {
            result = reader.getAttributeValue("", config.getWantedAttr());
        } else {
            result = XmlUtil.getCurrentNodeText(reader);
        }
        return result;
    }
}
