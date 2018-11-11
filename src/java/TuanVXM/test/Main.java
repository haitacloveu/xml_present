/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuanVXM.test;

import TuanVXM.DTO.TechOneProductDTO;
import TuanVXM.Util.HTMLParserUtil;
import java.util.List;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author TuanVXM
 */
public class Main {

    public static void main(String[] args) throws XMLStreamException {
        String url = "http://www.techone.vn/dien-thoai/&page=1";
        List<TechOneProductDTO> list = HTMLParserUtil.parseTechOne(url);
        System.out.println(list.size());
        //for (int i=0; i<list.size(); i++) {
        //    System.out.println(list.get(i).getName());
        //    System.out.println(list.get(i).getLabel());
        //    System.out.println(list.get(i).getImgLink());
        //    System.out.println(list.get(i).getLink());
        //    System.out.println(list.get(i).getPrice());
        //    System.out.println(list.get(i).getsPrice());
        //    System.out.println(list.get(i).getPromotion());
        //    System.out.println("------------");
        //}
    }
}
