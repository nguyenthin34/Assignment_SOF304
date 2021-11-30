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
import com.poly.dao.HoaDonBanHangDAO;
import com.poly.helper.Datehelper;
import com.poly.model.HoaDonBan;

public class BanHangTest {
	Connection connection;
	HoaDonBanHangDAO dao = new HoaDonBanHangDAO();
	Map<String, Object> testNgResult;
	HSSFWorkbook workbook;
	HSSFSheet sheet10;
	@BeforeTest
	public void connectDatabase() {
		try {
			connection = SQLConnect.getcon();
			workbook = new HSSFWorkbook();
			sheet10 = workbook.createSheet("Đơn Vị Tính");
			testNgResult = new LinkedHashMap<String, Object>();
			testNgResult.put("1",
					new Object[] { "Testcase code", "Testing Purpose", "Expected Output", "Actual Output" });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Parameters("NgayLap_HD")
	@Test
	public void BH_TC001_find_ByDateSuccess(String NgayLap_HD) {
		try {
			List<HoaDonBan> list = dao.find_ByDate(NgayLap_HD);
			assertTrue(list != null);
			testNgResult.put("2",
					new Object[] { "BH_TC001_find_ByDateSuccess", "Find HD Ban Hang By Date", "Find Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("2",
					new Object[] { "BH_TC001_find_ByDateSuccess", "Find HD Ban Hang By Date", "Find Success", "Fail" });
		}
	}
	
	@Test
	public void BH_TC002_find_ByDateFailed() {
		try {
			List<HoaDonBan> list = dao.find_ByDate("");
			assertTrue(list == null);
			testNgResult.put("3",
					new Object[] { "BH_TC002_find_ByDateFailed", "Find HD Ban Hang By Date", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("3",
					new Object[] { "BH_TC002_find_ByDateFailed", "Find HD Ban Hang By Date", "Find Fail", "Fail" });
		}
	}
	
	@Test
	public void BH_TC005_find_ByDateFailed() {
		try {
			List<HoaDonBan> list = dao.find_ByDate("2021-11-26");
			assertTrue(list == null);
			testNgResult.put("4",
					new Object[] { "BH_TC005_find_ByDateFailed", "Find HD Ban Hang By Date", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("4",
					new Object[] { "BH_TC005_find_ByDateFailed", "Find HD Ban Hang By Date", "Find Fail", "Fail" });
		}
	}
	@Parameters({"ID_HDB"})
	@Test
	public void BH_TC003_find_ByIDSuccess(int ID_HDB) {
		try {
			HoaDonBan hdb = dao.find_ByID(ID_HDB);
			assertTrue(hdb != null);
			testNgResult.put("5",
					new Object[] { "BH_TC003_find_ByIDSuccess", "Find HD Ban Hang By ID", "Find Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("5",
					new Object[] { "BH_TC003_find_ByIDSuccess", "Find HD Ban Hang By ID", "Find Success", "Fail" });
		}
	}
	
	@Test
	public void BH_TC004_find_ByIDFailed() {
		try {
			HoaDonBan pBH = dao.find_ByID(189723);
			assertFalse(pBH != null);
			testNgResult.put("6",
					new Object[] { "BH_TC004_find_ByIDFailed", "Find HD Ban Hang By ID", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("6",
					new Object[] { "BH_TC004_find_ByIDFailed", "Find HD Ban Hang By ID", "Find Fail", "Fail" });
		}
	}
	
	@Parameters({"ID_HDB", "NgayLap_HD"})
	@Test
	public void BH_TC006_find_BytowSuccess(int ID_HDB, String NgayLap_HD) {
		try {
			List<HoaDonBan> hdb = dao.find_Bytow(ID_HDB, NgayLap_HD);
			assertTrue(hdb != null);
			testNgResult.put("7",
					new Object[] { "BH_TC004_find_ByIDFailed", "Find HD Ban Hang By ID & Date", "Find Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("7",
					new Object[] { "BH_TC004_find_ByIDFailed", "Find HD Ban Hang By ID & Date", "Find Success", "Fail" });
		}
	}
	
	@Parameters({ "NgayLap_HD"})
	@Test
	public void BH_TC007_find_BytowFailed( String NgayLap_HD) {
		try {
			List<HoaDonBan> hdb = dao.find_Bytow(928374, NgayLap_HD);
			assertFalse(hdb != null);
			testNgResult.put("8",
					new Object[] { "BH_TC007_find_BytowFailed", "Find HD Ban Hang By ID & Date", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("8",
					new Object[] { "BH_TC007_find_BytowFailed", "Find HD Ban Hang By ID & Date", "Find Fail", "Fail" });
		}
	}
	
	@Parameters({"NgayLap_HD"})
	@Test
	public void BH_TC008_find_BytowFailed( String NgayLap_HD) {
		try {
			List<HoaDonBan> hdb = dao.find_Bytow(-1, NgayLap_HD);
			assertFalse(hdb != null);
			testNgResult.put("9",
					new Object[] { "BH_TC008_find_BytowFailed", "Find HD Ban Hang By ID & Date", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("9",
					new Object[] { "BH_TC008_find_BytowFailed", "Find HD Ban Hang By ID & Date", "Find Fail", "Fail" });
		}
	}
	@Parameters({"ID_HDB"})
	@Test
	public void BH_TC009_find_BytowFailed( int ID_HDB) {
		try {
			List<HoaDonBan> hdb = dao.find_Bytow(ID_HDB, "");
			assertFalse(hdb != null);
			testNgResult.put("10",
					new Object[] { "BH_TC009_find_BytowFailed", "Find HD Ban Hang By ID & Date", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("10",
					new Object[] { "BH_TC009_find_BytowFailed", "Find HD Ban Hang By ID & Date", "Find Fail", "Fail" });
		}
	}
	
	@Parameters({ "SDT_KH", "TongTien", "NgayLap_HD", "NguoiTao"})
	@Test
	public void BH_TC019_insertSuccess( String SDT_KH,float TongTien, String NgayLap_HD
			, String NguoiTao) {
		try {
			HoaDonBan hdb = new HoaDonBan(SDT_KH, TongTien, Datehelper.toDate(NgayLap_HD), NguoiTao);
			assertTrue(dao.Insert(hdb));
			testNgResult.put("11",
					new Object[] { "BH_TC019_insertSuccess", "Insert Hoa Don Ban", "Insert Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("11",
					new Object[] { "BH_TC019_insertSuccess", "Insert Hoa Don Ban", "Insert Success", "Fail" });
		}
	}
	
	@Parameters({ "TongTien", "NgayLap_HD", "NguoiTao"})
	@Test
	public void BH_TC010_insertFailed(float TongTien, String NgayLap_HD
			, String NguoiTao) {
		try {
			HoaDonBan hdb = new HoaDonBan("", TongTien, Datehelper.toDate(NgayLap_HD), NguoiTao);
			assertFalse(dao.Insert(hdb));
			testNgResult.put("12",
					new Object[] { "BH_TC010_insertFailed", "Insert Hoa Don Ban", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("12",
					new Object[] { "BH_TC010_insertFailed", "Insert Hoa Don Ban", "Insert Fail", "Fail" });
		}
	}
	@Parameters({ "TongTien", "NgayLap_HD", "NguoiTao"})
	@Test
	public void BH_TC011_insertFailed(float TongTien, String NgayLap_HD
			, String NguoiTao) {
		try {
			HoaDonBan hdb = new HoaDonBan(null, TongTien, Datehelper.toDate(NgayLap_HD), NguoiTao);
			assertFalse(dao.Insert(hdb));
			testNgResult.put("13",
					new Object[] { "BH_TC011_insertFailed", "Insert Hoa Don Ban", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("13",
					new Object[] { "BH_TC011_insertFailed", "Insert Hoa Don Ban", "Insert Fail", "Fail" });
		}
	}
	
	@Parameters({ "SDT_KH", "TongTien", "NgayLap_HD", "NguoiTao"})
	@Test
	public void BH_TC012_insertFailed( String SDT_KH,float TongTien, String NgayLap_HD
			, String NguoiTao) {
		try {
			HoaDonBan pBH = new HoaDonBan("NCnn", TongTien, Datehelper.toDate(NgayLap_HD), NguoiTao);
			assertFalse(dao.Insert(pBH));
			testNgResult.put("14",
					new Object[] { "BH_TC012_insertFailed", "Insert Hoa Don Ban", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("14",
					new Object[] { "BH_TC012_insertFailed", "Insert Hoa Don Ban", "Insert Fail", "Fail" });
		}
	}
	
	@Parameters({ "SDT_KH","NgayLap_HD", "NguoiTao"})
	@Test
	public void BH_TC013_insertFailed( String SDT_KH, String NgayLap_HD
			, String NguoiTao) {
		try {
			HoaDonBan pBH = new HoaDonBan(SDT_KH, -1, Datehelper.toDate(NgayLap_HD), NguoiTao);
			assertFalse(dao.Insert(pBH));
			testNgResult.put("15",
					new Object[] { "BH_TC013_insertFailed", "Insert Hoa Don Ban", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("15",
					new Object[] { "BH_TC013_insertFailed", "Insert Hoa Don Ban", "Insert Fail", "Fail" });
		}
	}
	
	@Parameters({ "SDT_KH", "TongTien", "NgayLap_HD"})
	@Test
	public void BH_TC014_insertFailed( String SDT_KH,float TongTien, String NgayLap_HD) {
		try {
			HoaDonBan pBH = new HoaDonBan(SDT_KH, TongTien, Datehelper.toDate(NgayLap_HD), "");
			assertFalse(dao.Insert(pBH));
			testNgResult.put("16",
					new Object[] { "BH_TC014_insertFailed", "Insert Hoa Don Ban", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("16",
					new Object[] { "BH_TC014_insertFailed", "Insert Hoa Don Ban", "Insert Fail", "Fail" });
		}
	}
	
	@Parameters({ "SDT_KH", "TongTien", "NgayLap_HD"})
	@Test
	public void BH_TC015_insertFailed( String SDT_KH,float TongTien, String NgayLap_HD) {
		try {
			HoaDonBan pBH = new HoaDonBan(SDT_KH, TongTien, Datehelper.toDate(NgayLap_HD), null);
			assertFalse(dao.Insert(pBH));
			testNgResult.put("17",
					new Object[] { "BH_TC015_insertFailed", "Insert Hoa Don Ban", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("17",
					new Object[] { "BH_TC015_insertFailed", "Insert Hoa Don Ban", "Insert Fail", "Fail" });
		}
	}
	
	@Parameters({ "SDT_KH", "TongTien", "NgayLap_HD"})
	@Test
	public void BH_TC016_insertFailed( String SDT_KH,float TongTien, String NgayLap_HD) {
		try {
			HoaDonBan pBH = new HoaDonBan(SDT_KH, TongTien, Datehelper.toDate(NgayLap_HD), "asd");
			assertFalse(dao.Insert(pBH));
			testNgResult.put("18",
					new Object[] { "BH_TC016_insertFailed", "Insert Hoa Don Ban", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("18",
					new Object[] { "BH_TC016_insertFailed", "Insert Hoa Don Ban", "Insert Fail", "Fail" });
		}
	}
	@Test
	public void BH_TC017_selectallSuccess() {
		try {
			List<HoaDonBan> list  = dao.select_nam();
			assertTrue(list != null);
			testNgResult.put("19",
					new Object[] { "BH_TC017_selectallSuccess", "Select All Hoa Don Ban", "Select All Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("19",
					new Object[] { "BH_TC017_selectallSuccess", "Select All Hoa Don Ban", "Select All Success", "Fail" });
		}
	}
	
	@Test
	public void BH_TC018_selectallFailed() {
		try {
			List<HoaDonBan> list  = dao.select_nam();
			assertFalse(list == null);
			testNgResult.put("20",
					new Object[] { "BH_TC018_selectallFailed", "Select All Hoa Don Ban", "Select All Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("20",
					new Object[] { "BH_TC018_selectallFailed", "Select All Hoa Don Ban", "Select All Fail", "Fail" });
		}
	}
	
	@AfterTest
	public void TearDown() {
		Set<String> keysSet = testNgResult.keySet();
		int rownum = 0;
		for (String key : keysSet) {
			Row row = sheet10.createRow(rownum++);
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
						new File("D:\\SOF304\\Assignment\\Assignment_KT\\excel\\TestBanHang.xls"));
				workbook.write(out);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
