/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.model.PhieuNhapHangCT;
import com.poly.model.Thuoc;
import com.poly.Jdbc.SQLConnect;
import com.poly.model.HoaDonBanCT;
import com.poly.model.PhieuNhapHang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhieuNhapHangCTDAO {

    public void insert(PhieuNhapHangCT model) {
        String sql = "insert into HDN_CT values(?,?,?,?,?)";
        SQLConnect.executeUpdate(sql, model.getID_HDN(), model.getID_Thuoc(),
                model.getSoluong(), model.getGiaNhap(), model.getHanSuDung());
    }
    
    public boolean Insert(PhieuNhapHangCT model) {
       if(model == null || model.getSoluong() <= 0 || model.getGiaNhap() <= 0) {
    	   return false;
       }else {
    	   PhieuNhapHangDAO daonh = new PhieuNhapHangDAO();
    	   PhieuNhapHang pnh = daonh.find_ByID(model.getID_HDN());
    	   ThuocDAO daot = new ThuocDAO();
    	   Thuoc thuoc = daot.findByID(model.getID_Thuoc());
    	   if(thuoc == null || pnh == null) {
    		   return false;
    	   }
    	   try {
    		   String sql = "insert into HDN_CT values(?,?,?,?,?)";
    	        SQLConnect.executeUpdate(sql, model.getID_HDN(), model.getID_Thuoc(),
    	                model.getSoluong(), model.getGiaNhap(), model.getHanSuDung());
    	        return true;
		} catch (Exception e) {
			return false;
		}	
       }
    }

    public List<PhieuNhapHangCT> find_ByID(int id) {
        String sql = "select * from HDN_CT "
                + " where ID_HDN = ?";
        List<PhieuNhapHangCT> list = select(sql, id);
        return list.size() > 0 ? list : null;
    }

    // tạo list nhân viên và add vào list
    private List<PhieuNhapHangCT> select(String sql, Object... args) {
        List<PhieuNhapHangCT> list = new ArrayList<>();
        try {
            ResultSet rs = null; // tạo đối tượng resultset
            try {
                rs = SQLConnect.excuteQuery(sql, args);
                while (rs.next()) {
                    PhieuNhapHangCT model = readFromResultSet(rs); // chạy và trả về resultset
                    list.add(model); // add đối tượng vào list
                }
            } finally {
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list; // trả về list sau khi add
    }

    private PhieuNhapHangCT readFromResultSet(ResultSet rs) throws SQLException {
        PhieuNhapHangCT model = new PhieuNhapHangCT();
        model.setID_HDN(rs.getInt(1));
        model.setID_Thuoc(rs.getInt(2));
        model.setSoluong(rs.getInt(3));
        model.setGiaNhap(rs.getFloat(4));
        model.setHanSuDung(rs.getDate(5));
        return model;
    }
}
