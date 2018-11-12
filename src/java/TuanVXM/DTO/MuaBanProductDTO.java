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
public class MuaBanProductDTO {
    private String title;
    private String link;
    private String imgLink;
    private long price;
    private String content;
    private String address;
    private String time;
    
    private int toProductId;
    private int id;

    public MuaBanProductDTO() {
    }

    public MuaBanProductDTO(String title, String link, String imgLink, long price, String content, String address, String time) {
        this.title = title;
        this.link = link;
        this.imgLink = imgLink;
        this.price = price;
        this.content = content;
        this.address = address;
        this.time = time;
    }

    public MuaBanProductDTO(List<String> strings) {
        this.title = strings.get(0);
        this.link = strings.get(1);
        this.imgLink = strings.get(2);
        this.price = DataFormatUtil.formatPrice(strings.get(3));
        this.content = strings.get(4);
        this.address = strings.get(5);
        this.time = strings.get(6);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getToProductId() {
        return toProductId;
    }

    public void setToProductId(int toProductId) {
        this.toProductId = toProductId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
