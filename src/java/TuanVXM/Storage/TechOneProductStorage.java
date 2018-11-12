/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuanVXM.Storage;

import TuanVXM.DTO.TechOneProductDTO;
import TuanVXM.Util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author TuanVXM
 */
public class TechOneProductStorage {

    PreparedStatement stm = null;
    ResultSet rs = null;
    Connection conn = null;

    public void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean saveProduct(TechOneProductDTO dto) {
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO TO_PRODUCT\n"
                        + "(id, label, link, imgLink, [name], price, sPrice, promotion)\n"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, dto.getId());
                stm.setString(2, dto.getLabel());
                stm.setString(3, dto.getLink());
                stm.setString(4, dto.getImgLink());
                stm.setString(5, dto.getName());
                stm.setLong(6, dto.getPrice());
                stm.setLong(7, dto.getsPrice());
                stm.setString(8, dto.getPromotion());
                int result = stm.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    public int getId(TechOneProductDTO dto) {
        int id = -1;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT id FROM TO_PRODUCT WHERE "
                        + "label = ? AND "
                        + "link = ? AND "
                        + "imgLink = ? AND "
                        + "[name] = ? AND "
                        + "price = ? AND "
                        + "sPrice = ? AND "
                        + "promotion = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, dto.getLabel());
                stm.setString(2, dto.getLink());
                stm.setString(3, dto.getImgLink());
                stm.setString(4, dto.getName());
                stm.setLong(5, dto.getPrice());
                stm.setLong(6, dto.getsPrice());
                stm.setString(7, dto.getPromotion());
                rs = stm.executeQuery();
                if (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return id;
    }
}
