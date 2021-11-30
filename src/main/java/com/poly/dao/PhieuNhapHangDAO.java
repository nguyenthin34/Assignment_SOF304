/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.model.NguoiDung;
import com.poly.model.NhaCungCap;
import com.poly.model.PhieuNhapHang;
import java.util.Date;
import com.poly.Jdbc.SQLConnect;
import com.poly.model.Thuoc;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.poly.dao.NhaCungCapDAO;
public class PhieuNhapHangDAO {

    public void insert(PhieuNhapHang model) {
        try {
            String sql = "insert into HoaDonNhap(ID_NhaCC, TongTienHang, NgayLap_HD, NguoiTao) values (?,?,?,?)";
            SQLConnect.executeUpdate(sql, model.getID_NhaCC(), model.getTongTienHang(), model.getNgayLapHD(),
                    model.getNguoiTao());
            JOptionPane.showMessageDialog(null,"Tạo Hóa Đơn Thành Công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Tạo Hóa Đơn Thất Bại" + e);
        }
    }
    
    public boolean Insert(PhieuNhapHang model) throws Exception {
       if(model == null || model.getID_NhaCC() == null ||  model.getID_NhaCC().equals("") 
    		   || model.getNguoiTao() == null || model.getNguoiTao().equals("") || model.getTongTienHang() <= 0) {
    	   return false;
       }else {
    	   NhaCungCapDAO daoncc = new NhaCungCapDAO();
    	   NguoiDungDAO daond = new NguoiDungDAO();
    	   NhaCungCap nhaCungCap = daoncc.findByID(model.getID_NhaCC());
    	   NguoiDung nguoiDung = daond.findbyID(model.getNguoiTao());
    	   if(nhaCungCap == null || nguoiDung == null) {
    		   return false;
    	   }
    	   try {
               String sql = "insert into HoaDonNhap(ID_NhaCC, TongTienHang, NgayLap_HD, NguoiTao) values (?,?,?,?)";
               SQLConnect.executeUpdate(sql, model.getID_NhaCC(), model.getTongTienHang(), model.getNgayLapHD(),
                       model.getNguoiTao());
              return true;
           } catch (Exception e) {
               return false;
           }
       }
    }
    public List<PhieuNhapHang> select1(){
        String sql = "select * from HoaDonNhap " +
                    " where not exists " +
                        " ( select * from PCNhapHangCT " +
                           " where HoaDonNhap.ID_HDN = PCNhapHangCT.ID_HDN)";
        List<PhieuNhapHang> list = select(sql);
        return list.size() > 0 ? list : null;
    }
    
     public List<PhieuNhapHang> select_ds(){
        String sql = "select * from HoaDonNhap ";
        List<PhieuNhapHang> list = select(sql);
        return list.size() > 0 ? list : null;
    }
    public PhieuNhapHang select() {
        String sql = "select * from HoaDonNhap " +
                "   order by ID_HDN desc";
        List<PhieuNhapHang> list = select(sql);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public  List<PhieuNhapHang> find_ByDate(String date){
    	if(date.equals("")) {
    		return null;
    	}
        String sql = "select * from HoaDonNhap "
                + " where NgayLap_HD = ? ";
        List<PhieuNhapHang> list = select(sql, date);
        return list.size() > 0 ? list : null;
    }
    
    public  PhieuNhapHang find_ByID(int id){
        String sql = "select * from HoaDonNhap "
                + " where ID_HDN = ? ";
        List<PhieuNhapHang> list = select(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public  List<PhieuNhapHang> find_Bytow(int id, String date){
    	if(date.equals("") || id < 0) {
    		return null;
    	}
        String sql = "select * from HoaDonNhap "
                + " where ID_HDN = ? and NgayLap_HD = ? ";
        List<PhieuNhapHang> list = select(sql, id, date);
        return list.size() > 0 ? list : null;
    }
    private List<PhieuNhapHang> select(String sql, Object... args) {
        List<PhieuNhapHang> list = new ArrayList<>();

        try {
            ResultSet rs = null;
            try {
                rs = SQLConnect.excuteQuery(sql, args);
                while (rs.next()) {
                    PhieuNhapHang model = readFromResult(rs);
                    list.add(model);
                }
            } finally {
                rs.close();
            }

        } catch (Exception e) {

        }
        return list;
    }
    
    private PhieuNhapHang readFromResult(ResultSet rs) throws SQLException {
        PhieuNhapHang model = new PhieuNhapHang();
        model.setID_HDN(rs.getInt(1));
        model.setID_NhaCC(rs.getString(2));
        model.setTongTienHang(rs.getFloat(3));
        model.setNgayLapHD(rs.getDate(4));
        model.setNguoiTao(rs.getString(5));
        return model;
    }
    }
