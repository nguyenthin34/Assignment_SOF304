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
import com.poly.dao.NguoiDungDAO;
import com.poly.dao.ThuocDAO;
import com.poly.model.NguoiDung;

public class NguoiDungTest {

	Connection connection;
	NguoiDungDAO dao = new NguoiDungDAO();
	Map<String, Object> testNgResult;
	HSSFWorkbook workbook;
	HSSFSheet sheet7;

	@BeforeTest
	public void connectDatabase() {
		try {
			connection = SQLConnect.getcon();
			workbook = new HSSFWorkbook();
			sheet7 = workbook.createSheet("Đơn Vị Tính");
			testNgResult = new LinkedHashMap<String, Object>();
			testNgResult.put("1",
					new Object[] { "Testcase code", "Testing Purpose", "Expected Output", "Actual Output" });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Parameters("ID_User")
	@Test
	public void ND_TC001_findbyIDSuccess(String ID_User) throws Exception {
		try {
			NguoiDung nguoiDung = dao.findbyID(ID_User);
			assertTrue(nguoiDung != null);
			testNgResult.put("2",
					new Object[] { "ND_TC001_findbyIDSuccess", "Find Nguoi Dung", "Find Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("2",
					new Object[] { "ND_TC001_findbyIDSuccess", "Find Nguoi Dung", "Find Success", "Fail" });
		}
	}

	@Test
	public void ND_TC023_findbyIDFailed() {
		try {
			NguoiDung nguoiDung = dao.findbyID(null);
			assertFalse(nguoiDung != null);
			testNgResult.put("26",
					new Object[] { "ND_TC023_findbyIDFailed", "Find Nguoi Dung", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("26",
					new Object[] { "ND_TC023_findbyIDFailed", "Find Nguoi Dung", "Find Fail", "Pass" });
		}

	}

	@Test
	public void ND_TC002_findbyIDFailed() throws Exception {
		try {
			NguoiDung nguoiDung = dao.findbyID("");
			assertFalse(nguoiDung != null);
			testNgResult.put("3",
					new Object[] { "ND_TC002_findbyIDFailed", "Find Nguoi Dung", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("3",
					new Object[] { "ND_TC002_findbyIDFailed", "Find Nguoi Dung", "Find Fail", "Fail" });
		}
	}

	@Test
	public void ND_TC003_findbyIDFailed() throws Exception {
		try {
			NguoiDung nguoiDung = dao.findbyID("aaaa");
			assertFalse(nguoiDung != null);
			testNgResult.put("4",
					new Object[] { "ND_TC003_findbyIDFailed", "Find Nguoi Dung", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("4",
					new Object[] { "ND_TC003_findbyIDFailed", "Find Nguoi Dung", "Find Fail", "Fail" });
		}
	}

	@Parameters("ID_User")
	@Test
	public void ND_TC004_forget_passSuccess(String ID_User) throws Exception {
		try {
			NguoiDung nguoiDung = dao.forget_pass(dao.findbyID(ID_User));
			assertTrue(nguoiDung != null);
			testNgResult.put("5 ",
					new Object[] { "ND_TC004_forget_passSuccess", "Forgot PassWord", "Forgot PassWord Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("5 ",
					new Object[] { "ND_TC004_forget_passSuccess", "Forgot PassWord", "Forgot PassWord Success", "Fail" });
		}
	}

	@Test
	public void ND_TC005_forget_passFailed() throws Exception {
		try {
			NguoiDung nguoiDung = dao.forget_pass(dao.findbyID(""));
			assertFalse(nguoiDung != null);
			testNgResult.put("6",
					new Object[] { "ND_TC005_forget_passFailed", "Forgot PassWord", "Forgot PassWord Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("6",
					new Object[] { "ND_TC005_forget_passFailed", "Forgot PassWord", "Forgot PassWord Fail", "Fail" });
		}
	}

	@Test
	public void ND_TC006_forget_passFailed() throws Exception {
		try {
			NguoiDung nguoiDung = dao.forget_pass(dao.findbyID("aaa"));
			assertFalse(nguoiDung != null);
			testNgResult.put("7",
					new Object[] { "ND_TC006_forget_passFailed", "Forgot PassWord", "Forgot PassWord Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("7",
					new Object[] { "ND_TC006_forget_passFailed", "Forgot PassWord", "Forgot PassWord Fail", "Fail" });
		}
	}

	@Parameters({ "Ma_ND", "Ten_ND", "Sdt", "Email", "VaiTro", "MatKhau", "TrangThai" })
	@Test
	public void ND_TC007_insertSuccess(String Ma_ND, String Ten_ND, String Sdt, String email, String MatKhau,
			boolean VaiTro, boolean TrangThai) {
		try {
			NguoiDung nguoiDung = new NguoiDung(Ma_ND, Ten_ND, Sdt, email, MatKhau, VaiTro, TrangThai);
			assertTrue(dao.Insert(nguoiDung));
			testNgResult.put("8",
					new Object[] { "ND_TC007_insertSuccess", "Insert Nguoi Dung", "Insert Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("8",
					new Object[] { "ND_TC007_insertSuccess", "Insert Nguoi Dung", "Insert Success", "Pass" });
		}
	}

	@Parameters({ "Ten_ND", "Sdt", "Email", "VaiTro", "MatKhau", "TrangThai" })
	@Test
	public void ND_TC008_insertFailed(String Ten_ND, String Sdt, String email, boolean VaiTro, String MatKhau,
			boolean TrangThai) {
		try {
			NguoiDung nguoiDung = new NguoiDung(null, Ten_ND, Sdt, email, MatKhau, VaiTro, TrangThai);
			assertFalse(dao.Insert(nguoiDung));
			testNgResult.put("9",
					new Object[] { "ND_TC008_insertFailed", "Insert Nguoi Dung", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("9",
					new Object[] { "ND_TC008_insertFailed", "Insert Nguoi Dung", "Insert Fail", "Fail" });
		}
	}

	@Parameters({ "Ten_ND", "Sdt", "Email", "VaiTro", "MatKhau", "TrangThai" })
	@Test
	public void ND_TC009_insertFailed(String Ten_ND, String Sdt, String email, boolean VaiTro, String MatKhau,
			boolean TrangThai) {
		try {
			NguoiDung nguoiDung = new NguoiDung("", Ten_ND, Sdt, email, MatKhau, VaiTro, TrangThai);
			assertFalse(dao.Insert(nguoiDung));
			testNgResult.put("10",
					new Object[] { "ND_TC009_insertFailed", "Insert Nguoi Dung", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("10",
					new Object[] { "ND_TC009_insertFailed", "Insert Nguoi Dung", "Insert Fail", "Fail" });
		}
	}

	@Parameters({ "Ten_ND", "Sdt", "Email", "VaiTro", "MatKhau", "TrangThai" })
	@Test
	public void ND_TC010_insertFailed(String Ten_ND, String Sdt, String email, boolean VaiTro, String MatKhau,
			boolean TrangThai) {
		try {
			NguoiDung nguoiDung = new NguoiDung("PH12915", Ten_ND, Sdt, email, MatKhau, VaiTro, TrangThai);
			assertFalse(dao.Insert(nguoiDung));
			testNgResult.put("11",
					new Object[] { "ND_TC010_insertFailed", "Insert Nguoi Dung", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("11",
					new Object[] { "ND_TC010_insertFailed", "Insert Nguoi Dung", "Insert Fail", "Fail" });
		}
	}

	@Test
	public void ND_TC024_insertFailed() {
		try {
			NguoiDung nguoiDung = new NguoiDung();
			assertFalse(dao.Insert(nguoiDung));
			testNgResult.put("12",
					new Object[] { "ND_TC024_insertFailed", "Insert Nguoi Dung", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("12",
					new Object[] { "ND_TC024_insertFailed", "Insert Nguoi Dung", "Insert Fail", "Fail" });
		}
	}

	@Test
	public void ND_TC025_insertFailed() {
		try {
			NguoiDung nguoiDung = null;
			assertFalse(dao.Insert(nguoiDung));
			testNgResult.put("13",
					new Object[] { "ND_TC025_insertFailed", "Insert Nguoi Dung", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("13",
					new Object[] { "ND_TC025_insertFailed", "Insert Nguoi Dung", "Insert Fail", "Pass" });
		}
	}

	@Test
	public void ND_TC011_selectAllSuccess() {
		try {
			List<NguoiDung> list = dao.select();
			assertTrue(list != null);
			testNgResult.put("14",
					new Object[] { "ND_TC011_selectAllSuccess", "Select All Nguoi Dung", "Select All Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("14",
					new Object[] { "ND_TC011_selectAllSuccess", "Select All Nguoi Dung", "Select All Success", "Fail" });
		}
	}

	@Test
	public void ND_TC012_selectAllFailed() {
		try {
			List<NguoiDung> list = dao.select();
			assertFalse(list == null);
			testNgResult.put("15",
					new Object[] { "ND_TC012_selectAllFailed", "Select All Nguoi Dung", "Select All Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("15",
					new Object[] { "ND_TC012_selectAllFailed", "Select All Nguoi Dung", "Select All Fail", "Fail" });
		}
	}

	@Parameters({ "Ten_ND", "Sdt", "Email", "VaiTro", "MatKhau", "TrangThai" })
	@Test
	public void ND_TC013_updateSuccess(String Ten_ND, String Sdt, String email, String MatKhau, boolean VaiTro,
			boolean TrangThai) {
		try {
			NguoiDung nguoiDung = new NguoiDung("PH12915", Ten_ND, Sdt, email, MatKhau, VaiTro, TrangThai);
			assertTrue(dao.Update(nguoiDung));
			testNgResult.put("16",
					new Object[] { "ND_TC013_updateSuccess", "Update Nguoi Dung", "Update Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("16",
					new Object[] { "ND_TC013_updateSuccess", "Update Nguoi Dung", "Update Success", "Fail" });
		}
	}

	@Parameters({ "Ten_ND", "Sdt", "Email", "VaiTro", "MatKhau", "TrangThai" })
	@Test
	public void ND_TC014_updateFailed(String Ten_ND, String Sdt, String email, boolean VaiTro, String MatKhau,
			boolean TrangThai) {
		try {
			NguoiDung nguoiDung = new NguoiDung(null, Ten_ND, Sdt, email, MatKhau, VaiTro, TrangThai);
			assertFalse(dao.Update(nguoiDung));
			testNgResult.put("25",
					new Object[] { "ND_TC014_updateFailed", "Update Nguoi Dung", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("25",
					new Object[] { "ND_TC014_updateFailed", "Update Nguoi Dung", "Update Fail", "Pass" });
		}
	}

	@Parameters({ "Ten_ND", "Sdt", "Email", "VaiTro", "MatKhau", "TrangThai" })
	@Test
	public void ND_TC015_updateFailed(String Ten_ND, String Sdt, String email, boolean VaiTro, String MatKhau,
			boolean TrangThai) {
		try {
			NguoiDung nguoiDung = new NguoiDung("", Ten_ND, Sdt, email, MatKhau, VaiTro, TrangThai);
			assertFalse(dao.Update(nguoiDung));
			testNgResult.put("17",
					new Object[] { "ND_TC015_updateFailed", "Update Nguoi Dung", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("17",
					new Object[] { "ND_TC015_updateFailed", "Update Nguoi Dung", "Update Fail", "Fail" });
		}
	}

	@Parameters({ "Ten_ND", "Sdt", "Email", "VaiTro", "MatKhau", "TrangThai" })
	@Test
	public void ND_TC016_updateFailed(String Ten_ND, String Sdt, String email, boolean VaiTro, String MatKhau,
			boolean TrangThai) {
		try {
			NguoiDung nguoiDung = new NguoiDung("aaaa", Ten_ND, Sdt, email, MatKhau, VaiTro, TrangThai);
			assertFalse(dao.Update(nguoiDung));
			testNgResult.put("18",
					new Object[] { "ND_TC016_updateFailed", "Update Nguoi Dung", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("18",
					new Object[] { "ND_TC016_updateFailed", "Update Nguoi Dung", "Update Fail", "Fail" });
		}
	}

	@Parameters({ "Ma_ND", "Ten_ND", "Sdt", "Email", "VaiTro", "MatKhau", "TrangThai" })
	@Test
	public void ND_TC017_update_passSuccess(String Ma_ND, String Ten_ND, String Sdt, String email, boolean VaiTro,
			String MatKhau, boolean TrangThai) {
		try {
			NguoiDung nguoiDung = new NguoiDung(Ma_ND, Ten_ND, Sdt, email, MatKhau, VaiTro, TrangThai);
			assertTrue(dao.Update_pass(nguoiDung));
			testNgResult.put("19",
					new Object[] { "ND_TC017_update_passSuccess", "Update Password Nguoi Dung", "Update Password Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("19",
					new Object[] { "ND_TC017_update_passSuccess", "Update Password Nguoi Dung", "Update Password Success", "Fail" });
		}
	}

	@Parameters({ "Ten_ND", "Sdt", "Email", "VaiTro", "MatKhau", "TrangThai" })
	@Test
	public void ND_TC018_update_passFailed(String Ten_ND, String Sdt, String email, boolean VaiTro, String MatKhau,
			boolean TrangThai) {
		try {
			NguoiDung nguoiDung = new NguoiDung(null, Ten_ND, Sdt, email, MatKhau, VaiTro, TrangThai);
			assertFalse(dao.Update_pass(nguoiDung));
			testNgResult.put("20",
					new Object[] { "ND_TC018_update_passFailed", "Update Password Nguoi Dung", "Update Password Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("20",
					new Object[] { "ND_TC018_update_passFailed", "Update Password Nguoi Dung", "Update Password Fail", "Fail" });
		}
	}

	@Parameters({ "Ten_ND", "Sdt", "Email", "VaiTro", "MatKhau", "TrangThai" })
	@Test
	public void ND_TC019_update_passFailed(String Ten_ND, String Sdt, String email, boolean VaiTro, String MatKhau,
			boolean TrangThai) {
		try {
			NguoiDung nguoiDung = new NguoiDung("", Ten_ND, Sdt, email, MatKhau, VaiTro, TrangThai);
			assertFalse(dao.Update_pass(nguoiDung));
			testNgResult.put("21",
					new Object[] { "ND_TC019_update_passFailed", "Update Password Nguoi Dung", "Update Password Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("21",
					new Object[] { "ND_TC019_update_passFailed", "Update Password Nguoi Dung", "Update Password Fail", "Fail" });
		}
	}

	@Parameters({ "Ten_ND", "Sdt", "Email", "VaiTro", "MatKhau", "TrangThai" })
	@Test
	public void ND_TC020_update_passFailed(String Ten_ND, String Sdt, String email, boolean VaiTro, String MatKhau,
			boolean TrangThai) {
		try {
			NguoiDung nguoiDung = new NguoiDung("aaaa", Ten_ND, Sdt, email, MatKhau, VaiTro, TrangThai);
			assertFalse(dao.Update_pass(nguoiDung));
			testNgResult.put("22",
					new Object[] { "ND_TC020_update_passFailed", "Update Password Nguoi Dung", "Update Password Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("22",
					new Object[] { "ND_TC020_update_passFailed", "Update Password Nguoi Dung", "Update Password Fail", "Fail" });
		}
	}

	@Parameters({ "Ma_ND", "Ten_ND", "Sdt", "Email", "VaiTro", "TrangThai" })
	@Test
	public void ND_TC021_update_passFailed(String Ma_ND, String Ten_ND, String Sdt, String email, boolean VaiTro,
			boolean TrangThai) {
		try {
			NguoiDung nguoiDung = new NguoiDung(Ma_ND, Ten_ND, Sdt, email, null, VaiTro, TrangThai);
			assertFalse(dao.Update_pass(nguoiDung));
			testNgResult.put("23",
					new Object[] { "ND_TC021_update_passFailed", "Update Password Nguoi Dung", "Update Password Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("23",
					new Object[] { "ND_TC021_update_passFailed", "Update Password Nguoi Dung", "Update Password Fail", "Fail" });
		}
	}

	@Parameters({ "Ma_ND", "Ten_ND", "Sdt", "Email", "VaiTro", "TrangThai" })
	@Test
	public void ND_TC022_update_passFailed(String Ma_ND, String Ten_ND, String Sdt, String email, boolean VaiTro,
			boolean TrangThai) {
		try {
			NguoiDung nguoiDung = new NguoiDung(Ma_ND, Ten_ND, Sdt, email, "", VaiTro, TrangThai);
			assertFalse(dao.Update_pass(nguoiDung));
			testNgResult.put("24",
					new Object[] { "ND_TC022_update_passFailed", "Update Password Nguoi Dung", "Update Password Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("24",
					new Object[] { "ND_TC022_update_passFailed", "Update Password Nguoi Dung", "Update Password Fail", "Fail" });
		}
	}

	@Parameters("Ma_ND")
	@AfterTest
	public void tearDown(String Ma_ND) {
		try {
			String sql = "DELETE FROM NguoiDung where Ma_ND = ?";
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, Ma_ND);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("loiox" + e.getMessage());
		}

		Set<String> keysSet = testNgResult.keySet();
		int rownum = 0;
		for (String key : keysSet) {
			Row row = sheet7.createRow(rownum++);
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
						new File("D:\\SOF304\\Assignment\\Assignment_KT\\excel\\TestNguoiDung.xls"));
				workbook.write(out);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
