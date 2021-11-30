/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.model.NhomThuoc;
import java.sql.*;
import com.poly.Jdbc.SQLConnect;
import com.poly.model.DonViTinh;
import java.util.ArrayList;
import java.util.List;

public class NhomThuocDAO {

    public void insert(NhomThuoc model) throws Exception {
        String sql = "insert into NhomThuoc values(?,?,?)";
        SQLConnect.executeUpdate(sql, model.getID_Nhom(), model.getTen_Nhom(), model.isTrangThai());
    }
    
    public boolean Insert(NhomThuoc model) {
      if(model == null || model.getID_Nhom() == null) {
    	  return false;
      }else {
    	  try {
    		  String sql = "insert into NhomThuoc values(?,?,?)";
              SQLConnect.executeUpdate(sql, model.getID_Nhom(), model.getTen_Nhom(), model.isTrangThai());
              return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
      }
      return false;
    }
    
    public void update(NhomThuoc model) throws Exception {
        String sql = "update NhomThuoc set TenNhom = ?, TrangThai = ?"
                + " where ID_NhomThuoc = ?";
        
        SQLConnect.executeUpdate(sql, model.getTen_Nhom(),
                model.isTrangThai() ? 1 : 0,
                model.getID_Nhom());
    }
    
    public boolean Update(NhomThuoc model) {
       if(model == null || model.getID_Nhom() == null) {
    	   return false;
       }else {
    	   try {
    		   String sql = "update NhomThuoc set TenNhom = ?, TrangThai = ?"
                       + " where ID_NhomThuoc = ?";
               
               SQLConnect.executeUpdate(sql, model.getTen_Nhom(),
                       model.isTrangThai() ? 1 : 0,
                       model.getID_Nhom());
               return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
       }
       return false;
    }
    public List<NhomThuoc> select()  {
        String sql = "select * from NhomThuoc";
        return select(sql);
    }
    public NhomThuoc findByID(String ID){
        String sql = "select * from NhomThuoc where ID_NhomThuoc = ? ";
        List<NhomThuoc> list = select(sql, ID);
        return list.size() > 0 ? list.get(0) : null;
    }
   
    private List<NhomThuoc> select(String sql, Object... args){
        List<NhomThuoc> list = new ArrayList<>();
        
        try {
            ResultSet rs = null;
            try {
                rs = SQLConnect.excuteQuery(sql, args);
                while (rs.next()) {
                    NhomThuoc model = readFromResult(rs);
                    list.add(model);
                }
            } finally {
                rs.close();
            }
            
        } catch (Exception e) {
            
        }
        return list;
    }
     public List<NhomThuoc>findByTT(){
        String sql = "select * from NhomThuoc where TrangThai = 1 ";
        
        List<NhomThuoc> list = select(sql);
        return list.size() > 0 ? list : null;
    }
      public List<NhomThuoc>findByTT2(){
        String sql = "select * from NhomThuoc where TrangThai = 0 ";
        
        List<NhomThuoc> list = select(sql);
        return list.size() > 0 ? list : null;
    }
    private NhomThuoc readFromResult(ResultSet rs) throws SQLException {
        NhomThuoc model = new NhomThuoc();
        model.setID_Nhom(rs.getString(1));
        model.setTen_Nhom(rs.getString(2));
        model.setTrangThai(rs.getBoolean(3));
        return model;
    }
}
