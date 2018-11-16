/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuanVXM.Util;

import TuanVXM.DTO.ProductsDTO;
import TuanVXM.DTO.TechOneProductDTO;
import java.io.InputStream;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author TuanVXM
 */
public class XmlUtil {

    public static XMLStreamReader parseFileToStAXCursor(InputStream is) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(is);
        return reader;
    }

    public static String getTextContent(String elementName, XMLStreamReader reader) throws XMLStreamException {
        if (reader != null) {
            while (reader.hasNext()) {
                int cursor = reader.next();
                if (cursor == XMLStreamConstants.START_ELEMENT) {
                    String tagName = reader.getLocalName();
                    if (tagName.equals(elementName)) {
                        return getCurrentNodeText(reader);
                    }
                }
            }
        }
        return null;
    }

    public static String getNodeValue(String elementName, String attrName,
            String namespaceUri, XMLStreamReader reader) throws XMLStreamException {
        if (reader != null) {
            while (reader.hasNext()) {
                int cursor = reader.next();
                if (cursor == XMLStreamConstants.START_ELEMENT) {
                    String tagName = reader.getLocalName();
                    if (tagName.equals(elementName)) {
                        String result
                                = reader.getAttributeValue(namespaceUri, attrName);
                        return result;
                    }
                }
            }
        }
        return null;
    }

    public static String getCurrentNodeText(XMLStreamReader reader) throws XMLStreamException {
        reader.next();
        String result = reader.getText();
        reader.nextTag();
        return result;
    }

    public static String marshallData(TechOneProductDTO product) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(TechOneProductDTO.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(product, sw);
        return sw.toString();
    }
}
