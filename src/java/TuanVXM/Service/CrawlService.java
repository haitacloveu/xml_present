/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuanVXM.Service;

import TuanVXM.Business.MuaBanProductBusiness;
import TuanVXM.Business.TechOneProductBusiness;
import TuanVXM.Config.Config;
import TuanVXM.Config.ReplaceConfig;
import TuanVXM.Config.SingleConfig;
import TuanVXM.Constant.CommonConstant;
import TuanVXM.DTO.MuaBanProductDTO;
import TuanVXM.DTO.TechOneProductDTO;
import TuanVXM.Util.HTMLParserUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author TuanVXM
 */
public class CrawlService {

    public boolean crawl() {
        TechOneProductBusiness techOneProductBusiness = new TechOneProductBusiness();
        MuaBanProductBusiness muaBanProductBusiness = new MuaBanProductBusiness();

        try {
            List<TechOneProductDTO> products = new ArrayList<>();
            int page = 0;

            while (true) {
                page++;
                String url = regenTechOneSearchUrl(page);
                //System.out.println(url);
                List<TechOneProductDTO> tmpList = crawlTechOne(url);
                if (tmpList.isEmpty()) {
                    break;
                }
                products.addAll(tmpList);
            }

            //System.out.println(products.size());
            int id = new Random().nextInt(1000) * 1000;

            for (TechOneProductDTO product : products) {
                id++;
                product.setId(id);
                if (techOneProductBusiness.saveProduct(product)) {
                    String searchUrl = regenMuaBanSearchUrl(product.getName());

                    System.out.println(searchUrl);
                    List<MuaBanProductDTO> muaBanProductDTOs = crawlMuaBan(searchUrl);
                    for (MuaBanProductDTO dto : muaBanProductDTOs) {
                        dto.setToProductId(id);
                    }
                    muaBanProductBusiness.saveProducts(muaBanProductDTOs);
                }
            }

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

    public List<MuaBanProductDTO> crawlMuaBan(String url) throws XMLStreamException {
        Config config = regenMuaBanCrawlConfig();

        List<List<String>> list = HTMLParserUtil.parseHtml(url, config.getReplaceConfigs(),
                config.getStartConfig(), config.getEndConfig(), config.getConfigs());

        List<MuaBanProductDTO> products = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            products.add(new MuaBanProductDTO(list.get(i)));
        }

        return products;
    }

    public static Config regenTechOneCrawlConfig() {
        try {
            Config config = new Config();
            
            File file = new File(CommonConstant.TECH_ONE_CONFIG_FILE);
            JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            config = (Config) unmarshaller.unmarshal(file);
            
            return config;
        } catch (JAXBException ex) {
            //Logger.getLogger(CrawlService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public static Config regenMuaBanCrawlConfig() {
        try {
            Config config = new Config();
            
            File file = new File(CommonConstant.MUA_BAN_CONFIG_FILE);
            JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            config = (Config) unmarshaller.unmarshal(file);
            
            return config;
        } catch (JAXBException ex) {
            //Logger.getLogger(CrawlService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    private String regenTechOneSearchUrl(int page) {
        String result = CommonConstant.TECH_ONE_URL_PATTERN;
        result = result.replace("{page}", Integer.toString(page));
        return result;
    }

    private String regenMuaBanSearchUrl(String keyword) {
        String result = CommonConstant.MUA_BAN_URL_PATTERN;
        keyword = keyword.replace("Công ty", "");
        keyword = keyword.replace("TechOne", "");
        keyword = keyword.replace("Like New", "");
        keyword = keyword.replace(" ", "%20");
        result = result.replace("{search}", keyword);
        return result;
    }
}
