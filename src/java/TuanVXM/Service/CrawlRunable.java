/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuanVXM.Service;

import TuanVXM.Business.MuaBanProductBusiness;
import TuanVXM.Business.TechOneProductBusiness;
import TuanVXM.DTO.MuaBanProductDTO;
import TuanVXM.DTO.TechOneProductDTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author TuanVXM
 */
public class CrawlRunable implements Runnable {

    private int id;
    private TechOneProductDTO product;
    private String realPath;

    public CrawlRunable(int id, TechOneProductDTO product, String realPath) {
        this.id = id;
        this.product = product;
        this.realPath = realPath;
    }

    @Override
    public void run() {
        TechOneProductBusiness techOneProductBusiness = new TechOneProductBusiness();
        MuaBanProductBusiness muaBanProductBusiness = new MuaBanProductBusiness();
        CrawlService crawlService = new CrawlService();
        if (techOneProductBusiness.saveProduct(product)) {
            try {
                String searchUrl = crawlService.regenMuaBanSearchUrl(product.getName());
                
                //System.out.println(searchUrl);
                List<MuaBanProductDTO> muaBanProductDTOs = crawlService.crawlMuaBan(searchUrl, realPath);
                for (MuaBanProductDTO dto : muaBanProductDTOs) {
                    dto.setToProductId(id);
                }
                muaBanProductBusiness.saveProducts(muaBanProductDTOs);
            } catch (XMLStreamException ex) {
                Logger.getLogger(CrawlRunable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
