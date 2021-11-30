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
import com.poly.dao.PhieuChiDAO;
import com.poly.dao.PhieuChiDAO;
import com.poly.helper.Datehelper;
import com.poly.model.PhieuChi;

public class PhieuChiTest {
	
	Connection connection;
	PhieuChiDAO dao = new PhieuChiDAO();
	Map<String, Object> testNgResult;
	HSSFWorkbook workbook;
	HSSFSheet sheet14;
	@BeforeTest
	public void connectDatabase() {
		try {
			connection = SQLConnect.getcon();
			workbook = new HSSFWorkbook();
			sheet14 = workbook.createSheet("Phiếu Chi");
			testNgResult = new LinkedHashMap<String, Object>();
			testNgResult.put("1",
					new Object[] { "Testcase code", "Testing Purpose", "Expected Output", "Actual Output" });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Parameters("NgayLapPC")
	@Test
	public void PC_TC001_find_ByDateSuccess(String NgayThu) {
		try {
			List<PhieuChi> PhieuChi =  dao.find_ByDate(Datehelper.toDate(NgayThu));
			assertTrue(PhieuChi != null);
			testNgResult.put("2", new Object[] { "PC_TC001_find_ByDateSuccess", "Find HD Phieu Chi By Date",
					"Find Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("2", new Object[] { "PC_TC001_find_ByDateSuccess", "Find HD Phieu Chi By Date",
					"Find Success", "Fail" });
		}
	}
	
	@Test
	public void PC_TC002_find_ByDateFailed() {
		try {
			Date ngaythu = new Date();
			List<PhieuChi> PhieuChi =  dao.find_ByDate(ngaythu);
			assertFalse(PhieuChi != null);
			testNgResult.put("3",
					new Object[] { "PC_TC002_find_ByDateFailed", "Find HD Phieu Chi By Date", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("3",
					new Object[] { "PC_TC002_find_ByDateFailed", "Find HD Phieu Chi By Date", "Find Fail", "Fail" });
		}
	}
	
	@Test
	public void PC_TC003_find_ByDateFailed() {
		try {
			List<PhieuChi> PhieuChi =  dao.find_ByDate(null);
			assertFalse(PhieuChi != null);
			testNgResult.put("12",
					new Object[] { "PC_TC003_find_ByDateFailed", "Find HD Phieu Chi By Date", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("12",
					new Object[] { "PC_TC003_find_ByDateFailed", "Find HD Phieu Chi By Date", "Find Fail", "Fail" });
		}
	}
	
	@Parameters({"NgayLapPC", "NguoiLap", "TienNhapThuoc", "TienTraHang"})
	@Test
	public void PC_TC004_insertSuccess(String NgayLapPC, String NguoiLap, float TienNhapThuoc, float TienTraHang) {
		try {
			PhieuChi phieuChi = new PhieuChi(Datehelper.toDate(NgayLapPC), 
					NguoiLap, TienNhapThuoc, TienTraHang, TienNhapThuoc+ TienTraHang);
			assertTrue(dao.Insert(phieuChi));
			testNgResult.put("4",
					new Object[] { "PC_TC004_insertSuccess", "Insert Phieu Chi", "Insert Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("4",
					new Object[] { "PC_TC004_insertSuccess", "Insert Phieu Chi", "Insert Success", "Fail" });
		}
	}
	
	@Parameters({"NgayLapPC", "TienNhapThuoc", "TienTraHang"})
	@Test
	public void PC_TC005_insertFailed(String NgayLapPC, float TienNhapThuoc, float TienTraHang) {
		try {
			PhieuChi phieuChi = new PhieuChi(Datehelper.toDate(NgayLapPC), "", TienNhapThuoc, TienTraHang, TienNhapThuoc+ TienTraHang);
			assertFalse(dao.Insert(phieuChi));
			testNgResult.put("5",
					new Object[] { "PC_TC005_insertFailed", "Insert Phieu Chi", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("5",
					new Object[] { "PC_TC005_insertFailed", "Insert Phieu Chi", "Insert Fail", "Fail" });
		}
	}
	
	@Parameters({"NgayLapPC","TienNhapThuoc", "TienTraHang"})
	@Test
	public void PC_TC004_insertFailed(String NgayLapPC,float TienNhapThuoc, float TienTraHang) {
		try {
			PhieuChi phieuChi = new PhieuChi(Datehelper.toDate(NgayLapPC), 
					null, TienNhapThuoc, TienTraHang, TienNhapThuoc+ TienTraHang);
			assertFalse(dao.Insert(phieuChi));
			testNgResult.put("6",
					new Object[] { "PC_TC004_insertFailed", "Insert Phieu Chi", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("6",
					new Object[] { "PC_TC004_insertFailed", "Insert Phieu Chi", "Insert Fail", "Fail" });
		}
	}
	
	@Parameters({"NgayLapPC","TienNhapThuoc", "TienTraHang"})
	@Test
	public void PC_TC006_insertFailed(String NgayLapPC, float TienNhapThuoc, float TienTraHang) {
		try {
			PhieuChi phieuChi = new PhieuChi(Datehelper.toDate(NgayLapPC), 
					"asd", TienNhapThuoc, TienTraHang, TienNhapThuoc+ TienTraHang);
			assertFalse(dao.Insert(phieuChi));
			testNgResult.put("7",
					new Object[] { "PC_TC006_insertFailed", "Insert Phieu Chi", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("7",
					new Object[] { "PC_TC006_insertFailed", "Insert Phieu Chi", "Insert Fail", "Fail" });
		}
	}
	
	@Parameters({"NgayLapPC", "NguoiLap", "TienTraHang"})
	@Test
	public void PC_TC007_insertFailed(String NgayLapPC, String NguoiLap,  float TienTraHang) {
		try {
			PhieuChi phieuChi = new PhieuChi(Datehelper.toDate(NgayLapPC), 
					NguoiLap, -1,TienTraHang , -1 + TienTraHang);
			assertFalse(dao.Insert(phieuChi));
			testNgResult.put("8",
					new Object[] { "PC_TC007_insertFailed", "Insert Phieu Chi", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("8",
					new Object[] { "PC_TC007_insertFailed", "Insert Phieu Chi", "Insert Fail", "Fail" });
		}
	}
	
	@Parameters({"NgayLapPC", "NguoiLap", "TienNhapThuoc"})
	@Test
	public void PC_TC008_insertFailed(String NgayLapPC, String NguoiLap, float TienNhapThuoc) {
		try {
			PhieuChi phieuChi = new PhieuChi(Datehelper.toDate(NgayLapPC), 
					NguoiLap, TienNhapThuoc, -1, TienNhapThuoc  -1);
			assertFalse(dao.Insert(phieuChi));
			testNgResult.put("9",
					new Object[] { "PC_TC008_insertFailed", "Insert Phieu Chi", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("9",
					new Object[] { "PC_TC008_insertFailed", "Insert Phieu Chi", "Insert Fail", "Fail" });
		}
	}
	@Test
	public void PC_TC009_selectallSuccess() {
		try {
			List<PhieuChi> list = dao.select();
			assertTrue(list != null);
			testNgResult.put("10",
					new Object[] { "PC_TC009_selectallSuccess", "Select All Phieu Chi", "Select All Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("10",
					new Object[] { "PC_TC009_selectallSuccess", "Select All Phieu Chi", "Select All Success", "Fail" });
		}
	}
	
	@Test
	public void PC_TC010_selectallFailed() {
		try {
			List<PhieuChi> list = dao.select();
			assertFalse(list == null);
			testNgResult.put("11",
					new Object[] { "PC_TC010_selectallFailed", "Select All Phieu Chi", "Select All Failed", "Pass" });
		} catch (Exception e) {
			testNgResult.put("11",
					new Object[] { "PC_TC010_selectallFailed", "Select All Phieu Chi", "Select All Failed", "Pass" });
		}
	}
	
	@AfterTest
	public void TearDown() {
		Set<String> keysSet = testNgResult.keySet();
		int rownum = 0;
		for (String key : keysSet) {
			Row row = sheet14.createRow(rownum++);
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
						new File("D:\\SOF304\\Assignment\\Assignment_KT\\excel\\TestPhieuChi.xls"));
				workbook.write(out);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
