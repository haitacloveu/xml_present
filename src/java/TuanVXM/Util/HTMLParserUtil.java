/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuanVXM.Util;

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
            //Logger.getLogger(HTMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content;
    }
    
    public static String prepareTechOneHtml(String html) {
        html = html.replace("itemscope", "itemscope=''");
        html = html.replace("&", "&amp;");
        html = html.replace("---", "--");
        return html;
    }

    public static List<TechOneProductDTO> parseTechOne(String url) throws XMLStreamException {
        List<TechOneProductDTO> result = new ArrayList<>();

        String html = prepareTechOneHtml(loadHtml(url));

        InputStream is = new ByteArrayInputStream(html.getBytes(StandardCharsets.UTF_8));

        XMLStreamReader reader = XmlUtil.parseFileToStAXCursor(is);
        while (reader.hasNext()) {
            try {
                int cursor = reader.next();
                if (cursor == XMLStreamConstants.START_ELEMENT) {
                    String tagName = reader.getLocalName();
                    if (tagName.equals("li")) {
                        String liClass
                                = reader.getAttributeValue("", "class");
                        if (liClass != null && liClass.equals("lli")) {
                            result.add(getNextProduct(reader));
                        }
                    }
                }
            } catch (XMLStreamException ex) {
                //Logger.getLogger(HTMLParser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException ex) {
                //Logger.getLogger(HTMLParser.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }

        return result;
    }

    public static TechOneProductDTO getNextProduct(XMLStreamReader reader) throws XMLStreamException {
        TechOneProductDTO product = new TechOneProductDTO();
        while (reader.hasNext()) {
            try {
                int cursor = reader.next();
                if (cursor == XMLStreamConstants.START_ELEMENT) {
                    String tagName = reader.getLocalName();
                    System.out.println("start: " + tagName);
                    if (tagName.equals("span")
                            && reader.getAttributeValue("", "class").contains("iconlct")) {
                        product.setLabel(XmlUtil.getCurrentNodeText(reader));
                    } else if (tagName.equals("a")) {
                        product.setLink(reader.getAttributeValue("", "href"));
                    } else if (tagName.equals("img")) {
                        product.setImgLink(reader.getAttributeValue("", "src"));
                        product.setName(reader.getAttributeValue("", "alt"));
                    //} else if (tagName.equals("h3")) {
                    //    product.setName(XmlUtil.getCurrentNodeText(reader));
                    } else if (tagName.equals("span")
                            && reader.getAttributeValue("", "class").equals("price")) {
                        product.setPrice(formatPrice(XmlUtil.getCurrentNodeText(reader)));
                    } else if (tagName.equals("span")
                            && reader.getAttributeValue("", "class").equals("lspecial")) {
                        product.setsPrice(formatPrice(XmlUtil.getCurrentNodeText(reader)));
                    } else if (tagName.equals("div")
                            && reader.getAttributeValue("", "class").equals("lmprom")) {
                        product.setPromotion(XmlUtil.getCurrentNodeText(reader));
                    } else if (tagName.equals("div")
                            && reader.getAttributeValue("", "class").contains("lmore")) {
                        //break;
                    }
                } else if (cursor == XMLStreamConstants.END_ELEMENT) {
                    String tagName = reader.getLocalName();
                        System.out.println("end: " + tagName);
                    if (tagName.equals("li")) {
                        break;
                    }
                }
            } catch (XMLStreamException ex) {
                //Logger.getLogger(HTMLParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return product;
    }
    
    private static long formatPrice(String priceString) {
        priceString = priceString.replace(".", "");
        priceString = priceString.replace("đ", "");
        return Long.parseLong(priceString);
    }
}
