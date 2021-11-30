/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.Jdbc.SQLConnect;
import com.poly.model.HoaDonBan;
import com.poly.model.KhachHang;
import com.poly.model.NguoiDung;
import com.poly.model.NhaCungCap;
import com.poly.model.PhieuNhapHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.Date;

public class HoaDonBanHangDAO {

    public void insert(HoaDonBan model) {
        try {
            String sql = "insert into HoaDonBan (SDT_KH, TongTien, NgayLap_HD, NguoiTao) values(?,?,?,?)";
            SQLConnect.executeUpdate(sql, model.getSDT_KH(), model.getTongTien(), model.getNgayLapHD(), model.getNguoiTao());
            JOptionPane.showMessageDialog(null, "Tạo Hóa Đơn Thành Công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tạo Hóa Đơn Thất Bại");

        }
    }
    
    public boolean Insert(HoaDonBan model) {
    	if(model == null || model.getSDT_KH() == null ||  model.getSDT_KH().equals("") 
     		   || model.getNguoiTao() == null || model.getNguoiTao().equals("") || model.getTongTien() <= 0) {
     	   return false;
        }else {
        try {
        	NguoiDungDAO daond = new NguoiDungDAO();
      	  	KhachHangDAO daokh = new KhachHangDAO();
      	  	KhachHang khachHang = daokh.findByID(model.getSDT_KH());
      	  	NguoiDung nguoiDung = daond.findbyID(model.getNguoiTao());
      	  	if(khachHang == null || nguoiDung == null) {
      	  		return false;
      	  	}
            String sql = "insert into HoaDonBan (SDT_KH, TongTien, NgayLap_HD, NguoiTao) values(?,?,?,?)";
            SQLConnect.executeUpdate(sql, model.getSDT_KH(), model.getTongTien(), model.getNgayLapHD(), model.getNguoiTao());
            return true;
        } catch (Exception e) {
           return false;
        }
        }
    }
    public List<HoaDonBan> select_nam() {
        String sql = "select * from HoaDonBan ";
        List<HoaDonBan> list = select(sql);
        return list.size() > 0 ? list : null;
    }

    public List<HoaDonBan> find_ByDate(String date) {
    	if(date.equals("")) {
    		return null;
    	}
        String sql = "select * from HoaDonBan "
                + "where NgayLap_HD = ? ";
        List<HoaDonBan> list = select(sql, date);
        return list.size() > 0 ? list : null;
    }

    public HoaDonBan find_ByID(int id) {
        String sql = "select * from HoaDonBan "
                + " where ID_HDB = ? ";
        List<HoaDonBan> list = select(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<HoaDonBan> find_Bytow(int id, String date) {
        String sql = "select * from HoaDonBan "
                + " where ID_HDB = ? and NgayLap_HD = ? ";
        List<HoaDonBan> list = select(sql, id, date);
        return list.size() > 0 ? list : null;
    }

    public List<HoaDonBan> select1() {
        String sql = "select * from HoaDonBan "
                + "where not exists (select * from PhieuThuCT "
                + "where HoaDonBan.ID_HDB = PhieuThuCT.ID_HDB) ";
        List<HoaDonBan> list = select(sql);
        return list.size() > 0 ? list : null;
    }

    public List<HoaDonBan> select2() {
        String sql = "select * from HoaDonBan "
                + " where not exists "
                + " (select * from HoaDonTraHang "
                + " where  HoaDonBan.ID_HDB = HoaDonTraHang.ID_HDB) ";
        List<HoaDonBan> list = select(sql);
        return list.size() > 0 ? list : null;
    }

    public HoaDonBan select() {
        String sql = "select * from HoaDonBan "
                + "   order by ID_HDB desc";
        List<HoaDonBan> list = select(sql);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<HoaDonBan> select3(int thang, int nam) {
        String sql = "select sum(TongTien) from HoaDonBan "
                + " where MONTH(NgayLap_HD) = ? and YEAR(NgayLap_HD) = ?";
        List<HoaDonBan> list = select(sql, thang, nam);
        return list.size() > 0 ? list : null;
    }

    private List<HoaDonBan> select(String sql, Object... args) {
        List<HoaDonBan> list = new ArrayList<>();

        try {
            ResultSet rs = null;
            try {
                rs = SQLConnect.excuteQuery(sql, args);
                while (rs.next()) {
                    HoaDonBan model = readFromResult(rs);
                    list.add(model);
                }
            } finally {
                rs.close();
            }

        } catch (Exception e) {

        }
        return list;
    }

    private HoaDonBan readFromResult(ResultSet rs) throws SQLException {
        HoaDonBan model = new HoaDonBan();
        model.setID_HDB(rs.getInt(1));
        model.setSDT_KH(rs.getString(2));
        model.setTongTien(rs.getFloat(3));
        model.setNgayLapHD(rs.getDate(4));
        model.setNguoiTao(rs.getString(5));

        return model;
    }

}
