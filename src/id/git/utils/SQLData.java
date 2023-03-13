package id.git.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import id.git.conn.DBEngine;

public class SQLData {
	private static Logger log = Logger.getLogger(SQLData.class.getName());

	public static void InsertLogSCH(String start, String finish, int total,
			int suc, int fail, String type) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO \"SP_LOG_SCH\" (\"LOG_SCH_SD\", \"LOG_SCH_FD\", \"LOG_SCH_TOTAL\", \"LOG_SCH_SUC\", \"LOG_SCH_FAIL\", \"LOG_SCH_TYPE\")"
				+ "VALUES('" + start + "', '" + finish + "', '" + total + "', '"
				+ suc + "', '" + fail + "', '" + type + "')";
		try {
			System.out.println(sql);
			conn = DBEngine.getConnection();
			ps = conn.prepareStatement(sql);
			int i = ps.executeUpdate();
			if (i > 0) {
				System.out.println("success");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static List<String> getContentPDF() {
		List<String> result = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		List<String> cid = new ArrayList<String>();
		cid.add("CID001");
		cid.add("CID004");
		cid.add("CID005");
		try {
			for (int i = 0; i < cid.size(); i++) {
				sql = "SELECT \"content_d\" FROM \"content\" WHERE \"content_id\" ='"
						+ cid.get(i) + "' AND \"Type\" = 'PDF'";
				conn = DBEngine.getConnection();
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					result.add(rs.getString("content_d"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			// TODO: handle exception
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public static boolean checkGenerateID(String id) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT \"LOG_INVOICE\" FROM \"SP_LOG\" WHERE \"LOG_INVOICE\" = '"
				+ id + "'";
		try {
			conn = DBEngine.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("masuk true di check invoice");
				result = true;
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			e.getStackTrace();
			// TODO: handle exception
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public static boolean checkInvoice(String id, String invo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM \"SP_INVOICE\" sinv JOIN \"SP_LOG\" sL ON sinv.\"OUTLET_ID\" = sL.\"CUSTOMER_ID\" WHERE \"OUTLET_ID\" ='"
				+ id + "' AND \"INVOICE_NO\" = '" + invo + "'";
		try {
			System.out.println(sql);
			conn = DBEngine.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("masuk true di check invoice");
				result = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static boolean checkSTGInvoice(String id, String invo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM \"SP_STG_INVOICE\" WHERE \"OUTLET_ID\" ='"
				+ id + "' AND \"INVOICE_NO\" = '" + invo + "'";
		System.out.println(sql);
		try {
			conn = DBEngine.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public static void updateLog(String id, String date, String status,
			String message, String pdfPath, String pathImage, String period,
			String invoice) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "";
		try {
			if (status.equals("R")) {
				sql = "UPDATE \"SP_LOG\" SET  \"LOG_GENERATE_DATE\"='" + date
						+ "', \"LOG_STATUS\"='" + status
						+ "', \"LOG_MESSAGE\"='" + message
						+ "', \"LOG_PATH_PDF\"='" + pdfPath
						+ "', \"LOG_PATH_IMAGE\"='" + pathImage
						+ "' , \"LOG_INVOICE\" = '" + invoice + "'  "
						+ " WHERE \"CUSTOMER_ID\"='" + id
						+ "' AND \"LOG_PERIOD\" = '" + period + "'";
			} else if (status.equals("N")) {
				sql = "UPDATE \"SP_LOG\" SET  \"LOG_GENERATE_DATE\"='" + date
						+ "', \"LOG_STATUS\"='" + status
						+ "', \"LOG_MESSAGE\"='" + message + "'"
						+ " WHERE \"CUSTOMER_ID\"='" + id
						+ "' AND \"LOG_PERIOD\" = '" + period + "'";
			}
			conn = DBEngine.getConnection();
			ps = conn.prepareStatement(sql);
			int i = ps.executeUpdate();
			if (i > 0) {
				System.out.println("Success");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean checkLog(String id, String period) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM \"SP_LOG\" WHERE \"CUSTOMER_ID\"='" + id
				+ "' AND \"LOG_PERIOD\"='" + period + "'";
		try {
			conn = DBEngine.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public static void deleteSTG(String id, String period) {
		Connection conn = null;
		PreparedStatement ps = null;
		String result = "";
		String sql = "DELETE FROM \"SP_STG_INVOICE\"  WHERE \"OUTLET_ID\" ='"
				+ id + "' AND \"period\"='" + period + "' ;";
		try {
			conn = DBEngine.getConnection();
			ps = conn.prepareStatement(sql);

			int res = ps.executeUpdate();
			if (res > 0) {
				result = "success";
				System.out.println(result);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = e.getMessage();
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void recordInvoice(String id, String period) {
		Connection conn = null;
		PreparedStatement ps = null;
		String result = "";
		String sql = "INSERT INTO \"SP_INVOICE\" SELECT * FROM \"SP_STG_INVOICE\" WHERE \"OUTLET_ID\" ='"
				+ id + "' AND \"period\"='" + period + "' ;";
		try {
			conn = DBEngine.getConnection();
			ps = conn.prepareStatement(sql);

			int res = ps.executeUpdate();
			if (res > 0) {
				result = "success";
				System.out.println(result);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = e.getMessage();
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static String insertSTGInvoice(String outletID, String INVOICE_DATE,
			String BRAND, String INVOICE_DUE, String INVOICE_NO, Double AMOUNT,
			Double REMAINING_AMOUNT, Double TOTAL, String OUTLET_NAME,
			String OUTLET_ADDRESS, String TTD_FAD_MGR, String COMPANY_NAME,
			String COMPANY_ADDRESS, String period) {
		String result = "";
		String sql = "INSERT INTO \"SP_STG_INVOICE\" (\"OUTLET_ID\", \"INVOICE_DATE\", \"BRAND\", "
				+ "\"INVOICE_DUE\", \"INVOICE_NO\", \"AMOUNT\", \"REMAINING_AMOUNT\", \"TOTAL\", "
				+ "\"OUTLET_NAME\", \"OUTLET_ADDRESS\", \"TTD_FAD_MGR\", \"COMPANY_NAME\",\"COMPANY_ADDRESS\",\"period\") "
				+ " VALUES(?, ?, ?, ?,?,?, ?, ?, ?, ?, ?, ?,?,?)";

		// String sql = "INSERT INTO \"SP_STG_INVOICE\" (\"OUTLET_ID\",
		// \"INVOICE_DATE\", \"BRAND\", "
		// +"\"INVOICE_DUE\", \"INVOICE_NO\", \"AMOUNT\", \"REMAINING_AMOUNT\",
		// \"TOTAL\", "
		// +"\"OUTLET_NAME\", \"OUTLET_ADDRESS\", \"TTD_FAD_MGR\",
		// \"COMPANY_NAME\",\"COMPANY_ADDRESS\") "
		// +" VALUES('"+outletID+"', '"+INVOICE_DATE+"', '"+BRAND+"',
		// '"+INVOICE_DUE+"','"+INVOICE_NO+"', "
		// + ""+AMOUNT+", "+REMAINING_AMOUNT+", "+TOTAL+", '"+OUTLET_NAME+"',
		// '"+OUTLET_ADDRESS+"', '"+TTD_FAD_MGR+"', "
		// + "'"+COMPANY_NAME+"','"+COMPANY_ADDRESS+"')";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBEngine.getConnection();
			ps = conn.prepareStatement(sql);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Date date1 = sdf.parse(INVOICE_DATE);
			Date date2 = sdf.parse(INVOICE_DUE);
			// System.out.println(date1);
			// System.out.println(date2);
			ps.setString(1, outletID);
			ps.setDate(2, java.sql.Date.valueOf(sdf.format(date1)));
			ps.setString(3, BRAND);
			ps.setDate(4, java.sql.Date.valueOf(sdf.format(date2)));
			ps.setString(5, INVOICE_NO);
			ps.setDouble(6, AMOUNT);
			ps.setDouble(7, REMAINING_AMOUNT);
			ps.setDouble(8, TOTAL);
			ps.setString(9, OUTLET_NAME);
			ps.setString(10, OUTLET_ADDRESS);
			ps.setString(11, TTD_FAD_MGR);
			ps.setString(12, COMPANY_NAME);
			ps.setString(13, COMPANY_ADDRESS);
			ps.setString(14, period);
			int res = ps.executeUpdate();
			if (res > 0) {
				result = "success";
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = e.getMessage();
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static void inputLOG(String id, String date, String status,
			String message, String pdfPath, String pathImage, String period,
			String invoice) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "";
		try {
			conn = DBEngine.getConnection();
			if (status.equals("R")) {
				sql = "INSERT INTO \"SP_LOG\" (\"CUSTOMER_ID\", \"LOG_GENERATE_DATE\", \"LOG_STATUS\", \"LOG_MESSAGE\", \"LOG_PATH_PDF\", \"LOG_PATH_IMAGE\", \"LOG_PERIOD\", \"LOG_INVOICE\" ) VALUES(?, ?, ?, ?, ?, ?,?, ?)";
				System.out.println(sql);
				ps = conn.prepareStatement(sql);
				ps.setString(1, id);
				ps.setDate(2, java.sql.Date.valueOf(date));
				ps.setString(3, status);
				ps.setString(4, message);
				ps.setString(5, pdfPath);
				ps.setString(6, pathImage);
				ps.setString(7, period);
				ps.setString(8, invoice);
			} else if (status.equals("N")) {
				sql = "INSERT INTO \"SP_LOG\" (\"CUSTOMER_ID\", \"LOG_GENERATE_DATE\", \"LOG_STATUS\", \"LOG_MESSAGE\", \"LOG_PERIOD\") VALUES(?, ?, ?, ?, ?)";
				System.out.println(sql);
				ps = conn.prepareStatement(sql);
				ps.setString(1, id);
				ps.setString(2, date);
				ps.setString(3, status);
				ps.setString(4, message);
				ps.setString(5, period);
			}
			int res = ps.executeUpdate();
			String result = "";
			if (res > 0) {
				result = "success";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static Map<String, String[]> getCommonParam(String[] key,
			boolean isClob) {
		log.info("key=" + Function.split(key) + ", isClob=" + isClob);
		return getParameter(
				"select \"parameter_Name\", \"parameter_Value\" from \"parameter\" WHERE ",
				"\"parameter_Name\"", key, isClob);
	}

	private static Map<String, String[]> getParameter(String sql, String field,
			String[] key, boolean isClob) {
		log.info("sql=" + sql + ", field=" + field + ", key="
				+ Function.split(key) + ", isClob=" + isClob);
		if (isClob) {
			return DBEngine.execute(sql + Function.whereSQL(field, key),
					isClob);
		}
		return DBEngine.execute(sql + Function.whereSQL(field, key), isClob);
	}
}
