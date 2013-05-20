package eresearch.audit.report;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beust.jcommander.JCommander;

import eresearch.audit.controller.StatisticsController;
import eresearch.audit.db.UserDao;
import eresearch.audit.pojo.BarDiagramStatistics;
import eresearch.audit.pojo.UserStatistics;

public class ReportTest {

	public ReportTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				new String[] { "audit-servlet.xml", "root-context.xml",
						"rest-audit-records-servlet.xml" });
		ReportUtils r = (ReportUtils) appContext.getBean("reportUtils");

//		try{
		new JCommander(r, args);
//		}
//		catch()
//		try {
//			r.getReportContent(null, r.getHistoryStartYear(),
//					r.getHistoryStartMonth()-1, r.getHistoryEndYear(),
//					r.getHistoryEndMonth()-1,false);
//			List<BarDiagramStatistics> bds = r.getBdslist();
//			List<UserStatistics> us = r.getUserstatslist();
//
//			r.createReport(us, bds);
//		} catch (Exception e) {
//			//e.printStackTrace();
//			System.out.println(e.getMessage());
//		}
		
		r.initReport();
		
		try {
			r.getReportContent(null, r.getHistoryStartYear(),
					r.getHistoryStartMonth()-1, r.getHistoryEndYear(),
					r.getHistoryEndMonth()-1, true);
			List<BarDiagramStatistics> bds = r.getBdslist();
			List<UserStatistics> us = r.getUserstatslist();

			r.createReport(us, bds);
		} catch (Exception e) {
			//e.printStackTrace();
			//System.out.println(e.getMessage());
			System.out.println("invalid parameters");
		}
		
		try {
			r.getReportContent(null, 2012,
					0, r.getHistoryEndYear(),
					r.getHistoryEndMonth()-1, false);
			List<BarDiagramStatistics> bds = r.getBdslist();
			List<UserStatistics> us = r.getUserstatslist();

			r.createReport(us, bds);
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		r.printReport();
		
		System.exit(0);
	}
}