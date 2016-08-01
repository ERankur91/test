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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.regexp.recompile;
import org.testng.annotations.Parameters;

import com.test.android.Constants;
import com.test.android.MethodTimeTracker.MethodTimeClass;

public class GetEventsFromLogsDB {
	static int count =0;
	static int totalDownloaded = 0;
	static int totalDownloaded_bgFetch = 0;
	static String bgFetchDownloadedApps;
	static int totalScanned = 0;
	static int totalScanned_bgFetch = 0;
	static String bgFetchScannedApps;
	static String appInstalled;
	static int rowNumber = 0;
	static int rowNumber2 = 0;
	static Map<String, Object[]> htTestResultDataMap1;
	static Map<String, Object[]> htTestResultDataMap2;
	static Map<String, Object[]> htTestResultDataMap3;		// used in resetAdvID
	static HSSFWorkbook workbook = new HSSFWorkbook();
	static HSSFSheet sheet1;
	static HSSFSheet sheet2;
	static HSSFSheet sheet3;		//used in reset adv id
	static String testMethodDescription;

	static DecimalFormat df = new DecimalFormat("#.##"); 
	static String fetchStartTime;
	static String fetchStopTime;
	static ArrayList<String> startArrayList= new ArrayList<String>();
	static ArrayList<String> stopArrayList= new ArrayList<String>();
	
	static String autoStartSession;
	static String autoEndSession;
	static String sensorResult;

	
	
	@Parameters({"port", "udid"})
	public static void generate_AdvID_Reset(String port, String udid) throws ClassNotFoundException, IOException
	{
		List<String> personalAgentList = new ArrayList<String>();
		List<String> userLoginList = new ArrayList<String>();
		List<String> deviceList = new ArrayList<String>();
		List<String> advtID = new ArrayList<String>();
		String expectedAdvtID = Utilities.getAdvtID();

		
		
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		
		try 
		{
			connection = DriverManager.getConnection("jdbc:sqlite:"+Constants.fileDownloadDirectory+"logs_"+udid+".sqlite");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(60);
			htTestResultDataMap3 = new LinkedHashMap<String, Object[]>();		
			workbook = new HSSFWorkbook();
			sheet3 = workbook.createSheet("Reset Advt ID");
			
			htTestResultDataMap3.put("S.No.", new Object[]{"Performed Scenarios"});
			for(int i=0; i<Constants.methodDescriptionList.size(); i++)
			{
				rowNumber++;
				htTestResultDataMap3.put(""+rowNumber, new Object[]{Constants.methodDescriptionList.get(i)});
			}
			
			rowNumber++;
			
			htTestResultDataMap3.put("S.No", new Object[]{"", "Expected", "Actual", "Result"});
			rowNumber++;
			
			ResultSet advtID1 = statement.executeQuery("SELECT DISTINCT message FROM logs where message like '%DeviceID:%'");
			try 
			{
				do 
				{
					String a = advtID1.getString("message");
					advtID.add(a);
				} while (advtID1.next());
			} 
			catch (Exception e) 
			{
				System.out.println(e);
			}
			String actualAdvtID= advtID.get(0).substring(9, 45);	//getting exact device ID
			
			if (expectedAdvtID.equalsIgnoreCase(actualAdvtID))
			{
//				htTestResultDataMap3.put(""+rowNumber, new Object[]{"Before Resetting advt ID", Constants.oldAdvtID, advtID.get(0), "Pass"});
//				rowNumber++;
				htTestResultDataMap3.put(""+rowNumber, new Object[]{"After Resetting advt ID", expectedAdvtID, advtID.get(0).substring(9, 45), "Pass"});
				rowNumber++;
			} 
			else 
			{
//				htTestResultDataMap3.put(""+rowNumber, new Object[]{"Before Resetting advt ID", Constants.oldAdvtID, advtID.get(0), "Fail"});
//				rowNumber++;
				htTestResultDataMap3.put(""+rowNumber, new Object[]{"After Resetting advt ID", expectedAdvtID, advtID.get(0).substring(9, 45), "Fail"});
				rowNumber++;
			}
			
			ResultSet rs1_one = statement.executeQuery("SELECT count(*) FROM logs WHERE message " +
					"LIKE '%AUTO_SESSION_START%'");
			String auto_session_start = rs1_one.getString("count(*)");
			rowNumber++;
			htTestResultDataMap3.put(""+rowNumber, new Object[]{"AUTO_SESSION_START", auto_session_start});
			
			ResultSet rs1_two = statement.executeQuery("SELECT count(*) FROM logs WHERE message " +
					"LIKE '%AUTO_SESSION_END%'");
			String auto_session_end = rs1_two.getString("count(*)");
			rowNumber++;
			htTestResultDataMap3.put(""+rowNumber, new Object[]{"AUTO_SESSION_END", auto_session_end});
			
			
			rowNumber++;
			
			htTestResultDataMap3.put(""+rowNumber, new Object[]{"", "Events Logged", "Events flushed to server", "Sucess Ratio"});
			
			ResultSet rs1_three = statement.executeQuery("SELECT count(*) FROM logs WHERE message " +
					"LIKE '%LOG_EVENT_601%'");
			String log_event_601 = rs1_three.getString("count(*)");
			double event_601 = Double.parseDouble(log_event_601);
			
			ResultSet rs1_four = statement.executeQuery("SELECT count(*) FROM logs WHERE message " +
					"LIKE '%LOG_SERVER_EVENT_601%'");
			String log_server_event_601 = rs1_four.getString("count(*)");
			double server_event_601 = Double.parseDouble(log_server_event_601);
			
			double successRatio_601 = ((server_event_601/event_601)*100);
			String SuccessRatio_601 = String.valueOf(successRatio_601);
			
			rowNumber++;
			htTestResultDataMap3.put(""+rowNumber, new Object[]{"Event Type 601", log_event_601, log_server_event_601, SuccessRatio_601});
			
			ResultSet rs1_five = statement.executeQuery("SELECT count(*) FROM logs WHERE message " +
					"LIKE '%LOG_EVENT_201_1%'");
			String log_event_201_1 = rs1_five.getString("count(*)");
			double event_201_1 = Double.parseDouble(log_event_201_1);
			
			ResultSet rs1_Six = statement.executeQuery("SELECT count(*) FROM logs WHERE message " +
					"LIKE '%LOG_SERVER_EVENT_201_1%'");
			String log_server_event_201_1 = rs1_Six.getString("count(*)");
			double server_event_201_1 = Double.parseDouble(log_server_event_201_1);
			
			double successRatio_201_1 = ((server_event_201_1/event_201_1)*100);
			String SuccessRatio_201_1 = String.valueOf(successRatio_201_1);
			
			rowNumber++;
			htTestResultDataMap3.put(""+rowNumber, new Object[]{"Event Type 201_1", log_event_201_1, log_server_event_201_1, SuccessRatio_201_1});
			
			ResultSet rs1_Seven = statement.executeQuery("SELECT count(*) FROM logs WHERE message " +
					"LIKE '%LOG_EVENT_201_2%'");
			String log_event_201_2 = rs1_Seven.getString("count(*)");
			double event_201_2 = Double.parseDouble(log_event_201_2);
			
			ResultSet rs1_Eight = statement.executeQuery("SELECT count(*) FROM logs WHERE message " +
					"LIKE '%LOG_SERVER_EVENT_201_2%'");
			String log_server_event_201_2 = rs1_Eight.getString("count(*)");
			double server_event_201_2 = Double.parseDouble(log_server_event_201_2);
			
			double successRatio_201_2 = ((server_event_201_2/event_201_2)*100);
			String SuccessRatio_201_2 = String.valueOf(successRatio_201_2);
			
			rowNumber++;
			htTestResultDataMap3.put(""+rowNumber, new Object[]{"Event Type 201_2", log_event_201_2, log_server_event_201_2, SuccessRatio_201_2});
			
			ResultSet rs1_Nine = statement.executeQuery("SELECT count(*) FROM logs WHERE message " +
					"LIKE '%SESSION_CLICK_EVENT_fg_event%'");
			String session_click_event_fg_event = rs1_Nine.getString("count(*)");
			rowNumber++;
			htTestResultDataMap3.put(""+rowNumber, new Object[]{"SESSION_CLICK_EVENT_fg_event", session_click_event_fg_event});
			
			ResultSet rs1_Ten = statement.executeQuery("SELECT count(*) FROM logs WHERE message " +
					"LIKE '%SESSION_CLICK_EVENT_bg_event%'");
			String session_click_event_bg_event = rs1_Ten.getString("count(*)");
			rowNumber++;
			htTestResultDataMap3.put(""+rowNumber, new Object[]{"SESSION_CLICK_EVENT_bg_event", session_click_event_bg_event});
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			reportEnty_resetAdv(port, udid);
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
		}
	}
	
	
	@Parameters({"port", "udid"})
	public static void reportEnty_resetAdv(String port, String udid) throws IOException
	{
		Set<String> keySet = htTestResultDataMap3.keySet();
		int rowNum = 0;
		for(String key : keySet)
		{
			Row row = sheet3.createRow(rowNum++);
			Object[] objArr = htTestResultDataMap3.get(key);
			int cellNum = 0;
			for (Object obj : objArr)
			{
				Cell cell = row.createCell(cellNum++);
				cell.setCellValue((String) obj);
			}
		}
		
		FileOutputStream fout = new FileOutputStream(Constants.projectOutputPath+"PGSDK-Android-EventReport_"+udid+".xls");
		workbook.write(fout);
		fout.close();
	}
	
	
	@Parameters({"port", "udid"})
	public static void generated_networkEventReport(String port, String udid) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		
		connection = DriverManager.getConnection("jdbc:sqlite:"+Constants.fileDownloadDirectory+"logs_"+udid+".sqlite");
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(60);
		htTestResultDataMap3 = new LinkedHashMap<String, Object[]>();		
		workbook = new HSSFWorkbook();
		sheet3 = workbook.createSheet("Network Report");
		
		htTestResultDataMap3.put("S.No.", new Object[]{"Performed Scenarios"});
		for(int i=0; i<Constants.methodDescriptionList.size(); i++)
		{
			rowNumber++;
			htTestResultDataMap3.put(""+rowNumber, new Object[]{Constants.methodDescriptionList.get(i)});
		}
		
		rowNumber++;
		
		htTestResultDataMap3.put("S.No", new Object[]{"", "Expected", "Actual", "Result"});
		rowNumber++;
		
		/////////////////////////////////need to work from here
		
		
		/*
		count++;
		try
		{
			Class.forName("org.sqlite.JDBC");	
			
			sheet2 = workbook.createSheet("Sensor State Report");
			htTestResultDataMap2.put("S.No", new Object[] {"Scenario", "Actual Result"});
			
			for(int i=0; i<Constants.methodNameList.size(); i++)
			{
				incrementRowNumber2();
				Connection connection = null;
				String methodName = Constants.methodNameList.get(i);
				connection = DriverManager.getConnection("jdbc:sqlite:"+Constants.fileDownloadDirectory+"logs_"+udid+"_"+methodName+".sqlite");
				Statement statement = connection.createStatement();
				statement.setQueryTimeout(60); 
				
				
			
				ResultSet rs1_one = statement.executeQuery("SELECT count(*) FROM logs WHERE message LIKE '%AUTO_SESSION_START%'");
				String auto_session_start = rs1_one.getString("count(*)");
				ResultSet rs1_two = statement.executeQuery("SELECT count(*) FROM logs WHERE message LIKE '%AUTO_SESSION_END%'");
				String auto_session_end = rs1_two.getString("count(*)");
				ResultSet rs1_three = statement.executeQuery("SELECT count(*) FROM logs WHERE message LIKE '%LOG_EVENT_601%'");
				String log_event_601 = rs1_three.getString("count(*)");
				ResultSet rs1_four = statement.executeQuery("SELECT count(*) FROM logs WHERE message LIKE '%LOG_SERVER_EVENT_601%'");
				String log_server_event_601 = rs1_four.getString("count(*)");
				ResultSet rs1_five = statement.executeQuery("SELECT count(*) FROM logs WHERE message LIKE '%LOG_EVENT_201_1%'");
				String log_event_201_1 = rs1_five.getString("count(*)");
				ResultSet rs1_Six = statement.executeQuery("SELECT count(*) FROM logs WHERE message LIKE '%LOG_SERVER_EVENT_201_1%'");
				String log_server_event_201_1 = rs1_Six.getString("count(*)");
				ResultSet rs1_Seven = statement.executeQuery("SELECT count(*) FROM logs WHERE message LIKE '%LOG_EVENT_201_2%'");
				String log_event_201_2 = rs1_Seven.getString("count(*)");
				ResultSet rs1_Eight = statement.executeQuery("SELECT count(*) FROM logs WHERE message LIKE '%LOG_SERVER_EVENT_201_2%'");
				String log_server_event_201_2 = rs1_Eight.getString("count(*)");
				ResultSet rs1_Nine = statement.executeQuery("SELECT count(*) FROM logs WHERE message LIKE '%SESSION_CLICK_EVENT_fg_event%'");
				String session_click_event_fg_event = rs1_Nine.getString("count(*)");
				ResultSet rs1_Ten = statement.executeQuery("SELECT count(*) FROM logs WHERE message LIKE '%SESSION_CLICK_EVENT_bg_event%'");
				String session_click_event_bg_event = rs1_Ten.getString("count(*)");
				
				
				htTestResultDataMap2.put(""+getCurrentRowNumber2(), new Object[] {Constants.methodDescriptionList.get(i), "AUTO_SESSION_START: "+auto_session_start+
					"\nAUTO_SESSION_END: "+auto_session_end+"\nLOG_EVENT_601: "+log_event_601+"\nLOG_SERVER_EVENT_601: "+log_server_event_601+"\nLOG_EVENT_201_1: "+log_event_201_1+
					"\nLOG_SERVER_EVENT_201_1: "+log_server_event_201_1+"\nLOG_EVENT_201_2: "+log_event_201_2+"\nLOG_SERVER_EVENT_201_2: "+log_server_event_201_2+"\nSESSION_CLICK_EVENT_fg_event: "+session_click_event_fg_event+
					"\nSESSION_CLICK_EVENT_bg_event: "+session_click_event_bg_event});

				connection.close();
				
			}
		}
		catch (Exception e)
		{
			System.err.println(e);
		}
		finally
		{
			reportTwoEntry(port, udid);	
			System.out.println("network report generated successfully");
		}
		
		*/
	}
	
	
	@Parameters({"port", "udid"})
	//	public static void main(String args[]) throws Exception {
	public static void generate_eventComparisonReport(String port, String udid) throws Exception { 
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");

		Connection connection = null;
		try
		{
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:"+Constants.fileDownloadDirectory+"logs_"+udid+".sqlite");  //"+Constants.fileDownloadDirectory+"
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(60);  // set timeout to 30 sec.
			htTestResultDataMap1 = new LinkedHashMap<String, Object[]>();
//			workbook = new HSSFWorkbook();
			sheet1 = workbook.createSheet("Event Report");
			htTestResultDataMap1.put("S.No", new Object[] {"Scenario", "Events Occured from App", "Events Flushed to Server", "Success Ratio" });
			
			
			
			
			try {
				ResultSet locationEvent = statement.executeQuery("SELECT count(*) FROM logs where message = 'LOG_EVENT_101' "); //'"+testStartTime+"'
				Constants.locationEvent = locationEvent.getString("count(*)");
				System.out.println("Location Events logged - "+Constants.locationEvent);

				ResultSet locationEventFlushed = statement.executeQuery("SELECT count(*) FROM logs where message = 'LOG_SERVER_EVENT_101' "); //'"+testStartTime+"'
				Constants.locationEventFlushed = locationEventFlushed.getString("count(*)");
				System.out.println("Location Events flushed - "+Constants.locationEventFlushed);	
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				ResultSet poiEvent = statement.executeQuery("SELECT count(*) FROM logs where message = 'LOG_SERVER_EVENT_102' "); //'"+testStartTime+"'
				Constants.poiEvent = poiEvent.getString("count(*)");
				System.out.println("POI Events logged - "+Constants.poiEvent);

				ResultSet poiEventFlushed = statement.executeQuery("SELECT count(*) FROM logs where message = 'LOG_SERVER_EVENT_102' "); //'"+testStartTime+"'
				Constants.poiEventFlushed = poiEventFlushed.getString("count(*)");
				System.out.println("POI Events flushed - "+Constants.poiEventFlushed);
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				ResultSet scanAppEvent = statement.executeQuery("SELECT count(*) FROM logs where message = 'LOG_EVENT_201_1' "); //'"+testStartTime+"'
				Constants.scanAppEvent = scanAppEvent.getString("count(*)");
				System.out.println("App Installed Events logged - "+Constants.scanAppEvent);

				ResultSet scanAppEventFlushed = statement.executeQuery("SELECT count(*) FROM logs where message = 'LOG_SERVER_EVENT_201_1' "); //'"+testStartTime+"'
				Constants.scanAppEventFlushed = scanAppEventFlushed.getString("count(*)");
				System.out.println("App Installed Events flushed - "+Constants.scanAppEventFlushed);
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				ResultSet runningAppEvent = statement.executeQuery("SELECT count(*) FROM logs where message = 'LOG_EVENT_201_2' "); //'"+testStartTime+"'
				Constants.runningAppEvent = runningAppEvent.getString("count(*)");
				System.out.println("Running App Events logged - "+Constants.runningAppEvent);

				ResultSet runningAppEventFlushed = statement.executeQuery("SELECT count(*) FROM logs where message = 'LOG_SERVER_EVENT_201_2' "); //'"+testStartTime+"'
				Constants.runningAppEventFlushed = runningAppEventFlushed.getString("count(*)");
				System.out.println("Running App Events flushed - "+Constants.runningAppEventFlushed);
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				ResultSet clickEvent = statement.executeQuery("SELECT count(*) FROM logs where message = 'LOG_EVENT_601' "); //'"+testStartTime+"'
				Constants.clickEvent = clickEvent.getString("count(*)");
				System.out.println("Click Events logged - "+Constants.clickEvent);

				ResultSet clickEventFlushed = statement.executeQuery("SELECT count(*) FROM logs where message = 'LOG_SERVER_EVENT_601' "); //'"+testStartTime+"'
				Constants.clickEventFlushed = clickEventFlushed.getString("count(*)");
				System.out.println("Click Events flushed - "+Constants.clickEventFlushed);
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				ResultSet fbEvent = statement.executeQuery("SELECT count(*) FROM logs where message = 'LOG_EVENT_401' "); //'"+testStartTime+"'
				Constants.fbEvent = fbEvent.getString("count(*)");
				System.out.println("Facebook Events logged - "+Constants.fbEvent);

				ResultSet fbEventFlushed = statement.executeQuery("SELECT count(*) FROM logs where message = 'LOG_SERVER_EVENT_401' "); //'"+testStartTime+"'
				Constants.fbEventFlushed = fbEventFlushed.getString("count(*)");
				System.out.println("Facebook Events flushed - "+Constants.fbEventFlushed);
			} catch (Exception e) {
				// TODO: handle exception
			}

			double loggedLocation = Double.parseDouble(Constants.locationEvent);
			double flushedLocation =  Double.parseDouble(Constants.locationEventFlushed);
			double ratioLocation = (flushedLocation / loggedLocation) * 100;
			String sucessRateLocation = String.valueOf(df.format(ratioLocation));

			double loggedPOI = Double.parseDouble(Constants.poiEvent);
			double flushedPOI =  Double.parseDouble(Constants.poiEventFlushed);
			double ratioPOI = (flushedPOI / loggedPOI) * 100;
			String sucessRatePOI = String.valueOf(df.format(ratioPOI));

			double loggedScan = Double.parseDouble(Constants.scanAppEvent);
			double flushedScan =  Double.parseDouble(Constants.scanAppEventFlushed);
			double ratioScan = (flushedScan / loggedScan) * 100;
			String sucessRateScan = String.valueOf(df.format(ratioScan));

			double loggedRunning = Double.parseDouble(Constants.runningAppEvent);
			double flushedRunning =  Double.parseDouble(Constants.runningAppEventFlushed);
			double ratioRunning = (flushedRunning / loggedRunning) * 100;
			String sucessRateRunning = String.valueOf(df.format(ratioRunning));

			double loggedClick = Double.parseDouble(Constants.clickEvent);
			double flushedClick =  Double.parseDouble(Constants.clickEventFlushed);
			double ratioClick = (flushedClick / loggedClick) * 100;
			String sucessRateClick = String.valueOf(df.format(ratioClick));

			double loggedFB = Double.parseDouble(Constants.fbEvent);
			double flushedFB =  Double.parseDouble(Constants.fbEventFlushed);
			double ratioFB = (flushedFB / loggedFB) * 100;
			String sucessRateFB = String.valueOf(df.format(ratioFB));


			incrementRowNumber();
			htTestResultDataMap1.put("" + getCurrentRowNumber(), new Object[] {"Location Events", Constants.locationEvent, Constants.locationEventFlushed, sucessRateLocation});
			incrementRowNumber();
			htTestResultDataMap1.put("" + getCurrentRowNumber(), new Object[] {"POI Events", Constants.poiEvent, Constants.poiEventFlushed, sucessRatePOI});
			incrementRowNumber();
			htTestResultDataMap1.put("" + getCurrentRowNumber(), new Object[] {"App Installed Events", Constants.scanAppEvent, Constants.scanAppEventFlushed, sucessRateScan});
			incrementRowNumber();
			htTestResultDataMap1.put("" + getCurrentRowNumber(), new Object[] {"Running App Events", Constants.runningAppEvent, Constants.runningAppEventFlushed, sucessRateRunning});
			incrementRowNumber();
			htTestResultDataMap1.put("" + getCurrentRowNumber(), new Object[] {"Analytic Events", Constants.clickEvent, Constants.clickEventFlushed, sucessRateClick});
			incrementRowNumber();
			htTestResultDataMap1.put("" + getCurrentRowNumber(), new Object[] {"Facebook Events", Constants.fbEvent, Constants.fbEventFlushed, sucessRateFB});

//			ResultSet downloadedAppsRows = statement.executeQuery("SELECT * FROM logs where message like '%APP_DB_DOWNLOAD_STAT_0_2%' "); //'"+testStartTime+"'
//			while(downloadedAppsRows.next())
//			{
//				String msgDownloaded = downloadedAppsRows.getString("message");
//				String downloadCount = msgDownloaded.substring(25);
//				String downloadedCount = downloadCount.substring(0, downloadCount.indexOf("_"));
//				int countDownloaded = Integer.parseInt(downloadedCount);
//				totalDownloaded = totalDownloaded + countDownloaded;
//				//						++totalDownloaded;
//			}
//			System.out.println("Total Downloaded Apps - "+totalDownloaded);
//			String totalDownloadedApps = String.valueOf(totalDownloaded);
//			incrementRowNumber();
//			htTestResultDataMap.put("" + getCurrentRowNumber(), new Object[] {""});
//			incrementRowNumber();
//			htTestResultDataMap.put("" + getCurrentRowNumber(), new Object[] {"Total Downloaded Apps", totalDownloadedApps});


			ResultSet scannedAppsRows = statement.executeQuery("select message from logs where message like '%APPS_SCANNED_LIST%' order by time asc");
			while(scannedAppsRows.next())
			{
				String msgScanned = scannedAppsRows.getString("message");
				int scannedAppCount = msgScanned.length() - msgScanned.replace(",", "").length();
//				String countScanned = String.valueOf(scannedAppCount);
				totalScanned = totalScanned + scannedAppCount;
				//						++totalScanned;
			}
			System.out.println("Total Scanned Apps - "+totalScanned);
			String totalScannedApps = String.valueOf(totalScanned);
			incrementRowNumber();
			htTestResultDataMap1.put("" + getCurrentRowNumber(), new Object[] {"Total Scanned Apps", totalScannedApps});


//			ResultSet installedAppsRows = statement.executeQuery("SELECT * FROM logs where message like '%INSTALLED_APPS_FOUND%' ");
//			incrementRowNumber();
//			htTestResultDataMap.put("" + getCurrentRowNumber(), new Object[] {""});
//			incrementRowNumber();
//			htTestResultDataMap.put("" + getCurrentRowNumber(), new Object[] {"", "List of Installed Apps"});
//			while(installedAppsRows.next())
//			{
//				String msgInstalled = scannedAppsRows.getString("message");
//				String installedApp = msgInstalled.substring(24);
//				String getAppID = installedApp.substring(0, installedApp.indexOf(")")).trim();
//				if(getAppID.length() > 0) {
//					appInstalled = getAppID; 
//					System.out.println("Installed App - "+appInstalled);
//					incrementRowNumber();
//					htTestResultDataMap.put("" + getCurrentRowNumber(), new Object[] {"Installed App ID - ", appInstalled});
//				}
//			}


/*			ResultSet bgFetchStartRows = statement.executeQuery("SELECT * FROM logs where message = 'BACKGROUND_FETCH' order by time asc ");
			while(bgFetchStartRows.next())
			{
				String start = bgFetchStartRows.getString("time");
				System.out.println(start);
				if (null!=startArrayList) {
					startArrayList.add(start);
				}
			}


			ResultSet bgFetchStopRows = statement.executeQuery("SELECT * FROM logs where message = 'STOP_BACKGROUND_FETCH_FETCH' order by time asc ");
			while(bgFetchStopRows.next())
			{
				String stop = bgFetchStartRows.getString("time");
				System.out.println(stop);
				if (null!=stopArrayList) {
					stopArrayList.add(stop);
				}
			}

			for (int i = 0; i < stopArrayList.size(); i++) {
				//					System.out.println("start"+startArrayList.get(0));
				//					System.out.println("stop"+stopArrayList.get(0));
				//					System.out.println("SELECT * FROM logs where message like '%APP_DB_DOWNLOAD_STAT_0_2%' AND timestamp between '"+startArrayList.get(i)+"' AND '"+stopArrayList.get(i)+"' ");
				ResultSet downloadedAppsRows_bgFetch = statement.executeQuery("SELECT * FROM logs where message like '%APP_DB_DOWNLOAD_STAT_0_2%' AND time between '"+startArrayList.get(i)+"' AND '"+stopArrayList.get(i)+"' "); 
				while(downloadedAppsRows_bgFetch.next())
				{
					String msgDownloaded_bgFetch = downloadedAppsRows_bgFetch.getString("message");
					String downloadCount_bgFetch = msgDownloaded_bgFetch.substring(25);
					String downloadedCount_bgFetch = downloadCount_bgFetch.substring(0, downloadCount_bgFetch.indexOf("_"));
					int countDownloaded_bgFetch = Integer.parseInt(downloadedCount_bgFetch);
					totalDownloaded_bgFetch = totalDownloaded_bgFetch + countDownloaded_bgFetch;
					bgFetchDownloadedApps = String.valueOf(totalDownloaded_bgFetch);
				}
			}
			System.out.println("Total Downloaded Apps while Background Fetch - "+bgFetchDownloadedApps);


			for (int i = 0; i < stopArrayList.size(); i++) {
				ResultSet scannedAppsRows_bgFetch = statement.executeQuery("SELECT * FROM logs where message like '%APPS_SCANNED%' AND message not like '%APPS_SCANNED_LIST%' AND time between '"+startArrayList.get(i)+"' AND '"+stopArrayList.get(i)+"' "); 
				while(scannedAppsRows_bgFetch.next())
				{
					String msgScanned_bgFetch = scannedAppsRows_bgFetch.getString("message");
					String scanCount_bgFetch = msgScanned_bgFetch.substring(25);
					String scannedCount_bgFetch = scanCount_bgFetch.substring(0, scanCount_bgFetch.indexOf("_"));
					int countScanned_bgFetch = Integer.parseInt(scannedCount_bgFetch);
					totalScanned_bgFetch = totalScanned_bgFetch + countScanned_bgFetch;
					bgFetchScannedApps = String.valueOf(totalScanned_bgFetch);
				}
			}
			System.out.println("Total Scanned Apps while Background Fetch - "+bgFetchScannedApps);

			incrementRowNumber();
			htTestResultDataMap.put("" + getCurrentRowNumber(), new Object[] {""});
			incrementRowNumber();
			htTestResultDataMap.put("" + getCurrentRowNumber(), new Object[] {"Total Downloaded Apps while Background Fetch", bgFetchDownloadedApps});
			incrementRowNumber();
			htTestResultDataMap.put("" + getCurrentRowNumber(), new Object[] {"Total Scanned Apps while Background Fetch", bgFetchScannedApps});
*/			
			
			ResultSet sessionStart = statement.executeQuery("SELECT count(message) FROM logs where message like '%AUTO_SESSION_START_EVENT%' ");
			String startCount = sessionStart.getString("count(message)");
			int sessionStartCount = Integer.parseInt(startCount);
			
			ResultSet sessionStarted = statement.executeQuery("Select count(DISTINCT time) from logs where message like 'onActivityStarted%' ");
			String startedCount = sessionStarted.getString("count(DISTINCT time)");
			int sessionStartedCount = Integer.parseInt(startedCount);
			
			ResultSet sessionEnd = statement.executeQuery("SELECT count(message) FROM logs where message like '%AUTO_SESSION_END_EVENT%' ");
			String endCount = sessionEnd.getString("count(message)");
			int sessionEndCount = Integer.parseInt(endCount);
			
			ResultSet sessionEnded = statement.executeQuery("Select count(DISTINCT time) from logs where message like 'onActivityStopped%' ");
			String endedCount = sessionEnded.getString("count(DISTINCT time)");
			int sessionEndedCount = Integer.parseInt(endedCount);
			
			if (sessionStartCount == sessionStartedCount)
			{
				autoStartSession = "PASS";
			} else {
				autoStartSession = "FAIL";
			}
			
			if (sessionEndCount == sessionEndedCount)
			{
				autoEndSession = "PASS";
			} else {
				autoEndSession = "FAIL";
			}
			
			incrementRowNumber();
			htTestResultDataMap1.put("" + getCurrentRowNumber(), new Object[] {""});
			incrementRowNumber();
			htTestResultDataMap1.put("" + getCurrentRowNumber(), new Object[] {"Auto Session Event Type", "Expected", "Actual", "Status"});
			incrementRowNumber();
			htTestResultDataMap1.put("" + getCurrentRowNumber(), new Object[] {"START EVENT", startedCount, startCount, autoStartSession });
			incrementRowNumber();
			htTestResultDataMap1.put("" + getCurrentRowNumber(), new Object[] {"END EVENT", endedCount, endCount, autoEndSession});

		}
		catch(SQLException e)
		{
			// if the error message is "out of memory", 
			// it probably means no database file is found
			System.err.println(e.getMessage());
		}
		finally
		{
			//			reportEntry();
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
		}
	}

	@Parameters ({"port", "udid"})
	//	public static void reportEntry() throws Exception {
	public static void reportOneEntry(String port, String udid) throws Exception {
		Set<String> keyset = htTestResultDataMap1.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet1.createRow(rownum++);
			Object[] objArr = htTestResultDataMap1.get(key);
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
			FileOutputStream out = new FileOutputStream(new File(Constants.projectOutputPath+"PGSDK-Android-EventReport"+udid+".xls"));
			workbook.write(out);
			out.close();
			// System.out.println("Excel written successfully..");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	static void incrementRowNumber() {
		rowNumber++;
	}

	static int getCurrentRowNumber() {
		return rowNumber;
	}
	
	static void incrementRowNumber2() {
		rowNumber2++;
	}

	static int getCurrentRowNumber2() {
		return rowNumber2;
	}
	
	static String getResult()
	{
		return sensorResult;
	}
	
	public static void putResult(String result)
	{
		GetEventsFromLogsDB.sensorResult = result;
	}
}