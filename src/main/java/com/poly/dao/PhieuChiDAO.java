/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.model.NguoiDung;
import com.poly.model.PhieuChi;
import com.poly.Jdbc.SQLConnect;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.Date;

public class PhieuChiDAO {

	public void insert(PhieuChi model) {
		try {
			String sql = "insert into PhieuChi (NgayLapPC, NguoiLap,TienNhapThuoc, TienTraHang, TongTienChi) values(?,?,?,?,?)";
			SQLConnect.executeUpdate(sql, model.getNgayLapPC(), model.getNguoiThu(), model.getTienNhapthuoc(),
					model.getTienTraHang(), model.getTongTienChi());
			JOptionPane.showMessageDialog(null, "Tạo Phiếu Chi Thành Công");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Tạo Phiếu Chi Thất Bại");
		}
	}

	public boolean Insert(PhieuChi model) throws Exception {
		if (model == null || model.getTienNhapthuoc() <= 0 || model.getTienTraHang() <= 0
				|| model.getNguoiThu() == null || model.getNguoiThu().equals("")) {
			return false;
		} else {
			NguoiDungDAO daond = new NguoiDungDAO();
			NguoiDung nguoiDung = daond.findbyID(model.getNguoiThu());
			if (nguoiDung == null) {
				return false;
			}
			try {
				String sql = "insert into PhieuChi (NgayLapPC, NguoiLap,TienNhapThuoc, TienTraHang, TongTienChi) values(?,?,?,?,?)";
				SQLConnect.executeUpdate(sql, model.getNgayLapPC(), model.getNguoiThu(), model.getTienNhapthuoc(),
						model.getTienTraHang(), model.getTongTienChi());
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}

	public List<PhieuChi> select() {
		String sql = "select * from PhieuChi ";
		List<PhieuChi> list = select(sql);
		return list.size() > 0 ? list : null;
	}

	public List<PhieuChi> find_ByDate(Date date) {
		if (date == null) {
			return null;
		}
		String sql = "select * from PhieuChi " + " where NgayLapPC = ?";
		List<PhieuChi> list = select(sql, date);
		return list.size() > 0 ? list : null;
	}

	public PhieuChi select_dt(int thang, int nam) throws Exception {
		PhieuChi model = new PhieuChi();
		String sql = "select sum(TienNhapThuoc) , sum(TienTraHang) , sum(TongTienChi) from PhieuChi "
				+ " where MONTH(NgayLapPC) = ? and YEAR(NgayLapPC) = ? ";
		PreparedStatement ppst = SQLConnect.getcon().prepareStatement(sql);
		ppst.setInt(1, thang);
		ppst.setInt(2, nam);
		ResultSet rs = ppst.executeQuery();
		while (rs.next()) {
			model.setTienNhapthuoc(rs.getFloat(1));
			model.setTienTraHang(rs.getFloat(2));
			model.setTongTienChi(rs.getFloat(3));
		}
		return model;
	}

	public PhieuChi select1() {
		String sql = "select * from PhieuChi " + " order by Ma_PC desc";
		List<PhieuChi> list = select(sql);
		return list.size() > 0 ? list.get(0) : null;
	}

	private List<PhieuChi> select(String sql, Object... args) {
		List<PhieuChi> list = new ArrayList<>();

		try {
			ResultSet rs = null;
			try {
				rs = SQLConnect.excuteQuery(sql, args);
				while (rs.next()) {
					PhieuChi model = readFromResult(rs);
					list.add(model);
				}
			} finally {
				rs.close();
			}

		} catch (Exception e) {

		}
		return list;
	}

	private PhieuChi readFromResult(ResultSet rs) throws SQLException {
		PhieuChi model = new PhieuChi();
		model.setMa_PC(rs.getInt(1));
		model.setNgayLapPC(rs.getDate(2));
		model.setNguoiThu(rs.getString(3));
		model.setTienNhapthuoc(rs.getFloat(4));
		model.setTienTraHang(rs.getFloat(5));
		model.setTongTienChi(rs.getFloat(6));
		return model;
	}
}
