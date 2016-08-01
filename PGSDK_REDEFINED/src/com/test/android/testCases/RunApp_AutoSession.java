package com.test.android.testCases;

import java.util.HashMap;

import io.appium.java_client.AndroidKeyCode;
import io.appium.java_client.MobileElement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.test.android.Base;
import com.test.android.Utilities;


public class RunApp_AutoSession extends Base{

	@Test (priority=0, description = "Stay idle on foreground for 30 seconds. Install 2nd sample app if not already installed.")
	public void idle_in_foreground() throws Exception {
		//Idle @foreground after App Launch
		Utilities.sleepForSeconds(5);
//		sleepForMinutes(1);
		//To check if another application is installed. If not then install
//		driver.isAppInstalled("");
//		driver.installApp(Constants.appPath_2ndSample);
	}

//	@Test (priority=1, description = "Send app in background for to end current session")
//	public void app_in_background1() throws Exception {
//		//During background for 'backgroundSeconds' seconds
//		app_in_background_newSession();
//	}
//
//	@Test (priority=2, description = "Take app to foreground and remain idle for 30 seconds")
//	public void idle_in_foreground2() throws Exception {
//		//Idle @foreground after App Launch
//		sleepForSeconds(30);
//	}
//	
//	@Test (priority=3, description = "Send app in background without ending current session")
//	public void app_in_background2() throws Exception {
//		//During background for 'backgroundSeconds' seconds
//		app_in_background_sameSession();
//	}
//	
//	@Test (priority=4, description = "Take app to foreground and remain idle for 1 minute")
//	public void idle_in_foreground3() throws Exception {
//		//Idle @foreground after App Launch
//		sleepForMinutes(1);
//	}
//
//	@Test (priority=5, description = "Send app in background to end current session")
//	public void app_in_background3() throws Exception {
//		//During background for 'backgroundSeconds' seconds
//		app_in_background_newSession();
//	}
//	
//	@Test (priority=6, description = "Take app to foreground and remain idle for 1 minute")
//	public void idle_in_foreground4() throws Exception {
//		//Idle @foreground after App Launch
//		sleepForMinutes(1);
//	}
//	
//	@Test (priority=7, description = "Close app and wait for 30 seconds")
//	public void close_app() throws Exception {
//		driver.closeApp();
//		sleepForSeconds(30);
//	}
//
//	@Test (priority=8, description = "Launch app again and remain idle for 1 minute")
//	public void launchApp_fromHome() throws Exception {
//		//launch app and remain idle for some seconds
//		try{
//		driver.launchApp();
//		sleepForSeconds(5);
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//		}
//		sleepForMinutes(1);
//	}
//	
//	@Test (priority=9, description = "Send app in background without ending current session")
//	public void app_in_background4() throws Exception {
//		//During background for 'backgroundSeconds' seconds
//		app_in_background_sameSession();
//	}
//	
//	@Test (priority=10, description = "Take app to foreground and remain idle for 30 seconds")
//	public void idle_in_foreground6() throws Exception {
//		//Idle @foreground after App Launch
//		sleepForSeconds(30);
//	}
//	
//	@Test (priority=11, description = "Close app and wait for 3 seconds without ending current session")
//	public void close_app1() throws Exception {
//		driver.closeApp();
//		sleepForSeconds(3);
//	}
//
//	@Test (priority=12, description = "Launch app again and remain idle for 30 seconds")
//	public void launchApp_fromHome1() throws Exception {
//		//launch app and remain idle for some seconds
//		try{
//		driver.launchApp();
//		sleepForSeconds(5);
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//		}
//		sleepForSeconds(30);
//	}
//	
//	@Test (priority=13, description = "Move to another activty within app and remain idle for 30 seconds")
//	public void open_anotherActivty() throws Exception {
//		driver.findElement(By.name("")).click();
//		sleepForSeconds(30);
//	}
//	
//	@Test (priority=14, description = "Send app in background to end current session")
//	public void app_in_background5() throws Exception {
//		//During background for 'backgroundSeconds' seconds
//		app_in_background_newSession();
//	}
	
	@Test (priority=15, description = "Change activty within app several times and remain idle for 30 seconds")
	public void changeActivties_withinApp() throws Exception {
//		driver.swipe(100, 200, 100, 800, 2);
		driver.swipe(200, 850, 200, 150, 10);
		Utilities.sleepForSeconds(10);
		driver.findElements(By.className("android.widget.Button")).get(8).click();
		Utilities.sleepForSeconds(10);
		driver.sendKeyEvent(AndroidKeyCode.BACK);
	}
	
//	@Test (priority=16, description = "Send app in background without ending current session")
//	public void app_in_background6() throws Exception {
//		//During background for 'backgroundSeconds' seconds
//		app_in_background_sameSession();
//	}
//	
//	@Test (priority=17, description = "Take app to foreground and remain idle for 15 seconds")
//	public void idle_in_foreground7() throws Exception {
//		//Idle @foreground after App Launch
//		sleepForSeconds(15);
//	}
//	
//	@Test (priority=18, description = "Send app in background to end current session")
//	public void app_in_background7() throws Exception {
//		//During background for 'backgroundSeconds' seconds
//		app_in_background_newSession();
//	}
//	
////	@Test (priority=19, description = "Start another app")
////	public void open_anotherApp() throws Exception {
////		driver.startActivity("", "", null, null);
////	}
//	
//	
//	
//	@Test (priority=100, description = "Take app to foreground and remain idle for 3 minutes to get all entries in log.db properly")
//	public void idle_in_foregroundFinal() throws Exception {
//		//Idle @foreground after App Launch
//		sleepForMinutes(3);
//	}
}



