/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;
import com.poly.model.PCNhapHangCT;
import com.poly.Jdbc.SQLConnect;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class PCNhapHangCTDAO {
    public void insert(PCNhapHangCT model){
        String sql = "insert into PCNhapHangCT values(?,?,?)";
        SQLConnect.executeUpdate(sql, model.getMa_PC(), model.getID_HDN(), model.getHDN_Tien());
    }
    
     public List<PCNhapHangCT> select(int mapc) {
        String sql = "select * from  PCNhapHangCT " +
                "  where Ma_PC = ?";
        List<PCNhapHangCT> list = select(sql, mapc);
        return list.size() > 0 ? list : null;
    }
    
    private List<PCNhapHangCT> select(String sql, Object... args) {
        List<PCNhapHangCT> list = new ArrayList<>();

        try {
            ResultSet rs = null;
            try {
                rs = SQLConnect.excuteQuery(sql, args);
                while (rs.next()) {
                    PCNhapHangCT model = readFromResult(rs);
                    list.add(model);
                }
            } finally {
                rs.close();
            }

        } catch (Exception e) {

        }
        return list;
    }
    
    private PCNhapHangCT readFromResult(ResultSet rs) throws SQLException {
        PCNhapHangCT model = new PCNhapHangCT();
        model.setMa_PC(rs.getInt(1));
        model.setID_HDN(rs.getInt(2));
        model.setHDN_Tien(rs.getFloat(3));
        return model;
    }
}
