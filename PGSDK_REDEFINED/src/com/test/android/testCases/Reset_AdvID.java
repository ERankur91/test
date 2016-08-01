package com.test.android.testCases;

import io.appium.java_client.AppiumDriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.test.android.Base;
import com.test.android.Constants;
import com.test.android.Utilities;

public class Reset_AdvID extends Base
{
	@AfterMethod
	public void afterMethod(ITestResult result)		//gets method description
	{
		String methodDescrption = result.getMethod().getDescription();
		Constants.methodDescriptionList.add(methodDescrption);
	}
	
	@Test (priority=0, description="wait for 10s")
	public void TC_01()
	{
		Utilities.sleepForSeconds(5);
		Utilities.sensorAlertOK();
		Utilities.sleepForSeconds(10);
	}
	
	@Test (priority=1, description="run app in background for more than 10s")
	public void TC_02()
	{
		driver.sendKeyEvent(4);
		Utilities.sleepForSeconds(10);
		driver.startActivity("com.letsgomo.pgsdk", "com.letsgomo.pgsdk.SampleActivity", null, null);
		Utilities.sleepForSeconds(5);
	}
	
	@Test (priority=2, description="logging foreground events then wait for more than 10s")
	public void TC_03()
	{
		List<WebElement> button = driver.findElements(By.className("android.widget.Button"));
		button.get(4).click();
		Utilities.sleepForSeconds(15);
		button.get(5).click();
		Utilities.sleepForSeconds(30);
	}
	
	@Test (priority=3, description="run app in background for 20s")
	public void TC_04()
	{
		driver.sendKeyEvent(4);
		Utilities.sleepForSeconds(30);
		driver.startActivity("com.letsgomo.pgsdk", "com.letsgomo.pgsdk.SampleActivity", null, null);
		
	}
	
	@Test (priority=4, description="run app in foreground for 10s")
	public void TC_05()
	{
		Utilities.sleepForSeconds(10);
	}
	
	@Test (priority=5, description="run app in background for 15s and get advertiser ID")
	public void TC_06()
	{
		driver.sendKeyEvent(4);
		Utilities.sleepForSeconds(15);
		Constants.oldAdvtID = Utilities.getAdvtID();
		System.out.println(Constants.oldAdvtID);
		driver.startActivity("com.letsgomo.pgsdk", "com.letsgomo.pgsdk.SampleActivity", null, null);
		Utilities.sleepForSeconds(2);
	}
	
	@Test (priority=6, description="run app in foreground for 15s")
	public void TC_07()
	{
		Utilities.sleepForSeconds(15);
	}
	

	@Test (priority=7, description="resetting the google advertising ID")
	public void TC_08()
	{
		driver.startActivity("com.google.android.gms", "com.google.android.gms.ads.settings.AdsSettingsActivity", null, null);
		
		WebElement ads = driver.findElement(By.name("Reset advertising ID"));
		ads.click();
		WebElement ok = driver.findElement(By.name("OK"));
		ok.click();
	}
	
	@Test (priority=8, description="kill application")
	public void TC_09()
	{
		driver.closeApp();
		Utilities.sleepForSeconds(20);
	}
	
	@Test (priority=9, description="launch application")
	public void TC_10()
	{
		driver.launchApp();
		Utilities.sleepForSeconds(3);
		Utilities.sensorAlertOK();
	}
	
	@Test (priority=10, description="run app in foreground for 10s")
	public void TC_11()
	{
		Utilities.sleepForSeconds(15);
	}
	
	@Test (priority=11, description="run app in background for more than 10s")
	public void TC_12()
	{
		
		driver.sendKeyEvent(4);
		Utilities.sleepForSeconds(15);
		driver.startActivity("com.letsgomo.pgsdk", "com.letsgomo.pgsdk.SampleActivity", null, null);
		Utilities.sleepForSeconds(15);
	}
	
	@Test (priority=12, description="navigate to FG and logging foreground events then wait for more than 10s")
	public void TC_13()
	{
		List<WebElement> button = driver.findElements(By.className("android.widget.Button"));
		button.get(4).click();
		Utilities.sleepForSeconds(15);
		button.get(5).click();
		Utilities.sleepForSeconds(30);
	}
	
	@Test (priority=13, description="run app in background for 40s")
	public void TC_14()
	{
		driver.sendKeyEvent(4);
		Utilities.sleepForSeconds(40);
		driver.startActivity("com.letsgomo.pgsdk", "com.letsgomo.pgsdk.SampleActivity", null, null);
		Utilities.sleepForSeconds(2);
	}
	
	@Test (priority=14, description="run app in foreground for 20s")
	public void TC_15()
	{
		Utilities.sleepForSeconds(20);
	}
	
}
