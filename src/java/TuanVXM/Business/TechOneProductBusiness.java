/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuanVXM.Business;

import TuanVXM.DTO.TechOneProductDTO;
import TuanVXM.Storage.TechOneProductStorage;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TuanVXM
 */
public class TechOneProductBusiness {

    public boolean saveProduct(TechOneProductDTO product) {
        TechOneProductStorage techOneProductStorage = new TechOneProductStorage();
        return techOneProductStorage.saveProduct(product);
    }
    
    public List<TechOneProductDTO> getProducts() {
        TechOneProductStorage techOneProductStorage = new TechOneProductStorage();
        return techOneProductStorage.getProducts();
    }
}
