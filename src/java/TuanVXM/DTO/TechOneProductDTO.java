/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuanVXM.DTO;

import TuanVXM.Util.DataFormatUtil;
import java.util.List;

/**
 *
 * @author TuanVXM
 */
public class TechOneProductDTO {
    private String label;
    private String link;
    private String imgLink;
    private String name;
    private long price;
    private long sPrice;
    private String promotion;
    
    private int id;
    
    private List<MuaBanProductDTO> oldProducts;

    public TechOneProductDTO() {
    }

    public TechOneProductDTO(String label, String link, String imgLink, String name, long price, long sPrice, String promotion) {
        this.label = label;
        this.link = link;
        this.imgLink = imgLink;
        this.name = name;
        this.price = price;
        this.sPrice = sPrice;
        this.promotion = promotion;
    }

    public TechOneProductDTO(List<String> strings) {this.label = label;
        this.label = strings.get(0);
        this.link = strings.get(1);
        this.imgLink = strings.get(2);
        this.name = strings.get(3);
        this.price = DataFormatUtil.formatPrice(strings.get(4));
        this.sPrice = DataFormatUtil.formatPrice(strings.get(5));
        this.promotion = strings.get(6);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getsPrice() {
        return sPrice;
    }

    public void setsPrice(long sPrice) {
        this.sPrice = sPrice;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MuaBanProductDTO> getOldProducts() {
        return oldProducts;
    }

    public void setOldProducts(List<MuaBanProductDTO> oldProducts) {
        this.oldProducts = oldProducts;
    }
    
    
}
