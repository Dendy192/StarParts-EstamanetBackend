package id.git.service;

import id.git.utils.Function;
import id.git.utils.SQLData;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobMontly implements Job {
	private static Logger log = Logger.getLogger(JobMontly.class.getName());
	public static boolean isRunning = false;
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		if (isRunning) {
			try {
				Function.printStatus("SCHEDULER: Timeout!");
				isRunning = false;
				Function.exit();
			} catch (Exception e) {
				e.printStackTrace();
				log.error(Function.getErrMsg(e));
			}
		} else {
			Function.printStatus("SCHEDULER: Starting");
			isRunning = true;
			PDFEngine pdf = new PDFEngine();
			pdf.engine();
		}
	}
}