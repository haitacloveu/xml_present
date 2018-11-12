/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuanVXM.Util;

/**
 *
 * @author TuanVXM
 */
public class DataFormatUtil {

    public static long formatPrice(String priceString) {
        if (priceString == null) {
            return -1;
        }
        priceString = priceString.replace(".", "");
        priceString = priceString.replace("đ", "");
        priceString = priceString.replace(" ", "");
        try {
            return Long.parseLong(priceString);
        } catch (Exception ex) {
            return -1;
        }
    }
}
