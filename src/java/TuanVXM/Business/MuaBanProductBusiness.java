/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuanVXM.Business;

import TuanVXM.DTO.MuaBanProductDTO;
import TuanVXM.Storage.MuaBanProductStorage;
import java.util.List;

/**
 *
 * @author TuanVXM
 */
public class MuaBanProductBusiness {

    public boolean saveProducts(List<MuaBanProductDTO> products) {
        System.out.println("MuaBan: " + products.size());
        MuaBanProductStorage muaBanProductStorage = new MuaBanProductStorage();
        for (MuaBanProductDTO product : products) {
            muaBanProductStorage.saveProduct(product);
        }
        return true;
    }
}
