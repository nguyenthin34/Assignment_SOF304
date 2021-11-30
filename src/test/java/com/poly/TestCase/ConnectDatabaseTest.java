package com.poly.TestCase;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ConnectDatabaseTest {
	String url;
	String user;
	String pass;
	Connection connection;
	Map<String, Object> testNgResult;
	HSSFWorkbook workbook;
	HSSFSheet sheet;
	@BeforeClass
	public void suiteSetup() {
		workbook = new HSSFWorkbook();
		sheet = workbook.createSheet("Connect Database");
		testNgResult = new LinkedHashMap<String, Object>();
		testNgResult.put("1", new Object[] { "Testcase code", "Testing Purpose", "Expected Output", "Actual Output" });
	}

	@Parameters({ "temphost", "tempuser", "temppass", "tempdb", "tempport" })
	@Test
	public void CDB_TC001_ConectSuccess(String temphost, String tempuser, String temppass, String tempdb,
			String tempport) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			url = "jdbc:sqlserver://" + temphost + ":" + tempport + ";databaseName=" + tempdb;
			user = tempuser;
			pass = temppass;
			connection = DriverManager.getConnection(url, user, pass);
			assertTrue(connection != null);
			testNgResult.put("2", new Object[] { "CDB_TC001_ConectSuccess", "Database connection",
					"Database connection successful", "Pass" });
		} catch (Exception e) {
			testNgResult.put("2", new Object[] { "CDB_TC001_ConectSuccess", "Database connection",
					"Database connection successful", "Fail" });
		}
	}

	@Parameters({ "tempuser", "temppass", "tempdb", "tempport" })
	@Test
	public void CDB_TC002_ConectFailed(String tempuser, String temppass, String tempdb, String tempport) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			url = "jdbc:sqlserver://" + "" + ":" + tempport + ";databaseName=" + tempdb;
			user = tempuser;
			pass = temppass;
			connection = DriverManager.getConnection(url, user, pass);
			assertFalse(connection != null);
			testNgResult.put("3", new Object[] { "CDB_TC002_ConectFailed", "Database connection",
					"Database connection Failed", "Fail" });
		} catch (Exception e) {
			testNgResult.put("3", new Object[] { "CDB_TC002_ConectFailed", "Database connection",
					"Database connection Failed", "Pass" });
		}
	}

	@Parameters({ "temphost", "temppass", "tempdb", "tempport" })
	@Test
	public void CDB_TC003_ConectFailed(String temphost, String temppass, String tempdb, String tempport) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			url = "jdbc:sqlserver://" + temphost + ":" + tempport + ";databaseName=" + tempdb;
			user = "";
			pass = temppass;
			connection = DriverManager.getConnection(url, user, pass);
			assertFalse(connection != null);
			testNgResult.put("4", new Object[] { "CDB_TC003_ConectFailed", "Database connection",
					"Database connection Failed", "Fail" });
		} catch (Exception e) {
			testNgResult.put("4", new Object[] { "CDB_TC003_ConectFailed", "Database connection",
					"Database connection Failed", "Pass" });
		}
	}

	@Parameters({ "temphost", "temppass", "tempdb", "tempport" })
	@Test
	public void CDB_TC004_ConectFailed(String temphost, String temppass, String tempdb, String tempport) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			url = "jdbc:sqlserver://" + temphost + ":" + tempport + ";databaseName=" + tempdb;
			user = "abv";
			pass = temppass;
			connection = DriverManager.getConnection(url, user, pass);
			assertFalse(connection != null);
			testNgResult.put("5", new Object[] { "CDB_TC004_ConectFailed", "Database connection",
					"Database connection Failed", "Fail" });
		} catch (Exception e) {
			testNgResult.put("5", new Object[] { "CDB_TC004_ConectFailed", "Database connection",
					"Database connection Failed", "Pass" });
		}
	}

	@Parameters({ "temphost", "tempuser", "tempdb", "tempport" })
	@Test
	public void CDB_TC005_ConectFailed(String temphost, String tempuser, String tempdb, String tempport) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			url = "jdbc:sqlserver://" + temphost + ":" + tempport + ";databaseName=" + tempdb;
			user = tempuser;
			pass = "";
			connection = DriverManager.getConnection(url, user, pass);
			assertFalse(connection != null);
			testNgResult.put("6", new Object[] { "CDB_TC005_ConectFailed", "Database connection",
					"Database connection Failed", "Fail" });
		} catch (Exception e) {
			testNgResult.put("6", new Object[] { "CDB_TC004_ConectFailed", "Database connection",
					"Database connection Failed", "Pass" });
		}
	}

	@Parameters({ "temphost", "tempuser", "tempdb", "tempport" })
	@Test
	public void CDB_TC006_ConectFailed(String temphost, String tempuser, String tempdb, String tempport) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			url = "jdbc:sqlserver://" + temphost + ":" + tempport + ";databaseName=" + tempdb;
			user = tempuser;
			pass = "123";
			connection = DriverManager.getConnection(url, user, pass);
			assertFalse(connection != null);
			testNgResult.put("7", new Object[] { "CDB_TC006_ConectFailed", "Database connection",
					"Database connection Failed", "Fail" });
		} catch (Exception e) {
			testNgResult.put("7", new Object[] { "CDB_TC006_ConectFailed", "Database connection",
					"Database connection Failed", "Pass" });
		}
	}

	@Parameters({ "temphost", "tempuser", "temppass", "tempport" })
	@Test
	public void CDB_TC007_ConectFailed(String temphost, String tempuser, String temppass, String tempport) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			url = "jdbc:sqlserver://" + temphost + ":" + tempport + ";databaseName=" + "a";
			user = tempuser;
			pass = temppass;
			connection = DriverManager.getConnection(url, user, pass);
			assertFalse(connection != null);
			testNgResult.put("8", new Object[] { "CDB_TC007_ConectFailed", "Database connection",
					"Database connection Failed", "Fail" });
		} catch (Exception e) {
			e.printStackTrace();
			testNgResult.put("8", new Object[] { "CDB_TC007_ConectFailed", "Database connection",
					"Database connection Failed", "Pass" });
		}
	}

	@Parameters({ "temphost", "tempuser", "temppass", "tempport" })
	@Test
	public void CDB_TC008_ConectFalse(String temphost, String tempuser, String temppass, String tempport) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			url = "jdbc:sqlserver://" + temphost + ":" + tempport + ";databaseName=" + "abc";
			user = tempuser;
			pass = temppass;
			connection = DriverManager.getConnection(url, user, pass);
			assertFalse(connection != null);
			testNgResult.put("9", new Object[] { "CDB_TC008_ConectFailed", "Database connection",
					"Database connection Failed", "Fail" });
		} catch (Exception e) {
			testNgResult.put("9", new Object[] { "CDB_TC008_ConectFailed", "Database connection",
					"Database connection Failed", "Pass" });
		}
	}

	@Parameters({ "temphost", "tempuser", "temppass", "tempdb" })
	@Test
	public void CDB_TC009_ConectFailed(String temphost, String tempuser, String temppass, String tempdb) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			url = "jdbc:sqlserver://" + temphost + ":" + "" + ";databaseName=" + tempdb;
			user = tempuser;
			pass = temppass;
			connection = DriverManager.getConnection(url, user, pass);
			assertFalse(connection != null);
			testNgResult.put("10", new Object[] { "CDB_TC009_ConectFailed", "Database connection",
					"Database connection Failed", "Fail" });
		} catch (Exception e) {
			testNgResult.put("10", new Object[] { "CDB_TC009_ConectFailed", "Database connection",
					"Database connection Failed", "Pass" });
		}
	}

	@Parameters({ "temphost", "tempuser", "temppass", "tempdb" })
	@Test
	public void CDB_TC010_ConectFailed(String temphost, String tempuser, String temppass, String tempdb) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			url = "jdbc:sqlserver://" + temphost + ":" + "123" + ";databaseName=" + tempdb;
			user = tempuser;
			pass = temppass;
			connection = DriverManager.getConnection(url, user, pass);
			assertFalse(connection != null);
			testNgResult.put("11", new Object[] { "CDB_TC010_ConectFailed", "Database connection",
					"Database connection Failed", "Fail" });
		} catch (Exception e) {
			testNgResult.put("11", new Object[] { "CDB_TC010_ConectFailed", "Database connection",
					"Database connection Failed", "Pass" });
		}
	}

	@Parameters({ "tempuser", "temppass", "tempdb", "tempport" })
	@Test
	public void CDB_TC011_ConectFailed(String tempuser, String temppass, String tempdb, String tempport) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			url = "jdbc:sqlserver://" + "abc" + ":" + tempport + ";databaseName=" + tempdb;
			user = tempuser;
			pass = temppass;
			connection = DriverManager.getConnection(url, user, pass);
			assertFalse(connection != null);
			testNgResult.put("12", new Object[] { "CDB_TC011_ConectFailed", "Database connection",
					"Database connection Failed", "Fail" });
		} catch (Exception e) {
			testNgResult.put("12", new Object[] { "CDB_TC011_ConectFailed", "Database connection",
					"Database connection Failed", "Pass" });
		}
	}

	@AfterClass
	public void suiteTearDown() {
		Set<String> keysSet = testNgResult.keySet();
		int rownum = 0;
		for (String key : keysSet) {
			Row row = sheet.createRow(rownum++);
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
				FileOutputStream out = new FileOutputStream(new File("D:\\SOF304\\Assignment\\Assignment_KT\\excel\\TestCase.xls"));
				workbook.write(out);
				System.out.println("success");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
