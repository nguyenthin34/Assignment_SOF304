/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.model.HoaDonBan;
import com.poly.model.HoaDonTraHang;
import com.poly.model.KhachHang;
import com.poly.model.NguoiDung;
import com.poly.Jdbc.SQLConnect;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.Date;

public class HoaDonTraHangDAO {

    public void insert(HoaDonTraHang model) {
        try {
            String sql = "insert into HoaDonTraHang (ID_HDB, TienCanTra, NgayLap_HD, NguoiTao, MoTa) values"
                    + " (?,?,?,?,?)";
            SQLConnect.executeUpdate(sql, model.getID_HDB(), model.getTienCanTra(), model.getNgayTao(), model.getNguoiTao(), model.getMoTa());
            JOptionPane.showMessageDialog(null, "Trả Hàng Thành Công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Trả Hàng Thất Bại" + e);
        }
    }
    
    public boolean Insert(HoaDonTraHang model) throws Exception {
        if(model == null || model.getID_HDB() <= 0 || model.getTienCanTra() <= 0 
        		||  model.getNguoiTao() == null || model.getNguoiTao().equals("")) {
        	return false;	
        }else {
        	HoaDonBanHangDAO daobh = new HoaDonBanHangDAO();
        	HoaDonBan hdb = daobh.find_ByID(model.getID_HDB());
        	NguoiDungDAO daond = new NguoiDungDAO();
      	  	NguoiDung nguoiDung = daond.findbyID(model.getNguoiTao());
      	  if(hdb == null || nguoiDung == null) {
    	  		return false;
    	  	}
        	try {
                String sql = "insert into HoaDonTraHang (ID_HDB, TienCanTra, NgayLap_HD, NguoiTao, MoTa) values"
                        + " (?,?,?,?,?)";
                SQLConnect.executeUpdate(sql, model.getID_HDB(), model.getTienCanTra(), model.getNgayTao(), model.getNguoiTao(), model.getMoTa());
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
    public List<HoaDonTraHang> select() {
        String sql = "select * from HoaDonTraHang "
                + "where not exists "
                + " (select * from PCTraThuocCT "
                + "  where  HoaDonTraHang.ID_HDTH = PCTraThuocCT.ID_HDTH) ";
        List<HoaDonTraHang> list = select(sql);
        return list.size() > 0 ? list : null;
    }

    public List<HoaDonTraHang> selectall() {
        String sql = "select * from HoaDonTraHang ";
        List<HoaDonTraHang> list = select(sql);
        return list.size() > 0 ? list : null;
    }

    public List<HoaDonTraHang> find_ByDate(String date) {
        String sql = "select * from HoaDonTraHang "
                + " where NgayLap_HD = ? ";
        List<HoaDonTraHang> list = select(sql, date);
        return list.size() > 0 ? list : null;
    }

    public HoaDonTraHang find_ByID(int id) {
        String sql = "select * from HoaDonTraHang "
                + " where ID_HDTH = ? ";
        List<HoaDonTraHang> list = select(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public List<HoaDonTraHang> find_Bytow(int id, String date){
         String sql = "select * from HoaDonTraHang "
                + " where ID_HDTH = ? and NgayLap_HD = ? ";
        List<HoaDonTraHang> list = select(sql, id, date);
        return list.size() > 0 ? list : null;
    }
    public List<HoaDonTraHang> select_dt(int thang, int nam) {
        String sql = "select * from PhieuThu "
                + " where MONTH(NgayLap_HD) = ? and YEAR(NgayLap_HD) = ? ";
        List<HoaDonTraHang> list = select(sql, thang, nam);
        return list.size() > 0 ? list : null;
    }

    private List<HoaDonTraHang> select(String sql, Object... args) {
        List<HoaDonTraHang> list = new ArrayList<>();

        try {
            ResultSet rs = null;
            try {
                rs = SQLConnect.excuteQuery(sql, args);
                while (rs.next()) {
                    HoaDonTraHang model = readFromResult(rs);
                    list.add(model);
                }
            } finally {
                rs.close();
            }

        } catch (Exception e) {

        }
        return list;
    }

    private HoaDonTraHang readFromResult(ResultSet rs) throws SQLException {
        HoaDonTraHang model = new HoaDonTraHang();
        model.setID_HDTH(rs.getInt(1));
        model.setID_HDB(rs.getInt(2));
        model.setTienCanTra(rs.getFloat(3));
        model.setNgayTao(rs.getDate(4));
        model.setNguoiTao(rs.getString(5));
        model.setMoTa(rs.getString(6));
        return model;
    }
}
