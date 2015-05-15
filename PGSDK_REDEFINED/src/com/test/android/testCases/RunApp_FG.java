package com.test.android.testCases;

import org.testng.annotations.Test;

import com.test.android.Base;
import com.test.android.Utilities;


public class RunApp_FG extends Base{

	@Test (priority=0, description = "Set event timer to 500ms and stay idle on foreground for 30 seconds")
	public void idle_in_foreground() throws Exception {
		//To set the timer. Events will take place after the difference of that timer value when applicable.
//		setEventTime();
		//Idle @foreground after App Launch
		Utilities.sleepForSeconds(30);
//		sleepForMinutes(1);
	}

	@Test (priority=1, description = "Log events 2 times for 1 minute with 30 seconds difference")
	public void event_logging1() throws Exception {
		//During Event logging 'n' times
		Utilities.event_logging();
	}

	@Test (priority=2, description = "Send app in background for 60 seconds")
	public void app_in_background1() throws Exception {
		//During background for 'backgroundSeconds' seconds
		Utilities.app_in_background();
	}

	@Test (priority=3, description = "Take app to foreground and remain idle for 30 seconds - 2nd Itteration")
	public void idle_in_foreground2() throws Exception {
		//Idle @foreground after App Launch
		Utilities.sleepForSeconds(30);
	}
//
	@Test (priority=4, description = "Log events 2 times for 1 minute with 30 seconds difference and then remain idle for 30 seconds - 3rd Itteration")
	public void event_logging2() throws Exception {
		Utilities.event_logging();
		Utilities.sleepForSeconds(30);
	}
	
	@Test (priority=5, description = "Send app in background for 60 seconds - 2nd Itteration")
	public void app_in_background2() throws Exception {
		//During background for 'backgroundSeconds' seconds
		Utilities.app_in_background();
	}
	
	@Test (priority=6, description = "Take app to foreground and remain idle for 3 minutes")
	public void idle_in_foreground3() throws Exception {
		//Idle @foreground after App Launch
		Utilities.sleepForMinutes(3);
	}

	@Test (priority=7, description = "Log events 2 times for 1 minute with 30 seconds difference and then remain idle for 30 seconds - 3rd Itteration")
	public void event_logging3() throws Exception {
		Utilities.event_logging();
		Utilities.sleepForSeconds(30);
	}
	
	@Test (priority=8, description = "Send app in background for 60 seconds - 3rd Itteration")
	public void app_in_background3() throws Exception {
		//During background for 'backgroundSeconds' seconds
		Utilities.app_in_background();
	}
	
	@Test (priority=9, description = "Take app to foreground and remain idle for 1 minute")
	public void idle_in_foreground4() throws Exception {
		//Idle @foreground after App Launch
		Utilities.sleepForMinutes(1);
	}
	
	@Test (priority=10, description = "Log events 4 times for 1 minute each with 30 seconds difference and then remain idle for 1 minute")
	public void event_logging4() throws Exception {
		Utilities.event_logging();
		Utilities.sleepForSeconds(30);
		Utilities.event_logging();
		Utilities.sleepForMinutes(1);
	}

//	@Test (priority=11, description = "Close app and wait for 30 seconds")
//	public void close_app() throws Exception {
//		driver.closeApp();
//		sleepForSeconds(30);
//	}
//
//	@Test (priority=12, description = "Launch app again and remain idle for 5 minutes")
//	public void launchApp_fromHome() throws Exception {
//		//launch app and remain idle for some seconds
//		try{
//		driver.launchApp();
//		sleepForSeconds(15);
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//		}
//		sleepForMinutes(5);
//	}
	
	@Test (priority=13, description = "Take app to foreground and remain idle for 3 minutes")
	public void idle_in_foreground6() throws Exception {
		//Idle @foreground after App Launch
		Utilities.sleepForMinutes(3);
	}
}



