/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.model.PCTraThuocCT;

import java.sql.*;
import com.poly.Jdbc.SQLConnect;
import java.util.ArrayList;
import java.util.List;
public class PCTraThuocCTDAO {
    public void insert(PCTraThuocCT model){
        String sql = "insert into PCTraThuocCT values(?,?,?)";
        SQLConnect.executeUpdate(sql, model.getMa_PC(), model.getID_HDTH(), model.getHDTH_Tien());
    }
    
     public List<PCTraThuocCT> select(int mapc) {
        String sql = "select * from  PCTraThuocCT " +
                "  where Ma_PC = ?";
        List<PCTraThuocCT> list = select(sql, mapc);
        return list.size() > 0 ? list : null;
    }
    
    private List<PCTraThuocCT> select(String sql, Object... args) {
        List<PCTraThuocCT> list = new ArrayList<>();

        try {
            ResultSet rs = null;
            try {
                rs = SQLConnect.excuteQuery(sql, args);
                while (rs.next()) {
                    PCTraThuocCT model = readFromResult(rs);
                    list.add(model);
                }
            } finally {
                rs.close();
            }

        } catch (Exception e) {

        }
        return list;
    }
    
    private PCTraThuocCT readFromResult(ResultSet rs) throws SQLException {
        PCTraThuocCT model = new PCTraThuocCT();
        model.setMa_PC(rs.getInt(1));
        model.setID_HDTH(rs.getInt(2));
        model.setHDTH_Tien(rs.getFloat(3));
        return model;
    }
}
