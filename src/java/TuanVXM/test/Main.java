/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuanVXM.test;

import TuanVXM.Config.SingleConfig;
import TuanVXM.DTO.TechOneProductDTO;
import TuanVXM.Service.CrawlService;
import TuanVXM.Util.HTMLParserUtil;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author TuanVXM
 */
public class Main {

    public static void main(String[] args) throws XMLStreamException {
        //new CrawlService().crawl();
        new CrawlService().crawl2();
    }
}
