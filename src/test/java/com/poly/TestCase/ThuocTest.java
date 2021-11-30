package com.poly.TestCase;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.poly.Jdbc.SQLConnect;
import com.poly.dao.SanPhamThuocDAO;
import com.poly.dao.ThuocDAO;
import com.poly.model.Thuoc;

public class ThuocTest {
	Connection connection;
	ThuocDAO dao = new ThuocDAO();
	Map<String, Object> testNgResult;
	HSSFWorkbook workbook;
	HSSFSheet sheet6;

	@BeforeTest
	public void connectDatabase() {
		try {
			connection = SQLConnect.getcon();
			workbook = new HSSFWorkbook();
			sheet6 = workbook.createSheet("Đơn Vị Tính");
			testNgResult = new LinkedHashMap<String, Object>();
			testNgResult.put("1",
					new Object[] { "Testcase code", "Testing Purpose", "Expected Output", "Actual Output" });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Parameters({ "ID_Thuoc" })
	@Test
	public void TTT_TC001_findByIDSuccess(int ID_Thuoc) {
		try {
			Thuoc thuoc = dao.findByID(ID_Thuoc);
			assertTrue(thuoc != null);
			testNgResult.put("2",
					new Object[] { "TTT_TC001_findByIDSuccess", "Find Thuoc", "Find Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("2",
					new Object[] { "TTT_TC001_findByIDSuccess", "Find Thuoc", "Find Success", "Fail" });
		}
	}

	@Test
	public void TTT_TC002_findByIDFailed() {
		try {
			Thuoc thuoc = dao.findByID(0);
			assertFalse(thuoc != null);
			testNgResult.put("3",
					new Object[] { "TTT_TC002_findByIDFailed", "Find Thuoc", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("3",
					new Object[] { "TTT_TC002_findByIDFailed", "Find Thuoc", "Find Fail", "Fail" });
		}
	}

	@Test
	public void TTT_TC003_findByIDFailed() {
		try {
			Thuoc thuoc = dao.findByID(-1);
			assertFalse(thuoc != null);
			testNgResult.put("4",
					new Object[] { "TTT_TC003_findByIDFailed", "Find Thuoc", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("4",
					new Object[] { "TTT_TC003_findByIDFailed", "Find Thuoc", "Find Fail", "Pass" });
		}
	}

	@Parameters({ "ID_NhomThuoc" })
	@Test
	public void TTT_TC004_findByIDNhomThuocSuccess(String ID_NhomThuoc) {
		try {
			List<Thuoc> list = dao.findByIDNhomThuoc(ID_NhomThuoc);
			assertTrue(list != null);
			testNgResult.put("5",
					new Object[] { "TTT_TC004_findByIDNhomThuocSuccess", "Find ID Nhom Thuoc", "Find Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("5",
					new Object[] { "TTT_TC004_findByIDNhomThuocSuccess", "Find ID Nhom Thuoc", "Find Success", "Fail" });
		}
	}

	@Test
	public void TTT_TC005_findByIDNhomThuocFailed() {
		try {
			List<Thuoc> list = dao.findByIDNhomThuoc("");
			assertTrue(list == null);
			testNgResult.put("6",
					new Object[] { "TTT_TC005_findByIDNhomThuocFailed", "Find ID Nhom Thuoc", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("6",
					new Object[] { "TTT_TC005_findByIDNhomThuocFailed", "Find ID Nhom Thuoc", "Find Fail", "Fail" });
		}
	}

	@Test
	public void TTT_TC006_findByIDNhomThuocFailed() {
		try {
			List<Thuoc> list = dao.findByIDNhomThuoc("abc");
			assertTrue(list.size() == 0);
			testNgResult.put("7",
					new Object[] { "TTT_TC006_findByIDNhomThuocFailed", "Find ID Nhom Thuoc", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("7",
					new Object[] { "TTT_TC006_findByIDNhomThuocFailed", "Find ID Nhom Thuoc", "Find Fail", "Fail" });
		}
	}

	@Parameters({ "Ten_Thuoc", "ID_NhomThuoc", "ID_DVT", "Cach_Dung", "HoatChat", "HamLuong", "DongGoi", "NoiSanXuat" })
	@Test
	public void TTT_TC007_insertSuccess(String Ten_Thuoc, String ID_NhomThuoc, String ID_DVT, String Cach_Dung,
			String HoatChat, String Ham_Luong, String Dong_Goi, String NoiSanXuat) {
		try {
			Thuoc thuoc = new Thuoc(Ten_Thuoc, ID_NhomThuoc, ID_DVT, Cach_Dung, HoatChat, Ham_Luong, Dong_Goi,
					NoiSanXuat, true);
			assertTrue(dao.Insert(thuoc));
			testNgResult.put("8",
					new Object[] { "TTT_TC007_insertSuccess", "Insert Thuoc", "Insert Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("8",
					new Object[] { "TTT_TC007_insertSuccess", "Insert Thuoc", "Insert Success", "Fail" });
		}
	}

	@Test
	public void TTT_TC008_insertFailed() {
		try {
			Thuoc thuoc = null;
			assertFalse(dao.Insert(thuoc));
			testNgResult.put("9",
					new Object[] { "TTT_TC008_insertSuccess", "Insert Thuoc", "Insert Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("9",
					new Object[] { "TTT_TC008_insertSuccess", "Insert Thuoc", "Insert Success", "Fail" });
		}
	}

	@Test
	public void TTT_TC009_insertFailed() {
		try {
			Thuoc thuoc = new Thuoc();
			assertFalse(dao.Insert(thuoc));
			testNgResult.put("10",
					new Object[] { "TTT_TC009_insertSuccess", "Insert Thuoc", "Insert Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("10",
					new Object[] { "TTT_TC009_insertSuccess", "Insert Thuoc", "Insert Success", "Fail" });
		}
	}

	@Parameters({ "Ten_Thuoc", "ID_DVT", "Cach_Dung", "HoatChat", "HamLuong", "DongGoi", "NoiSanXuat" })
	@Test
	public void TTT_TC0010_insertFailed(String Ten_Thuoc, String ID_DVT, String Cach_Dung, String HoatChat,
			String Ham_Luong, String Dong_Goi, String NoiSanXuat) {
		try {
			Thuoc thuoc = new Thuoc(Ten_Thuoc, null, ID_DVT, Cach_Dung, HoatChat, Ham_Luong, Dong_Goi, NoiSanXuat,
					true);
			assertFalse(dao.Insert(thuoc));
			testNgResult.put("11",
					new Object[] { "TTT_TC010_insertSuccess", "Insert Thuoc", "Insert Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("11",
					new Object[] { "TTT_TC010_insertSuccess", "Insert Thuoc", "Insert Success", "Fail" });
		}
	}

	@Parameters({ "Ten_Thuoc", "ID_NhomThuoc", "Cach_Dung", "HoatChat", "HamLuong", "DongGoi", "NoiSanXuat" })
	@Test
	public void TTT_TC011_insertFailed(String Ten_Thuoc, String ID_NhomThuoc, String Cach_Dung, String HoatChat,
			String Ham_Luong, String Dong_Goi, String NoiSanXuat) {
		try {
			Thuoc thuoc = new Thuoc(Ten_Thuoc, ID_NhomThuoc, null, Cach_Dung, HoatChat, Ham_Luong, Dong_Goi, NoiSanXuat,
					true);
			assertFalse(dao.Insert(thuoc));
			testNgResult.put("12",
					new Object[] { "TTT_TC011_insertSuccess", "Insert Thuoc", "Insert Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("12",
					new Object[] { "TTT_TC011_insertSuccess", "Insert Thuoc", "Insert Success", "Fail" });
		}
	}

	@Parameters({ "ID_Thuoc", "Ten_Thuoc", "ID_NhomThuoc", "ID_DVT", "Cach_Dung", "HoatChat", "HamLuong", "DongGoi",
			"NoiSanXuat" })
	@Test
	public void TTT_TC012_updateSuccess(int ID_Thuoc, String Ten_Thuoc, String ID_NhomThuoc, String ID_DVT,
			String Cach_Dung, String HoatChat, String Ham_Luong, String Dong_Goi, String NoiSanXuat) {
		try {
			Thuoc thuoc = new Thuoc(ID_Thuoc, Ten_Thuoc, ID_NhomThuoc, ID_DVT, Cach_Dung, HoatChat, Ham_Luong, Dong_Goi,
					NoiSanXuat, false);
			assertTrue(dao.Update(thuoc));
			testNgResult.put("13",
					new Object[] { "TTT_TC012_updateSuccess", "Update Thuoc", "Update Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("13",
					new Object[] { "TTT_TC012_updateSuccess", "Update Thuoc", "Update Success", "Pass" });
		}
	}

	@Parameters({ "Ten_Thuoc", "ID_NhomThuoc", "ID_DVT", "Cach_Dung", "HoatChat", "HamLuong", "DongGoi", "NoiSanXuat" })
	@Test
	public void TTT_TC013_updateFailed(String Ten_Thuoc, String ID_NhomThuoc, String ID_DVT, String Cach_Dung,
			String HoatChat, String Ham_Luong, String Dong_Goi, String NoiSanXuat) {
		try {
			Thuoc thuoc = new Thuoc(-1, Ten_Thuoc, ID_NhomThuoc, ID_DVT, Cach_Dung, HoatChat, Ham_Luong, Dong_Goi,
					NoiSanXuat, false);
			assertFalse(dao.Update(thuoc));
			testNgResult.put("14",
					new Object[] { "TTT_TC013_updateFailed", "Update Thuoc", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("14",
					new Object[] { "TTT_TC013_updateFailed", "Update Thuoc", "Update Fail", "Fail" });
		}
	}

	@Parameters({ "ID_Thuoc", "Ten_Thuoc", "ID_DVT", "Cach_Dung", "HoatChat", "HamLuong", "DongGoi", "NoiSanXuat" })
	@Test
	public void TTT_TC014_updateFailed(int ID_Thuoc, String Ten_Thuoc, String ID_DVT, String Cach_Dung, String HoatChat,
			String Ham_Luong, String Dong_Goi, String NoiSanXuat) {
		try {
			Thuoc thuoc = new Thuoc(ID_Thuoc, Ten_Thuoc, null, ID_DVT, Cach_Dung, HoatChat, Ham_Luong, Dong_Goi, NoiSanXuat,
					false);
			assertFalse(dao.Update(thuoc));
			testNgResult.put("15",
					new Object[] { "TTT_TC014_updateFailed", "Update Thuoc", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("15",
					new Object[] { "TTT_TC014_updateFailed", "Update Thuoc", "Update Fail", "Fail" });
		}
	}

	@Parameters({ "ID_Thuoc", "Ten_Thuoc", "ID_NhomThuoc", "Cach_Dung", "HoatChat", "HamLuong", "DongGoi",
			"NoiSanXuat" })
	@Test
	public void TTT_TC015_updateFailed(int ID_Thuoc, String Ten_Thuoc, String ID_NhomThuoc, String Cach_Dung,
			String HoatChat, String Ham_Luong, String Dong_Goi, String NoiSanXuat) {
		try {
			Thuoc thuoc = new Thuoc(ID_Thuoc, Ten_Thuoc, ID_NhomThuoc, null, Cach_Dung, HoatChat, Ham_Luong, Dong_Goi,
					NoiSanXuat, false);
			assertFalse(dao.Update(thuoc));
			testNgResult.put("16",
					new Object[] { "TTT_TC015_updateFailed", "Update Thuoc", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("16",
					new Object[] { "TTT_TC015_updateFailed", "Update Thuoc", "Update Fail", "Fail" });
		}
	}

	@Test
	public void TTT_TC016_selectallSuccess() {
		try {
			List<Thuoc> list = dao.select();
			assertTrue(list != null);
			testNgResult.put("17",
					new Object[] { "TTT_TC016_selectallSuccess", "Select All Thuoc", "Select Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("17",
					new Object[] { "TTT_TC016_selectallSuccess", "Select All Thuoc", "Select Success", "Fail" });
		}
	}

	@Test
	public void TTT_TC017_selectallFailed() {
		try {
			List<Thuoc> list = dao.select();
			assertFalse(list == null); 
			testNgResult.put("18",
					new Object[] { "TTT_TC017_selectallSuccess", "Select All Thuoc", "Select Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("18",
					new Object[] { "TTT_TC017_selectallSuccess", "Select All Thuoc", "Select Fail", "Pass" });
		}
	}

	@AfterTest
	public void suiteTearDown() {
		Set<String> keysSet = testNgResult.keySet();
		int rownum = 0;
		for (String key : keysSet) {
			Row row = sheet6.createRow(rownum++);
			Object[] objArr = (Object[]) testNgResult.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof Date) {
					cell.setCellValue((Date) obj);
				} else if (obj instanceof Boolean) {
					cell.setCellValue((Boolean) obj);
				} else if (obj instanceof String) {
					cell.setCellValue((String) obj);
				} else if (obj instanceof Double) {
					cell.setCellValue((Double) obj);
				}
			}

			try {
				FileOutputStream out = new FileOutputStream(
						new File("D:\\SOF304\\Assignment\\Assignment_KT\\excel\\TestThuoc.xls"));
				workbook.write(out);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
