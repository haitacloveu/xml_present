/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuanVXM.Service;

import TuanVXM.Config.Config;
import TuanVXM.Config.ReplaceConfig;
import TuanVXM.Config.SingleConfig;
import TuanVXM.DTO.TechOneProductDTO;
import TuanVXM.Util.HTMLParserUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author TuanVXM
 */
public class CrawlService {
    public boolean crawl() {
        try {
            String url = "http://www.techone.vn/dien-thoai/&page=1";
            
            List<TechOneProductDTO> products = crawlTechOne(url);

            for (int i = 0; i < products.size(); i++) {
                System.out.println(products.get(i).getName());
                System.out.println(products.get(i).getLabel());
                System.out.println(products.get(i).getImgLink());
                System.out.println(products.get(i).getLink());
                System.out.println(products.get(i).getPrice());
                System.out.println(products.get(i).getsPrice());
                System.out.println(products.get(i).getPromotion());
                System.out.println("------------");
            }

            System.out.println(products.size());

            return true;
        } catch (XMLStreamException ex) {
            return false;
        }
    }
    public boolean crawl2() {
        try {
            String url = "http://www.techone.vn/dien-thoai/&page=1";
            
            List<TechOneProductDTO> products = crawlTechOne(url);

            return true;
        } catch (XMLStreamException ex) {
            return false;
        }
    }

    public List<TechOneProductDTO> crawlTechOne(String url) throws XMLStreamException {
        Config config = regenTechOneCrawlConfig();

        List<List<String>> list = HTMLParserUtil.parseHtml(url, config.getReplaceConfigs(),
                config.getStartConfig(), config.getEndConfig(), config.getConfigs());

        List<TechOneProductDTO> products = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            products.add(new TechOneProductDTO(list.get(i)));
        }
        
        return products;
    }

    private static Config regenTechOneCrawlConfig() {
        Config config = new Config();

        config.setStartConfig(new SingleConfig("li", "class", "lli"));
        config.setEndConfig(new SingleConfig("div", "class", "lmore"));

        config.getConfigs().add(new SingleConfig("span", "class", "iconlct"));
        config.getConfigs().add(new SingleConfig("a", null, null, "href"));
        config.getConfigs().add(new SingleConfig("img", null, null, "src"));
        config.getConfigs().add(new SingleConfig("img", null, null, "alt"));
        config.getConfigs().add(new SingleConfig("span", "class", "price"));
        config.getConfigs().add(new SingleConfig("span", "class", "lspecial"));
        config.getConfigs().add(new SingleConfig("div", "class", "lmprom"));

        config.getReplaceConfigs().add(new ReplaceConfig("itemscope", "itemscope=''"));
        config.getReplaceConfigs().add(new ReplaceConfig("&", "&amp;"));
        config.getReplaceConfigs().add(new ReplaceConfig("---", "--"));

        return config;
    }
}
