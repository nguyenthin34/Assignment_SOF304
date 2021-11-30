/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import java.sql.*;
import com.poly.model.DonViTinh;
import com.poly.Jdbc.SQLConnect;
import com.poly.model.NhomThuoc;
import java.util.ArrayList;
import java.util.List;

public class DonViTinhDAO {

    public void insert(DonViTinh model) throws Exception {
        String sql = "insert into DonViTinh values(?,?,?)";
        SQLConnect.executeUpdate(sql, model.getID_DonViTinh(), model.getTen_DVT(), model.isTrangThai());
    }
    
    public boolean Insert(DonViTinh model) throws Exception {
       if(model == null || model.getID_DonViTinh() == null) {
    	   return false;
       }else {
    	   try {
           	String sql = "insert into DonViTinh values(?,?,?)";
               SQLConnect.executeUpdate(sql, model.getID_DonViTinh(), model.getTen_DVT(), model.isTrangThai()); 
               return true;
   		} catch (Exception e) {
   			// TODO: handle exception
   		}
       }
        return false;
    }
    
    public void update(DonViTinh model) throws Exception {
        String sql = "update  DonViTinh set Ten_DVT = ?, TrangThai = ?"
                + " where ID_DonViTinh = ?";
        SQLConnect.executeUpdate(sql, model.getTen_DVT(), 
                                        model.isTrangThai()? 1 : 0, 
                                        model.getID_DonViTinh());
    }
    
    public boolean Update(DonViTinh model) throws Exception {
       if(model == null || model.getID_DonViTinh() == null) {
    	   return false;
       }else {
    	   try {
           	String sql = "update  DonViTinh set Ten_DVT = ?, TrangThai = ?"
                       + " where ID_DonViTinh = ?";
               SQLConnect.executeUpdate(sql, model.getTen_DVT(), 
                                               model.isTrangThai()? 1 : 0, 
                                               model.getID_DonViTinh());
               return true;
   		} catch (Exception e) {
   			// TODO: handle exception
   		}
           return false;
       }
    }
    
    public List<DonViTinh> select() throws Exception {
        String sql = "select * from DonViTinh";
        return select(sql);
    }
    
    public DonViTinh findByID(String ID_DVT) {
        String sql = "select * from DonViTinh where ID_DonViTinh = ?";
        List<DonViTinh> list = select(sql, ID_DVT);
        return list.size() > 0 ? list.get(0) : null;
    }
    public List<DonViTinh>findByTT(){
        String sql = "select * from DonviTinh where TrangThai = 1 ";
        
        List<DonViTinh> list = select(sql);
        return list.size() > 0 ? list : null;
    }
     public List<DonViTinh>findByTT2(){
        String sql = "select * from DonviTinh where TrangThai = 0 ";
        
        List<DonViTinh> list = select(sql);
        return list.size() > 0 ? list : null;
    }
    private List<DonViTinh> select(String sql, Object... args) {
        List<DonViTinh> list = new ArrayList<>();
        
        try {
            ResultSet rs = null;
            try {
                rs = SQLConnect.excuteQuery(sql, args);
                while (rs.next()) {
                    DonViTinh model = readFromResult(rs);
                    list.add(model);
                }
            } finally {
                rs.close();
            }
            
        } catch (Exception e) {
            
        }
        return list;
    }
    
    private DonViTinh readFromResult(ResultSet rs) throws SQLException {
        DonViTinh model = new DonViTinh();
        model.setID_DonViTinh(rs.getString(1));
        model.setTen_DVT(rs.getString(2));
        model.setTrangThai(rs.getBoolean(3));
        return model;
    }
}
