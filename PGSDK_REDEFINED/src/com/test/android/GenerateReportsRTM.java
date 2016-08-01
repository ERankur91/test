package com.test.android;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Parameters;

import com.test.android.MethodTimeTracker.MethodTimeClass;

public class GenerateReportsRTM extends Base
{
	public static String total_iap_event_count;
	public static String total_rap_event_count;
	public static String total_session_count;
	public static String total_3g_dur;
	public static String no_intrnt_conn_duration;
	public static String wifi_duration;
	public static String total_session_duration;
	public static String total_service_run_duration;
	public static String fb_token_shared;
	public static String startTime;
	public static String sdk_version;
	
	static int rowNumber = 0;
	static Map<String, Object[]> htTestResultDataMap;
	static HSSFWorkbook workbook = new HSSFWorkbook();
	static HSSFSheet sheet;
	
	
	
	
	@Parameters({"port", "udid"})
	public static void generateReportV2(String port, String udid) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		
		connection = DriverManager.getConnection("jdbc:sqlite:"+Constants.fileDownloadDirectory+"logs_"+udid+".sqlite");  //"+Constants.fileDownloadDirectory+"
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(60);  // set timeout to 30 sec.
		htTestResultDataMap = new LinkedHashMap<String, Object[]>();
//		workbook = new HSSFWorkbook();
		sheet = workbook.createSheet("Sensor State Report");
		
		htTestResultDataMap.put("S.No.", new Object[]{"Performed Scenarios"});
		for(int i=0; i<Constants.methodDescriptionList.size(); i++)
		{
			rowNumber++;
			htTestResultDataMap.put(""+rowNumber, new Object[]{Constants.methodDescriptionList.get(i)});
		}
		
		rowNumber++;
		htTestResultDataMap.put("S.No", new Object[] {"Scenario", "Events Occured from App", "Events Flushed to Server", "Success Ratio" });
		
		
	}
	
	
	
	
	@Parameters({"port", "udid"})
	public static void generate_RTM_Report(String port, String udid) throws ClassNotFoundException, SQLException
	{
		
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		
		connection = DriverManager.getConnection("jdbc:sqlite:"+Constants.fileDownloadDirectory+"logs_"+udid+".sqlite");  //"+Constants.fileDownloadDirectory+"
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(60);  // set timeout to 30 sec.
		htTestResultDataMap = new LinkedHashMap<String, Object[]>();
//		workbook = new HSSFWorkbook();
		sheet = workbook.createSheet("Run Time Matrics");
		
		htTestResultDataMap.put("S.No.", new Object[]{"Performed Scenarios"});
		for(int i=0; i<Constants.methodDescriptionList.size(); i++)
		{
			rowNumber++;
			htTestResultDataMap.put(""+rowNumber, new Object[]{Constants.methodDescriptionList.get(i)});
		}
		
		rowNumber++;
		htTestResultDataMap.put(""+rowNumber, new Object[]{"Event type in RTM JSON", 
				"Value in RTM JSON", "Event type in log.db", "Value in log.db", "Result"});
		rowNumber++;
		
		String query1 = "SELECT count(*) FROM logs where message like '%LOG_EVENT_201_1%'";
		ResultSet total_iap_event_count = statement.executeQuery(query1);
		String total_iap_count = total_iap_event_count.getString("count(*)");
		
		if (total_iap_count.equalsIgnoreCase(GenerateReportsRTM.total_iap_event_count)) 
		{
			htTestResultDataMap.put(""+rowNumber, new Object[]{"total_iap_event_count", 
					GenerateReportsRTM.total_iap_event_count, "LOG_EVENT_201_1", total_iap_count, "pass"});
			rowNumber++;
		} 
		else {
			htTestResultDataMap.put(""+rowNumber, new Object[]{"total_iap_event_count", 
					GenerateReportsRTM.total_iap_event_count, "LOG_EVENT_201_1", total_iap_count, "fail"});
			rowNumber++;
		}
		
		String query2 = "SELECT count(*) FROM logs where message like '%LOG_EVENT_201_2%'";
		ResultSet total_rap_event_count = statement.executeQuery(query2);
		String total_rap_count = total_rap_event_count.getString("count(*)");
		
		if(total_rap_count.equalsIgnoreCase(GenerateReportsRTM.total_rap_event_count))
		{
			htTestResultDataMap.put(""+rowNumber, new Object[]{"total_rap_event_count", 
					GenerateReportsRTM.total_rap_event_count, "LOG_EVENT_201_2", total_rap_count, "pass"});
			rowNumber++;
		}
		else {
			htTestResultDataMap.put(""+rowNumber, new Object[]{"total_rap_event_count", 
					GenerateReportsRTM.total_rap_event_count, "LOG_EVENT_201_2", total_rap_count, "fail"});
			rowNumber++;
		}
		
		String query3 = "SELECT count(*) FROM logs where message like '%AUTO_SESSION_START%'";
		String query3_1 = "SELECT count(*) From logs where message like '%AUTO_SESSSION_END%'";
		ResultSet total_session_count = statement.executeQuery(query3);
		String session_start_count = total_session_count.getString("count(*)");
		ResultSet total_session_count_1 = statement.executeQuery(query3_1);
		String session_end_count = total_session_count_1.getString("count(*)");
		
		if(session_start_count.equalsIgnoreCase(GenerateReportsRTM.total_session_count) || session_end_count.equalsIgnoreCase(GenerateReportsRTM.total_session_count))
		{
			htTestResultDataMap.put(""+rowNumber, new Object[]{"total_session_count", 
					GenerateReportsRTM.total_session_count, "Session count in log.db", session_start_count, "pass"});
			rowNumber++;
		}
		else {
			htTestResultDataMap.put(""+rowNumber, new Object[]{"total_session_count", 
					GenerateReportsRTM.total_session_count, "session count in log.db", session_start_count, "fail"});
			rowNumber++;
		}
		
		try 
		{
			String query4 = "SELECT message FROM logs where message like '%Updated key : no_intrnt_conn_duration%'";
			double d = 0;
			ResultSet no_intrnt_conn_duration = statement.executeQuery(query4);
			do {
				String s = no_intrnt_conn_duration.getString("message").substring(65);
				d = Double.parseDouble(s) + d;
			} while (no_intrnt_conn_duration.next());
			
			double d_inSeconds = (d/1000);
			String network_notAvail_inSeconds = String.valueOf(d_inSeconds);
			
			if(network_notAvail_inSeconds.equalsIgnoreCase(GenerateReportsRTM.no_intrnt_conn_duration))
			{
				htTestResultDataMap.put(""+rowNumber, new Object[]{"no_intrnt_conn_duration in seconds", 
						GenerateReportsRTM.no_intrnt_conn_duration, "DUR_202 in seconds", network_notAvail_inSeconds, "pass"});
				rowNumber++;
			}
			else
			{
				htTestResultDataMap.put(""+rowNumber, new Object[]{"no_intrnt_conn_duration in seconds", 
						GenerateReportsRTM.no_intrnt_conn_duration, "DUR_202 in seconds", network_notAvail_inSeconds, "fail"});
				rowNumber++;
			}
			
			htTestResultDataMap.put(""+rowNumber, new Object[]{"no_intrnt_conn_duration in seconds", 
					GenerateReportsRTM.no_intrnt_conn_duration, "test duration when network not available", String.valueOf(Constants.noInternetDuration), ""});
			rowNumber++;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		try {
			
			String query5 = "SELECT message FROM logs where message like '%Updated key : total_3g_dur%'";
			double d = 0;
			ResultSet total_3g_dur = statement.executeQuery(query5);
			do {
				String s1 = total_3g_dur.getString("message").substring(54);
				d = Double.parseDouble(s1) + d;
			} while (total_3g_dur.next());
			
			double d_inSeconds = (d/1000);
			String network_3G_inSeconds = String.valueOf(d_inSeconds);
			
			if(network_3G_inSeconds.equalsIgnoreCase(GenerateReportsRTM.total_3g_dur))
			{
				htTestResultDataMap.put(""+rowNumber, new Object[]{"total_3g_dur in seconds", 
						GenerateReportsRTM.total_3g_dur, "DUR_201 in seconds", network_3G_inSeconds, "pass"});
				rowNumber++;
			}
			else
			{
				htTestResultDataMap.put(""+rowNumber, new Object[]{"total_3g_dur in seconds", 
						GenerateReportsRTM.total_3g_dur, "DUR_201 in seconds", network_3G_inSeconds, "fail"});
				rowNumber++;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		try {
			String query6 = "SELECT message FROM logs where message like '%Updated key : total_session_duration%'";
			double d = 0;
			ResultSet total_session_duration = statement.executeQuery(query6);
			do {
				String s1 = total_session_duration.getString("message").substring(64);
				d = Double.parseDouble(s1) + d;
			} while (total_session_duration.next());
			
			double d_inSeconds = (d/1000);
			String total_session_inSeconds = String.valueOf(d_inSeconds);
			
			if(total_session_inSeconds.equalsIgnoreCase(GenerateReportsRTM.total_session_duration))
			{
				htTestResultDataMap.put(""+rowNumber, new Object[]{"total_session_duration in seconds", 
						GenerateReportsRTM.total_session_duration, "DUR_208 in seconds", total_session_inSeconds, "pass"});
				rowNumber++;
			}
			else
			{
				htTestResultDataMap.put(""+rowNumber, new Object[]{"total_3g_dur in seconds", 
						GenerateReportsRTM.total_session_duration, "DUR_208 in seconds", total_session_inSeconds, "fail"});
				rowNumber++;
			}
			
			htTestResultDataMap.put(""+rowNumber, new Object[]{"total_session_duration in seconds", 
					GenerateReportsRTM.total_session_duration, "total test session in seconds", String.valueOf(Constants.totalSessionDuration), ""});
			rowNumber++;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		htTestResultDataMap.put(""+rowNumber, new Object[]{"wifi_duration in seconds", 
				GenerateReportsRTM.wifi_duration, "test duration in seconds when wifi is available", String.valueOf(Constants.wifiDuration), ""});
		rowNumber++;
		
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally
		{
			try {
				reportOneEntry(port, udid);
				System.out.println("Event report generated successfully");
				try
				{
					if(connection != null)
						connection.close();
				}
				catch(SQLException e)
				{
					// connection close failed.
					System.err.println(e);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
//		try {
//			
//			String query7 = "SELECT message FROM logs where message like '%wifi_Duration%'";
//			double d = 0;
//			ResultSet wifi_duration = statement.executeQuery(query7);
//			do {
//				String s1 = wifi_duration.getString("message").substring(49);
//				d = Double.parseDouble(s1) + d;
//			} while (wifi_duration.next());
//			
//			double d_inSeconds = (d/1000);
//			String network_3G_inSeconds = String.valueOf(d_inSeconds);
//			
//			if(network_3G_inSeconds.equalsIgnoreCase(GenerateReportsRTM.total_3g_dur))
//			{
//				htTestResultDataMap.put(""+rowNumber, new Object[]{"total_3g_dur in seconds", 
//						GenerateReportsRTM.total_3g_dur, "DUR_201 in seconds", network_3G_inSeconds, "pass"});
//				rowNumber++;
//			}
//			else
//			{
//				htTestResultDataMap.put(""+rowNumber, new Object[]{"total_3g_dur in seconds", 
//						GenerateReportsRTM.total_3g_dur, "DUR_201 in seconds", network_3G_inSeconds, "fail"});
//				rowNumber++;
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println(e);
//		}
		
	}
	
	
	

	
	@Parameters ({"port", "udid"})
	//	public static void reportEntry() throws Exception {
	public static void reportOneEntry(String port, String udid) throws Exception {
		Set<String> keyset = htTestResultDataMap.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = htTestResultDataMap.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				cell.setCellValue((String) obj);
			}
		}

		try {
			File file = new File(Constants.projectOutputPath);
			if (!file.exists()) {
				if (file.mkdir()) {
					System.out.println("Directory is created!");
				} else {
					System.out.println("Failed to create directory!");
				}
			}
			FileOutputStream out = new FileOutputStream(new File(Constants.projectOutputPath+"PGSDK-Android-"+udid+".xls"));
			workbook.write(out);
			out.close();
			// System.out.println("Excel written successfully..");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void getMatricsKeyValues() throws JSONException
	{
		JSONObject matrics = new JSONObject(Constants.runTimeMatricsJSON);
		sdk_version = matrics.getString("sdk_version");
		startTime = matrics.getString("startTime");
		//final JSONObject counters = matrics.getJSONObject("counters");
		
		JSONArray counters = matrics.optJSONArray("counters");
		int length = counters.length();
		for(int i =0; i<length; i++)
		{
			JSONObject obj = counters.optJSONObject(i);
			Iterator<String> iter = obj.keys();
			while(iter.hasNext())
			{
				String key = iter.next();
				String value = obj.optString(key);
				if (key.equalsIgnoreCase("total_iap_event_count")) 
				{
					GenerateReportsRTM.total_iap_event_count = value;
				} else if (key.equalsIgnoreCase("total_rap_event_count"))
				{
					GenerateReportsRTM.total_rap_event_count = value;
				}
				else if (key.equalsIgnoreCase("total_session_count")) 
				{
					GenerateReportsRTM.total_session_count = value;
				}
			}
		}
		
		JSONArray gauges = matrics.optJSONArray("gauges");
		int length2 = gauges.length();
		for(int i =0; i<length2; i++)
		{
			JSONObject obj = gauges.optJSONObject(i);
			Iterator<String> iter = obj.keys();
			while(iter.hasNext())
			{
				String key = iter.next();
				String value = obj.optString(key);
				if (key.equalsIgnoreCase("fb_token_shared")) 
				{
					GenerateReportsRTM.fb_token_shared = value;
				} 
			}
		}
		JSONArray timings = matrics.optJSONArray("timings");
		int length3 = timings.length();
		for(int i =0; i<length3; i++)
		{
			JSONObject obj = timings.optJSONObject(i);
			Iterator<String> iter = obj.keys();
			while(iter.hasNext())
			{
				String key = iter.next();
				String value = obj.optString(key);
				if (key.equalsIgnoreCase("total_3g_dur")) 
				{
					GenerateReportsRTM.total_3g_dur = value;
				} 
				else if (key.equalsIgnoreCase("no_intrnt_conn_duration")) 
				{
					GenerateReportsRTM.no_intrnt_conn_duration = value;
				}
				else if (key.equalsIgnoreCase("wifi_duration")) 
				{
					GenerateReportsRTM.wifi_duration = value;
				}
				else if (key.equalsIgnoreCase("total_session_duration")) 
				{
					GenerateReportsRTM.total_session_duration = value;
				}
				else if (key.equalsIgnoreCase("total_service_run_duration")) 
				{
					GenerateReportsRTM.total_service_run_duration = value;
				}
			}
		}
	}
	
}
