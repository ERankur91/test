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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.testng.annotations.Parameters;

import com.test.android.MethodTimeTracker.MethodTimeClass;
import com.test.android.Constants;
import com.test.android.MethodTimeTracker;

public class GeneratePerformanceReport extends Base{
	static int rowNumber = 0;
	static Map<String, Object[]> htTestResultDataMap;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static String testStartTime;
	static String testEndTime;
	static String testMethodName;
	static String durationTestRun;
	
	static DecimalFormat df = new DecimalFormat("#.##");      

	@Parameters({"port", "udid"})
	public static void generate_performanceReport(String port, String udid) throws Exception { //generateCPU_report
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");

		Connection connection = null;
		try
		{
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:"+Constants.fileDownloadDirectory+"PersonaGraph_Performance_"+udid+".sqlite");  //"+Constants.fileDownloadDirectory+"
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(60);  // set timeout to 30 sec.
			htTestResultDataMap = new LinkedHashMap<String, Object[]>();
			workbook = new HSSFWorkbook();
			sheet = workbook.createSheet("CPU Performance Report");
			htTestResultDataMap.put("S.No", new Object[] {"Scenarion", "CPU (MAX - %)", "CPU (MIN - %)", "CPU Average", "MEMORY(MAX - MB)", "MEMORY(MIN - MB)", "MEMORY Average", "Network IN(Bytes)", "Network OUT(Bytes)", "Test Duration(Seconds)" });

			
			
			ArrayList<MethodTimeClass> allMethodsList = MethodTimeTracker.get().getAllValues();
			for (int i = 0; i < allMethodsList.size(); i++) {
				MethodTimeClass methodTimeObject = allMethodsList.get(i);
				testStartTime = methodTimeObject.getStart();
				testEndTime = methodTimeObject.getEnd();
				testMethodName = methodTimeObject.getMethodName();
				durationTestRun = methodTimeObject.getDurationTestRun();
				
				if (testMethodName == "idle_in_foreground") {
					
					//For max CPU
					ResultSet rs1 = statement.executeQuery("select * from CPU_Performance where time < '"+testEndTime+"' order by cpu_usage desc"); //'"+testStartTime+"'
					Constants.maxCPU = rs1.getString("cpu_usage");
					System.out.println(Constants.maxCPU);
					
					//For min CPU
					ResultSet rs2 = statement.executeQuery("select * from CPU_Performance where time < '"+testEndTime+"' order by cpu_usage asc");
					Constants.minCPU = rs2.getString("cpu_usage");
					System.out.println(Constants.minCPU);
					
					//For Average CPU
					ResultSet rs12 = statement.executeQuery("select SUM(cpu_usage) from CPU_Performance where time < '"+testEndTime+"' ");
					String cpuTotal = rs12.getString("SUM(cpu_usage)");
					double totalCPU = Double.parseDouble(cpuTotal);
					ResultSet rs21 = statement.executeQuery("select count(*) from CPU_Performance where time < '"+testEndTime+"' ");
					String cpuRows = rs21.getString("count(*)");
					double rowsCPU = Double.parseDouble(cpuRows);
					
					double cpuAvg0 = (totalCPU / rowsCPU) ;
					double cpuAvg = Double.valueOf(df.format(cpuAvg0));
					Constants.avgCPU = String.valueOf(cpuAvg);
					System.out.println(Constants.avgCPU);
					
					//For max Memory
					ResultSet rs3 = statement.executeQuery("select * from Memory_Performance where time < '"+testEndTime+"' order by memory_usage desc"); //'"+testStartTime+"'
					Constants.maxMemory = rs3.getString("memory_usage");
					System.out.println(Constants.maxMemory);
					
					//For min Memory
					ResultSet rs4 = statement.executeQuery("select * from Memory_Performance where time < '"+testEndTime+"' order by memory_usage asc");
					Constants.minMemory = rs4.getString("memory_usage");
					System.out.println(Constants.minMemory);
					
					//For average Memory
					ResultSet rs34 = statement.executeQuery("select SUM(memory_usage) from Memory_Performance where time < '"+testEndTime+"' ");
					String memoryTotal = rs34.getString("SUM(memory_usage)");
					double totalMemory = Double.parseDouble(memoryTotal);
					ResultSet rs43 = statement.executeQuery("select count(*) from Memory_Performance where time < '"+testEndTime+"' ");
					String memoryRows = rs43.getString("count(*)");
					double rowsMemory = Double.parseDouble(memoryRows);
					
					double memoryAvg0 = (totalMemory / rowsMemory) ;
					double memoryAvg = Double.valueOf(df.format(memoryAvg0));
					Constants.avgMemory = String.valueOf(memoryAvg);
					System.out.println(Constants.avgMemory);
					
					//For network bandwidth received
					ResultSet rs5 = statement.executeQuery("select SUM(network_usage_in) from Network_Performance where time < '"+testEndTime+"' "); //'"+testStartTime+"'
					Constants.networkIn = rs5.getString("SUM(network_usage_in)");
					System.out.println(Constants.networkIn);
					
					//For network bandwidth sent
					ResultSet rs6 = statement.executeQuery("select SUM(network_usage_out) from Network_Performance where time < '"+testEndTime+"' ");
					Constants.networkOut = rs6.getString("SUM(network_usage_out)");
					System.out.println(Constants.networkOut);
					
					incrementRowNumber();
					htTestResultDataMap.put("" + getCurrentRowNumber(), new Object[] {testMethodName, Constants.maxCPU, Constants.minCPU, Constants.avgCPU, Constants.maxMemory, Constants.minMemory, Constants.avgMemory, Constants.networkIn, Constants.networkOut, durationTestRun});
				
				} else {

				//For max CPU
				ResultSet rs1 = statement.executeQuery("select * from CPU_Performance where time between '"+testStartTime+"' AND '"+testEndTime+"' order by cpu_usage desc"); //'"+testStartTime+"'
				Constants.maxCPU = rs1.getString("cpu_usage");
				System.out.println(Constants.maxCPU);
				
				//For min CPU
				ResultSet rs2 = statement.executeQuery("select * from CPU_Performance where time between '"+testStartTime+"' AND '"+testEndTime+"' order by cpu_usage asc");
				Constants.minCPU = rs2.getString("cpu_usage");
				System.out.println(Constants.minCPU);
				
				//For Average CPU
				ResultSet rs12 = statement.executeQuery("select SUM(cpu_usage) from CPU_Performance where time between '"+testStartTime+"' AND '"+testEndTime+"' ");
				String cpuTotal = rs12.getString("SUM(cpu_usage)");
				double totalCPU = Double.parseDouble(cpuTotal);
				ResultSet rs21 = statement.executeQuery("select count(*) from CPU_Performance where time between '"+testStartTime+"' AND '"+testEndTime+"' ");
				String cpuRows = rs21.getString("count(*)");
				double rowsCPU = Double.parseDouble(cpuRows);
				
				double cpuAvg0 = (totalCPU / rowsCPU) ;
				double cpuAvg = Double.valueOf(df.format(cpuAvg0));
				Constants.avgCPU = String.valueOf(cpuAvg);
				System.out.println(Constants.avgCPU);
				
				//For max Memory
				ResultSet rs3 = statement.executeQuery("select * from Memory_Performance where time between '"+testStartTime+"' AND '"+testEndTime+"' order by memory_usage desc"); //'"+testStartTime+"'
				Constants.maxMemory = rs3.getString("memory_usage");
				System.out.println(Constants.maxMemory);
				
				//For min Memory
				ResultSet rs4 = statement.executeQuery("select * from Memory_Performance where time between '"+testStartTime+"' AND '"+testEndTime+"' order by memory_usage asc");
				Constants.minMemory = rs4.getString("memory_usage");
				System.out.println(Constants.minMemory);
				
				//For average Memory
				ResultSet rs34 = statement.executeQuery("select SUM(memory_usage) from Memory_Performance where time between '"+testStartTime+"' AND '"+testEndTime+"' ");
				String memoryTotal = rs34.getString("SUM(memory_usage)");
				double totalMemory = Double.parseDouble(memoryTotal);
				ResultSet rs43 = statement.executeQuery("select count(*) from Memory_Performance where time between '"+testStartTime+"' AND '"+testEndTime+"' ");
				String memoryRows = rs43.getString("count(*)");
				double rowsMemory = Double.parseDouble(memoryRows);
				
				double memoryAvg0 = (totalMemory / rowsMemory) ;
				double memoryAvg = Double.valueOf(df.format(memoryAvg0));
				Constants.avgMemory = String.valueOf(memoryAvg);
				System.out.println(Constants.avgMemory);
				
				//For network bandwidth received
				ResultSet rs5 = statement.executeQuery("select SUM(network_usage_in) from Network_Performance where time between '"+testStartTime+"' AND '"+testEndTime+"'"); //'"+testStartTime+"'
				Constants.networkIn = rs5.getString("SUM(network_usage_in)");
				System.out.println(Constants.networkIn);
				
				//For network bandwidth sent
				ResultSet rs6 = statement.executeQuery("select SUM(network_usage_out) from Network_Performance where time between '"+testStartTime+"' AND '"+testEndTime+"'");
				Constants.networkOut = rs6.getString("SUM(network_usage_out)");
				System.out.println(Constants.networkOut);
				
				incrementRowNumber();
				htTestResultDataMap.put("" + getCurrentRowNumber(), new Object[] {testMethodName, Constants.maxCPU, Constants.minCPU, Constants.avgCPU, Constants.maxMemory, Constants.minMemory, Constants.avgMemory, Constants.networkIn, Constants.networkOut, durationTestRun});
			}
			}
		}
		catch(SQLException e)
		{
			// if the error message is "out of memory", 
			// it probably means no database file is found
			System.err.println(e.getMessage());
		}
		finally
		{
			reportEntry(port, udid);
			System.out.println("Performance report generated successfully");
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
	public static void reportEntry(String port, String udid) throws Exception {
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
			FileOutputStream out = new FileOutputStream(new File(Constants.projectOutputPath+"PGSDK-Android-PerformanceReport"+udid+".xls"));
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
}