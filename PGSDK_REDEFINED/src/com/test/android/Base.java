package com.test.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.NetworkConnectionSetting;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.mobile.NetworkConnection;
import org.openqa.selenium.mobile.NetworkConnection.ConnectionType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Base 
{
	protected static AppiumDriver driver;
	public static String eventDifference = "500";
//	public static NetworkConnection mobileDriver = (NetworkConnection) driver;
	DesiredCapabilities capabilities = new DesiredCapabilities();
	
	public static String appType = "prod";   //always set app type [staging or prod]

	@BeforeClass
	@Parameters({ "port", "udid" })
	public void launch_app(String port, String udid) throws Exception {
		
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");
		capabilities.setCapability(CapabilityType.VERSION, "4.2");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Android Device");
		capabilities.setCapability("newCommandTimeout", "900000");
		capabilities.setCapability("deviceReadyTimeout", "15");
		capabilities.setCapability("app", Constants.appPath_Staging);	//always verify app name in constant class and generate_AdvID_Reset method
		capabilities.setCapability("launchTimeout", "30000");
		
		
		//For local devices
		if (port.equalsIgnoreCase("4723")) {
//			capabilities.setCapability("app", Constants.appPath4);
			capabilities.setCapability("bootstrap-port", "4724");
			capabilities.setCapability("udid", Constants.spiceAndroidOne);
			driver = new AppiumDriver(new URL("http://localhost:" + port
					+ "/wd/hub"), capabilities);
			Constants.startTime = Utilities.getCurrentDateTime();
			Constants.wifiStart = Constants.startTime;
			Constants.foregroundStartTime = Constants.startTime;
			System.out.println("Start time: "+ Constants.startTime);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		}

		if (port.equalsIgnoreCase("4733")) {
			capabilities.setCapability("bootstrap-port", "4734");
			capabilities.setCapability("udid", Constants.spiceAndroidOne);
			driver = new AppiumDriver(new URL("http://localhost:" + port
					+ "/wd/hub"), capabilities);
			long time = Utilities.getCurrentDateTime();
			Constants.startTime = time;
			Constants.wifiStart = Constants.startTime;
			Constants.foregroundStartTime = time;
			System.out.println("Start time: "+ Constants.startTime);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		}

		if (port.equalsIgnoreCase("4743")) {
//			capabilities.setCapability("app", Constants.appPath3);
			capabilities.setCapability("bootstrap-port", "4744");
			capabilities.setCapability("udid", Constants.desire310);
			driver = new AppiumDriver(new URL("http://localhost:" + port
					+ "/wd/hub"), capabilities);
			Constants.startTime = Utilities.getCurrentDateTime();
			Constants.wifiStart = Constants.startTime;
			Constants.foregroundStartTime = Constants.startTime;
			System.out.println("Start time: "+ Constants.startTime);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
	
		}

		if (port.equalsIgnoreCase("4753")) {
//			capabilities.setCapability("app", Constants.appPath3);
			capabilities.setCapability("bootstrap-port", "4754");
			capabilities.setCapability("udid", Constants.karbonnS2Plus);
			driver = new AppiumDriver(new URL("http://localhost:" + port
					+ "/wd/hub"), capabilities);
			Constants.startTime = Utilities.getCurrentDateTime();
			Constants.wifiStart = Constants.startTime;
			Constants.foregroundStartTime = Constants.startTime;
			System.out.println("Start time: "+ Constants.startTime);
		}
		
		//For remote devices
		if (port.equalsIgnoreCase("5723")) {
			capabilities.setCapability("bootstrap-port", "5724");
			capabilities.setCapability("udid", Constants.karbonnS2Plus);
			driver = new AppiumDriver(new URL("http://localhost:" + port
					+ "/wd/hub"), capabilities);	
			Constants.startTime = Utilities.getCurrentDateTime();
			Constants.wifiStart = Constants.startTime;
			Constants.foregroundStartTime = Constants.startTime;
			System.out.println("Start time: "+ Constants.startTime);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		}
		
		if (port.equalsIgnoreCase("5733")) {
			capabilities.setCapability("bootstrap-port", "5734");
			capabilities.setCapability("udid", Constants.desire310);
			driver = new AppiumDriver(new URL("http://localhost:" + port
					+ "/wd/hub"), capabilities);
			Constants.startTime = Utilities.getCurrentDateTime();
			Constants.wifiStart = Constants.startTime;
			Constants.foregroundStartTime = Constants.startTime;
			System.out.println("Start time: "+ Constants.startTime);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		}
		
		if(port.equalsIgnoreCase("5743")) {
			capabilities.setCapability("bootstrap-port", "5744");
			capabilities.setCapability("udid", Constants.udidSpice1);
			driver = new AppiumDriver(new URL("http://localhost:" + port
					+ "/wd/hub"), capabilities);	
			Constants.startTime = Utilities.getCurrentDateTime();
			Constants.wifiStart = Constants.startTime;
			Constants.foregroundStartTime = Constants.startTime;
			System.out.println("Start time: "+ Constants.startTime);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		}

	}
	
	/*
	
	@Test
	public void rough()
	{
		
		driver.findElements(By.className("android.widget.Button")).get(22).click();
		
//		Set<String> s = driver.getContextHandles();
//		System.out.println();
		
		for(String a : driver.getContextHandles())
		{
			System.out.println(a);
			driver.context(a);
			List<WebElement> e = driver.findElements(By.className("android.widget.EditText"));
			e.get(0).sendKeys("manish@letsgomo.com");
			e.get(1).sendKeys("turbo124");
		}
		
		
		
				
//		driver.findElements(By.className("android.widget.Button")).get(20).click();
//		Utilities.sleepForSeconds(5);
//		String JSON = driver.findElements(By.className("android.widget.TextView")).get(1).getText();
//		
//		System.out.println(JSON);
//		 
//		try {
//			JSONObject obj = new JSONObject(JSON);
//			 final JSONObject counters = obj.getJSONObject("counters");
//			 
//			 String sessionCount = counters.getString("Session count");
//			 String installedAppEventsCount = counters.getString("Installed App event count");
//			 String runningAppEventsCount = counters.getString("Running App event count");
//			 System.out.println(sessionCount);
//			    final int n = counters.length();
//			    for (int i = 0; i < n; ++i) {
//			      final JSONObject person = counters.getJSONObject(i);
//			      System.out.println(person.getString("Session count"));
//			      System.out.println(person.getString("Installed App event count"));
//			      System.out.println(person.getString("Background fetch event count"));
//			      System.out.println(person.getDouble("Running App event count"));
//			    }
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	*/
		
//		Utilities.sleepForSeconds(5);
//	}

	
	@AfterMethod(alwaysRun = true)
	public void getInfo(ITestResult result) throws Exception {
		String myMethodName = result.getMethod().getDescription();
		System.out.println("Method Description = "+myMethodName);
		
		Long start = result.getStartMillis();
		System.out.println("Start = " + start);
		
		String startTime = String.valueOf(start);
		System.out.println(startTime);
		
		Long end = result.getEndMillis();
		System.out.println("End = " + end);
		
		String endTime = String.valueOf(end);
		System.out.println(endTime);
		
		Long duration = (result.getEndMillis() - result.getStartMillis());
		System.out.println("Duration = " + duration);
		
		Long duration_seconds = (duration / 1000);
		System.out.println("Duration in seconds = " + duration_seconds);
		
		String durationTestRun = String.valueOf(duration_seconds);
		System.out.println(durationTestRun);
		//		String myMethodName = result.getMethod().getMethodName();
		
		String methodDescrption = result.getMethod().getDescription();
		Constants.methodDescriptionList.add(methodDescrption);

		MethodTimeTracker.get().track(startTime, endTime, myMethodName,
				durationTestRun);
	}

	
	
	
	@AfterClass(alwaysRun = true)
	@Parameters({ "port", "udid" })
	public void tearDown(String port, String udid) throws IOException {
		Utilities.sleepForMinutes(1);
		List<WebElement> button = driver.findElements(By.className("android.widget.Button"));
		button.get(8).click();
		Utilities.toggleWiFi();
		Utilities.startActivityPGSDK();
		Utilities.toggleWiFi();
		Utilities.startActivityPGSDK();
		Utilities.sleepForSeconds(1);
		button.get(20).click();
		Utilities.sleepForSeconds(2);
		Utilities.setForegroundTime();
		Constants.wifiEnd = Utilities.getCurrentDateTime();
		long temp = Constants.wifiEnd - Constants.wifiStart;
		Constants.wifiDuration = Constants.wifiDuration + temp;
		Constants.totalSessionDuration = Constants.totalForegroundTime + Constants.totalBackgroundTime;
		System.out.println("totalForegroundTime: "+Constants.totalForegroundTime);
		System.out.println("totalBackgroundTime: "+Constants.totalBackgroundTime);
		System.out.println("totalSessionDuration: "+Constants.totalSessionDuration);
		Constants.runTimeMatricsJSON = driver.findElements(By.className("android.widget.TextView")).get(1).getText();
		Utilities.sleep(500);
		
		try {
			GenerateReportsRTM.getMatricsKeyValues();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Utilities.sleepForSeconds(30);
//		driver.findElements(By.className("android.widget.Button")).get(3).click();
//		sleepForSeconds(15);
//		driver.findElements(By.className("android.widget.Button")).get(3).click();
//		sleepForSeconds(15);
		

		byte[] perf_logs = driver
				.pullFile("mnt/sdcard/com_letsgomo_pgsdk/PersonaGraph_Performance_"
						+ udid + ".sqlite");
		String path = Constants.fileDownloadDirectory
				+ "PersonaGraph_Performance_" + udid + ".sqlite";
		FileOutputStream fos = new FileOutputStream(path);
		fos.write(perf_logs);
		fos.close();

		byte[] logs = driver.pullFile("mnt/sdcard/com_letsgomo_pgsdk/logs_"
				+ udid + ".sqlite");
		String path1 = Constants.fileDownloadDirectory + "logs_"
				+ udid + ".sqlite";
		FileOutputStream fos1 = new FileOutputStream(path1);
		fos1.write(logs);
		fos1.close();

		//Utilities.sleepForSeconds(30);
/*
				 try {
				 DownloadLogFileFromSFTP.downloadFromSFTP(port, udid);
				 } catch (Exception e) {
				 System.out.println("Error in downloading log files");
				 }
*/
		
		/*		
 		try {
			GeneratePerformanceReport.generate_performanceReport(port, udid);
		} catch (Exception e) {
			System.out.println("Error in generating performance report");
		}
		 */		
		
		try {
			GenerateReportsRTM.generate_RTM_Report(port, udid);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		try {
			GetEventsFromLogsDB.generate_eventComparisonReport(port, udid);
		} catch (Exception e) {
			System.out.println("Error in generating event report");
		}
		*/
		
		/*
		try {
			GetEventsFromLogsDB.generate_AdvID_Reset(port, udid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error in generating report");
		}
		*/
		
//		GenerateSessionReport.generate_SessionReport(port, udid);
		// GetEventsLoggedTimeFromLogsDB.get_eventLoggedTime(port, udid);
		// Email.sendEmail(port, udid);
		driver.resetApp();
		driver.quit();
	}
	

}
