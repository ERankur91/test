package com.test.android.testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.android.Base;
import com.test.android.GetEventsFromLogsDB;
import com.test.android.Utilities;

public class SensorState extends Base
{
	@Test (priority=0, description="Persistance of App/Running sensor and installed app sensor when user is navigated to background and then foreground")
	public void TC_01()
	{
		String expectedResult1, expectedResult2;
		String actualResult1, actualResult2;
		
		Utilities.sleepForSeconds(20);
		expectedResult1 = driver.findElement(By.id("android.widget.checkbox:id/app_runningSenssor")).getText();
		actualResult1 = driver.findElement(By.id("android.widget.checkbox:id/installedappSenssor")).getText();
	
		driver.runAppInBackground(10);
		
		expectedResult2 = driver.findElement(By.id("android.widget.checkbox:id/app_runningSenssor")).getText();
		actualResult2 = driver.findElement(By.id("android.widget.checkbox:id/installedappSenssor")).getText();
		
		if(expectedResult1.equalsIgnoreCase(actualResult1))
		{
			GetEventsFromLogsDB.putResult("Pass");
			Assert.assertTrue(true);
		}
		else
		{
			GetEventsFromLogsDB.putResult("Fail");
			Assert.assertTrue(false);
		}
		if(expectedResult2.equalsIgnoreCase(actualResult2))
		{
			GetEventsFromLogsDB.putResult("Pass");
			Assert.assertTrue(true);
		}
		else
		{
			GetEventsFromLogsDB.putResult("Fail");
			Assert.assertTrue(false);
		}
	}

}
