/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.model.PhieuThu;
import com.poly.Jdbc.SQLConnect;
import com.poly.model.KhachHang;
import com.poly.model.NguoiDung;
import com.poly.model.PhieuNhapHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.Date;
public class PhieuThuDAO {

    public void insert(PhieuThu model) {
        try {
            String sql = "insert into PhieuThu (NgayThu, NguoiThu, TienThu) values(?,?,?)";
            SQLConnect.executeUpdate(sql, model.getNgayThu(), model.getNguoiThu(), model.getTienThu());
            JOptionPane.showMessageDialog(null, "Tạo Phiếu Thu Thành Công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tạo Phiếu Thu Thất Bại");
        }
    }
    
    public boolean Insert(PhieuThu model) throws Exception {
        if(model == null || model.getTienThu() <= 0 || model.getNguoiThu() == null || model.getNguoiThu().equals("")) {
        	return false;
        }else {
        	NguoiDungDAO daond = new NguoiDungDAO();
      	  	NguoiDung nguoiDung = daond.findbyID(model.getNguoiThu());
      	  	if(nguoiDung == null) {
      	  		return false;
      	  	}
        	try {
                String sql = "insert into PhieuThu (NgayThu, NguoiThu, TienThu) values(?,?,?)";
                SQLConnect.executeUpdate(sql, model.getNgayThu(), model.getNguoiThu(), model.getTienThu());
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public List<PhieuThu> select() {
        String sql = "select * from PhieuThu ";
        List<PhieuThu> list = select(sql);
        return list.size() > 0 ? list : null;
    }
    
    public List<PhieuThu> find_ByDate(Date date){
    	if(date == null) {
    		return null;
    	}
          String sql = "select * from PhieuThu "
                  + " where NgayThu = ? ";
        List<PhieuThu> list = select(sql, date);
        return list.size() > 0 ? list : null;
    }
    public PhieuThu select_dt(int thang, int nam) throws Exception{
        PhieuThu model = new PhieuThu();
        String sql = "select  sum(TienThu)  from PhieuThu "
                + " where MONTH(NgayThu) = ? and  YEAR(NgayThu) = ? ";
        PreparedStatement ppst = SQLConnect.getcon().prepareStatement(sql);
        ppst.setInt(1, thang);
        ppst.setInt(2, nam);
        ResultSet rs = ppst.executeQuery();
        while(rs.next()){
            model.setTienThu(rs.getFloat(1));
        }
        return model;
    }

    public PhieuThu select1() {
        String sql = "select * from PhieuThu "
                + " order by Ma_PT desc";
        List<PhieuThu> list = select(sql);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<PhieuThu> select(String sql, Object... args) {
        List<PhieuThu> list = new ArrayList<>();

        try {
            ResultSet rs = null;
            try {
                rs = SQLConnect.excuteQuery(sql, args);
                while (rs.next()) {
                    PhieuThu model = readFromResult(rs);
                    list.add(model);
                }
            } finally {
                rs.close();
            }

        } catch (Exception e) {

        }
        return list;
    }

    private PhieuThu readFromResult(ResultSet rs) throws SQLException {
        PhieuThu model = new PhieuThu();
        model.setMa_PT(rs.getInt(1));
        model.setNgayThu(rs.getDate(2));
        model.setNguoiThu(rs.getString(3));
        model.setTienThu(rs.getFloat(4));
        return model;
    }
}
