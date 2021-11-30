/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.model.PhieuThuCT;
import com.poly.Jdbc.SQLConnect;
import com.poly.model.PhieuNhapHang;
import com.poly.model.PhieuThu;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class PhieuThuCTDAO {
    public void insert(PhieuThuCT model){
        String sql ="insert into PhieuThuCT values(?,?,?)";
        SQLConnect.executeUpdate(sql, model.getMa_PT(),model.getID_HDB(), model.getHDB_Tien());
        
    }
    
    public List<PhieuThuCT> select(int mapt) {
        String sql = "select * from PhieuThuCT " +
                "  where Ma_PT = ?";
        List<PhieuThuCT> list = select(sql, mapt);
        return list.size() > 0 ? list : null;
    }
    
    private List<PhieuThuCT> select(String sql, Object... args) {
        List<PhieuThuCT> list = new ArrayList<>();

        try {
            ResultSet rs = null;
            try {
                rs = SQLConnect.excuteQuery(sql, args);
                while (rs.next()) {
                    PhieuThuCT model = readFromResult(rs);
                    list.add(model);
                }
            } finally {
                rs.close();
            }

        } catch (Exception e) {

        }
        return list;
    }
    
    private PhieuThuCT readFromResult(ResultSet rs) throws SQLException {
        PhieuThuCT model = new PhieuThuCT();
        model.setMa_PT(rs.getInt(1));
        model.setID_HDB(rs.getInt(2));
        model.setHDB_Tien(rs.getFloat(3));
        return model;
    }
}
