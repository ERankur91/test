package com.test.android;

import io.appium.java_client.AndroidKeyCode;
import io.appium.java_client.MobileElement;
import io.appium.java_client.NetworkConnectionSetting;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;

public class Utilities extends Base
{
	
	
////	public static void getMatricsValue(JSONArray arrayName)
////	{
////		int length = arrayName.length();
////		for(int i =0; i<length; i++)
////		{
////			JSONObject obj = arrayName.optJSONObject(i);
////			Iterator<String> iter = obj.keys();
////			while(iter.hasNext())
////			{
////				String key = iter.next();
////				String value = obj.optString(key);
////				switch (key) {
////				case GenerateReportsRTM.total_iap_event_count:
////					GenerateReportsRTM.total_iap_event_count = value;
////					break;
////				case GenerateReportsRTM.total_rap_event_count:
////					GenerateReportsRTM.total_rap_event_count = value;
////					break;
////				case GenerateReportsRTM.total_session_count:
////					GenerateReportsRTM.total_session_count = value;
////					break;
////				case GenerateReportsRTM.fb_token_shared:
////					GenerateReportsRTM.fb_token_shared = value;
////					break;
////				case GenerateReportsRTM.total_3g_dur:
////					GenerateReportsRTM.total_3g_dur = value;
////					break;
////				case GenerateReportsRTM.no_intrnt_conn_duration:
////					GenerateReportsRTM.no_intrnt_conn_duration = value;
////					break;
////				case GenerateReportsRTM.wifi_duration:
////					GenerateReportsRTM.wifi_duration = value;
////					break;
////				case GenerateReportsRTM.total_session_duration:
////					GenerateReportsRTM.total_session_duration = value;
////					break;
////				case GenerateReportsRTM.total_service_run_duration:
////					GenerateReportsRTM.total_service_run_duration = value;
////					break;
////				default:
////					break;
////				}
////				System.out.println(key+": "+value);
////			}
////		}
//		
//		
//	}
	
	
	public static void setNetworkState(String connection)
	{
		NetworkConnectionSetting NONE = new NetworkConnectionSetting(false, false, false);
		NetworkConnectionSetting AIRPLANE = new NetworkConnectionSetting(true, false, false);
		NetworkConnectionSetting WIFI = new NetworkConnectionSetting(false, true, false);
		NetworkConnectionSetting DATA = new NetworkConnectionSetting(false, false, true);
		NetworkConnectionSetting WIFI_DATA = new NetworkConnectionSetting(false, true, true);
		
		if(connection.equalsIgnoreCase("NONE"))
		{
			driver.setNetworkConnection(NONE);
		}
		else if(connection.equalsIgnoreCase("AIRPLANE"))
		{
			driver.setNetworkConnection(AIRPLANE);
		}
		else if(connection.equalsIgnoreCase("WIFI"))
		{
			driver.setNetworkConnection(WIFI);
		}
		else if(connection.equalsIgnoreCase("DATA"))
		{
			driver.setNetworkConnection(DATA);
		}
		else if(connection.equalsIgnoreCase("WIFI_DATA"))
		{
			driver.setNetworkConnection(WIFI_DATA);
		}
		else
		{
			
		}
	}
	
	public static NetworkConnectionSetting getNetworkType()
	{
		return driver.getNetworkConnection();
	}
	
	
	public static void toggleWiFi()
	{
		driver.startActivity("com.android.settings", "com.android.settings.wifi.WifiSettings", null, null);
		WebElement wifiSwitch = driver.findElement(By.className("android.widget.Switch"));
		wifiSwitch.click();
		driver.sendKeyEvent(AndroidKeyCode.HOME);
		Utilities.sleepForSeconds(1);
	}
	
	public static void switchToOtherWiFi()
	{
		driver.startActivity("com.android.settings", "com.android.settings.wifi.WifiSettings", null, null);
		
		Utilities.sleepForSeconds(1);
		List<WebElement> wifiNetworks = driver.findElements(By.className("android.widget.RelativeLayout"));
		wifiNetworks.get(1).click();
		Utilities.sleepForSeconds(1);
		driver.findElement(By.name("Connect")).click();
		Utilities.sleepForSeconds(10);
		driver.sendKeyEvent(AndroidKeyCode.HOME);
		Utilities.sleepForSeconds(5);
		
	}
	
	public static void startActivityPGSDK()
	{
		driver.startActivity("com.letsgomo.pgsdk", "com.letsgomo.pgsdk.SampleActivity", null, null);
		Utilities.sleep(200);
	}
	
	public static long getCurrentDateTime()
	{
		long time = System.currentTimeMillis();
		long timeSeconds = TimeUnit.MILLISECONDS.toSeconds(time);
		return timeSeconds;
	}
	
	public static void setForegroundTime()
	{
		long time = Utilities.getCurrentDateTime();
		Constants.foregroundEndTime = time;
		long temp = Constants.foregroundEndTime - Constants.foregroundStartTime;
		Constants.totalForegroundTime = Constants.totalForegroundTime + temp;
		Constants.backgroundStartTime = time;
	}
	
	public static void setBackgroundTime()
	{
		long time2 = Utilities.getCurrentDateTime();
		Constants.backgroundEndTime = time2;
		long temp2 = Constants.backgroundEndTime - Constants.backgroundStartTime;
		Constants.totalBackgroundTime = Constants.totalBackgroundTime + temp2;
		Constants.foregroundStartTime = time2;
	}
	
	public static void setEventTime() {
		//		driver.findElements(By.className("android.widget.EditText")).get(0).clear();
		//		driver.findElements(By.className("android.widget.EditText")).get(0).click();
		//		sleep(1000);
		//		clearTextFully(driver.findElement(By.className("android.widget.EditText")));
		//		sleep(1000);
		MobileElement textField = (MobileElement) driver.findElements(By.className("android.widget.EditText")).get(0);
		textField.setValue(eventDifference);
		//		driver.findElements(By.className("android.widget.EditText")).get(0).sendKeys(eventDifference);
		driver.findElements(By.className("android.widget.Button")).get(6).click();

	}

	public static void event_logging() throws Exception {
		//During Event logging 'n' times
		for (int i = 0; i < Constants.eventLoopFG; i++) {
			List<WebElement> btn = driver.findElements(By.className("android.widget.Button"));
			btn.get(0).click();
			//		sleepForSeconds(10);
			sleepForMinutes(1);
			btn.get(1).click();
			sleepForSeconds(30);
		}
	}
	
	public static String getAdvtID()
	{
		driver.startActivity("com.google.android.gms", "com.google.android.gms.ads.settings.AdsSettingsActivity", null, null);
		
		List<WebElement> text = driver.findElements(By.className("android.widget.TextView"));
		String c = text.get(5).getText().substring(21);
		return c;
	}
	
	public static void sensorAlertOK()
	{
		driver.findElements(By.className("android.widget.Button")).get(7).click();
		WebElement ok = driver.findElement(By.name("OK"));
		ok.click();
	}

	public static void app_in_background() throws Exception {
		//During background for 'backgroundSeconds' seconds
		driver.runAppInBackground(Constants.backgroundSecondsFG);
	}
	
	public static void app_in_background_sameSession() throws Exception {
		//During background for 'backgroundSeconds' seconds
		driver.runAppInBackground(5);
	}
	
	public static void app_in_background_newSession() throws Exception {
		//During background for 'backgroundSeconds' seconds
		driver.runAppInBackground(15);
	}

	public static void app_in_background_with_loggingEnabled() throws Exception {
		//During background for 'backgroundSeconds' seconds
		driver.findElements(By.className("android.widget.Button")).get(2).click();
		sleep(100);
		driver.runAppInBackground(Constants.backgroundSeconds);
	}

	public static void sleep(int numOfMiliSeconds) {
		try {
			Thread.sleep(numOfMiliSeconds * 1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void sleepForSeconds(int numOfSeconds) {
		try {
			Thread.sleep(numOfSeconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void sleepForMinutes(int numOfMinutes) {
		try {
			Thread.sleep(numOfMinutes * 60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	

	public static void clearTextFully(WebElement element) {
		int stringLength = element.getText().length();

		for (int i = 0; i < stringLength; i++) {
			driver.sendKeyEvent(22); // "KEYCODE_DPAD_RIGHT"
		}

		for (int i = 0; i < stringLength; i++) {
			driver.sendKeyEvent(67); // "KEYCODE_DEL"
		}
	}
	
	public void simpleSwipe_Downwards() throws Exception {
		((JavascriptExecutor) driver).executeScript("mobile: swipe", new HashMap<String, Double>() {{ 
			put("touchCount", (double) 1); 
			put("startX", (double) 200); 
			put("startY", (double) 300); 
			put("endX", (double) 200); 
			put("endY", (double) 1200); 
			put("duration", 1.0); }});
		sleepForSeconds(2);
	}

	public static void getMethodDetails(ITestResult result) 
	{
		String Method_Description = result.getMethod().getDescription();
		Constants.methodDescriptionList.add(Method_Description);
		Constants.Method_Name = result.getMethod().getMethodName();
		Constants.methodNameList.add(Constants.Method_Name);
		// TODO Auto-generated method stub
		
	}
	
	public static void resetAdverID()
	{
		driver.startActivity("com.google.android.gms", "com.google.android.gms.ads.settings.AdsSettingsActivity", null, null);
		WebElement ads = driver.findElement(By.name("Reset advertising ID"));
		ads.click();
		WebElement ok = driver.findElement(By.name("OK"));
		ok.click();
	}

}
