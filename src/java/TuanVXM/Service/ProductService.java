/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuanVXM.Service;

import TuanVXM.Business.MuaBanProductBusiness;
import TuanVXM.Business.TechOneProductBusiness;
import TuanVXM.DTO.ProductsDTO;
import TuanVXM.DTO.TechOneProductDTO;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author TuanVXM
 */
public class ProductService {

    public String getAllProducts() {
        String result = null;
        try {
            TechOneProductBusiness techOneProductBusiness = new TechOneProductBusiness();
            MuaBanProductBusiness muaBanProductBusiness = new MuaBanProductBusiness();
            List<TechOneProductDTO> products = techOneProductBusiness.getProducts();
            ProductsDTO productsDTO = new ProductsDTO();
            productsDTO.setProduct(products);
            
            for (TechOneProductDTO product : products) {
                product.setOldProducts(muaBanProductBusiness.getByTOProductId(product.getId()));
            }
            
            JAXBContext jaxbContext = JAXBContext.newInstance(ProductsDTO.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(productsDTO, sw);
            result = sw.toString();
            System.out.println(result);
        } catch (JAXBException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
