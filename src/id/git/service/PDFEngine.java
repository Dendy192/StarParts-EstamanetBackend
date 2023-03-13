package id.git.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import id.git.api.service.GetAPIData;
import id.git.conn.DBEngine;
import id.git.model.Param;
import id.git.utils.Function;
import id.git.utils.SQLData;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class PDFEngine {
	List<String> contentPDF = SQLData.getContentPDF();
	List<String> total = new ArrayList<String>();
	List<String> failed = new ArrayList<String>();
	List<String> succes = new ArrayList<String>();

	private static Logger log = Logger.getLogger(PDFEngine.class.getName());
	public void engine() {

		int availableThread = Runtime.getRuntime().availableProcessors();
		ThreadPoolExecutor executor = new ThreadPoolExecutor(1, availableThread,
				0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
		executor.prestartAllCoreThreads();
		GetAPIData gap = new GetAPIData();
		HashMap<String, String[]> outletName = gap.GetCustomerDataAll();
		Set<String> no = outletName.keySet();
		myCellAble callable = null;
		Future<String> test = null;
		List<Future<String>> lF = new ArrayList<Future<String>>();
		log.info("GENERATE START");
		String period = Function.getPeriod();
		log.info("PERIOD: " + period);
		for (String n : no) {
			String[] outlet = outletName.get(n);
			// System.out.println(n);
			// System.out.println(outlet[1]);
			HashMap<String, Object[]> getInvoice = gap.getInvoice(outlet[0]);
			Set<String> noIn = getInvoice.keySet();
			for (String nIn : noIn) {
				// System.out.println("getInvo "+nIn);
				if (!nIn.equals("kosong")) {

					// System.out.println("sampe sini"+nIn);
					Object[] invo = getInvoice.get(nIn);

					String tes1 = String.valueOf(invo[4]);
					String tes2 = String.valueOf(invo[8]);
					String tes3 = String.valueOf(invo[9]);
					String tes4 = String.valueOf(invo[10]);
					String tes5 = String.valueOf(invo[11]);
					Double tes6 = Double.valueOf(invo[12].toString());
					Double tes7 = Double.valueOf(invo[13].toString());
					Double tes8 = Double.valueOf(invo[14].toString());
					String tes9 = String.valueOf(invo[5]);
					String tes10 = String.valueOf(invo[6]);
					String tes11 = String.valueOf(invo[7]);
					String tes12 = String.valueOf(invo[2]);
					String tes13 = String.valueOf(invo[3]);
					// log.info(tes1 + " : " + tes2 + " : " + tes3 + " : " +
					// tes4
					// + " : " + tes5 + " : " + tes6 + " : " + tes7 + " : "
					// + tes8);
					// log.info(" : " + tes9 + " : " + tes10 + " : " + tes11
					// + " : " + tes12 + " : " + tes13 + " : "
					// + Function.getPeriod());
					if (SQLData.checkInvoice(tes1, tes5) == false) {
						if (SQLData.checkSTGInvoice(tes1, tes5) == false) {
							SQLData.insertSTGInvoice(tes1, tes2, tes3, tes4,
									tes5, tes6, tes7, tes8, tes9, tes10, tes11,
									tes12, tes13, Function.getPeriod());

						}
					}
				}
			}
			callable = new myCellAble(outlet[1], period);

			test = executor.submit(callable);
			// lF.add(test);

			// }
			// for() {
			//
		}
		// Future<String> test = lF.get(arg0);
		try {
			while (!test.isDone() && !test.isCancelled()) {

				Thread.sleep(200);
				System.out.println("Waiting for task completion...");

			}
			String result123 = test.get();
			Date date = new Date();
			SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM");
			SimpleDateFormat sf2 = new SimpleDateFormat("YYYY");
			log.info("Retrieved result from the task - " + result123);
			String start = Param.getStartDate();
			String end = Param.getFinishDate();
			String my = sf1.format(date);
			String years = sf2.format(date);
			String sDate = my + "-" + start;
			String fDate = my + "-" + end;
			int to = total.size();
			int su = succes.size();
			int fa = failed.size();
			SQLData.InsertLogSCH(sDate, fDate, to, su, fa, "PDF");
			executor.shutdown();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			log.info("executor catch : " + e.getMessage());
			e.printStackTrace();
		}

	}

	class myCellAble implements Callable<String> {
		private String name1;
		private String period;
		public myCellAble(String name1, String period) {
			this.name1 = name1;
			this.period = period;
			// TODO Auto-generated constructor stub
		}
		@Override
		public String call() throws Exception {
			String result = getData(name1, period);

			log.info("dari call: " + result);
			return result;
		}
	}
	public String getData(String name1, String period) {
		System.out.println("masuk ke sini apa kaga");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = "";
		try {

			String sourcePDF = Param.getPdfSource();
			String pathReport = "";
			if (name1.contains("/")) {
				name1 = name1.replace("/", "//");
			}
			String query = "SELECT DISTINCT \"OUTLET_NAME\",\"OUTLET_ID\" ,\"INVOICE_DATE\", \"INVOICE_DUE\", "
					+ "\"BRAND\", \"INVOICE_NO\", \"AMOUNT\", \"REMAINING_AMOUNT\", \"TTD_FAD_MGR\" "
					+ "FROM \"SP_STG_INVOICE\" WHERE \"OUTLET_NAME\" = ?";
			System.out.println(query);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String date1 = sdf.format(date);
			conn = DBEngine.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, name1);
			rs = ps.executeQuery();
			if (rs.next()) {
				String id = rs.getString("OUTLET_ID");
				String[] result1 = generateFile(id, period, name1, pathReport,
						sourcePDF);
				boolean check = SQLData.checkLog(id, period);
				System.out.println("masuk ke dalem rs");
				if (result1[0].equals("success")) {
					succes.add(id);
					total.add(id);
					log.info(id + " : " + date1 + " : " + "R" + " : "
							+ "Ready to Send" + " : " + result1[1] + " : " + ""
							+ " : " + period);
					if (check == true) {
						// log.info(id+" "+ date1+" "+ "R"+" "+ "Ready to
						// Send"+" "+ result1[1]+" "+ resultConvert[1]+" "+
						// period);
						SQLData.updateLog(id, date1, "R", "Ready to Send",
								result1[1], "", period, result1[2]);
					} else {
						SQLData.inputLOG(id, date1, "R", "Ready to Send",
								result1[1], "", period, result1[2]);

					}

				} else {

					total.add(id);
					failed.add(id);
					log.info(id + " : " + date1 + " : " + "N" + " : "
							+ result1[0] + " : " + "" + " : " + "" + " : "
							+ period);
					if (check == true) {
						SQLData.updateLog(id, date1, "N", result1[0], "", "",
								period, "");
					} else {
						SQLData.inputLOG(id, date1, "N", result1[0], "", "",
								period, "");
					}
				}
				result = "kelar";

			}

			else {
				log.info("no data");
				result = "no data";
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("tes: " + e.getMessage());
			result = e.getMessage();
		}
		return result;
	}
	public String[] generateFile(String id, String period, String name1,
			String pathReport, String sourcePDF) {
		String result = "";

		JasperReport jasperReport = null;
		Connection conn2 = null;
		String path = "";
		String idInvoice = generate();

		String foot1 = contentPDF.get(0);
		String foot2 = contentPDF.get(1);
		String foot3 = contentPDF.get(2);
		try {
			System.out.println("masuk if di get data");
			File dir = new File(Param.getPdfPath() + period);
			dir.mkdir();
			conn2 = DBEngine.getConnection();
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("tableParam1", name1);
			parameters.put("idInvoice", idInvoice);
			parameters.put("foot1", foot1);
			parameters.put("foot2", foot2);
			parameters.put("foot3", foot3);
			JRPdfExporter exporter = new JRPdfExporter();
			InputStream is = new FileInputStream(new File(sourcePDF));
			jasperReport = (JasperReport) JRLoader.loadObject(is);
			System.out.println("connection");
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
					parameters, conn2);
			System.out.println("fill report");
			exporter.setParameter(JRExporterParameter.JASPER_PRINT,
					jasperPrint);
			ByteArrayOutputStream byteOS = new ByteArrayOutputStream();
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, byteOS);
			pathReport = Param.getPdfPath() + period + "/";
			path = pathReport + name1 + ".pdf";
			// System.out.println("view");
			// JasperViewer.viewReport(jasperPrint);
			exporter.exportReport();
			log.error("coba di sini dah");
			FileOutputStream outputStream = new FileOutputStream(
					new File(path));
			byteOS.writeTo(outputStream);
			byteOS.flush();
			byteOS.close();
			outputStream.flush();
			outputStream.close();
			log.info(id + " " + period);
			SQLData.recordInvoice(id, period);
			SQLData.deleteSTG(id, period);
			result = "success";
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
			result = e.getMessage();
		}
		String[] result1 = {result, path, idInvoice};
		return result1;
	}

	public String generate() {
		String result = "";
		try {
			Date date = new Date();
			SimpleDateFormat dtf = new SimpleDateFormat("MMYY");
			String dateFormat = dtf.format(date);
			String randomSt = generateRandom();
			String randomSc = generateRandom();
			String finalInvo = dateFormat + "/SPM-FAD/" + randomSt + "/"
					+ randomSc;
			if (SQLData.checkGenerateID(finalInvo) == true) {
				do {
					finalInvo = reGenerate();
				} while (SQLData.checkGenerateID(finalInvo) == false);
			}
			result = finalInvo;
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();

			// TODO: handle exception
		}
		return result;
	}
	public String generateRandom() {
		String result = "";
		try {
			// String tes1 = "";
			for (int i = 0; i < 4; i++) {
				int randomNum = ThreadLocalRandom.current().nextInt(0, 9 + 1);
				String tes2 = String.valueOf(randomNum);
				if (!result.equals("")) {
					// System.out.println("masuk if");
					result = result + tes2;
				} else {
					// System.out.println("masuk else");
					result = tes2;
				}
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
			// TODO: handle exception
		}
		return result;
	}
	public String reGenerate() {
		String result = "";
		try {
			Date date = new Date();
			SimpleDateFormat dtf = new SimpleDateFormat("MMYY");
			String dateFormat = dtf.format(date);
			String randomSt = generateRandom();
			String randomSc = generateRandom();
			result = dateFormat + "/SPM-FAD/" + randomSt + "/" + randomSc;
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();

			// TODO: handle exception
		}
		return result;
	}

}
