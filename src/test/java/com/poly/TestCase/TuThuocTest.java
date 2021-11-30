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
import com.poly.dao.TuThuocDAO;
import com.poly.model.TuThuoc;

public class TuThuocTest {
	Map<String, Object> testNgResult;
	HSSFWorkbook workbook;
	HSSFSheet sheet2;
	Connection connection;
	TuThuocDAO dao = new TuThuocDAO();

	@BeforeTest
	public void connectDatabase() {
		try {
			connection = SQLConnect.getcon();
			workbook = new HSSFWorkbook();
			sheet2 = workbook.createSheet("Tủ Thuốc");
			testNgResult = new LinkedHashMap<String, Object>();
			testNgResult.put("1",
					new Object[] { "Testcase code", "Testing Purpose", "Expected Output", "Actual Output" });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterTest
	public void suiteTearDown() {
		Set<String> keysSet = testNgResult.keySet();
		int rownum = 0;
		for (String key : keysSet) {
			Row row = sheet2.createRow(rownum++);
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
				FileOutputStream out = new FileOutputStream(new File("D:\\SOF304\\Assignment\\Assignment_KT\\excel\\TestTuThuoc.xls"));
				workbook.write(out);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	PreparedStatement st = null;

	@Parameters({ "IDTu", "TenTu" })
	@Test
	public void TT_TC001_insertSuccess(String IDTu, String TenTu) {
		try {
			String sql = "insert into TuThuoc values(?,?,?)";
			st = connection.prepareStatement(sql);
			st.setString(1, IDTu);
			st.setString(2, TenTu);
			st.setBoolean(3, true);
			int r = st.executeUpdate();
			assertTrue(r > 0);
			testNgResult.put("2",
					new Object[] { "TT_TC001_insertSuccess", "Insert Tu Thuoc", "Insert Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("2",
					new Object[] { "TT_TC001_insertSuccess", "Insert Tu Thuoc", "Insert Success", "Fail" });
		}
	}

	@Test
	public void TT_TC002_insertFailed() throws Exception {
		try {
			TuThuoc tuThuoc = new TuThuoc();
			System.out.println(tuThuoc.getID_Tu());
			assertFalse(dao.Insert(tuThuoc));
			testNgResult.put("3", new Object[] { "TT_TC002_insertFailed", "Insert Tu Thuoc", "Insert Failed", "Pass" });
		} catch (Exception e) {
			testNgResult.put("3", new Object[] { "TT_TC002_insertFailed", "Insert Tu Thuoc", "Insert Failed", "Fail" });
		}
	}

	@Test
	public void TT_TC003_insertFailed() throws Exception {
		try {
			TuThuoc tuThuoc = null;
			assertFalse(dao.Insert(tuThuoc));
			testNgResult.put("4", new Object[] { "TT_TC003_insertFailed", "Insert Tu Thuoc", "Insert Failed", "Pass" });
		} catch (Exception e) {
			testNgResult.put("4", new Object[] { "TT_TC003_insertFailed", "Insert Tu Thuoc", "Insert Failed", "Fail" });
		}
	}

	@Parameters({ "IDTu", "TenTu" })
	@Test
	public void TT_TC004_UpdateSuccess(String IDTu, String TenTu) throws Exception {
		try {
			TuThuoc tuThuoc = new TuThuoc(IDTu, TenTu, true);
			assertTrue(dao.Update(tuThuoc));
			testNgResult.put("5",
					new Object[] { "TT_TC004_UpdateSuccess", "Update Tu Thuoc", "Update Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("5",
					new Object[] { "TT_TC004_UpdateSuccess", "Update Tu Thuoc", "Update Success", "Fail" });
		}
	}

	@Test
	public void TT_TC005_UpdateFailed() throws Exception {
		try {
			TuThuoc tuThuoc = new TuThuoc();
			assertFalse(dao.Update(tuThuoc));
			testNgResult.put("6", new Object[] { "TT_TC005_UpdateFailed", "Update Tu Thuoc", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("6", new Object[] { "TT_TC005_UpdateFailed", "Update Tu Thuoc", "Update Fail", "Fail" });
		}
	}

	@Test
	public void TT_TC006_UpdateFailed() throws Exception {
		try {
			TuThuoc tuThuoc = null;
			assertFalse(dao.Update(tuThuoc));
			testNgResult.put("7", new Object[] { "TT_TC006_UpdateFailed", "Update Tu Thuoc", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("7", new Object[] { "TT_TC006_UpdateFailed", "Update Tu Thuoc", "Update Fail", "Fail" });
		}
	}

	@Parameters({ "IDTu" })
	@Test
	public void TT_TC007_DeleteSuccess(String IDTu) throws Exception {
		try {
			String sql = "delete from TuThuoc where ID_Tu = ?";
			SQLConnect.executeUpdate(sql, IDTu);
			testNgResult.put("8",
					new Object[] { "TT_TC007_DeleteSuccess", "Delete Tu Thuoc", "Delete Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("8",
					new Object[] { "TT_TC007_DeleteSuccess", "Delete Tu Thuoc", "Delete Success", "Fail" });
		}
	}

	@Test
	public void TT_TC008_DeleteFailed() throws Exception {
		try {
			String sql = "delete from TuThuoc where ID_Tu = ?";
			st = SQLConnect.getcon().prepareStatement(sql);
			st.setString(1, "Dv");
			int r = st.executeUpdate();
			assertFalse(r < 0);
			testNgResult.put("9", new Object[] { "TT_TC008_DeleteFailed", "Delete Tu Thuoc", "Delete Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("9", new Object[] { "TT_TC008_DeleteFailed", "Delete Tu Thuoc", "Delete Fail", "Fail" });
		}
	}

	@Parameters({ "ID_Tu" })
	@Test
	public void TT_TC009_FindSuccess(String IDTu) throws Exception {
		try {
			TuThuoc tuThuoc = dao.findByID(IDTu);
			assertTrue(tuThuoc != null);
			testNgResult.put("10", new Object[] { "TT_TC009_FindSuccess", "Find Tu Thuoc", "Find Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("10", new Object[] { "TT_TC009_FindSuccess", "Find Tu Thuoc", "Find Success", "Fail" });
		}
	}

	@Test
	public void TT_TC010_FindFailed() throws Exception {
		try {
			TuThuoc tuThuoc = dao.findByID("");
			assertFalse(tuThuoc != null);
			testNgResult.put("11", new Object[] { "TT_TC010_FindFailed", "Find Tu Thuoc", "Find Fail", "Fail" });
		} catch (Exception e) {
			testNgResult.put("11", new Object[] { "TT_TC010_FindFailed", "Find Tu Thuoc", "Find Fail", "Pass" });
		}
	}

	@Test
	public void TT_TC011_FindListSuccess() throws Exception {
		try {
			List<TuThuoc> list = dao.select();
			assertTrue(list != null);
			testNgResult.put("11", new Object[] { "TT_TC011_FindListSuccess", "Find List Tu Thuoc", "Find List Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("11", new Object[] { "TT_TC011_FindListSuccess", "Find List Tu Thuoc", "Find List Success", "Fail" });
		}
	}
}
