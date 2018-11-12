/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuanVXM.test;

import TuanVXM.Config.Config;
import TuanVXM.Config.SingleConfig;
import TuanVXM.DTO.TechOneProductDTO;
import TuanVXM.Service.CrawlService;
import TuanVXM.Util.HTMLParserUtil;
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;
import org.apache.tomcat.util.http.parser.HttpParser;

/**
 *
 * @author TuanVXM
 */
public class Main {

    public static void main(String[] args) throws XMLStreamException, JAXBException {     
        new CrawlService().crawl();
        
        //File file = new File();
        //JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
        //Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        //Config config = (Config) unmarshaller.unmarshal(file);
        //System.out.println(config.toString());
    }
}
