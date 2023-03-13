package id.git.service;

import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Date;

import org.apache.log4j.Logger;

import id.git.utils.Config;

public class tester {
	private static Logger log = Logger.getLogger(tester.class.getName());

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Config.init();
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// Date date = new Date();
		// String date1 = sdf.format(date);
		// PDFEngine pdf = new PDFEngine();
		// String period = Function.getPeriod();
		// String pathReport = "E:\\KERJABLOK\\newEstatement\\";
		// String sourcePDF = "pdf/StartPart.jasper";
		// try {
		// Connection con = DBEngine.getConnection();
		// String sql = "\r\n"
		// + "SELECT \"OUTLET_ID\", \"OUTLET_NAME\" FROM \"SP_STG_INVOICE\"
		// WHERE period = '012023'";
		// PreparedStatement ps = con.prepareStatement(sql);
		// ResultSet rs = ps.executeQuery();
		// while (rs.next()) {
		// String id = pdf.generate();
		// String[] result1 = pdf.generateFile(id, period,
		// rs.getString("OUTLET_NAME"), pathReport, sourcePDF);
		// SQLData.inputLOG(rs.getString("OUTLET_ID"), date1, "R",
		// "Ready to Send", result1[1], "", period);
		//
		// }

		// } catch (Exception e) {
		// // TODO: handle exception
		// e.printStackTrace();
		// }
		Config.init();
		SimpleDateFormat sf2 = new SimpleDateFormat("YYYY");
		SimpleDateFormat sf3 = new SimpleDateFormat("MM");
		Date date = new Date();
		int years = Integer.valueOf(sf2.format(date));
		int month = Integer.valueOf(sf3.format(date));
		System.out.println(month + " " + years);
		YearMonth ym = YearMonth.of(years, month);
		int y = ym.lengthOfMonth();
		System.out.println(y);
		// SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM");
		// // System.out.println(sf1.format(date));
		// // System.out.println(sf2.format(date));
		// String start = Param.getStartDate();
		// String end = Param.getFinishDate();
		// String my = sf1.format(date);
		// String sDate = my + "-" + start;
		// String fDate = my + "-" + end;
		// System.out.println(sDate);
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// System.out.println(java.sql.Date.valueOf(sdf.format(date)));
		// SQLData.InsertLogSCH(sDate, fDate, 100, 100, 0, "PDF");
	}

}
