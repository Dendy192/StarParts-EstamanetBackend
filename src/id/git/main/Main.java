package id.git.main;

import org.apache.log4j.Logger;

import id.git.service.PDFEngine;
import id.git.utils.Config;

public class Main {
	private static Logger log = Logger.getLogger(Main.class.getName());
	public static void main(String[] args) {
		// if (Config.init()) {
		// if (Function.isSchedule(Param.getStartDate(),
		// Param.getFinishDate()))
		// new SchedulerBuilder(JobMontly.class, Param.getStartTime(),
		// Param.getFinishTime(), "groupMonthly");
		//
		// } else {
		// System.err.println("Error configuration!");
		// System.exit(0);
		// }
		Config.init();
		log.info("test");
		PDFEngine pdf = new PDFEngine();
		pdf.engine();
	}
}
