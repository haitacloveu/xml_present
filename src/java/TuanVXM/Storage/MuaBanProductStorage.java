/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuanVXM.Storage;

import TuanVXM.DTO.MuaBanProductDTO;
import TuanVXM.Util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TuanVXM
 */
public class MuaBanProductStorage {

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
            Logger.getLogger(MuaBanProductStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean saveProduct(MuaBanProductDTO dto) {
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO MB_PRODUCT\n"
                        + "(toProductId, title, link, imgLink, price, content, [address], [time])\n"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, dto.getToProductId());
                stm.setString(2, dto.getTitle());
                stm.setString(3, dto.getLink());
                stm.setString(4, dto.getImgLink());
                stm.setLong(5, dto.getPrice());
                stm.setString(6, dto.getContent());
                stm.setString(7, dto.getAddress());
                stm.setString(8, dto.getTime());
                int result = stm.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MuaBanProductStorage.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return false;
    }

    public List<MuaBanProductDTO> getByTOProductId(int toProductId) {
        List<MuaBanProductDTO> result = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT id, toProductId, title, link, imgLink, price, content, address, time "
                        + "FROM MB_PRODUCT "
                        + "WHERE toProductId = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, toProductId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    MuaBanProductDTO dto = new MuaBanProductDTO();
                    dto.setId(rs.getInt("id"));
                    dto.setToProductId(rs.getInt("toProductId"));
                    dto.setTitle(rs.getString("title"));
                    dto.setLink(rs.getString("link"));
                    dto.setImgLink(rs.getString("imgLink"));
                    dto.setPrice(rs.getInt("price"));
                    dto.setContent(rs.getString("content"));
                    dto.setAddress(rs.getString("address"));
                    dto.setTime(rs.getString("time"));
                    result.add(dto);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TechOneProductStorage.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return result;
    }
}
