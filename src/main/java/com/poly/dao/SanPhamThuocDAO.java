package com.poly.dao;

import com.poly.Jdbc.SQLConnect;
import com.poly.model.DonViTinh;
import com.poly.model.SanPhamThuoc;
import com.poly.model.Thuoc;

import java.awt.image.SampleModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class SanPhamThuocDAO {

	public void insert(SanPhamThuoc model) throws Exception {
		try {
			String sql = "insert into ThuocChiTiet(ID_Thuoc, ID_NhaCC, ID_Tu, HanSuDung, GiaNhap, GiaBan, SoLuongTon, TrangThai) "
					+ " values(?,?,?,?,?,?,?,?)";
			SQLConnect.executeUpdate(sql, model.getID_Thuoc(), model.getID_NhaCC(), model.getID_Tu(),
					model.getHanSuDung(), model.getGiaNhap(), model.getGiaBan(), model.getSoLuongTon(),
					model.isTrangThai());
			JOptionPane.showMessageDialog(null, "Thêm Thành Công");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Thêm Thất Bại");
		}
	}

	public boolean Insert(SanPhamThuoc model) {
		if (model == null || model.getID_Tu() == null || model.getID_NhaCC() == null || model.getID_Thuoc() <= 0) {
			return false;
		} else {
			List<SanPhamThuoc> thuoc = findByIDThuoc(model.getID_Thuoc());
			if (thuoc == null) {
				return false;
			}
			try {
				String sql = "insert into ThuocChiTiet(ID_Thuoc, ID_NhaCC, ID_Tu, HanSuDung, GiaNhap, GiaBan, SoLuongTon, TrangThai) "
						+ " values(?,?,?,?,?,?,?,?)";
				SQLConnect.executeUpdate(sql, model.getID_Thuoc(), model.getID_NhaCC(), model.getID_Tu(),
						model.getHanSuDung(), model.getGiaNhap(), model.getGiaBan(), model.getSoLuongTon(),
						model.isTrangThai());
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	public void insert2(SanPhamThuoc model) throws Exception {

		String sql = "insert into ThuocChiTiet(ID_Thuoc, ID_NhaCC, ID_Tu, HanSuDung, GiaNhap, GiaBan, SoLuongTon, TrangThai) "
				+ " values(?,?,?,?,?,?,?,?)";
		SQLConnect.executeUpdate(sql, model.getID_Thuoc(), model.getID_NhaCC(), model.getID_Tu(), model.getHanSuDung(),
				model.getGiaNhap(), model.getGiaBan(), model.getSoLuongTon(), model.isTrangThai());

	}

	public void update(SanPhamThuoc model) throws Exception {
		try {
			String sql = "update ThuocChiTiet set ID_Thuoc = ? , ID_NhaCC = ?, ID_Tu = ?, HanSuDung = ?, GiaNhap = ?, GiaBan = ?, SoLuongTon = ?, TrangThai = ?"
					+ " where Ma_Thuoc = ?";
			SQLConnect.executeUpdate(sql, model.getID_Thuoc(), model.getID_NhaCC(), model.getID_Tu(),
					model.getHanSuDung(), model.getGiaNhap(), model.getGiaBan(), model.getSoLuongTon(),
					model.isTrangThai() ? 1 : 0, model.getMa_Thuoc());
			JOptionPane.showMessageDialog(null, "Cập Nhật Thành Công");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Cập Nhật Không Thành Công " + e);
		}
	}

	public boolean Update(SanPhamThuoc model) throws Exception {
		if (model == null || model.getID_Tu() == null || model.getID_NhaCC() == null || model.getID_Thuoc() <= 0 || model.getMa_Thuoc()<= 0) {
			return false;
		} else {
			SanPhamThuoc spt = findByID(model.getMa_Thuoc());
			List<SanPhamThuoc> thuoc = findByIDThuoc(model.getID_Thuoc());
			if (thuoc == null || spt == null) {
				return false;
			}
			try {
				String sql = "update ThuocChiTiet set ID_Thuoc = ? , ID_NhaCC = ?, ID_Tu = ?, HanSuDung = ?, GiaNhap = ?, GiaBan = ?, SoLuongTon = ?, TrangThai = ?"
						+ " where Ma_Thuoc = ?";
				SQLConnect.executeUpdate(sql, model.getID_Thuoc(), model.getID_NhaCC(), model.getID_Tu(),
						model.getHanSuDung(), model.getGiaNhap(), model.getGiaBan(), model.getSoLuongTon(),
						model.isTrangThai() ? 1 : 0, model.getMa_Thuoc());
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	public void update_hsd(Date hsd) {
		try {
			String sql = "update ThuocChiTiet set TrangThai = 0" + " where HanSuDung = ?";
			SQLConnect.executeUpdate(sql, hsd);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public void update_tt(int mathuoc) {
		try {
			String sql = "update ThuocChiTiet set TrangThai = 0" + " where Ma_Thuoc = ? ";
			SQLConnect.executeUpdate(sql, mathuoc);
			JOptionPane.showMessageDialog(null, "Update Trạng Thái Thành Công");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public void update(int soluong, int id_thuoc, String id_ncc, Date hsd, float gianhap) {
		try {
			String sql = "update ThuocChiTiet set SoLuongTon = ? ,  ID_Tu = 'T1' , TrangThai = 1"
					+ " where ID_Thuoc = ? and ID_NhaCC = ? and HanSuDung = ? and GiaNhap = ?";
			SQLConnect.executeUpdate(sql, soluong, id_thuoc, id_ncc, hsd, gianhap);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e + "Lỗi Update");
		}
	}

	public void update_sl(int soluong, int mathuoc) {
		try {
			String sql = "update ThuocChiTiet set SoLuongTon = ? " + " where Ma_Thuoc = ? ";
			SQLConnect.executeUpdate(sql, soluong, mathuoc);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e + "Lỗi Update");
		}
	}

	public List<SanPhamThuoc> select() {
		String sql = "select * from ThuocChiTiet";
		return select(sql);
	}

	public SanPhamThuoc checksl(int soluong, int mathuoc) {
		String sql = "select * from ThuocChiTiet " + " where SoLuongTon < ? and Ma_Thuoc = ?";
		List<SanPhamThuoc> list = select(sql, soluong, mathuoc);
		return list.size() > 0 ? list.get(0) : null;

	}

	public SanPhamThuoc findByID(int Ma_Thuoc) {
		String sql = "select * from ThuocChiTiet where Ma_Thuoc = ?";
		List<SanPhamThuoc> list = select(sql, Ma_Thuoc);
		return list.size() > 0 ? list.get(0) : null;
	}

	public SanPhamThuoc findByIDdk(int Ma_Thuoc) {
		String sql = "select * from ThuocChiTiet where Ma_Thuoc = ?" + " and TrangThai = 1";
		List<SanPhamThuoc> list = select(sql, Ma_Thuoc);
		return list.size() > 0 ? list.get(0) : null;
	}

	public SanPhamThuoc findByIDNhacc(String nhacc) {
		String sql = "select * from ThuocChiTiet where ID_NhaCC = ?";
		List<SanPhamThuoc> list = select(sql, nhacc);
		return list.size() > 0 ? list.get(0) : null;
	}

	public List<SanPhamThuoc> findByIDThuoc(int id_thuoc) {
		String sql = "select * from ThuocChiTiet where ID_Thuoc = ?";
		List<SanPhamThuoc> list = select(sql, id_thuoc);
		return list.size() > 0 ? list : null;
	}

	public List<SanPhamThuoc> findByconhang() {
		String sql = "select * from ThuocChiTiet where SoLuongTon > 0";
		List<SanPhamThuoc> list = select(sql);
		return list.size() > 0 ? list : null;
	}

	public List<SanPhamThuoc> findBySlT(int soluong) {
		String sql = "select * from ThuocChiTiet where SoLuongTon >= ?";
		List<SanPhamThuoc> list = select(sql, soluong);
		return list.size() > 0 ? list : null;
	}

	public List<SanPhamThuoc> findbySLH(int soluong) {
		String sql = "select * from ThuocChiTiet where SoLuongTon < ?";
		List<SanPhamThuoc> list = select(sql, soluong);
		return list.size() > 0 ? list : null;
	}

	public SanPhamThuoc findbySLHT(int soluong, int mathuoc) {
		String sql = "select * from ThuocChiTiet " + " where SoLuongTon < ? and Ma_Thuoc = ?";
		List<SanPhamThuoc> list = select(sql, soluong, mathuoc);
		return list.size() > 0 ? list.get(0) : null;
	}

	public List<SanPhamThuoc> findByTT() {
		String sql = "select * from ThuocChiTiet where TrangThai = 1";
		List<SanPhamThuoc> list = select(sql);
		return list.size() > 0 ? list : null;
	}

	public List<SanPhamThuoc> findByHH() {
		String sql = "select * from ThuocChiTiet where TrangThai = 0";
		List<SanPhamThuoc> list = select(sql);
		return list.size() > 0 ? list : null;
	}

	public List<SanPhamThuoc> checksn(int songay) {
		String sql = "SELECT  *  FROM ThuocChiTiet "
				+ "	 where DATEDIFF(day, getDate(), HanSuDung) <= ? and TrangThai = 1";
		List<SanPhamThuoc> list = select(sql, songay);
		return list.size() > 0 ? list : null;
	}

	public List<SanPhamThuoc> checksn7() {
		String sql = "SELECT  *  FROM ThuocChiTiet "
				+ "	 where DATEDIFF(day, getDate(), HanSuDung) <= 7 and TrangThai = 1";
		List<SanPhamThuoc> list = select(sql);
		return list.size() > 0 ? list : null;
	}

	public SanPhamThuoc findBydk(int ID_thuoc, String id_ncc, Date hsd, float gianhap) {
		String sql1 = "select * from ThuocChiTiet where "
				+ " ID_Thuoc = ? and ID_NhaCC = ? and HanSuDung = ? and GiaNhap = ? and TrangThai = 1";
		String sql = "select * from ThuocChiTiet "
				+ " where ID_Thuoc = ? and ID_NhaCC = ? and HanSuDung = ? and GiaNhap = ?   and TrangThai = 1";
		List<SanPhamThuoc> list = select(sql, ID_thuoc, id_ncc, hsd, gianhap);
		return list.size() > 0 ? list.get(0) : null;
	}

	private List<SanPhamThuoc> select(String sql, Object... args) {
		List<SanPhamThuoc> list = new ArrayList<>();

		try {
			ResultSet rs = null;
			try {
				rs = SQLConnect.excuteQuery(sql, args);
				while (rs.next()) {
					SanPhamThuoc model = readFromResult(rs);
					list.add(model);
				}
			} finally {
				rs.close();
			}

		} catch (Exception e) {

		}
		return list;
	}

	private SanPhamThuoc readFromResult(ResultSet rs) throws SQLException {
		SanPhamThuoc model = new SanPhamThuoc();
		model.setMa_Thuoc(rs.getInt(1));
		model.setID_Thuoc(rs.getInt(2));
		model.setID_NhaCC(rs.getString(3));
		model.setID_Tu(rs.getString(4));
		model.setHanSuDung(rs.getDate(5));
		model.setGiaNhap(rs.getFloat(6));
		model.setGiaBan(rs.getFloat(7));
		model.setSoLuongTon(rs.getInt(8));
		model.setTrangThai(rs.getBoolean(9));
		return model;
	}
}
