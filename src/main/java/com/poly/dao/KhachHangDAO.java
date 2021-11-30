/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.model.KhachHang;
import com.poly.Jdbc.SQLConnect;
import com.poly.model.DonViTinh;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {

	public void insert(KhachHang model) throws Exception {
		String sql = "insert into KhachHang values(?,?,?,?)";
		SQLConnect.executeUpdate(sql, model.getSDT_KH(), model.getTen_KH(), model.getEmail_KH(), model.getDiaChi());
	}

	public boolean Insert(KhachHang model) {
		if (model == null || model.getSDT_KH() == null || model.getSDT_KH().equals("")) {
			return false;
		} else {
			KhachHang khachHang = findByID(model.getSDT_KH());
			if (khachHang != null) {
				return false;
			}
			try {
				String sql = "insert into KhachHang values(?,?,?,?)";
				SQLConnect.executeUpdate(sql, model.getSDT_KH(), model.getTen_KH(), model.getEmail_KH(),
						model.getDiaChi());
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

	}

	public void update(KhachHang model) throws Exception {
		String sql = "update KhachHang set Ten_KH = ?, Email_Kh = ?, DiaChi = ?" + " where SDT_KH = ?";
		SQLConnect.executeUpdate(sql, model.getTen_KH(), model.getEmail_KH(), model.getDiaChi(), model.getSDT_KH());
	}
	
	public boolean Update(KhachHang model) {
		if (model == null || model.getSDT_KH() == null || model.getSDT_KH().equals("")) {
			return false;
		} else {
			KhachHang khachHang = findByID(model.getSDT_KH());
			if (khachHang == null) {
				return false;
			}
			try {
				String sql = "update KhachHang set Ten_KH = ?, Email_Kh = ?, DiaChi = ?" + " where SDT_KH = ?";
				SQLConnect.executeUpdate(sql, model.getTen_KH(), model.getEmail_KH(), model.getDiaChi(), model.getSDT_KH());
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
				// TODO: handle exception
			}
		}
		
	}
	public void update_diem(String sdt, float diem) throws Exception {
		String sql = "update KhachHang set SoLanMua = ?" + " where SDT_KH = ?";
		SQLConnect.executeUpdate(sql, sdt, diem);
	}

	public List<KhachHang> select() throws Exception {
		String sql = "select * from KhachHang";
		return select(sql);
	}

	public KhachHang findByID(String SDT_KH) {
		if(SDT_KH == null || SDT_KH.equals("")) {
			return null;
		}
		try {
			String sql = "select * from KhachHang where SDT_KH = ?";
			List<KhachHang> list = select(sql, SDT_KH);
			return list.size() > 0 ? list.get(0) : null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public KhachHang findBydiem(String sdt, float diem) {
		String sql = "select * from KhachHang " + " where SDT_KH = ? and SoLanMua > ?";
		List<KhachHang> list = select(sql, sdt, diem);
		return list.size() > 0 ? list.get(0) : null;
	}

	private List<KhachHang> select(String sql, Object... args) {
		List<KhachHang> list = new ArrayList<>();

		try {
			ResultSet rs = null;
			try {
				rs = SQLConnect.excuteQuery(sql, args);
				while (rs.next()) {
					KhachHang model = readFromResult(rs);
					list.add(model);
				}
			} finally {
				rs.close();
			}

		} catch (Exception e) {

		}
		return list;
	}

	private KhachHang readFromResult(ResultSet rs) throws SQLException {
		KhachHang model = new KhachHang();
		model.setSDT_KH(rs.getString(1));
		model.setTen_KH(rs.getString(2));
		model.setEmail_KH(rs.getString(3));
		model.setDiaChi(rs.getString(4));
		return model;
	}
}
