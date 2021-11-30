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
import com.poly.dao.HoaDonTraHangDAO;
import com.poly.helper.Datehelper;
import com.poly.model.HoaDonTraHang;

public class TraHangTest {

	Connection connection;
	HoaDonTraHangDAO dao = new HoaDonTraHangDAO();
	Map<String, Object> testNgResult;
	HSSFWorkbook workbook;
	HSSFSheet sheet12;
	@BeforeTest
	public void connectDatabase() {
		try {
			connection = SQLConnect.getcon();
			workbook = new HSSFWorkbook();
			sheet12 = workbook.createSheet("Trả Hàng");
			testNgResult = new LinkedHashMap<String, Object>();
			testNgResult.put("1",
					new Object[] { "Testcase code", "Testing Purpose", "Expected Output", "Actual Output" });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Parameters("NgayLap_HD")
	@Test
	public void TH_TC001_find_ByDateSuccess(String NgayLap_HD) {
		try {
			List<HoaDonTraHang> list = dao.find_ByDate(NgayLap_HD);
			assertTrue(list != null);
			testNgResult.put("2",
					new Object[] { "TH_TC001_find_ByDateSuccess", "Find HD Tra Hang By Date", "Find Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("2",
					new Object[] { "TH_TC001_find_ByDateSuccess", "Find HD Tra Hang By Date", "Find Success", "Fail" });
		}
	}
	
	@Test
	public void TH_TC002_find_ByDateFailed() {
		try {
			List<HoaDonTraHang> list = dao.find_ByDate("");
			assertTrue(list == null);
			testNgResult.put("3",
					new Object[] { "TH_TC002_find_ByDateFailed", "Find HD Tra Hang By Date", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("3",
					new Object[] { "TH_TC002_find_ByDateFailed", "Find HD Tra Hang By Date", "Find Fail", "Fail" });
		}
	}
	
	@Test
	public void TH_TC005_find_ByDateFailed() {
		try {
			List<HoaDonTraHang> list = dao.find_ByDate("2021-11-26");
			assertTrue(list == null);
			testNgResult.put("4",
					new Object[] { "TH_TC005_find_ByDateFailed", "Find HD Tra Hang By Date", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("4",
					new Object[] { "TH_TC005_find_ByDateFailed", "Find HD Tra Hang By Date", "Find Fail", "Fail" });
		}
	}
	@Parameters({"ID_HDTH"})
	@Test
	public void TH_TC003_find_ByIDSuccess(int ID_HDTH) {
		try {
			HoaDonTraHang hdb = dao.find_ByID(ID_HDTH);
			assertTrue(hdb != null);
			testNgResult.put("5",
					new Object[] { "TH_TC003_find_ByIDSuccess", "Find HD Tra Hang By ID", "Find Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("5",
					new Object[] { "TH_TC003_find_ByIDSuccess", "Find HD Tra Hang By ID", "Find Success", "Fail" });
		}
	}
	
	@Test
	public void TH_TC004_find_ByIDFailed() {
		try {
			HoaDonTraHang pTH = dao.find_ByID(189723);
			assertFalse(pTH != null);
			testNgResult.put("6",
					new Object[] { "TH_TC004_find_ByIDFailed", "Find HD Tra Hang By ID", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("6",
					new Object[] { "TH_TC004_find_ByIDFailed", "Find HD Tra Hang By ID", "Find Fail", "Fail" });
		}
	}
	
	@Parameters({"ID_HDTH", "NgayLap_HD"})
	@Test
	public void TH_TC006_find_BytowSuccess(int ID_HDTH, String NgayLap_HD) {
		try {
			List<HoaDonTraHang> hdb = dao.find_Bytow(ID_HDTH, NgayLap_HD);
			assertTrue(hdb != null);
			testNgResult.put("7",
					new Object[] { "TH_TC004_find_ByIDFailed", "Find HD Tra Hang By ID & Date", "Find Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("7",
					new Object[] { "TH_TC004_find_ByIDFailed", "Find HD Tra Hang By ID & Date", "Find Success", "Fail" });
		}
	}
	
	@Parameters({ "NgayLap_HD"})
	@Test
	public void TH_TC007_find_BytowFailed( String NgayLap_HD) {
		try {
			List<HoaDonTraHang> hdb = dao.find_Bytow(928374, NgayLap_HD);
			assertFalse(hdb != null);
			testNgResult.put("8",
					new Object[] { "TH_TC007_find_BytowFailed", "Find HD Tra Hang By ID & Date", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("8",
					new Object[] { "TH_TC007_find_BytowFailed", "Find HD Tra Hang By ID & Date", "Find Fail", "Fail" });
		}
	}
	
	@Parameters({"NgayLap_HD"})
	@Test
	public void TH_TC008_find_BytowFailed( String NgayLap_HD) {
		try {
			List<HoaDonTraHang> hdb = dao.find_Bytow(-1, NgayLap_HD);
			assertFalse(hdb != null);
			testNgResult.put("9",
					new Object[] { "TH_TC008_find_BytowFailed", "Find HD Tra Hang By ID & Date", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("9",
					new Object[] { "TH_TC008_find_BytowFailed", "Find HD Tra Hang By ID & Date", "Find Fail", "Fail" });
		}
	}
	@Parameters({"ID_HDTH"})
	@Test
	public void TH_TC009_find_BytowFailed( int ID_HDTH) {
		try {
		List<HoaDonTraHang> hdb = dao.find_Bytow(ID_HDTH, "");
		assertFalse(hdb != null);
		testNgResult.put("10",
				new Object[] { "TH_TC009_find_BytowFailed", "Find HD Tra Hang By ID & Date", "Find Fail", "Pass" });
		}catch (Exception e) {
			testNgResult.put("10",
					new Object[] { "TH_TC009_find_BytowFailed", "Find HD Tra Hang By ID & Date", "Find Fail", "Fail" });
		}
	}
	
	@Parameters({ "ID_HDB", "TienCanTra", "NgayLap_HD", "NguoiTao", "MoTa"})
	@Test
	public void TH_TC019_insertSuccess( int ID_HDB,float TienCanTra, String NgayLap_HD
			, String NguoiTao, String MoTa) {
		try {
			HoaDonTraHang hdb = new HoaDonTraHang(ID_HDB, TienCanTra, Datehelper.toDate(NgayLap_HD), NguoiTao, MoTa);
			assertTrue(dao.Insert(hdb));
			testNgResult.put("11",
					new Object[] { "TH_TC019_insertSuccess", "Insert Hoa Don Tra", "Insert Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("11",
					new Object[] { "TH_TC019_insertSuccess", "Insert Hoa Don Tra", "Insert Success", "Fail" });
		}
	}
	
	@Parameters({ "TienCanTra", "NgayLap_HD", "NguoiTao", "MoTa"})
	@Test
	public void TH_TC010_insertFailed(float TienCanTra, String NgayLap_HD
			, String NguoiTao, String MoTa) {
		try {
			HoaDonTraHang hdb = new HoaDonTraHang(-1, TienCanTra, Datehelper.toDate(NgayLap_HD), NguoiTao, MoTa);
			assertFalse(dao.Insert(hdb));
			testNgResult.put("12",
					new Object[] { "TH_TC010_insertFailed", "Insert Hoa Don Tra", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("12",
					new Object[] { "TH_TC010_insertFailed", "Insert Hoa Don Tra", "Insert Fail", "Fail" });
		}
	}

	
	@Parameters({ "ID_HDB", "TienCanTra", "NgayLap_HD", "NguoiTao", "MoTa"})
	@Test
	public void TH_TC012_insertFailed( String ID_HDB,float TienCanTra, String NgayLap_HD
			, String NguoiTao, String MoTa) {
		try {
			HoaDonTraHang pTH = new HoaDonTraHang(123897, TienCanTra, Datehelper.toDate(NgayLap_HD), NguoiTao, MoTa);
			assertFalse(dao.Insert(pTH));
			testNgResult.put("14",
					new Object[] { "TH_TC012_insertFailed", "Insert Hoa Don Tra", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("14",
					new Object[] { "TH_TC012_insertFailed", "Insert Hoa Don Tra", "Insert Fail", "Fail" });
		}
	}
	
	@Parameters({ "ID_HDB","NgayLap_HD", "NguoiTao", "MoTa"})
	@Test
	public void TH_TC013_insertFailed(int ID_HDB, String NgayLap_HD
			, String NguoiTao, String MoTa) {
		try {
			HoaDonTraHang pTH = new HoaDonTraHang(ID_HDB, -1, Datehelper.toDate(NgayLap_HD), NguoiTao, MoTa);
			assertFalse(dao.Insert(pTH));
			testNgResult.put("15",
					new Object[] { "TH_TC013_insertFailed", "Insert Hoa Don Tra", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("15",
					new Object[] { "TH_TC013_insertFailed", "Insert Hoa Don Tra", "Insert Fail", "Fail" });
		}
	}
	
	@Parameters({ "ID_HDB", "TienCanTra", "NgayLap_HD", "MoTa"})
	@Test
	public void TH_TC014_insertFailed( int ID_HDB,float TienCanTra, String NgayLap_HD, String MoTa) {
		try {
			HoaDonTraHang pTH = new HoaDonTraHang(ID_HDB, TienCanTra, Datehelper.toDate(NgayLap_HD), "", MoTa);
			assertFalse(dao.Insert(pTH));
			testNgResult.put("16",
					new Object[] { "TH_TC014_insertFailed", "Insert Hoa Don Tra", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("16",
					new Object[] { "TH_TC014_insertFailed", "Insert Hoa Don Tra", "Insert Fail", "Fail" });		}
	}
	
	@Parameters({ "ID_HDB", "TienCanTra", "NgayLap_HD", "MoTa"})
	@Test
	public void TH_TC015_insertFailed( int ID_HDB,float TienCanTra, String NgayLap_HD, String MoTa) {
		try {
			HoaDonTraHang pTH = new HoaDonTraHang(ID_HDB, TienCanTra, Datehelper.toDate(NgayLap_HD), null, MoTa);
			assertFalse(dao.Insert(pTH));
			testNgResult.put("17",
					new Object[] { "TH_TC015_insertFailed", "Insert Hoa Don Tra", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("17",
					new Object[] { "TH_TC015_insertFailed", "Insert Hoa Don Tra", "Insert Fail", "Fail" });
		}
	}
	
	@Parameters({ "ID_HDB", "TienCanTra", "NgayLap_HD", "MoTa"})
	@Test
	public void TH_TC016_insertFailed( int ID_HDB,float TienCanTra, String NgayLap_HD, String MoTa) {
		try {
			HoaDonTraHang pTH = new HoaDonTraHang(ID_HDB, TienCanTra, Datehelper.toDate(NgayLap_HD), "asd",MoTa);
			assertFalse(dao.Insert(pTH));
			testNgResult.put("18",
					new Object[] { "TH_TC016_insertFailed", "Insert Hoa Don Tra", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("18",
					new Object[] { "TH_TC016_insertFailed", "Insert Hoa Don Tra", "Insert Fail", "Fail" });
		}
	}
	@Test
	public void TH_TC017_selectallSuccess() {
		try {
			List<HoaDonTraHang> list  = dao.selectall();
			assertTrue(list != null);
			testNgResult.put("19",
					new Object[] { "TH_TC017_selectallSuccess", "Select All Hoa Don Tra", "Select All Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("19",
					new Object[] { "TH_TC017_selectallSuccess", "Select All Hoa Don Tra", "Select All Success", "Fail" });
		}
	}
	
	@Test
	public void TH_TC018_selectallFailed() {
		try {
			List<HoaDonTraHang> list  = dao.selectall();
			assertFalse(list == null);
			testNgResult.put("20",
					new Object[] { "TH_TC018_selectallFailed", "Select All Hoa Don Tra", "Select All Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("20",
					new Object[] { "TH_TC018_selectallFailed", "Select All Hoa Don Tra", "Select All Fail", "Fail" });
		}
	}
	
	@AfterTest
	public void TearDown() {
		Set<String> keysSet = testNgResult.keySet();
		int rownum = 0;
		for (String key : keysSet) {
			Row row = sheet12.createRow(rownum++);
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
						new File("D:\\SOF304\\Assignment\\Assignment_KT\\excel\\TestTraHang.xls"));
				workbook.write(out);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
