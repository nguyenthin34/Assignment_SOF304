package com.poly.TestCase;

import org.testng.annotations.Test;

import com.poly.Jdbc.SQLConnect;
import com.poly.dao.NhaCungCapDAO;
import com.poly.helper.Datehelper;
import com.poly.model.NhaCungCap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

public class NhaCungCapTest {

	Connection connection;
	NhaCungCapDAO dao = new NhaCungCapDAO();
	Map<String, Object> testNgResult;
	HSSFWorkbook workbook;
	HSSFSheet sheet9;

	@BeforeTest
	public void connectDatabase() {
		try {
			connection = SQLConnect.getcon();
			workbook = new HSSFWorkbook();
			sheet9 = workbook.createSheet("Đơn Vị Tính");
			testNgResult = new LinkedHashMap<String, Object>();
			testNgResult.put("1",
					new Object[] { "Testcase code", "Testing Purpose", "Expected Output", "Actual Output" });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Parameters({ "ID_Nhacc" })
	@Test
	public void NCC_TC001_findByIDSuccess(String ID_Nhacc) {
		try {
			NhaCungCap ncc = dao.findByID(ID_Nhacc);
			assertTrue(ncc != null);
			testNgResult.put("2",
					new Object[] { "NCC_TC001_findbyIDSuccess", "Find Nha Cung Cap", "Find Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("2",
					new Object[] { "NCC_TC001_findbyIDSuccess", "Find Nha Cung Cap", "Find Success", "Fail" });
		}
	}

	@Test
	public void NCC_TC002_findByIDFailed() {
		try {
			NhaCungCap ncc = dao.findByID("");
			assertTrue(ncc == null);
			testNgResult.put("3",
					new Object[] { "NCC_TC002_findByIDFailed", "Find Nha Cung Cap", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("3",
					new Object[] { "NCC_TC002_findByIDFailed", "Find Nha Cung Cap", "Find Fail", "Fail" });
		}
	}

	@Test
	public void NCC_TC003_findByIDFailed() {
		try {
			NhaCungCap ncc = dao.findByID(null);
			assertTrue(ncc == null);
			testNgResult.put("4",
					new Object[] { "NCC_TC003_findByIDFailed", "Find Nha Cung Cap", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("4",
					new Object[] { "NCC_TC003_findByIDFailed", "Find Nha Cung Cap", "Find Fail", "Fail" });
		}
	}

	@Test
	public void NCC_TC004_findByIDFailed() {
		try {
			NhaCungCap ncc = dao.findByID("aaaa");
			assertTrue(ncc == null);
			testNgResult.put("5",
					new Object[] { "NCC_TC004_findByIDFailed", "Find Nha Cung Cap", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("5",
					new Object[] { "NCC_TC004_findByIDFailed", "Find Nha Cung Cap", "Find Fail", "Fail" });
		}
	}

	@Parameters({ "ID_NhaCC", "Ten_NCC", "Email_NCC", "SDT_NCC", "DiaChi_NCC", "NgayTao", "TrangThai" })
	@Test
	public void NCC_TC005_insertSuccess(String ID_NhaCC, String Ten_NCC, String Email_NCC, String SDT_NCC,
			String DiaChi_NCC, String NgayTao, boolean TrangThai) {
		try {
			NhaCungCap nhaCungCap = new NhaCungCap(ID_NhaCC, Ten_NCC, Email_NCC, SDT_NCC, DiaChi_NCC,
					Datehelper.toDate(NgayTao), TrangThai);
			assertTrue(dao.Insert(nhaCungCap));
			testNgResult.put("6",
					new Object[] { "NCC_TC005_insertSuccess", "Insert Nha Cung Cap", "Insert Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("6",
					new Object[] { "NCC_TC005_insertSuccess", "Insert Nha Cung Cap", "Insert Success", "Fail" });
		}
	}

	@Parameters({ "Ten_NCC", "Email_NCC", "SDT_NCC", "DiaChi_NCC", "NgayTao", "TrangThai" })
	@Test
	public void NCC_TC006_insertFailed(String Ten_NCC, String Email_NCC, String SDT_NCC, String DiaChi_NCC,
			String NgayTao, boolean TrangThai) {
		try {
			NhaCungCap nhaCungCap = new NhaCungCap("NCC3", Ten_NCC, Email_NCC, SDT_NCC, DiaChi_NCC,
					Datehelper.toDate(NgayTao), TrangThai);
			assertFalse(dao.Insert(nhaCungCap));
			testNgResult.put("7",
					new Object[] { "NCC_TC006_insertFailed", "Insert Nha Cung Cap", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("7",
					new Object[] { "NCC_TC006_insertFailed", "Insert Nha Cung Cap", "Insert Fail", "Fail" });
		}
	}

	@Parameters({ "Ten_NCC", "Email_NCC", "SDT_NCC", "DiaChi_NCC", "NgayTao", "TrangThai" })
	@Test
	public void NCC_TC007_insertFailed(String Ten_NCC, String Email_NCC, String SDT_NCC, String DiaChi_NCC,
			String NgayTao, boolean TrangThai) {
		try {
			NhaCungCap nhaCungCap = new NhaCungCap("", Ten_NCC, Email_NCC, SDT_NCC, DiaChi_NCC,
					Datehelper.toDate(NgayTao), TrangThai);
			assertFalse(dao.Insert(nhaCungCap));
			testNgResult.put("8",
					new Object[] { "NCC_TC007_insertFailed", "Insert Nha Cung Cap", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("8",
					new Object[] { "NCC_TC007_insertFailed", "Insert Nha Cung Cap", "Insert Fail", "Pass" });
		}
	}

	@Parameters({ "Ten_NCC", "Email_NCC", "SDT_NCC", "DiaChi_NCC", "NgayTao", "TrangThai" })
	@Test
	public void NCC_TC008_insertFailed(String Ten_NCC, String Email_NCC, String SDT_NCC, String DiaChi_NCC,
			String NgayTao, boolean TrangThai) {
		try {
			NhaCungCap nhaCungCap = new NhaCungCap(null, Ten_NCC, Email_NCC, SDT_NCC, DiaChi_NCC,
					Datehelper.toDate(NgayTao), TrangThai);
			assertFalse(dao.Insert(nhaCungCap));
			testNgResult.put("9",
					new Object[] { "NCC_TC008_insertFailed", "Insert Nha Cung Cap", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("9",
					new Object[] { "NCC_TC008_insertFailed", "Insert Nha Cung Cap", "Insert Fail", "Fail" });
		}
	}

	@Test
	public void NCC_TC009_insertFailed() {
		try {
			NhaCungCap nhaCungCap = null;
			assertFalse(dao.Insert(nhaCungCap));
			testNgResult.put("10",
					new Object[] { "NCC_TC009_insertFailed", "Insert Nha Cung Cap", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("10",
					new Object[] { "NCC_TC009_insertFailed", "Insert Nha Cung Cap", "Insert Fail", "Fail" });
		}
	}

	@Test
	public void NCC_TC010_insertFailed() {
		try {
			NhaCungCap nhaCungCap = new NhaCungCap();
			assertFalse(dao.Insert(nhaCungCap));
			testNgResult.put("11",
					new Object[] { "NCC_TC010_insertFailed", "Insert Nha Cung Cap", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("11",
					new Object[] { "NCC_TC010_insertFailed", "Insert Nha Cung Cap", "Insert Fail", "Fail" });
		}
	}

	@Test
	public void NCC_TC011_selectallSuccess() {
		try {
			List<NhaCungCap> list = dao.select();
			assertTrue(list != null);
			testNgResult.put("12",
					new Object[] { "NCC_TC011_selectallSuccess", "Select All Nha Cung Cap", "Select All Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("12",
					new Object[] { "NCC_TC011_selectallSuccess", "Select All Nha Cung Cap", "Select All Success", "Fail" });
		}
	}

	@Test
	public void NCC_TC012_selectallFailed() {
		try {
			List<NhaCungCap> list = dao.select();
			assertFalse(list == null);
			testNgResult.put("13",
					new Object[] { "NCC_TC012_selectallFailed", "Select All Nha Cung Cap", "Select All Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("13",
					new Object[] { "NCC_TC012_selectallFailed", "Select All Nha Cung Cap", "Select All Fail", "Pass" });
		}
	}

	@Parameters({ "ID_NhaCC", "Ten_NCC", "Email_NCC", "SDT_NCC", "DiaChi_NCC", "NgayTao", "TrangThai" })
	@Test
	public void NCC_TC013_updateSuccess(String ID_NhaCC, String Ten_NCC, String Email_NCC, String SDT_NCC,
			String DiaChi_NCC, String NgayTao, boolean TrangThai) {
		try {
			NhaCungCap nhaCungCap = new NhaCungCap(ID_NhaCC, Ten_NCC, Email_NCC, SDT_NCC, DiaChi_NCC,
					Datehelper.toDate(NgayTao), TrangThai);
			assertTrue(dao.Update(nhaCungCap));
			testNgResult.put("14",
					new Object[] { "NCC_TC013_updateSuccess", "Update Nha Cung Cap", "Update Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("14",
					new Object[] { "NCC_TC013_updateSuccess", "Update Nha Cung Cap", "Update Success", "Fail" });
		}
	}

	@Parameters({ "Ten_NCC", "Email_NCC", "SDT_NCC", "DiaChi_NCC", "NgayTao", "TrangThai" })
	@Test
	public void NCC_TC014_updateFailed(String Ten_NCC, String Email_NCC, String SDT_NCC, String DiaChi_NCC,
			String NgayTao, boolean TrangThai) {
		try {
			NhaCungCap nhaCungCap = new NhaCungCap("", Ten_NCC, Email_NCC, SDT_NCC, DiaChi_NCC,
					Datehelper.toDate(NgayTao), TrangThai);
			assertFalse(dao.Update(nhaCungCap));
			testNgResult.put("15",
					new Object[] { "NCC_TC014_updateSuccess", "Update Nha Cung Cap", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("15",
					new Object[] { "NCC_TC014_updateSuccess", "Update Nha Cung Cap", "Update Fail", "Fail" });
		}
	}

	@Parameters({ "Ten_NCC", "Email_NCC", "SDT_NCC", "DiaChi_NCC", "NgayTao", "TrangThai" })
	@Test
	public void NCC_TC015_updateFailed(String Ten_NCC, String Email_NCC, String SDT_NCC, String DiaChi_NCC,
			String NgayTao, boolean TrangThai) {
		try {
			NhaCungCap nhaCungCap = new NhaCungCap(null, Ten_NCC, Email_NCC, SDT_NCC, DiaChi_NCC,
					Datehelper.toDate(NgayTao), TrangThai);
			assertFalse(dao.Update(nhaCungCap));
			testNgResult.put("16",
					new Object[] { "NCC_TC015_updateFailed", "Update Nha Cung Cap", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("16",
					new Object[] { "NCC_TC015_updateFailed", "Update Nha Cung Cap", "Update Fail", "Fail" });
		}
	}

	@Parameters({ "Ten_NCC", "Email_NCC", "SDT_NCC", "DiaChi_NCC", "NgayTao", "TrangThai" })
	@Test
	public void NCC_TC016_updateFailed(String Ten_NCC, String Email_NCC, String SDT_NCC, String DiaChi_NCC,
			String NgayTao, boolean TrangThai) {
		try {
			NhaCungCap nhaCungCap = new NhaCungCap("aaaa", Ten_NCC, Email_NCC, SDT_NCC, DiaChi_NCC,
					Datehelper.toDate(NgayTao), TrangThai);
			assertFalse(dao.Update(nhaCungCap));
			testNgResult.put("17",
					new Object[] { "NCC_TC016_updateFailed", "Update Nha Cung Cap", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("17",
					new Object[] { "NCC_TC016_updateFailed", "Update Nha Cung Cap", "Update Fail", "Fail" });
		}
	}

	@Test
	public void NCC_TC017_updateFailed() {
		try {
			NhaCungCap nhaCungCap = new NhaCungCap();
			assertFalse(dao.Update(nhaCungCap));
			testNgResult.put("18",
					new Object[] { "NCC_TC017_updateFailed", "Update Nha Cung Cap", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("18",
					new Object[] { "NCC_TC017_updateFailed", "Update Nha Cung Cap", "Update Fail", "Fail" });
		}
	}

	@Test
	public void NCC_TC018_updateFailed() {
		try {
			NhaCungCap nhaCungCap = null;
			assertFalse(dao.Update(nhaCungCap));
			testNgResult.put("19",
					new Object[] { "NCC_TC018_updateFailed", "Update Nha Cung Cap", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("19",
					new Object[] { "NCC_TC018_updateFailed", "Update Nha Cung Cap", "Update Fail", "Pass" });
		}
	}

	@Parameters("ID_NhaCC")
	@AfterTest
	public void tearDown(String ID_NhaCC) {
		try {
			String sql = "DELETE FROM NhaCungCap where ID_NhaCC = ?";
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, ID_NhaCC);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("loiox" + e.getMessage());
		}
		Set<String> keysSet = testNgResult.keySet();
		int rownum = 0;
		for (String key : keysSet) {
			Row row = sheet9.createRow(rownum++);
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
						new File("D:\\SOF304\\Assignment\\Assignment_KT\\excel\\TestNhaCungCap.xls"));
				workbook.write(out);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
