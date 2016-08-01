package com.test.android.testCases;

import java.io.FileOutputStream;
import java.util.List;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.internal.annotations.ITest;

import com.test.android.Base;
import com.test.android.Constants;
import com.test.android.GetEventsFromLogsDB;
import com.test.android.Utilities;

public class RunAppWithoutNetwork extends Base 
{
	
	@AfterMethod
	public void afterMethod(ITestResult result)		//gets method description
	{
		String methodDescrption = result.getMethod().getDescription();
		Constants.methodDescriptionList.add(methodDescrption);
	}
	
	@Test (priority=1, description="Network is available \nApp is launched \nWaited for 10-15s")
	public void TC_01()      // FG event logging when network is not available
	{
		
		Utilities.sleepForSeconds(12);
				
	}
	
	@Test (priority=2, description="Navigate to background")
	public void TC_02()
	{
		driver.sendKeyEvent(4);
		Utilities.sleepForSeconds(2);
	}
	
	@Test (priority=3, description="switch OFF WiFi")
	public void TC_03()
	{
		Utilities.toggleWiFi();
	}
	
	@Test (priority=4, description="navigate to foreground")
	public void TC_04()
	{
		Utilities.startActivityPGSDK();
	}
	
	@Test (priority=5, description="logged 5 single events")
	public void TC_05()
	{
		List<WebElement> btn = driver.findElements(By.className("android.widget.Button"));
		
		for(int i=0; i<5; i++)
		{
			btn.get(3).click();
			Utilities.sleepForSeconds(2);
		}
	}
	
	@Test (priority=6, description="wait for few seconds")
	public void TC_06()
	{
		Utilities.sleepForSeconds(10);
	}
	
	@Test (priority=7, description="run app in background for more than 10s")
	public void TC_07()
	{
		driver.runAppInBackground(15);
	}
	
	@Test (priority=8, description="run app in foreground for more than and log 5 events")
	public void TC_08()
	{
		List<WebElement> btn = driver.findElements(By.className("android.widget.Button"));
		
		for(int i=0; i<5; i++)
		{
			btn.get(3).click();
			Utilities.sleepForSeconds(2);
		}
	}
	
	@Test (priority=9, description="run app in background for more than 10s")
	public void TC_09()
	{
		driver.runAppInBackground(15);
	}
	
	@Test (priority=10, description="run app in foreground and wait for 10s")
	public void TC_10()
	{
		Utilities.sleepForSeconds(10);
	}
	
	@Test (priority=11, description="navigate to background")
	public void TC_11()
	{
		driver.sendKeyEvent(4);
		driver.sendKeyEvent(4);
	}
	
	@Test (priority=12, description="Switch ON WiFi network")
	public void TC_12()
	{
		Utilities.toggleWiFi();
	}
	
	@Test (priority=13, description="navigate to foreground")
	public void TC_13()
	{
		Utilities.startActivityPGSDK();
	}
	
	@Test (priority=14, description="wait for 1 min in foreground")
	public void TC_14()
	{
		Utilities.sleepForMinutes(1);
	}
	
	@Test (priority=15, description="run app in background for 1 min")
	public void TC_15()
	{
		driver.runAppInBackground(60);
	}
	
}
