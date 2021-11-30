/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.model.Thuoc;
import com.sun.mail.imap.protocol.ID;
import com.poly.Jdbc.SQLConnect;
import com.poly.model.DonViTinh;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ThuocDAO {

    public void insert(Thuoc model) {
        try {
            String sql = "insert into Thuoc(Ten_Thuoc, ID_NhomThuoc, ID_DVT, Cach_Dung, HoatChat, HamLuong, DongGoi, NoiSanXuat, TrangThai) "
                    + "values(?,?,?,?,?,?,?,?,?)";
            SQLConnect.executeUpdate(sql,
                    model.getTen_Thuoc(), model.getID_NhomThuoc(), model.getID_DVT(), model.getCach_Dung(),
                    model.getHoatChat(), model.getHamLuong(), model.getDongGoi(), model.getNoiSanXuat(),
                    model.isTrangThai());
            JOptionPane.showMessageDialog(null, "Thêm Thành Công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
        
        public boolean Insert(Thuoc model) {
            if(model == null || model.getID_DVT() == null || model.getID_NhomThuoc() == null) {
            	return false;
            }else {
            	try {
                    String sql = "insert into Thuoc(Ten_Thuoc, ID_NhomThuoc, ID_DVT, Cach_Dung, HoatChat, HamLuong, DongGoi, NoiSanXuat, TrangThai) "
                            + "values(?,?,?,?,?,?,?,?,?)";
                    SQLConnect.executeUpdate(sql,
                            model.getTen_Thuoc(), model.getID_NhomThuoc(), model.getID_DVT(), model.getCach_Dung(),
                            model.getHoatChat(), model.getHamLuong(), model.getDongGoi(), model.getNoiSanXuat(),
                            model.isTrangThai());
                   return true;
                } catch (Exception e) {
                   e.printStackTrace();
                }
            	return false;
            }
    }

    public void update(Thuoc model)  {
        try {
            String sql = "update Thuoc set Ten_Thuoc = ?,ID_NhomThuoc = ?, ID_DVT = ?, Cach_Dung = ?, "
                + " HoatChat = ? , HamLuong = ?, DongGoi = ?, NoiSanXuat = ?, TrangThai = ?"
                + " where ID_Thuoc = ?";
        SQLConnect.executeUpdate(sql, model.getTen_Thuoc(), model.getID_NhomThuoc(), model.getID_DVT(), model.getCach_Dung(),
                model.getHoatChat(), model.getHamLuong(), model.getDongGoi(), model.getNoiSanXuat(),
                model.isTrangThai() ? 1 : 0,
                model.getID_Thuoc());
            JOptionPane.showMessageDialog(null,"Cập Nhật Thành Công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
    public boolean Update(Thuoc model)  {
    	
        if(model == null || model.getID_DVT() == null || model.getID_NhomThuoc() == null) {
        	return false;
        }else {
        	Thuoc thuoc = findByID(model.getID_Thuoc());
        	if(thuoc == null) {
        		return false;
        	}
        	try {
                String sql = "update Thuoc set Ten_Thuoc = ?,ID_NhomThuoc = ?, ID_DVT = ?, Cach_Dung = ?, "
                    + " HoatChat = ? , HamLuong = ?, DongGoi = ?, NoiSanXuat = ?, TrangThai = ?"
                    + " where ID_Thuoc = ?";
            SQLConnect.executeUpdate(sql, model.getTen_Thuoc(), model.getID_NhomThuoc(), model.getID_DVT(), model.getCach_Dung(),
                    model.getHoatChat(), model.getHamLuong(), model.getDongGoi(), model.getNoiSanXuat(),
                    model.isTrangThai() ? 1 : 0,
                    model.getID_Thuoc());
               return true;
            } catch (Exception e) {
            	e.printStackTrace();
                return false;
            }
        }

    }
    public List<Thuoc> select() {
        String sql = "select * from Thuoc";
        return select(sql);
    }

    public Thuoc findByID(int ID_Thuoc) {
        try {
        	String sql = "select * from Thuoc where ID_Thuoc = ?";
            List<Thuoc> list = select(sql, ID_Thuoc);
            return list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

    public List<Thuoc> findByIDNhomThuoc(String ID_NhomThuoc) {
        	if(ID_NhomThuoc.equals("")) {
        		return null;
        	}else {
        		try {
        			String sql = "select * from Thuoc where ID_NhomThuoc = ?";
                    List<Thuoc> list = select(sql, ID_NhomThuoc);
                    return list;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
        	}
        	
        	
    }

    private List<Thuoc> select(String sql, Object... args) {
        List<Thuoc> list = new ArrayList<>();

        try {
            ResultSet rs = null;
            try {
                rs = SQLConnect.excuteQuery(sql, args);
                while (rs.next()) {
                    Thuoc model = readFromResult(rs);
                    list.add(model);
                }
            } finally {
                rs.close();
            }

        } catch (Exception e) {

        }
        return list;
    }

    private Thuoc readFromResult(ResultSet rs) throws SQLException {
        Thuoc model = new Thuoc();
        model.setID_Thuoc(rs.getInt(1));
        model.setTen_Thuoc(rs.getString(2));
        model.setID_NhomThuoc(rs.getString(3));
        model.setID_DVT(rs.getString(4));
        model.setCach_Dung(rs.getString(5));
        model.setHoatChat(rs.getString(6));
        model.setHamLuong(rs.getString(7));
        model.setDongGoi(rs.getString(8));
        model.setNoiSanXuat(rs.getString(9));
        model.setTrangThai(rs.getBoolean(10));
        return model;
    }
}
