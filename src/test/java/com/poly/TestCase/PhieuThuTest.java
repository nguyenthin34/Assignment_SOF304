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
import com.poly.dao.PhieuThuDAO;
import com.poly.helper.Datehelper;
import com.poly.model.PhieuThu;

public class PhieuThuTest {

	Connection connection;
	PhieuThuDAO dao = new PhieuThuDAO();
	Map<String, Object> testNgResult;
	HSSFWorkbook workbook;
	HSSFSheet sheet13;

	@BeforeTest
	public void connectDatabase() {
		try {
			connection = SQLConnect.getcon();
			workbook = new HSSFWorkbook();
			sheet13 = workbook.createSheet("Trả Hàng");
			testNgResult = new LinkedHashMap<String, Object>();
			testNgResult.put("1",
					new Object[] { "Testcase code", "Testing Purpose", "Expected Output", "Actual Output" });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Parameters("NgayThu")
	@Test
	public void PT_TC001_find_ByDateSuccess(String NgayThu) {
		try {
			List<PhieuThu> phieuThu = dao.find_ByDate(Datehelper.toDate(NgayThu));
			assertTrue(phieuThu != null);
			testNgResult.put("2", new Object[] { "PT_TC001_find_ByDateSuccess", "Find HD Phieu Thu By Date",
					"Find Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("2", new Object[] { "PT_TC001_find_ByDateSuccess", "Find HD Phieu Thu By Date",
					"Find Success", "Fail" });
		}
	}

	@Test
	public void PT_TC002_find_ByDateFailed() {
		try {
			Date ngaythu = new Date();
			List<PhieuThu> phieuThu = dao.find_ByDate(ngaythu);
			assertFalse(phieuThu != null);
			testNgResult.put("3",
					new Object[] { "PT_TC002_find_ByDateFailed", "Find HD Phieu Thu By Date", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("3",
					new Object[] { "PT_TC002_find_ByDateFailed", "Find HD Phieu Thu By Date", "Find Fail", "Fail" });
		}
	}

	@Test
	public void PT_TC003_find_ByDateFailed() {
		try {
			List<PhieuThu> phieuThu = dao.find_ByDate(null);
			assertFalse(phieuThu != null);
			testNgResult.put("4",
					new Object[] { "PT_TC003_find_ByDateFailed", "Find HD Phieu Thu By Date", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("4",
					new Object[] { "PT_TC003_find_ByDateFailed", "Find HD Phieu Thu By Date", "Find Fail", "Pass" });
		}
	}

	@Parameters({ "NgayThu", "NguoiThu", "TienThu" })
	@Test
	public void PT_TC009_insertSuccess(String NgayThu, String NguoiThu, float TienThu) {
		try {
			PhieuThu phieuThu = new PhieuThu(Datehelper.toDate(NgayThu), NguoiThu, TienThu);
			assertTrue(dao.Insert(phieuThu));
			testNgResult.put("5",
					new Object[] { "PT_TC009_insertSuccess", "Insert Phieu Thu", "Insert Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("5",
					new Object[] { "PT_TC009_insertSuccess", "Insert Phieu Thu", "Insert Success", "Fail" });
		}
	}

	@Parameters({ "NgayThu", "TienThu" })
	@Test
	public void PT_TC004_insertFailed(String NgayThu, float TienThu) {
		try {
			PhieuThu phieuThu = new PhieuThu(Datehelper.toDate(NgayThu), "", TienThu);
			assertFalse(dao.Insert(phieuThu));
			testNgResult.put("6", new Object[] { "PT_TC004_insertFailed", "Insert Phieu Thu", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("6", new Object[] { "PT_TC004_insertFailed", "Insert Phieu Thu", "Insert Fail", "Pass" });
		}
	}

	@Parameters({ "NgayThu", "TienThu" })
	@Test
	public void PT_TC005_insertFailed(String NgayThu, float TienThu) {
		try {
			PhieuThu phieuThu = new PhieuThu(Datehelper.toDate(NgayThu), null, TienThu);
			assertFalse(dao.Insert(phieuThu));
			testNgResult.put("7", new Object[] { "PT_TC005_insertFailed", "Insert Phieu Thu", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("7", new Object[] { "PT_TC005_insertFailed", "Insert Phieu Thu", "Insert Fail", "Fail" });
		}
	}

	@Parameters({ "NgayThu", "NguoiThu" })
	@Test
	public void PT_TC006_insertFailed(String NgayThu, String NguoiThu) {
		try {
			PhieuThu phieuThu = new PhieuThu(Datehelper.toDate(NgayThu), NguoiThu, -1);
			assertFalse(dao.Insert(phieuThu));
			testNgResult.put("8", new Object[] { "PT_TC006_insertFailed", "Insert Phieu Thu", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("8", new Object[] { "PT_TC006_insertFailed", "Insert Phieu Thu", "Insert Fail", "Fail" });
		}
	}

	@Test
	public void PT_TC010_insertFailed() {
		try {
			PhieuThu phieuThu = null;
			assertFalse(dao.Insert(phieuThu));
			testNgResult.put("9",
					new Object[] { "PT_TC010_insertFailed", "Insert Phieu Thu", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("9",
					new Object[] { "PT_TC010_insertFailed", "Insert Phieu Thu", "Insert Fail", "Fail" });
		}
	}

	@Test
	public void PT_TC007_selectallSuccess() {
		try {
			List<PhieuThu> list = dao.select();
			assertTrue(list != null);
			testNgResult.put("10",
					new Object[] { "PT_TC007_selectallSuccess", "Select All Phieu Thu", "Select All Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("10",
					new Object[] { "PT_TC007_selectallSuccess", "Select All Phieu Thu", "Select All Success", "Fail" });
		}
	}

	@Test
	public void PT_TC008_selectallFailed() {
		try {
			List<PhieuThu> list = dao.select();
			assertFalse(list == null);
			testNgResult.put("11",
					new Object[] { "PT_TC008_selectallFailed", "Select All Phieu Thu", "Select All Failed", "Pass" });
		} catch (Exception e) {
			testNgResult.put("11",
					new Object[] { "PT_TC008_selectallFailed", "Select All Phieu Thu", "Select All Failed", "Fail" });
		}
	}

	@AfterTest
	public void TearDown() {
		Set<String> keysSet = testNgResult.keySet();
		int rownum = 0;
		for (String key : keysSet) {
			Row row = sheet13.createRow(rownum++);
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
						new File("D:\\SOF304\\Assignment\\Assignment_KT\\excel\\TestPhieuThu.xls"));
				workbook.write(out);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
