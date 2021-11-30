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
import com.poly.dao.HoaDonBanCTDAO;
import com.poly.dao.HoaDonBanHangDAO;
import com.poly.model.HoaDonBanCT;

public class BanHangCTTest {
	Connection connection;
	HoaDonBanCTDAO dao = new HoaDonBanCTDAO();
	Map<String, Object> testNgResult;
	HSSFWorkbook workbook;
	HSSFSheet sheet15;
	@BeforeTest
	public void connectDatabase() {
		try {
			connection = SQLConnect.getcon();
			workbook = new HSSFWorkbook();
			sheet15 = workbook.createSheet("Nhập Hàng");
			testNgResult = new LinkedHashMap<String, Object>();
			testNgResult.put("1",
					new Object[] { "Testcase code", "Testing Purpose", "Expected Output", "Actual Output" });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Parameters("ID_HDB")
	@Test
	public void BHCT_TC001_findbyIDSucess(int ID_HDB) {
		try {
			List<HoaDonBanCT> hoaDonBanCT = dao.findbyID(ID_HDB);
			assertTrue(hoaDonBanCT!=null);
			testNgResult.put("2", new Object[] { "BHCT_TC001_findbyIDSucess", "Find HDB Chi Tiet",
					"Find Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("2", new Object[] { "BHCT_TC001_findbyIDSucess", "Find HDB Chi Tiet",
					"Find Success", "Fail" });
		}
	}
	
	@Test
	public void BHCT_TC002_findbyIDFailed() {
		try {
			List<HoaDonBanCT> hoaDonBanCT = dao.findbyID(-1);
			assertTrue(hoaDonBanCT==null);
			testNgResult.put("3", new Object[] { "BHCT_TC002_findbyIDFailed", "Find HDB Chi Tiet",
					"Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("3", new Object[] { "BHCT_TC002_findbyIDFailed", "Find HDB Chi Tiet",
					"Find Fail", "Fail" });
		}
	}
	
	@Parameters({"ID_HDB", "Ma_Thuoc", "SoLuong", "GiaBan"})
	@Test
	public void  BHCT_TC003_insertSuccess(int ID_HDB, int Ma_Thuoc, int SoLuong, float GiaBan) {
		try {
			HoaDonBanCT hoaDonBanCT = new HoaDonBanCT(ID_HDB, Ma_Thuoc, SoLuong, GiaBan);
			assertTrue(dao.Insert(hoaDonBanCT));
			testNgResult.put("4", new Object[] { "BHCT_TC003_insertSuccess", "Insert HDB Chi Tiet",
					"Insert Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("4", new Object[] { "BHCT_TC003_insertSuccess", "Insert HDB Chi Tiet",
					"Insert Success", "Fail" });
		}
	}
	
	@Parameters({ "Ma_Thuoc", "SoLuong", "GiaBan"})
	@Test
	public void  BHCT_TC004_insertFailed( int Ma_Thuoc, int SoLuong, float GiaBan) {
		try {
			HoaDonBanCT hoaDonBanCT = new HoaDonBanCT(-1, Ma_Thuoc, SoLuong, GiaBan);
			assertFalse(dao.Insert(hoaDonBanCT));
			testNgResult.put("5", new Object[] { "BHCT_TC004_insertFailed", "Insert HDB Chi Tiet",
					"Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("5", new Object[] { "BHCT_TC004_insertFailed", "Insert HDB Chi Tiet",
					"Insert Fail", "Fail" });
		}
	}
	
	@Parameters({"ID_HDB", "SoLuong", "GiaBan"})
	@Test
	public void  BHCT_TC005_insertFailed(int ID_HDB, int SoLuong, float GiaBan) {
		try {
			HoaDonBanCT hoaDonBanCT = new HoaDonBanCT(ID_HDB, -1, SoLuong, GiaBan);
			assertFalse(dao.Insert(hoaDonBanCT));
			testNgResult.put("6", new Object[] { "BHCT_TC005_insertFailed", "Insert HDB Chi Tiet",
					"Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("6", new Object[] { "BHCT_TC005_insertFailed", "Insert HDB Chi Tiet",
					"Insert Fail", "Pass" });
		}
	}
	
	@Parameters({"ID_HDB", "Ma_Thuoc", "SoLuong"})
	@Test
	public void  BHCT_TC006_insertFailed(int ID_HDB, int Ma_Thuoc, int SoLuong) {
		try {
			HoaDonBanCT hoaDonBanCT = new HoaDonBanCT(ID_HDB, Ma_Thuoc, SoLuong, -1);
			assertFalse(dao.Insert(hoaDonBanCT));
			testNgResult.put("7", new Object[] { "BHCT_TC006_insertFailed", "Insert HDB Chi Tiet",
					"Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("7", new Object[] { "BHCT_TC006_insertFailed", "Insert HDB Chi Tiet",
					"Insert Fail", "Pass" });
		}
	}
	
	@Parameters({"ID_HDB", "Ma_Thuoc", "GiaBan"})
	@Test
	public void  BHCT_TC007_insertFailed(int ID_HDB, int Ma_Thuoc, float GiaBan) {
		try {
			HoaDonBanCT hoaDonBanCT = new HoaDonBanCT(ID_HDB, Ma_Thuoc, -1, GiaBan);
			assertFalse(dao.Insert(hoaDonBanCT));
			testNgResult.put("8", new Object[] { "BHCT_TC007_insertFailed", "Insert HDB Chi Tiet",
					"Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("8", new Object[] { "BHCT_TC007_insertFailed", "Insert HDB Chi Tiet",
					"Insert Fail", "Pass" });
		}
	}
	
	@AfterTest
	public void TearDown() {
		Set<String> keysSet = testNgResult.keySet();
		int rownum = 0;
		for (String key : keysSet) {
			Row row = sheet15.createRow(rownum++);
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
						new File("D:\\SOF304\\Assignment\\Assignment_KT\\excel\\TestBanHangCT.xls"));
				workbook.write(out);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
