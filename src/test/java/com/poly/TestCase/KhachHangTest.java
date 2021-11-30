package com.poly.TestCase;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import com.poly.dao.KhachHangDAO;
import com.poly.dao.NguoiDungDAO;
import com.poly.model.KhachHang;

public class KhachHangTest {

	Connection connection;
	KhachHangDAO dao = new KhachHangDAO();
	Map<String, Object> testNgResult;
	HSSFWorkbook workbook;
	HSSFSheet sheet8;

	@BeforeTest
	public void connectDatabase() {
		try {
			connection = SQLConnect.getcon();
			workbook = new HSSFWorkbook();
			sheet8 = workbook.createSheet("Đơn Vị Tính");
			testNgResult = new LinkedHashMap<String, Object>();
			testNgResult.put("1",
					new Object[] { "Testcase code", "Testing Purpose", "Expected Output", "Actual Output" });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Parameters("SDT_kh")
	@Test
	public void KH_TC001_findByIDSuccess(String SDT_KH) {
		try {
			KhachHang khachHang = dao.findByID(SDT_KH);
			assertTrue(khachHang != null);
			testNgResult.put("2",
					new Object[] { "ND_TC001_findbyIDSuccess", "Find KhachHang", "Find Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("2",
					new Object[] { "ND_TC001_findbyIDSuccess", "Find KhachHang", "Find Success", "Fail" });
		}
	}

	@Test
	public void KH_TC002_findByIDFailed() {
		try {
			KhachHang khachHang = dao.findByID("");
			assertFalse(khachHang != null);
			testNgResult.put("3",
					new Object[] { "KH_TC002_findByIDFailed", "Find KhachHang", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("3",
					new Object[] { "KH_TC002_findByIDFailed", "Find KhachHang", "Find Fail", "Fail" });
		}
	}

	@Test
	public void KH_TC018_findByIDFailed() {
		try {
			KhachHang khachHang = dao.findByID(null);
			assertFalse(khachHang != null);
			testNgResult.put("4",
					new Object[] { "KH_TC018_findByIDFailed", "Find KhachHang", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("4",
					new Object[] { "KH_TC018_findByIDFailed", "Find KhachHang", "Find Fail", "Fail" });
		}
	}

	@Test
	public void KH_TC003_findByIDFailed() {
		try {
			KhachHang khachHang = dao.findByID("a1323");
			assertFalse(khachHang != null);
			testNgResult.put("5",
					new Object[] { "KH_TC003_findByIDFailed", "Find KhachHang", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("5",
					new Object[] { "KH_TC003_findByIDFailed", "Find KhachHang", "Find Fail", "Fail" });
		}
	}

	@Parameters({ "SDT_KH", "Ten_KH", "Email_KH", "DiaChi" })
	@Test
	public void KH_TC004_insertSuccess(String SDT_KH, String Ten_KH, String Email_KH, String DiaChi) {
		try {
			KhachHang khachHang = new KhachHang(SDT_KH, Ten_KH, Email_KH, DiaChi);
			assertTrue(dao.Insert(khachHang));
			testNgResult.put("6",
					new Object[] { "KH_TC004_insertSuccess", "Insert KhachHang", "Insert Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("6",
					new Object[] { "KH_TC004_insertSuccess", "Insert KhachHang", "Insert Success", "Fail" });
		}
	}

	@Parameters({ "Ten_KH", "Email_KH", "DiaChi" })
	@Test
	public void KH_TC005_insertFailed(String Ten_KH, String Email_KH, String DiaChi) {
		try {
			KhachHang khachHang = new KhachHang("12334234", Ten_KH, Email_KH, DiaChi);
			assertFalse(dao.Insert(khachHang));
			testNgResult.put("7",
					new Object[] { "KH_TC005_insertFailed", "Insert KhachHang", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("7",
					new Object[] { "KH_TC005_insertFailed", "Insert KhachHang", "Insert Fail", "Fail" });
		}
	}

	@Parameters({ "Ten_KH", "Email_KH", "DiaChi" })
	@Test
	public void KH_TC006_insertFailed(String Ten_KH, String Email_KH, String DiaChi) {
		try {
			KhachHang khachHang = new KhachHang("", Ten_KH, Email_KH, DiaChi);
			assertFalse(dao.Insert(khachHang));
			testNgResult.put("8",
					new Object[] { "KH_TC005_insertFailed", "Insert KhachHang", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("8",
					new Object[] { "KH_TC005_insertFailed", "Insert KhachHang", "Insert Fail", "Fail" });
		}
	}

	@Parameters({ "Ten_KH", "Email_KH", "DiaChi" })
	@Test
	public void KH_TC007_insertFailed(String Ten_KH, String Email_KH, String DiaChi) {
		try {
			KhachHang khachHang = new KhachHang(null, Ten_KH, Email_KH, DiaChi);
			assertFalse(dao.Insert(khachHang));
			testNgResult.put("9",
					new Object[] { "KH_TC007_insertFailed", "Insert KhachHang", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("9",
					new Object[] { "KH_TC007_insertFailed", "Insert KhachHang", "Insert Fail", "Fail" });
		}
	}

	@Test
	public void KH_TC014_insertFailed() {
		try {
			KhachHang khachHang = new KhachHang();
			assertFalse(dao.Insert(khachHang));
			testNgResult.put("10",
					new Object[] { "KH_TC014_insertFailed", "Insert KhachHang", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("10",
					new Object[] { "KH_TC014_insertFailed", "Insert KhachHang", "Insert Fail", "Fail" });
		}
	}

	@Test
	public void KH_TC015_insertFailed() {
		try {
			KhachHang khachHang = null;
			assertFalse(dao.Insert(khachHang));
			testNgResult.put("11",
					new Object[] { "KH_TC015_insertFailed", "Insert KhachHang", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("11",
					new Object[] { "KH_TC015_insertFailed", "Insert KhachHang", "Insert Fail", "Fail" });
		}
	}

	@Test
	public void KH_TC008_selectallSuccess() {
		try {
			List<KhachHang> list = dao.select();
			assertTrue(list != null);
			testNgResult.put("12",
					new Object[] { "KH_TC008_selectallSuccess", "Select All KhachHang", "Select All Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("12",
					new Object[] { "KH_TC008_selectallSuccess", "Select All KhachHang", "Select All Success", "Fail" });
		}
	}

	@Test
	public void KH_TC009_selectallFailed() {
		try {
			List<KhachHang> list = dao.select();
			assertFalse(list == null);
			testNgResult.put("13",
					new Object[] { "KH_TC009_selectallFailed", "Select All KhachHang", "Select All Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("13",
					new Object[] { "KH_TC009_selectallFailed", "Select All KhachHang", "Select All Fail", "Fail" });
		}
	}

	@Parameters({ "SDT_KH", "Ten_KH", "Email_KH", "DiaChi" })
	@Test
	public void KH_TC010_updateSuccess(String SDT_KH, String Ten_KH, String Email_KH, String DiaChi) {
		try {
			KhachHang khachHang = new KhachHang(SDT_KH, Ten_KH, Email_KH, DiaChi);
			assertTrue(dao.Update(khachHang));
			testNgResult.put("14",
					new Object[] { "KH_TC010_updateSuccess", "Update KhachHang", "Update Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("14",
					new Object[] { "KH_TC010_updateSuccess", "Update KhachHang", "Update Success", "Fail" });
		}
	}

	@Parameters({ "Ten_KH", "Email_KH", "DiaChi" })
	@Test
	public void KH_TC011_updateFailed(String Ten_KH, String Email_KH, String DiaChi) {
		try {
			KhachHang khachHang = new KhachHang(null, Ten_KH, Email_KH, DiaChi);
			assertFalse(dao.Update(khachHang));
			testNgResult.put("15",
					new Object[] { "KH_TC011_updateFailed", "Update KhachHang", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("15",
					new Object[] { "KH_TC011_updateFailed", "Update KhachHang", "Update Fail", "Fail" });
		}
	}

	@Parameters({ "Ten_KH", "Email_KH", "DiaChi" })
	@Test
	public void KH_TC012_updateFailed(String Ten_KH, String Email_KH, String DiaChi) {
		try {
			KhachHang khachHang = new KhachHang("", Ten_KH, Email_KH, DiaChi);
			assertFalse(dao.Update(khachHang));
			testNgResult.put("16",
					new Object[] { "KH_TC012_updateFailed", "Update KhachHang", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("16",
					new Object[] { "KH_TC012_updateFailed", "Update KhachHang", "Update Fail", "Fail" });
		}
	}

	@Parameters({ "Ten_KH", "Email_KH", "DiaChi" })
	@Test
	public void KH_TC013_updateFailed(String Ten_KH, String Email_KH, String DiaChi) {
		try {
			KhachHang khachHang = new KhachHang("aaaA", Ten_KH, Email_KH, DiaChi);
			assertFalse(dao.Update(khachHang));
			testNgResult.put("17",
					new Object[] { "KH_TC013_updateFailed", "Update KhachHang", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("17",
					new Object[] { "KH_TC013_updateFailed", "Update KhachHang", "Update Fail", "Fail" });
		}
	}

	@Test
	public void KH_TC016_updateFailed() {
		try {
			KhachHang khachHang = new KhachHang();
			assertFalse(dao.Update(khachHang));
			testNgResult.put("18",
					new Object[] { "KH_TC016_updateFailed", "Update KhachHang", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("18",
					new Object[] { "KH_TC016_updateFailed", "Update KhachHang", "Update Fail", "Fail" });
		}
	}

	@Test
	public void KH_TC017_updateFailed() {
		try {
			KhachHang khachHang = null;
			assertFalse(dao.Update(khachHang));
			testNgResult.put("19",
					new Object[] { "KH_TC017_updateFailed", "Update KhachHang", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("19",
					new Object[] { "KH_TC017_updateFailed", "Update KhachHang", "Update Fail", "Fail" });
		}
	}

	@Parameters("SDT_KH")
	@AfterTest
	public void tearDown(String SDT_KH) {
		try {
			String sql = "DELETE FROM KhachHang where SDT_KH = ?";
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, SDT_KH);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("loiox" + e.getMessage());
		}

		Set<String> keysSet = testNgResult.keySet();
		int rownum = 0;
		for (String key : keysSet) {
			Row row = sheet8.createRow(rownum++);
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
						new File("D:\\SOF304\\Assignment\\Assignment_KT\\excel\\TestKhachHang.xls"));
				workbook.write(out);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
