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
import com.poly.helper.Datehelper;
import com.poly.model.SanPhamThuoc;

public class SanPhamThuocTest {
	Connection connection;
	SanPhamThuocDAO dao = new SanPhamThuocDAO();
	Map<String, Object> testNgResult;
	HSSFWorkbook workbook;
	HSSFSheet sheet5;

	@BeforeTest
	public void connectDatabase() {
		try {
			connection = SQLConnect.getcon();
			workbook = new HSSFWorkbook();
			sheet5 = workbook.createSheet("Sản Phẩm Thuốc");
			testNgResult = new LinkedHashMap<String, Object>();
			testNgResult.put("1",
					new Object[] { "Testcase code", "Testing Purpose", "Expected Output", "Actual Output" });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Parameters({ "Ma_Thuoc" })
	@Test
	public void SPT_TC001_findByIDSuccess(int SPT) {
		try {
			SanPhamThuoc spt = dao.findByID(SPT);
			assertTrue(spt != null);
			testNgResult.put("2",
					new Object[] { "SPT_TC001_findByIDSuccess", "Find San Pham Thuoc", "Find Success", "Pass" });

		} catch (Exception e) {
			testNgResult.put("2",
					new Object[] { "SPT_TC001_findByIDSuccess", "Find San Pham Thuoc", "Find Success", "Fail" });
		}
	}

	@Test
	public void SPT_TC002_findByIDFailed() {
		try {
			SanPhamThuoc spt = dao.findByID(0);
			assertFalse(spt != null);
			testNgResult.put("3",
					new Object[] { "SPT_TC002_findByIDFailed", "Find San Pham Thuoc", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("3",
					new Object[] { "SPT_TC002_findByIDFailed", "Find San Pham Thuoc", "Find Fail", "Fail" });
		}
	}

	@Test
	public void SPT_TC003_findByIDFailed() {
		try {
			SanPhamThuoc spt = dao.findByID(-1);
			assertFalse(spt != null);
			testNgResult.put("4",
					new Object[] { "SPT_TC003_findByIDFailed", "Find San Pham Thuoc", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("4",
					new Object[] { "SPT_TC003_findByIDFailed", "Find San Pham Thuoc", "Find Fail", "Fail" });
		}
	}

	@Parameters({ "ID_Thuoc" })
	@Test
	public void SPT_TC004_findByIDThuocSuccess(int ID_Thuoc) {
		try {
			List<SanPhamThuoc> list = dao.findByIDThuoc(ID_Thuoc);
			assertTrue(list != null);
			testNgResult.put("5",
					new Object[] { "SPT_TC004_findByIDThuocSuccess", "Find Thuoc", "Find Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("5",
					new Object[] { "SPT_TC004_findByIDThuocSuccess", "Find Thuoc", "Find Success", "Fail" });
		}
	}

	@Test
	public void SPT_TC005_findByIDThuocFailed() {
		try {
			List<SanPhamThuoc> list = dao.findByIDThuoc(0);
			assertFalse(list != null);
			testNgResult.put("6", new Object[] { "SPT_TC005_findByIDThuocFailed", "Find Thuoc", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("6", new Object[] { "SPT_TC005_findByIDThuocFailed", "Find Thuoc", "Find Fail", "Fail" });
		}
	}

	@Test
	public void SPT_TC006_findByIDThuocFailed() {
		try {
			List<SanPhamThuoc> list = dao.findByIDThuoc(-1);
			assertFalse(list != null);
			testNgResult.put("7", new Object[] { "SPT_TC006_findByIDThuocFailed", "Find Thuoc", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("7", new Object[] { "SPT_TC006_findByIDThuocFailed", "Find Thuoc", "Find Fail", "Fail" });
		}
	}

	@Test
	public void SPT_TC007_findConHangSuccess() {
		try {
			List<SanPhamThuoc> list = dao.findByconhang();
			assertTrue(list != null);
			testNgResult.put("8",
					new Object[] { "SPT_TC007_findConHangSuccess", "Find So Luong Ton SPT", "Find Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("8",
					new Object[] { "SPT_TC007_findConHangSuccess", "Find So Luong Ton SPT", "Find Success", "Fail" });
		}
	}

	@Test
	public void SPT_TC008_findConHangFailed() {
		try {
			List<SanPhamThuoc> list = dao.findByconhang();
			assertFalse(list == null);
			testNgResult.put("9",
					new Object[] { "SPT_TC008_findConHangFailed", "Find So Luong Ton SPT", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("9",
					new Object[] { "SPT_TC008_findConHangFailed", "Find So Luong Ton SPT", "Find Fail", "Fail" });
		}
	}

	@Parameters({ "SoLuongTon" })
	@Test
	public void SPT_TC009_findSoLuongSuccess(int soluongton) {
		try {
			List<SanPhamThuoc> list = dao.findBySlT(soluongton);
			assertTrue(list != null);
			testNgResult.put("10",
					new Object[] { "SPT_TC009_findSoLuongSuccess", "Find So Luong", "Find Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("10",
					new Object[] { "SPT_TC009_findSoLuongSuccess", "Find So Luong", "Find Success", "Fail" });
		}
	}

	@Test
	public void SPT_TC010_findSoLuongFailed() {
		try {
			List<SanPhamThuoc> list = dao.findBySlT(-1);
			assertFalse(list == null);
			testNgResult.put("11",
					new Object[] { "SPT_TC010_findSoLuongFailed", "Find So Luong", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("11",
					new Object[] { "SPT_TC010_findSoLuongFailed", "Find So Luong", "Find Fail", "Fail" });
		}
	}

	@Parameters({ "ID_Thuoc", "ID_NhaCC", "ID_Tu", "HanSuDung", "GiaNhap", "GiaBan", "SoLuongTon" })
	@Test
	public void SPT_TC011_InsertSuccess(String ID_thuoc, String ID_NhaCC, String ID_Tu, String HanSuDung,
			String GiaNhap, String GiaBan, String SoLuongTon) {
		try {
			SanPhamThuoc spt = new SanPhamThuoc(Integer.parseInt(ID_thuoc), ID_NhaCC, ID_Tu, Float.parseFloat(GiaNhap),
					Float.parseFloat(GiaBan), Integer.parseInt(SoLuongTon), Datehelper.toDate(HanSuDung), true);
			boolean check = dao.Insert(spt);
			assertTrue(check);
			testNgResult.put("12",
					new Object[] { "SPT_TC011_InsertSuccess", "Insert San Pham Thuoc", "Insert Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("12",
					new Object[] { "SPT_TC011_InsertSuccess", "Insert San Pham Thuoc", "Insert Success", "Fail" });
		}
	}

	@Parameters({ "ID_NhaCC", "ID_Tu", "HanSuDung", "GiaNhap", "GiaBan", "SoLuongTon" })
	@Test
	public void SPT_TC019_InsertFailed(String ID_NhaCC, String ID_Tu, String HanSuDung, float GiaNhap, float GiaBan,
			int SoLuongTon) {
		try {
			SanPhamThuoc spt = new SanPhamThuoc(-1, ID_NhaCC, ID_Tu, GiaNhap, GiaBan, SoLuongTon,
					Datehelper.toDate(HanSuDung), true);
			boolean check = dao.Insert(spt);
			assertFalse(check);
			testNgResult.put("13",
					new Object[] { "SPT_TC019_InsertFailed", "Insert San Pham Thuoc", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("13",
					new Object[] { "SPT_TC019_InsertFailed", "Insert San Pham Thuoc", "Insert Fail", "Fail" });
		}
	}

	@Parameters({ "ID_Thuoc", "ID_Tu", "HanSuDung", "GiaNhap", "GiaBan", "SoLuongTon" })
	@Test
	public void SPT_TC020_InsertFailed(int ID_thuoc, String ID_Tu, String HanSuDung, float GiaNhap, float GiaBan,
			int SoLuongTon) {
		try {
			SanPhamThuoc spt = new SanPhamThuoc(ID_thuoc, null, ID_Tu, GiaNhap, GiaBan, SoLuongTon,
					Datehelper.toDate(HanSuDung), true);
			boolean check = dao.Insert(spt);
			assertFalse(check);
			testNgResult.put("14",
					new Object[] { "SPT_TC020_InsertFailed", "Insert San Pham Thuoc", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("14",
					new Object[] { "SPT_TC020_InsertFailed", "Insert San Pham Thuoc", "Insert Fail", "Fail" });
		}
	}

	@Parameters({ "ID_Thuoc", "ID_NhaCC", "HanSuDung", "GiaNhap", "GiaBan", "SoLuongTon" })
	@Test
	public void SPT_TC021_InsertFailed(int ID_thuoc, String ID_NhaCC, String HanSuDung, float GiaNhap, float GiaBan,
			int SoLuongTon) {
		try {
			SanPhamThuoc spt = new SanPhamThuoc(ID_thuoc, ID_NhaCC, null, GiaNhap, GiaBan, SoLuongTon,
					Datehelper.toDate(HanSuDung), true);
			boolean check = dao.Insert(spt);
			assertFalse(check);
			testNgResult.put("15",
					new Object[] { "SPT_TC021_InsertFailed", "Insert San Pham Thuoc", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("15",
					new Object[] { "SPT_TC021_InsertFailed", "Insert San Pham Thuoc", "Insert Fail", "Fail" });
		}
	}

	@Test
	public void SPT_TC012_InsertFailed() {
		try {
			Date date = new Date();
			SanPhamThuoc spt = new SanPhamThuoc(-1, "NCC1", "T1", 234, 312, 25, date, true);
			boolean check = dao.Insert(spt);
			assertFalse(check);
			testNgResult.put("16",
					new Object[] { "SPT_TC012_InsertFailed", "Insert San Pham Thuoc", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("16",
					new Object[] { "SPT_TC012_InsertFailed", "Insert San Pham Thuoc", "Insert Fail", "Fail" });
		}
	}

	@Test
	public void SPT_TC013_InsertFailed() {
		try {
			SanPhamThuoc spt = null;
			boolean check = dao.Insert(spt);
			assertFalse(check);
			testNgResult.put("17",
					new Object[] { "SPT_TC013_InsertFailed", "Insert San Pham Thuoc", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("17",
					new Object[] { "SPT_TC013_InsertFailed", "Insert San Pham Thuoc", "Insert Fail", "Fail" });
		}
	}

	@Test
	public void SPT_TC014_selectallSuccess() {
		try {
			List<SanPhamThuoc> list = dao.select();
			assertTrue(list != null);
			testNgResult.put("18", new Object[] { "SPT_TC014_selectallSuccess", "Select all San Pham Thuoc",
					"Select Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("18", new Object[] { "SPT_TC014_selectallSuccess", "Select all San Pham Thuoc",
					"Select Success", "Fail" });
		}
	}

	@Test
	public void SPT_TC015_selectallFailed() {
		try {
			List<SanPhamThuoc> list = dao.select();
			assertFalse(list == null);
			testNgResult.put("19",
					new Object[] { "SPT_TC015_selectallFailed", "Select all San Pham Thuoc", "Select Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("19",
					new Object[] { "SPT_TC015_selectallFailed", "Select all San Pham Thuoc", "Select Fail", "Fail" });
		}
	}

	@Parameters({ "Ma_Thuoc", "ID_Thuoc", "ID_NhaCC", "ID_Tu", "HanSuDung", "GiaNhap", "GiaBan", "SoLuongTon" })
	@Test
	public void SPT_TC016_UpdateSuccess(int Ma_Thuoc, int ID_thuoc, String ID_NhaCC, String ID_Tu, String HanSuDung,
			float GiaNhap, float GiaBan, int SoLuongTon) throws Exception {
		try {
			SanPhamThuoc spt = new SanPhamThuoc(Ma_Thuoc, ID_thuoc, ID_NhaCC, ID_Tu, Datehelper.toDate(HanSuDung),
					GiaNhap, GiaBan, SoLuongTon, true);
			boolean check = dao.Update(spt);
			assertTrue(check);
			testNgResult.put("20",
					new Object[] { "SPT_TC016_UpdateSuccess", "Update San Pham Thuoc", "Update Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("20",
					new Object[] { "SPT_TC016_UpdateSuccess", "Update San Pham Thuoc", "Update Success", "Fail" });
		}
	}

	@Parameters({ "ID_Thuoc", "ID_NhaCC", "ID_Tu", "HanSuDung", "GiaNhap", "GiaBan", "SoLuongTon" })
	@Test
	public void SPT_TC022_UpdateFailed(int ID_thuoc, String ID_NhaCC, String ID_Tu, String HanSuDung, float GiaNhap,
			float GiaBan, int SoLuongTon) throws Exception {
		try {
			SanPhamThuoc spt = new SanPhamThuoc(-1, ID_thuoc, ID_NhaCC, ID_Tu, Datehelper.toDate(HanSuDung), GiaNhap,
					GiaBan, SoLuongTon, true);
			boolean check = dao.Update(spt);
			assertFalse(check);
			testNgResult.put("21",
					new Object[] { "SPT_TC022_UpdateFailed", "Update San Pham Thuoc", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("21",
					new Object[] { "SPT_TC022_UpdateFailed", "Update San Pham Thuoc", "Update Fail", "Fail" });
		}
	}

	@Parameters({ "ID_Thuoc", "ID_NhaCC", "ID_Tu", "HanSuDung", "GiaNhap", "GiaBan", "SoLuongTon" })
	@Test
	public void SPT_TC023_UpdateFailed(int ID_thuoc, String ID_NhaCC, String ID_Tu, String HanSuDung, float GiaNhap,
			float GiaBan, int SoLuongTon) throws Exception {
		try {
			SanPhamThuoc spt = new SanPhamThuoc(123786, ID_thuoc, ID_NhaCC, ID_Tu, Datehelper.toDate(HanSuDung),
					GiaNhap, GiaBan, SoLuongTon, true);
			boolean check = dao.Update(spt);
			assertFalse(check);
			testNgResult.put("22",
					new Object[] { "SPT_TC023_UpdateFailed", "Update San Pham Thuoc", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("22",
					new Object[] { "SPT_TC023_UpdateFailed", "Update San Pham Thuoc", "Update Fail", "Fail" });
		}
	}

	@Parameters({ "Ma_Thuoc", "ID_NhaCC", "ID_Tu", "HanSuDung", "GiaNhap", "GiaBan", "SoLuongTon" })
	@Test
	public void SPT_TC024_UpdateFailed(int Ma_Thuoc, String ID_NhaCC, String ID_Tu, String HanSuDung, float GiaNhap,
			float GiaBan, int SoLuongTon) throws Exception {
		try {
			SanPhamThuoc spt = new SanPhamThuoc(Ma_Thuoc, -1, ID_NhaCC, ID_Tu, Datehelper.toDate(HanSuDung), GiaNhap,
					GiaBan, SoLuongTon, true);
			boolean check = dao.Update(spt);
			assertFalse(check);
			testNgResult.put("23",
					new Object[] { "SPT_TC024_UpdateFailed", "Update San Pham Thuoc", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("23",
					new Object[] { "SPT_TC024_UpdateFailed", "Update San Pham Thuoc", "Update Fail", "Fail" });
		}
	}

	@Parameters({ "Ma_Thuoc", "ID_Thuoc", "ID_Tu", "HanSuDung", "GiaNhap", "GiaBan", "SoLuongTon" })
	@Test
	public void SPT_TC025_UpdateFailed(int Ma_Thuoc, int ID_thuoc, String ID_Tu, String HanSuDung, float GiaNhap,
			float GiaBan, int SoLuongTon) throws Exception {
		try {
			SanPhamThuoc spt = new SanPhamThuoc(Ma_Thuoc, ID_thuoc, null, ID_Tu, Datehelper.toDate(HanSuDung), GiaNhap,
					GiaBan, SoLuongTon, true);
			boolean check = dao.Update(spt);
			assertFalse(check);
			testNgResult.put("24",
					new Object[] { "SPT_TC025_UpdateFailed", "Update San Pham Thuoc", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("24",
					new Object[] { "SPT_TC025_UpdateFailed", "Update San Pham Thuoc", "Update Fail", "Fail" });
		}
	}

	@Parameters({ "Ma_Thuoc", "ID_Thuoc", "ID_NhaCC", "HanSuDung", "GiaNhap", "GiaBan", "SoLuongTon" })
	@Test
	public void SPT_TC026_UpdateFailed(int Ma_Thuoc, int ID_thuoc, String ID_NhaCC, String HanSuDung, float GiaNhap,
			float GiaBan, int SoLuongTon) throws Exception {
		try {
			SanPhamThuoc spt = new SanPhamThuoc(Ma_Thuoc, ID_thuoc, ID_NhaCC, null, Datehelper.toDate(HanSuDung),
					GiaNhap, GiaBan, SoLuongTon, true);
			boolean check = dao.Update(spt);
			assertFalse(check);
			testNgResult.put("25",
					new Object[] { "SPT_TC026_UpdateFailed", "Update San Pham Thuoc", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("25",
					new Object[] { "SPT_TC026_UpdateFailed", "Update San Pham Thuoc", "Update Fail", "Fail" });
		}
	}

	@Parameters({ "Ma_Thuoc", "ID_NhaCC", "ID_Tu", "HanSuDung", "GiaNhap", "GiaBan", "SoLuongTon" })
	@Test
	public void SPT_TC027_UpdateFailed(int Ma_Thuoc, String ID_NhaCC, String ID_Tu, String HanSuDung, float GiaNhap,
			float GiaBan, int SoLuongTon) throws Exception {
		try {
			SanPhamThuoc spt = new SanPhamThuoc(Ma_Thuoc, 123412321, ID_NhaCC, ID_Tu, Datehelper.toDate(HanSuDung),
					GiaNhap, GiaBan, SoLuongTon, true);
			boolean check = dao.Update(spt);
			assertFalse(check);
			testNgResult.put("26",
					new Object[] { "SPT_TC027_UpdateFailed", "Update San Pham Thuoc", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("26",
					new Object[] { "SPT_TC027_UpdateFailed", "Update San Pham Thuoc", "Update Fail", "Fail" });
		}
	}

	@Test
	public void SPT_TC017_UpdateFailed() throws Exception {
		try {
			SanPhamThuoc spt = null;
			boolean check = dao.Update(spt);
			assertFalse(check);
			testNgResult.put("27",
					new Object[] { "SPT_TC017_UpdateFailed", "Update San Pham Thuoc", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("27",
					new Object[] { "SPT_TC017_UpdateFailed", "Update San Pham Thuoc", "Update Fail", "Fail" });
		}
	}

	@Parameters({ "ID_Thuoc", "ID_NhaCC", "ID_Tu", "HanSuDung", "GiaNhap", "GiaBan", "SoLuongTon" })
	@Test
	public void SPT_TC018_UpdateFailed(int ID_thuoc, String ID_NhaCC, String ID_Tu, String HanSuDung, float GiaNhap,
			float GiaBan, int SoLuongTon) throws Exception {
		try {
			SanPhamThuoc spt = new SanPhamThuoc(-1, ID_thuoc, ID_NhaCC, ID_Tu, Datehelper.toDate(HanSuDung), GiaNhap,
					GiaBan, SoLuongTon, true);
			boolean check = dao.Update(spt);
			assertFalse(check);
			testNgResult.put("28",
					new Object[] { "SPT_TC018_UpdateFailed", "Update San Pham Thuoc", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("28",
					new Object[] { "SPT_TC018_UpdateFailed", "Update San Pham Thuoc", "Update Fail", "Pass" });
		}
	}

	@AfterTest
	public void suiteTearDown() {
		Set<String> keysSet = testNgResult.keySet();
		int rownum = 0;
		for (String key : keysSet) {
			Row row = sheet5.createRow(rownum++);
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
						new File("D:\\SOF304\\Assignment\\Assignment_KT\\excel\\TestSanPhamThuoc.xls"));
				workbook.write(out);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
