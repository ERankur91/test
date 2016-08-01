package com.test.android.testCases;

import io.appium.java_client.AndroidKeyCode;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import bsh.util.Util;

import com.test.android.Base;
import com.test.android.Constants;
import com.test.android.Utilities;

public class TestRunTimeMatrics extends Base
{
	
	@Test (priority=1, description="allow install app sensor, run app in foreground for 5 min")
	public void TC_01()
	{
		Utilities.sensorAlertOK();
		Utilities.sleepForMinutes(5);
	}
	
	@Test (priority=2, description="log 20 single events and remain idle in foreground for 2 min")
	public void TC_02()
	{
		List<WebElement> btn = driver.findElements(By.className("android.widget.Button"));
		
		for(int i=0; i<20; i++)
		{
			btn.get(3).click();
			Utilities.sleepForSeconds(1);
		}
		Utilities.sleepForMinutes(2);
	}
	
	@Test (priority=3, description="run app in background for 20 seconds, run app in foreground for 10s", invocationCount=2)
	public void TC_03()
	{
		Utilities.setForegroundTime();
		driver.runAppInBackground(30);
		Utilities.setBackgroundTime();
		Utilities.sleepForSeconds(10);
	}
	
	@Test (priority=4, invocationCount=2, description="log 3 single events, then run app in backgroud for 15s and stay idle in foreground for 5s")
	public void TC_04()
	{
		List<WebElement> btn = driver.findElements(By.className("android.widget.Button"));
		
		for(int i=0; i<3; i++)
		{
			btn.get(3).click();
			Utilities.sleepForSeconds(1);
		}
		Utilities.setForegroundTime();
		driver.runAppInBackground(15);
		Utilities.setBackgroundTime();
		Utilities.sleepForSeconds(5);
	}
	
	@Test (priority=5, description="log foreground and background event, and navigate to FG and BG in 30s interval for 3 mins")
	public void TC_05()
	{
		List<WebElement> btn = driver.findElements(By.className("android.widget.Button"));
		
		btn.get(15).click();
		
		for(int i=0; i<3; i++)
		{
			Utilities.setForegroundTime();
			driver.runAppInBackground(30);
			Utilities.setBackgroundTime();
			Utilities.sleepForSeconds(30);
		}
		btn.get(16).click();
	}
	
	@Test (priority=6, description="remain idle in foreground for 5 min")
	public void TC_06()
	{
		Utilities.sleepForMinutes(5);
	}
	
	@Test (priority=7, invocationCount=2, description="navigate to BG using back button, run app in background for 20s, run app in foreground for 5s")
	public void TC_07()
	{
		driver.sendKeyEvent(AndroidKeyCode.BACK);
		Utilities.setForegroundTime();
		Utilities.sleepForSeconds(20);
		Utilities.startActivityPGSDK();
		Utilities.setBackgroundTime();
		Utilities.sleepForSeconds(5);
	}
	
	@Test (priority=8, description="remain idle in background for 2 min")
	public void TC_08()
	{
		driver.sendKeyEvent(AndroidKeyCode.BACK);
		Utilities.setForegroundTime();
		Utilities.sleepForMinutes(2);
	}
	
	@Test (priority=9, description="remain idle in foreground for 2 min")
	public void TC_09()
	{
		Utilities.startActivityPGSDK();
		Utilities.setBackgroundTime();
		Utilities.sleepForMinutes(2);
	}
	
//	@Test (priority=10, description="navigate to BG using back button and switch to other wifi network")
//	public void TC_10()
//	{
//		driver.sendKeyEvent(AndroidKeyCode.BACK);
//		Utilities.setForegroundTime();
//		Utilities.sleepForSeconds(2);
//		Utilities.switchToOtherWiFi();
//	}
//	
//	@Test (priority=11, description="navigate to foreground and remain idle for 30s")
//	public void TC_11()
//	{
//		Utilities.startActivityPGSDK();
//		Utilities.setBackgroundTime();
//		Utilities.sleepForSeconds(30);
//	}
	
	@Test (priority=12, description="run app in background for 20 seconds, run app in foreground for 10s", invocationCount=2)
	public void TC_12()
	{
		Utilities.setForegroundTime();
		driver.runAppInBackground(30);
		Utilities.setBackgroundTime();
		Utilities.sleepForSeconds(10);
	}
	
	@Test (priority=13, description="log foreground and background event, and navigate to FG and BG in 30s interval for 3 mins")
	public void TC_13()
	{
		List<WebElement> btn = driver.findElements(By.className("android.widget.Button"));
		
		btn.get(15).click();
		
		for(int i=0; i<3; i++)
		{
			Utilities.setForegroundTime();
			driver.runAppInBackground(30);
			Utilities.setBackgroundTime();
			Utilities.sleepForSeconds(30);
		}
		btn.get(16).click();
	}
	
	@Test (priority=14, description="remain idle in background for 2 min")
	public void TC_14()
	{
		Utilities.setForegroundTime();
		driver.runAppInBackground(120);
		Utilities.setBackgroundTime();
	}
	
	@Test (priority=15, description="remain idle in foreground for 2 min")
	public void TC_15()
	{
		Utilities.sleepForMinutes(2);
	}
	
	@Test (priority=16, description="navigate to background and switch off the wifi network")
	public void TC_16()
	{
		driver.sendKeyEvent(AndroidKeyCode.BACK);
		Utilities.setForegroundTime();
		Utilities.toggleWiFi();
		Constants.wifiEnd = Utilities.getCurrentDateTime();
		long temp = Constants.wifiEnd - Constants.wifiStart;
		Constants.wifiDuration = Constants.wifiDuration + temp;
	}
	
	@Test (priority=17, description="navigate to foreground and remain idle for 30s")
	public void TC_17()
	{
		Utilities.startActivityPGSDK();
		long time = Utilities.getCurrentDateTime();
		Constants.noInternetstart = time;
		Utilities.setBackgroundTime();
		Utilities.sleepForSeconds(30);
	}
	
	@Test (priority=18, description="run app in background for 20 seconds, run app in foreground for 10s", invocationCount=2)
	public void TC_18()
	{
		Utilities.setForegroundTime();
		driver.runAppInBackground(20);
		Utilities.setBackgroundTime();
		Utilities.sleepForSeconds(10);
	}
	
	@Test (priority=19, description="run app in background for 20 seconds, run app in foreground for 10s, using back button", invocationCount=2)
	public void TC_19()
	{
		driver.sendKeyEvent(AndroidKeyCode.BACK);
		Utilities.setForegroundTime();
		Utilities.sleepForSeconds(20);
		Utilities.startActivityPGSDK();
		Utilities.setBackgroundTime();
		Utilities.sleepForSeconds(10);
	}
	
	@Test (priority=20, description="log foreground and background event, and navigate to FG and BG in 30s interval for 3 mins")
	public void TC_20()
	{
		List<WebElement> btn = driver.findElements(By.className("android.widget.Button"));
		
		btn.get(15).click();
		for(int i=0; i<3; i++)
		{
			Utilities.setForegroundTime();
			driver.runAppInBackground(30);
			Utilities.setBackgroundTime();
			Utilities.sleepForSeconds(30);
		}
		btn.get(16).click();
	}
	
	@Test (priority=21, description="navigate to background and switch ON wifi network")
	public void TC_21()
	{
		driver.sendKeyEvent(AndroidKeyCode.BACK);
		Utilities.setForegroundTime();
		Utilities.sleepForSeconds(1);
		Utilities.toggleWiFi();
	}
	
	@Test (priority=22, description="remain idle in foregound for 2 min")
	public void TC_22()
	{
		long time = Utilities.getCurrentDateTime();
		Constants.noInternetEnd = time;
		Constants.wifiStart = time;
		Constants.noInternetDuration = Constants.noInternetEnd - Constants.noInternetstart;
		Utilities.startActivityPGSDK();
		Utilities.setBackgroundTime();
		Utilities.sleepForMinutes(2);
	}
	
	@Test (priority=23, invocationCount=4, description="navigate to BG for 5s and run app in FG for 5s")
	public void TC_23()
	{
		Utilities.setForegroundTime();
		driver.runAppInBackground(5);
		Utilities.setBackgroundTime();
		Utilities.sleepForSeconds(5);
	}
	
	@Test (priority=24, invocationCount=4, description="navigate to BG for 5s and run app in FG for 5s, using back button")
	public void TC_24()
	{
		driver.sendKeyEvent(AndroidKeyCode.BACK);
		Utilities.setForegroundTime();
		Utilities.sleepForSeconds(5);
		Utilities.startActivityPGSDK();
		Utilities.setBackgroundTime();
		Utilities.sleepForSeconds(5);
	}
	
	@Test (priority=25, description="run app in background and foreground for 3 min")
	public void TC_25()
	{
		driver.sendKeyEvent(AndroidKeyCode.BACK);
		Utilities.setForegroundTime();
		Utilities.sleepForMinutes(3);
		Utilities.startActivityPGSDK();
		Utilities.setBackgroundTime();
		Utilities.sleepForMinutes(3);
	}

}
