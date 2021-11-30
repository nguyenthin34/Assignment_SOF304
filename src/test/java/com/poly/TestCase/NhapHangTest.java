package com.poly.TestCase;

import org.testng.annotations.Test;

import com.poly.Jdbc.SQLConnect;
import com.poly.dao.NguoiDungDAO;
import com.poly.dao.PhieuNhapHangDAO;
import com.poly.helper.Datehelper;
import com.poly.model.PhieuNhapHang;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

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

public class NhapHangTest {
	Connection connection;
	PhieuNhapHangDAO dao = new PhieuNhapHangDAO();
	Map<String, Object> testNgResult;
	HSSFWorkbook workbook;
	HSSFSheet sheet11;
	@BeforeTest
	public void connectDatabase() {
		try {
			connection = SQLConnect.getcon();
			workbook = new HSSFWorkbook();
			sheet11 = workbook.createSheet("Nhập Hàng");
			testNgResult = new LinkedHashMap<String, Object>();
			testNgResult.put("1",
					new Object[] { "Testcase code", "Testing Purpose", "Expected Output", "Actual Output" });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Parameters("NgayLap_HD")
	@Test
	public void NH_TC001_find_ByDateSuccess(String NgayLap_HD) {
		try {
			List<PhieuNhapHang> list = dao.find_ByDate(NgayLap_HD);
			assertTrue(list != null);
			testNgResult.put("2",
					new Object[] { "NH_TC001_find_ByDateSuccess", "Find HD Nhap Hang By Date", "Find Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("2",
					new Object[] { "NH_TC001_find_ByDateSuccess", "Find HD Nhap Hang By Date", "Find Success", "Fail" });
		}
	}
	
	@Test
	public void NH_TC002_find_ByDateFailed() {
		try {
			List<PhieuNhapHang> list = dao.find_ByDate("");
			assertTrue(list == null);
			testNgResult.put("3",
					new Object[] { "NH_TC002_find_ByDateFailed", "Find HD Nhap Hang By Date", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("3",
					new Object[] { "NH_TC002_find_ByDateFailed", "Find HD Nhap Hang By Date", "Find Fail", "Fail" });
		}
	}
	
	@Test
	public void NH_TC005_find_ByDateFailed() {
		try {
			List<PhieuNhapHang> list = dao.find_ByDate("2021-11-26");
			assertTrue(list == null);
			testNgResult.put("4",
					new Object[] { "NH_TC005_find_ByDateFailed", "Find HD Nhap Hang By Date", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("4",
					new Object[] { "NH_TC005_find_ByDateFailed", "Find HD Nhap Hang By Date", "Find Fail", "Fail" });
		}
	}
	@Parameters({"ID_HDN"})
	@Test
	public void NH_TC003_find_ByIDSuccess(int ID_HDN) {
		try {
			PhieuNhapHang pnh = dao.find_ByID(ID_HDN);
			assertTrue(pnh != null);
			testNgResult.put("5",
					new Object[] { "NH_TC003_find_ByIDSuccess", "Find HD Nhap Hang By ID", "Find Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("5",
					new Object[] { "NH_TC003_find_ByIDSuccess", "Find HD Nhap Hang By ID", "Find Success", "Fail" });
		}
	}
	
	@Test
	public void NH_TC004_find_ByIDFailed() {
		try {
			PhieuNhapHang pnh = dao.find_ByID(189723);
			assertFalse(pnh != null);
			testNgResult.put("6",
					new Object[] { "NH_TC004_find_ByIDFailed", "Find HD Nhap Hang By ID", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("6",
					new Object[] { "NH_TC004_find_ByIDFailed", "Find HD Nhap Hang By ID", "Find Fail", "Fail" });
		}
	}
	
	@Parameters({"ID_HDN", "NgayLap_HD"})
	@Test
	public void NH_TC006_find_BytowSuccess(int ID_HDN, String NgayLap_HD) {
		try {
			List<PhieuNhapHang> pnh = dao.find_Bytow(ID_HDN, NgayLap_HD);
			assertTrue(pnh != null);
			testNgResult.put("7",
					new Object[] { "NH_TC004_find_ByIDFailed", "Find HD Nhap Hang By ID & Date", "Find Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("7",
					new Object[] { "NH_TC004_find_ByIDFailed", "Find HD Nhap Hang By ID & Date", "Find Success", "Fail" });
		}
	}
	
	@Parameters({ "NgayLap_HD"})
	@Test
	public void NH_TC007_find_BytowFailed( String NgayLap_HD) {
		try {
			List<PhieuNhapHang> pnh = dao.find_Bytow(928374, NgayLap_HD);
			assertFalse(pnh != null);
			testNgResult.put("8",
					new Object[] { "NH_TC007_find_BytowFailed", "Find HD Nhap Hang By ID & Date", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("8",
					new Object[] { "NH_TC007_find_BytowFailed", "Find HD Nhap Hang By ID & Date", "Find Fail", "Fail" });
		}
	}
	
	@Parameters({"NgayLap_HD"})
	@Test
	public void NH_TC008_find_BytowFailed( String NgayLap_HD) {
		try {
			List<PhieuNhapHang> pnh = dao.find_Bytow(-1, NgayLap_HD);
			assertFalse(pnh != null);
			testNgResult.put("9",
					new Object[] { "NH_TC008_find_BytowFailed", "Find HD Nhap Hang By ID & Date", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("9",
					new Object[] { "NH_TC008_find_BytowFailed", "Find HD Nhap Hang By ID & Date", "Find Fail", "Fail" });
		}
	}
	@Parameters({"ID_HDN"})
	@Test
	public void NH_TC009_find_BytowFailed( int ID_HDN) {
		try {
			List<PhieuNhapHang> pnh = dao.find_Bytow(ID_HDN, "");
			assertFalse(pnh != null);
			testNgResult.put("10",
					new Object[] { "NH_TC009_find_BytowFailed", "Find HD Nhap Hang By ID & Date", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("10",
					new Object[] { "NH_TC009_find_BytowFailed", "Find HD Nhap Hang By ID & Date", "Find Fail", "Fail" });
		}
	}
	
	@Parameters({ "ID_NhaCC", "TongTienHang", "NgayLap_HD", "NguoiTao"})
	@Test
	public void NH_TC019_insertSuccess( String ID_NhaCC,float TongTienHang, String NgayLap_HD
			, String NguoiTao) {
		try {
			PhieuNhapHang pnh = new PhieuNhapHang(ID_NhaCC, TongTienHang, Datehelper.toDate(NgayLap_HD), NguoiTao);
			assertTrue(dao.Insert(pnh));
			testNgResult.put("11",
					new Object[] { "NH_TC019_insertSuccess", "Insert Hoa Don Nhap", "Insert Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("11",
					new Object[] { "NH_TC019_insertSuccess", "Insert Hoa Don Nhap", "Insert Success", "Fail" });
		}
	}
	
	@Parameters({ "TongTienHang", "NgayLap_HD", "NguoiTao"})
	@Test
	public void NH_TC010_insertFailed(float TongTienHang, String NgayLap_HD
			, String NguoiTao) {
		try {
			PhieuNhapHang pnh = new PhieuNhapHang("", TongTienHang, Datehelper.toDate(NgayLap_HD), NguoiTao);
			assertFalse(dao.Insert(pnh));
			testNgResult.put("12",
					new Object[] { "NH_TC010_insertFailed", "Insert Hoa Don Nhap", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("12",
			new Object[] { "NH_TC010_insertFailed", "Insert Hoa Don Nhap", "Insert Fail", "Fail" });
		}
	}
	@Parameters({ "TongTienHang", "NgayLap_HD", "NguoiTao"})
	@Test
	public void NH_TC011_insertFailed(float TongTienHang, String NgayLap_HD
			, String NguoiTao) {
		try {
			PhieuNhapHang pnh = new PhieuNhapHang(null, TongTienHang, Datehelper.toDate(NgayLap_HD), NguoiTao);
			assertFalse(dao.Insert(pnh));
			testNgResult.put("13",
					new Object[] { "NH_TC011_insertFailed", "Insert Hoa Don Nhap", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("13",
					new Object[] { "NH_TC011_insertFailed", "Insert Hoa Don Nhap", "Insert Fail", "Fail" });
		}
	}
	
	@Parameters({ "ID_NhaCC", "TongTienHang", "NgayLap_HD", "NguoiTao"})
	@Test
	public void NH_TC012_insertFailed( String ID_NhaCC,float TongTienHang, String NgayLap_HD
			, String NguoiTao) {
		try {
			PhieuNhapHang pnh = new PhieuNhapHang("NCnn", TongTienHang, Datehelper.toDate(NgayLap_HD), NguoiTao);
			assertFalse(dao.Insert(pnh));
			testNgResult.put("14",
					new Object[] { "NH_TC012_insertFailed", "Insert Hoa Don Nhap", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("14",
					new Object[] { "NH_TC012_insertFailed", "Insert Hoa Don Nhap", "Insert Fail", "Fail" });
		}
	}
	
	@Parameters({ "ID_NhaCC","NgayLap_HD", "NguoiTao"})
	@Test
	public void NH_TC013_insertFailed( String ID_NhaCC, String NgayLap_HD
			, String NguoiTao) {
		try {
			PhieuNhapHang pnh = new PhieuNhapHang(ID_NhaCC, -1, Datehelper.toDate(NgayLap_HD), NguoiTao);
			assertFalse(dao.Insert(pnh));
			testNgResult.put("15",
					new Object[] { "NH_TC013_insertFailed", "Insert Hoa Don Nhap", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("15",
					new Object[] { "NH_TC013_insertFailed", "Insert Hoa Don Nhap", "Insert Fail", "Fail" });
		}
	}
	
	@Parameters({ "ID_NhaCC", "TongTienHang", "NgayLap_HD"})
	@Test
	public void NH_TC014_insertFailed( String ID_NhaCC,float TongTienHang, String NgayLap_HD) {
		try {
			PhieuNhapHang pnh = new PhieuNhapHang(ID_NhaCC, TongTienHang, Datehelper.toDate(NgayLap_HD), "");
			assertFalse(dao.Insert(pnh));
			testNgResult.put("16",
					new Object[] { "NH_TC014_insertFailed", "Insert Hoa Don Nhap", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("16",
					new Object[] { "NH_TC014_insertFailed", "Insert Hoa Don Nhap", "Insert Fail", "Fail" });
		}
	}
	
	@Parameters({ "ID_NhaCC", "TongTienHang", "NgayLap_HD"})
	@Test
	public void NH_TC015_insertFailed( String ID_NhaCC,float TongTienHang, String NgayLap_HD) {
		try {
			PhieuNhapHang pnh = new PhieuNhapHang(ID_NhaCC, TongTienHang, Datehelper.toDate(NgayLap_HD), null);
			assertFalse(dao.Insert(pnh));
			testNgResult.put("17",
					new Object[] { "NH_TC015_insertFailed", "Insert Hoa Don Nhap", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("17",
					new Object[] { "NH_TC015_insertFailed", "Insert Hoa Don Nhap", "Insert Fail", "Fail" });
		}
	}
	
	@Parameters({ "ID_NhaCC", "TongTienHang", "NgayLap_HD"})
	@Test
	public void NH_TC016_insertFailed( String ID_NhaCC,float TongTienHang, String NgayLap_HD) {
		try {
			PhieuNhapHang pnh = new PhieuNhapHang(ID_NhaCC, TongTienHang, Datehelper.toDate(NgayLap_HD), "asd");
			assertFalse(dao.Insert(pnh));
			testNgResult.put("18",
					new Object[] { "NH_TC016_insertFailed", "Insert Hoa Don Nhap", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("18",
					new Object[] { "NH_TC016_insertFailed", "Insert Hoa Don Nhap", "Insert Fail", "Fail" });
		}
	}
	@Test
	public void NH_TC017_selectallSuccess() {
		try {
			List<PhieuNhapHang> list  = dao.select_ds();
			assertTrue(list != null);
			testNgResult.put("19",
					new Object[] { "NH_TC017_selectallSuccess", "Select All Hoa Don Nhap", "Select All Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("19",
					new Object[] { "NH_TC017_selectallSuccess", "Select All Hoa Don Nhap", "Select All Success", "Fail" });
		}
	}
	
	@Test
	public void NH_TC018_selectallFailed() {
		try {
			List<PhieuNhapHang> list  = dao.select_ds();
			assertFalse(list == null);
			testNgResult.put("20",
					new Object[] { "NH_TC018_selectallFailed", "Select All Hoa Don Nhap", "Select All Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("20",
					new Object[] { "NH_TC018_selectallFailed", "Select All Hoa Don Nhap", "Select All Fail", "Fail" });
		}
	}
	
	
	@AfterTest
	public void TearDown() {
		Set<String> keysSet = testNgResult.keySet();
		int rownum = 0;
		for (String key : keysSet) {
			Row row = sheet11.createRow(rownum++);
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
						new File("D:\\SOF304\\Assignment\\Assignment_KT\\excel\\TestNhapHang.xls"));
				workbook.write(out);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
