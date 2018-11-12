/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuanVXM.DTO;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TuanVXM
 */
@XmlRootElement(name = "Products")
public class ProductsDTO {
    private List<TechOneProductDTO> product;

    public List<TechOneProductDTO> getProduct() {
        return product;
    }

    public void setProduct(List<TechOneProductDTO> products) {
        this.product = products;
    }
}
