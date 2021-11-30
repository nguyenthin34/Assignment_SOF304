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
import com.poly.dao.DonViTinhDAO;
import com.poly.dao.NhomThuocDAO;
import com.poly.model.DonViTinh;
import com.poly.model.NhomThuoc;

public class NhomThuocTest {
	Connection connection;
	NhomThuocDAO dao = new NhomThuocDAO();
	Map<String, Object> testNgResult;
	HSSFWorkbook workbook;
	HSSFSheet sheet3;

	@BeforeTest
	public void connectDatabase() {
		try {
			connection = SQLConnect.getcon();
			workbook = new HSSFWorkbook();
			sheet3 = workbook.createSheet("Nhóm Thuốc");
			testNgResult = new LinkedHashMap<String, Object>();
			testNgResult.put("1",
					new Object[] { "Testcase code", "Testing Purpose", "Expected Output", "Actual Output" });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	PreparedStatement st = null;

	@Parameters({ "ID_NT", "Ten_NT" })
	@Test
	public void NT_TC001_insertSuccess(String ID_NT, String Ten_NT) throws Exception {
		try {
			String sql = "insert into NhomThuoc values(?,?,?)";
			st = connection.prepareStatement(sql);
			st.setString(1, ID_NT);
			st.setString(2, Ten_NT);
			st.setBoolean(3, true);
			int r = st.executeUpdate();
			assertTrue(r > 0);
			testNgResult.put("2",
					new Object[] { "NT_TC001_insertSuccess", "Insert Nhom Thuoc", "Insert Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("2",
					new Object[] { "NT_TC001_insertSuccess", "Insert Nhom Thuoc", "Insert Success", "Fail" });
		}
	}

	@Test
	public void NT_TC002_insertFailed() throws Exception {
		try {
			NhomThuoc nhomThuoc = new NhomThuoc();
			assertFalse(dao.Insert(nhomThuoc));
			testNgResult.put("3", new Object[] { "NT_TC002_insertFailed", "Insert Nhom Thuoc", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("3", new Object[] { "NT_TC002_insertFailed", "Insert Nhom Thuoc", "Insert Fail", "Fail" });
		}
	}

	@Test
	public void NT_TC003_insertFailed() throws Exception {
		try {
			NhomThuoc nhomThuoc = null;
			assertFalse(dao.Insert(nhomThuoc));
			testNgResult.put("4", new Object[] { "NT_TC003_insertFailed", "Insert Nhom Thuoc", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("4", new Object[] { "NT_TC003_insertFailed", "Insert Nhom Thuoc", "Insert Fail", "Fail" });
		}
	}

	@Parameters({ "ID_NT", "Ten_NT" })
	@Test
	public void NT_TC004_UpdateSuccess(String ID_NT, String Ten_NT) throws Exception {
		try {
			NhomThuoc nhomThuoc = new NhomThuoc(ID_NT, Ten_NT, true);
			assertTrue(dao.Update(nhomThuoc));
			testNgResult.put("5",
					new Object[] { "NT_TC004_UpdateSuccess", "Update Nhom Thuoc", "Upadate Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("5",
					new Object[] { "NT_TC004_UpdateSuccess", "Update Nhom Thuoc", "Upadate Success", "Fail" });
		}
	}

	@Test
	public void NT_TC005_UpdateFailed() throws Exception {
		try {
			NhomThuoc nhomThuoc = new NhomThuoc();
			assertFalse(dao.Update(nhomThuoc));
			testNgResult.put("6",
					new Object[] { "NT_TC005_UpdateFailed", "Update Nhom Thuoc", "Upadate Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("6",
					new Object[] { "NT_TC005_UpdateFailed", "Update Nhom Thuoc", "Upadate Fail", "Fail" });
		}
	}

	@Test
	public void NT_TC006_UpdateFailed() throws Exception {
		try {
			NhomThuoc nhomThuoc = null;
			assertFalse(dao.Update(nhomThuoc));
			testNgResult.put("7",
					new Object[] { "NT_TC006_UpdateFailed", "Update Nhom Thuoc", "Upadate Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("7",
					new Object[] { "NT_TC006_UpdateFailed", "Update Nhom Thuoc", "Upadate Fail", "Fail" });
		}

	}

	@Parameters({ "ID_NT" })
	@Test
	public void NT_TC007_DeleteSuccess(String IDNT) throws Exception {
		try {
			String sql = "delete from NhomThuoc where ID_NhomThuoc = ?";
			SQLConnect.executeUpdate(sql, IDNT);
			testNgResult.put("8",
					new Object[] { "NT_TC007_DeleteSuccess", "Delete Nhom Thuoc", "Delete Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("8",
					new Object[] { "NT_TC007_DeleteSuccess", "Delete Nhom Thuoc", "Delete Success", "Fail" });
		}
	}

	@Test
	public void NT_TC008_DeleteFailed() throws Exception {
		try {
			String sql = "delete from NhomThuoc where ID_NhomThuoc = ?";
			st = SQLConnect.getcon().prepareStatement(sql);
			st.setString(1, "");
			int r = st.executeUpdate();
			assertFalse(r < 0);
			testNgResult.put("9",
					new Object[] { "NT_TC008_DeleteFailed", "Delete Nhom Thuoc", "Delete Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("9", new Object[] { "NT_TC008_DeleteFailed", "Delete Nhom Thuoc", "Delete Fail", "Fail" });
		}
	}

	@Parameters({ "IDNT" })
	@Test
	public void NT_TC009_FindSuccess(String IDNT) throws Exception {
		try {
			NhomThuoc dvt = dao.findByID(IDNT);
			assertTrue(dvt != null);
			testNgResult.put("10", new Object[] { "NT_TC009_FindSuccess", "Find Nhom Thuoc", "Find Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("10", new Object[] { "NT_TC009_FindSuccess", "Find Nhom Thuoc", "Find Success", "Fail" });
		}
	}

	@Test
	public void TT_TC010_FindFailed() throws Exception {
		try {
			NhomThuoc dvt = dao.findByID("");
			assertFalse(dvt != null);
			testNgResult.put("11",
					new Object[] { "TT_TC010_FindFailed", "Find Nhom Thuoc", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("11",
					new Object[] { "TT_TC010_FindFailed", "Find Nhom Thuoc", "Find Fail", "Fail" });
		}
	}

	@Test
	public void TT_TC011_FindListSuccess() throws Exception {
		try {
			List<NhomThuoc> list = dao.select();
			assertTrue(list != null);
			testNgResult.put("12",
					new Object[] { "TT_TC011_FindListSuccess", "Find List Nhom Thuoc", "Find List Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("12",
					new Object[] { "TT_TC011_FindListSuccess", "Find List Nhom Thuoc", "Find List Success", "Fail"});
		}
	}
	
	@AfterTest
	public void suiteTearDown() {
		Set<String> keysSet = testNgResult.keySet();
		int rownum = 0;
		for (String key : keysSet) {
			Row row = sheet3.createRow(rownum++);
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
				FileOutputStream out = new FileOutputStream(new File("D:\\SOF304\\Assignment\\Assignment_KT\\excel\\TestNhomThuoc.xls"));
				workbook.write(out);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
