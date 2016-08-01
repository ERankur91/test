package com.test.android.testCases;

import org.testng.annotations.Test;

import com.test.android.Base;
import com.test.android.Utilities;

public class RunApp_BG extends Base{

	@Test (priority=0, description = "Set event timer to 500ms and stay idle on foreground for 30 seconds")
	public void idle_in_foreground() throws Exception {
		//To set the timer. Events will take place after the difference of that timer value when applicable.
//		setEventTime();
		//Idle @foreground after App Launch
		Utilities.sleepForSeconds(30);
		//		sleepForMinutes(1);
	}

	@Test (priority=1, description = "Send app in background for 3 minutes with event logging enabled")
	public void app_in_background1() throws Exception {
		//During background for 'backgroundSeconds' seconds
		Utilities.app_in_background_with_loggingEnabled();
	}

	@Test (priority=2, description = "Take app to foreground and remain idle for 4 minutes")
	public void idle_in_foreground2() throws Exception {
		//Idle @foreground after App Launch
		Utilities.sleepForMinutes(4);
		//		sleepForSeconds(30);
	}
	//
	@Test (priority=3, description = "Send app in background for 3 minutes with event logging enabled - 2nd Itteration")
	public void app_in_background2() throws Exception {
		//During background for 'backgroundSeconds' seconds
		Utilities.app_in_background_with_loggingEnabled();
	}

	@Test (priority=4, description = "Take app to foreground and remain idle for 1 minute")
	public void idle_in_foreground3() throws Exception {
		//Idle @foreground after App Launch
		Utilities.sleepForMinutes(1);
		//		sleepForSeconds(10);
	}

	@Test (priority=5, description = "Send app in background for 3 minutes with event logging enabled - 3rd Itteration")
	public void app_in_background3() throws Exception {
		//During background for 'backgroundSeconds' seconds
		Utilities.app_in_background_with_loggingEnabled();
	}

	@Test (priority=6, description = "Take app to foreground and remain idle for 30 seconds")
	public void idle_in_foreground4() throws Exception {
		//Idle @foreground after App Launch
		Utilities.sleepForSeconds(30);
	}

//	@Test (priority=7, description = "Close app for 30 secons")
//	public void close_app() throws Exception {
//		driver.closeApp();
//		sleepForSeconds(30);
//	}
//
//	@Test (priority=8, description = "Launch app again and remain idle for 4 minutes")
//	public void launch_app2() throws Exception {
//		//launch app and remain idle for some seconds
//		try{
//			driver.launchApp();
//			sleepForSeconds(10);
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//		}
//		sleepForMinutes(4);
//	}

	@Test (priority=9, description = "Send app in background for 1 minute without event logging")
	public void app_in_background4() throws Exception {
		//During background for 'backgroundSeconds' seconds
		Utilities.app_in_background();
	}

	@Test (priority=10, description = "Take app to foreground and remain idle for 30 seconds")
	public void idle_in_foreground5() throws Exception {
		//Idle @foreground after App Launch
		//		sleepForMinutes(4);
		Utilities.sleepForSeconds(30);
	}

	@Test (priority=11, description = "Send app in background for 3 minutes with event logging enabled - 4th Itteration")
	public void app_in_background5() throws Exception {
		//During background for 'backgroundSeconds' seconds
		Utilities.app_in_background_with_loggingEnabled();
	}

	@Test (priority=12, description = "Take app to foreground and remain idle for 3 minutes")
	public void idle_in_foreground6() throws Exception {
		//Idle @foreground after App Launch
		Utilities.sleepForMinutes(3);
	}
}



