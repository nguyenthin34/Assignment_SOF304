/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.Jdbc.SQLConnect;
import com.poly.model.NguoiDung;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class NguoiDungDAO {

    public void insert(NguoiDung model) throws Exception {
        String sql = "insert into NguoiDung values(?,?,?,?,?,?)";
        SQLConnect.executeUpdate(sql,
                model.getMa_ND(), model.getTen_ND(),
                model.getSdt(), model.getEmail(), model.isVaiTro(), model.getMatKhau(), model.isTrangThai());
    }
    
    public boolean Insert(NguoiDung model)  {
    	if(model == null || model.getMa_ND() == null || model.getMa_ND().equals("")) {
    		return false;
    	}
        try {
        	NguoiDung nguoiDung = findbyID(model.getMa_ND());
        	if(nguoiDung != null) {
        		return false;
        	}
        	String sql = "insert into NguoiDung values(?,?,?,?,?,?,?)";
            SQLConnect.executeUpdate(sql,
                    model.getMa_ND(), model.getTen_ND(),
                    model.getSdt(), model.getEmail(), model.isVaiTro(), model.getMatKhau(), model.isTrangThai());
            return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }

    public void update(NguoiDung model) throws Exception {
        String sql = "Update NguoiDung set Ten_ND = ?, Sdt = ?, Email = ?, VaiTro = ?, MatKhau = ?, TrangThai = ? "
                + " where Ma_ND = ?";
        SQLConnect.executeUpdate(sql, model.getTen_ND(),
                model.getSdt(), model.getEmail(),
                model.isVaiTro() ? 1 : 0,
                model.getMatKhau(),
                model.isTrangThai() ? 1 : 0,
                model.getMa_ND());
    }
    
    public boolean Update(NguoiDung model) {
    	if(model == null || model.getMa_ND() == null) {
    		return false;
    	}
        try {
        	NguoiDung nguoiDung = findbyID(model.getMa_ND());
        	if(nguoiDung == null) {
        		return false;
        	}
        	String sql = "Update NguoiDung set Ten_ND = ?, Sdt = ?, Email = ?, VaiTro = ?, MatKhau = ?, TrangThai = ? "
                    + " where Ma_ND = ?";
            SQLConnect.executeUpdate(sql, model.getTen_ND(),
                    model.getSdt(), model.getEmail(),
                    model.isVaiTro() ? 1 : 0,
                    model.getMatKhau(),
                    model.isTrangThai() ? 1 : 0,
                    model.getMa_ND());
            return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
    public boolean Update_pass(NguoiDung model) {
    	if(model == null || model.getMa_ND() == null || model.getMatKhau() == null || model.getMatKhau().equals("")) {
    		return false;
    	}else {
    		try {
            	NguoiDung nguoiDung = findbyID(model.getMa_ND());
            	if(nguoiDung == null) {
            		return false;
            	}
                String sql = "update NguoiDung set MatKhau = ? "
                        + " where Ma_ND = ? ";
                SQLConnect.executeUpdate(sql, model.getMatKhau(), model.getMa_ND());
                return true;
            } catch (Exception e) {
            	e.printStackTrace();
            	return false;
            }
    	}
        
    }
    public void update_pass(NguoiDung model) {
    	
        try {
            String sql = "update NguoiDung set MatKhau = ? "
                    + " where Ma_ND = ? ";
            SQLConnect.executeUpdate(sql, model.getMatKhau(), model.getMa_ND());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi" + e);
        }
    }
    public NguoiDung forget_pass(NguoiDung model) {
    	if(model == null) {
    		return null;
    	}
        String sql = "select * from NguoiDung where Ma_ND = ? and Email_ND = ?";
        List<NguoiDung> list = select(sql, model.getMa_ND(), model.getEmail());
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<NguoiDung> select() {
        String sql = "select * from NguoiDung";
        return select(sql);
    }

    public NguoiDung findbyID(String mand) throws Exception {
    	if(mand.equals("") || mand == null) {
    		return null;
    	}
        String sql = "select * from NguoiDung "
                + " where Ma_ND = ?";
        List<NguoiDung> list = select(sql, mand);
        return list.size() > 0 ? list.get(0) : null;
    }

    // tạo list nhân viên và add vào list
    private List<NguoiDung> select(String sql, Object... args) {
        List<NguoiDung> list = new ArrayList<>();
        try {
            ResultSet rs = null; // tạo đối tượng resultset
            try {
                rs = SQLConnect.excuteQuery(sql, args);
                while (rs.next()) {
                    NguoiDung model = readFromResultSet(rs); // chạy và trả về resultset
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

    private NguoiDung readFromResultSet(ResultSet rs) throws SQLException {
        NguoiDung model = new NguoiDung(); // tạo đối tượng model của class nhanVien
        model.setMa_ND(rs.getString(1));
        model.setTen_ND(rs.getString(2));
        model.setSdt(rs.getString(3));
        model.setEmail(rs.getString(4));
        model.setVaiTro(rs.getBoolean(5));
        model.setMatKhau(rs.getString(6));
        model.setTrangThai(rs.getBoolean(7));
        return model;
    }
}
