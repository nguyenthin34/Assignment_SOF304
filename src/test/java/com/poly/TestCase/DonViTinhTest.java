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
import com.poly.model.DonViTinh;

public class DonViTinhTest {

	Connection connection;
	DonViTinhDAO dao = new DonViTinhDAO();
	Map<String, Object> testNgResult;
	HSSFWorkbook workbook;
	HSSFSheet sheet4;

	@BeforeTest
	public void connectDatabase() {
		try {
			connection = SQLConnect.getcon();
			workbook = new HSSFWorkbook();
			sheet4 = workbook.createSheet("Đơn Vị Tính");
			testNgResult = new LinkedHashMap<String, Object>();
			testNgResult.put("1",
					new Object[] { "Testcase code", "Testing Purpose", "Expected Output", "Actual Output" });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	PreparedStatement st = null;

	@Parameters({ "ID_DVT", "Ten_DVT" })
	@Test
	public void DVT_TC001_insertSuccess(String Id_DVT, String Ten_DVT) throws Exception {
		try {
			String sql = "insert into DonViTinh values(?,?,?)";
			st = connection.prepareStatement(sql);
			st.setString(1, Id_DVT);
			st.setString(2, Ten_DVT);
			st.setBoolean(3, true);
			int r = st.executeUpdate();
			assertTrue(r > 0);
			testNgResult.put("2",
					new Object[] { "DVT_TC001_insertSuccess", "Insert Don Vi Tinh", "Insert Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("2",
					new Object[] { "DVT_TC001_insertSuccess", "Insert Don Vi Tinh", "Insert Success", "Fail" });
		}
	}

	@Test
	public void DVT_TC002_insertFailed() throws Exception {
		try {
			DonViTinh dvt = new DonViTinh();
			assertFalse(dao.Insert(dvt));
			testNgResult.put("3",
					new Object[] { "DVT_TC002_insertFailed", "Insert Don Vi Tinh", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("3",
					new Object[] { "DVT_TC002_insertFailed", "Insert Don Vi Tinh", "Insert Fail", "Fail" });

		}
	}

	@Test
	public void DVT_TC003_insertFailed() throws Exception {
		try {
			DonViTinh dvt = null;
			assertFalse(dao.Insert(dvt));
			testNgResult.put("4",
					new Object[] { "DVT_TC003_insertFailed", "Insert Don Vi Tinh", "Insert Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("4",
					new Object[] { "DVT_TC003_insertFailed", "Insert Don Vi Tinh", "Insert Fail", "Fail" });
		}
	}

	@Parameters({ "ID_DVT", "Ten_DVT" })
	@Test
	public void DVT_TC004_UpdateSuccess(String ID_DVT, String Ten_DVT) throws Exception {
		try {
			DonViTinh dvt = new DonViTinh(ID_DVT, Ten_DVT, true);
			assertTrue(dao.Update(dvt));
			testNgResult.put("5",
					new Object[] { "DVT_TC004_UpdateSuccess", "Update Don Vi Tinh", "Update Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("5",
					new Object[] { "DVT_TC004_UpdateSuccess", "Update Don Vi Tinh", "Update Success", "Fail" });
		}
	}

	@Test
	public void DVT_TC005_UpdateFailed() throws Exception {
		try {
			DonViTinh dvt = new DonViTinh();
			assertFalse(dao.Update(dvt));
			testNgResult.put("6",
					new Object[] { "DVT_TC005_UpdateSuccess", "Update Don Vi Tinh", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("6",
					new Object[] { "DVT_TC005_UpdateSuccess", "Update Don Vi Tinh", "Update Fail", "Fail" });
		}
	}

	@Test
	public void DVT_TC006_UpdateFailed() throws Exception {
		try {
			DonViTinh dvt = null;
			assertFalse(dao.Update(dvt));
			testNgResult.put("7",
					new Object[] { "DVT_TC006_UpdateSuccess", "Update Don Vi Tinh", "Update Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("7",
					new Object[] { "DVT_TC006_UpdateSuccess", "Update Don Vi Tinh", "Update Fail", "Fail" });
		}
	}

	@Parameters({ "ID_DVT" })
	@Test
	public void DVT_TC007_DeleteSuccess(String IDDVT) throws Exception {
		try {
			String sql = "delete from DonViTinh where ID_DonViTinh = ?";
			SQLConnect.executeUpdate(sql, IDDVT);
			testNgResult.put("8",
					new Object[] { "DVT_TC007_DeleteSuccess", "Delete Don Vi Tinh", "Delete Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("8",
					new Object[] { "DVT_TC007_DeleteSuccess", "Delete Don Vi Tinh", "Delete Success", "Fail" });
		}
	}

	@Test
	public void DVT_TC008_DeleteFailed() throws Exception {
		try {
			String sql = "delete from DonViTinh where ID_DonViTinh = ?";
			st = SQLConnect.getcon().prepareStatement(sql);
			st.setString(1, "");
			int r = st.executeUpdate();
			assertFalse(r < 0);
			testNgResult.put("9",
					new Object[] { "DVT_TC008_DeleteFailed", "Delete Don Vi Tinh", "Delete Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("9",
					new Object[] { "DVT_TC008_DeleteFailed", "Delete Don Vi Tinh", "Delete Fail", "Fail" });
		}
	}

	@Parameters({ "IDDVT" })
	@Test
	public void DVT_TC009_FindSuccess(String IDVVT) throws Exception {
		try {
			DonViTinh dvt = dao.findByID(IDVVT);
			assertTrue(dvt != null);
			testNgResult.put("10",
					new Object[] { "DVT_TC009_FindSuccess", "Find Don Vi Tinh", "Find Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("10",
					new Object[] { "DVT_TC009_FindSuccess", "Find Don Vi Tinh", "Find Success", "Fail" });
		}
	}

	@Test
	public void DVT_TC010_FindFailed() throws Exception {
		try {
			DonViTinh dvt = dao.findByID("");
			assertFalse(dvt != null);
			testNgResult.put("11",
					new Object[] { "DVT_TC010_FindFailed", "Find Don Vi Tinh", "Find Fail", "Pass" });
		} catch (Exception e) {
			testNgResult.put("11",
					new Object[] { "DVT_TC010_FindFailed", "Find Don Vi Tinh", "Find Fail", "Fail" });
		}
	}

	@Test
	public void DVT_TC011_FindListSuccess() throws Exception {
		try {
			List<DonViTinh> list = dao.select();
			assertTrue(list != null);
			testNgResult.put("12",
					new Object[] { "DVT_TC011_FindListSuccess", "Find List Don Vi Tinh", "Find Success", "Pass" });
		} catch (Exception e) {
			testNgResult.put("12",
					new Object[] { "DVT_TC011_FindListSuccess", "Find List Don Vi Tinh", "Find Success", "Fail" });

		}
	}

	@AfterTest
	public void suiteTearDown() {
		Set<String> keysSet = testNgResult.keySet();
		int rownum = 0;
		for (String key : keysSet) {
			Row row = sheet4.createRow(rownum++);
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
						new File("D:\\SOF304\\Assignment\\Assignment_KT\\excel\\TestDonViTinh.xls"));
				workbook.write(out);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
