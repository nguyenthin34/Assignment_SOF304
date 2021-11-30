/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.model.HoaDonBan;
import com.poly.model.HoaDonBanCT;
import com.poly.model.SanPhamThuoc;
import com.poly.Jdbc.SQLConnect;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class HoaDonBanCTDAO {

	public void insert(HoaDonBanCT model) {
		String sql = "insert into HDB_CT values(?,?,?,?)";
		SQLConnect.executeUpdate(sql, model.getID_HDB(), model.getMa_Thuoc(), model.getSoLuong(), model.getGiaBan());

	}

	public boolean Insert(HoaDonBanCT model) {
		if (model == null || model.getSoLuong() <= 0 || model.getGiaBan() <= 0) {
			return false;
		} else {
			HoaDonBanHangDAO daohdb = new HoaDonBanHangDAO();
			HoaDonBan hdb = daohdb.find_ByID(model.getID_HDB());
			SanPhamThuocDAO dapspt = new SanPhamThuocDAO();
			SanPhamThuoc spt = dapspt.findByID(model.getMa_Thuoc());
			if(spt == null || hdb == null) {
				return false;
			}
			try {
				String sql = "insert into HDB_CT values(?,?,?,?)";
				SQLConnect.executeUpdate(sql, model.getID_HDB(), model.getMa_Thuoc(), model.getSoLuong(),
						model.getGiaBan());
				return true;
			} catch (Exception e) {
				return false;
			}
		}

	}

	public List<HoaDonBanCT> select(int id) {
		String sql = "select * from HDB_CT " + " where ID_HDB = ?";
		List<HoaDonBanCT> list = select(sql, id);
		return list.size() > 0 ? list : null;
	}

	public List<HoaDonBanCT> findbyID(int idhdbct) throws Exception {
		String sql = "select * from HDB_CT " + " where ID_HDB = ?";
		List<HoaDonBanCT> list = select(sql, idhdbct);
		return list.size() > 0 ? list : null;
	}

	public List<HoaDonBanCT> select_tk(int thang, int nam) throws Exception {

		List<HoaDonBanCT> list = new ArrayList<>();
		String sql = "select top 10 Ma_Thuoc, sum(SoLuong) from HDB_CT join HoaDonBan on HoaDonBan.ID_HDB = HDB_CT.ID_HDB "
				+ " where MONTH(HoaDonBan.NgayLap_HD) = ? and YEAR(HoaDonBan.NgayLap_HD) = ? " + " group by Ma_Thuoc "
				+ " order by sum(soluong) desc ";
		PreparedStatement ppst = SQLConnect.getcon().prepareStatement(sql);
		ppst.setInt(1, thang);
		ppst.setInt(2, nam);
		ResultSet rs = ppst.executeQuery();
		while (rs.next()) {
			HoaDonBanCT model = new HoaDonBanCT();
			model.setMa_Thuoc(rs.getInt(1));
			model.setSoLuong(rs.getInt(2));
			list.add(model);
		}

		return list.size() > 0 ? list : null;
	}

	// tạo list nhân viên và add vào list
	private List<HoaDonBanCT> select(String sql, Object... args) {
		List<HoaDonBanCT> list = new ArrayList<>();
		try {
			ResultSet rs = null; // tạo đối tượng resultset
			try {
				rs = SQLConnect.excuteQuery(sql, args);
				while (rs.next()) {
					HoaDonBanCT model = readFromResultSet(rs); // chạy và trả về resultset
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

	private HoaDonBanCT readFromResultSet(ResultSet rs) throws SQLException {
		HoaDonBanCT model = new HoaDonBanCT();
		model.setID_HDB(rs.getInt(1));
		model.setMa_Thuoc(rs.getInt(2));
		model.setSoLuong(rs.getInt(3));
		model.setGiaBan(rs.getFloat(4));
		return model;
	}
}
