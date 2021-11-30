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
import com.poly.dao.PhieuNhapHangCTDAO;
import com.poly.helper.Datehelper;
import com.poly.dao.PhieuNhapHangCTDAO;
import com.poly.model.PhieuNhapHangCT;

public class NhapHangCTTest {

	Connection connection;
	PhieuNhapHangCTDAO dao = new PhieuNhapHangCTDAO();
	Map<String, Object> testNgResult;
	HSSFWorkbook workbook;
	HSSFSheet sheet16;

	@BeforeTest
	public void connectDatabase() {
		try {
			connection = SQLConnect.getcon();
			workbook = new HSSFWorkbook();
			sheet16 = workbook.createSheet("Phiếu Chi");
			testNgResult = new LinkedHashMap<String, Object>();
			testNgResult.put("1",
					new Object[] { "Testcase code", "Testing Purpose", "Expected Output", "Actual Output" });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Parameters({ "ID_HDN", "ID_Thuoc", "SoLuong", "GiaNhap", "HanSuDung" })
	@Test
	public void NHCT_TC001_insertSuccess(int ID_HDN, int ID_Thuoc, int SoLuong, float GiaNhap, String hsd) {
		try {
			PhieuNhapHangCT PhieuNhapHangCT = new PhieuNhapHangCT(ID_HDN, ID_Thuoc, SoLuong, GiaNhap,
					Datehelper.toDate(hsd));
			assertTrue(dao.Insert(PhieuNhapHangCT));
			testNgResult.put("2",
					new Object[] { "NHCT_TC001_insertSuccess", "Insert HDN Chi Tiet", "Insert Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("2",
					new Object[] { "NHCT_TC001_insertSuccess", "Insert HDN Chi Tiet", "Insert Success", "Fail" });
		}
	}

	@Parameters({ "ID_HDN", "SoLuong", "GiaNhap", "HanSuDung" })
	@Test
	public void NHCT_TC002_insertFailed(int ID_HDN, int SoLuong, float GiaNhap, String hsd) {
		try {
			PhieuNhapHangCT PhieuNhapHangCT = new PhieuNhapHangCT(ID_HDN, -1, SoLuong, GiaNhap, Datehelper.toDate(hsd));
			assertFalse(dao.Insert(PhieuNhapHangCT));
			testNgResult.put("3",
					new Object[] { "NHCT_TC002_insertFailed", "Insert HDN Chi Tiet", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("3",
					new Object[] { "NHCT_TC002_insertFailed", "Insert HDN Chi Tiet", "Insert Fail", "Fail" });
		}
	}

	@Parameters({ "ID_HDN", "ID_Thuoc", "GiaNhap", "HanSuDung" })
	@Test
	public void NHCT_TC003_insertFailed(int ID_HDN, int ID_Thuoc, float GiaNhap, String hsd) {
		try {
			PhieuNhapHangCT PhieuNhapHangCT = new PhieuNhapHangCT(ID_HDN, ID_Thuoc, -1, GiaNhap,
					Datehelper.toDate(hsd));
			assertFalse(dao.Insert(PhieuNhapHangCT));
			testNgResult.put("4",
					new Object[] { "NHCT_TC003_insertFailed", "Insert HDN Chi Tiet", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("4",
					new Object[] { "NHCT_TC003_insertFailed", "Insert HDN Chi Tiet", "Insert Fail", "Fail" });
		}
	}

	@Parameters({ "ID_HDN", "ID_Thuoc", "SoLuong", "HanSuDung" })
	@Test
	public void NHCT_TC04_insertFalse(int ID_HDN, int ID_Thuoc, int SoLuong, String hsd) {
		try {
			PhieuNhapHangCT PhieuNhapHangCT = new PhieuNhapHangCT(ID_HDN, ID_Thuoc, SoLuong, -1,
					Datehelper.toDate(hsd));
			assertFalse(dao.Insert(PhieuNhapHangCT));
			testNgResult.put("4",
					new Object[] { "NHCT_TC003_insertFailed", "Insert HDN Chi Tiet", "Insert Fail", "Pass" });
			testNgResult.put("5",
					new Object[] { "NHCT_TC004_insertFailed", "Insert HDN Chi Tiet", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("5",
					new Object[] { "NHCT_TC004_insertFailed", "Insert HDN Chi Tiet", "Insert Fail", "Fail" });
		}
	}

	@AfterTest
	public void TearDown() {
		Set<String> keysSet = testNgResult.keySet();
		int rownum = 0;
		for (String key : keysSet) {
			Row row = sheet16.createRow(rownum++);
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
						new File("D:\\SOF304\\Assignment\\Assignment_KT\\excel\\TestNhapHangCT.xls"));
				workbook.write(out);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
