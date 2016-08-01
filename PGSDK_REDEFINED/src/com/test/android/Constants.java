package com.test.android;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
//	public static String appPath = "https://dl.dropboxusercontent.com/u/59232873/withgoogle.apk";
	public static String appPath_Staging = System.getProperty("user.dir")+"/raw/WithoutGoExternalCall.apk";
	public static String appPath_Prod = System.getProperty("user.dir")+"/raw/SampleActivityProd.apk";
	public static String appPath_WithoutGoogle = System.getProperty("user.dir")+"/raw/prowithgoExternalnotcall_1.16_upgrade.apk";
	public static String appPath_upgradedVersion = System.getProperty("user.dir")+"/raw/prowithgoExternalnotcall_1.17.1-b4_upgrade.apk";
//	public static String appPath_2ndSample = System.getProperty("user.dir")+"/raw/automaticwithoutgoogle.apk";
	public static String appPath_WithGoogle = System.getProperty("user.dir")+"/raw/SampleActivity_WithGoogle.apk";
//	public static int port = 4723;
	
//	public static String appPath1 = "https://dl.dropboxusercontent.com/u/59232873/PGSDK_Sample_v1.14.0_b6_with_session.apk";
//	public static String appPath2 = "https://dl.dropboxusercontent.com/u/59232873/PGSDK_Sample_v1.14.0_b6_without_session.apk";
//	public static String appPath3 = "https://dl.dropboxusercontent.com/u/59232873/PGSDK_Sample_v1.13.0_b3_with_session.apk";
//	public static String appPath4 = "https://dl.dropboxusercontent.com/u/59232873/PGSDK_Sample_v1.13.0_b3_without_session.apk";
	
	public static int backgroundSeconds = 180;
	public static int backgroundSecondsBG = 210;
	public static int backgroundSecondsFG = 60;
	
	public static String udidSpice1 = "Mi498HA14100059";
	public static String udidMotoG2 = "TA93308TSQ";
	public static String udidMotoX = "TA64301TSI";
	public static String udidMotoE = "ZX1B32B3ZF";
	public static String udidTab4_1 = "16b2961a04ce34ca";
	public static String udidHonor6 = "X9L0214917002178";
	public static String udidLG_GPro = "LGE9884b4a260e";
	
	public static String udidNote2 = "4d002fb856a3604b";
	public static String udidE7 = "e1164a79";
	public static String canvas2 = "0123456789ABCDEF";
	public static String udidS3 = "32308e0da9d6b0d7";
	public static String udidMate = "021YHB7N34015907";  //021YHB7N34015907
	public static String udidNexus7New = "015d2ea4a84c0a10";
	public static String udidHTCOne = "FA29CW100265";
	
	public static String aceDuos = "42038057d6729100";
	public static String desire310 = "BI7DA6ZPHMMN458T";
	public static String karbonnS2Plus = "0123456789ABCDEF";
	public static String karbonnS25 = "GQOFAIG6BMWG89OR";
	public static String eluga = "8a161f0";
	public static String starDuos = "4203111ce2f19100";
	public static String lenovoS820 = "0123456789ABCDEF";
	public static String bolt = "0123456789ABCDEF";
	public static String redmiNote = "FAAYZDO7AQCM7TTS";
	public static String honor6 = "X9L0214917002178";
	public static String galaxyNexus = "014E05470C009015";
	public static String galaxyTab4 = "9aafec3a0403b527";
	public static String motoG = "ZX1D63PZBD";
	public static String HTCDesire310 = "BI7DA6ZPHMMN458T";
	public static String spiceAndroidOne = "Mi498HA14100059";
	public static String Nexus7 = "0a22f2d0";
	
	
	public static int eventLoop = 1000;
	public static int eventLoopFG = 2;
	
	public static int eventOccured = eventLoop * 4;
	
	public static String projectOutputPath = System.getProperty("user.dir")+"/test-output-android/";
	public static String fileDownloadDirectory = System.getProperty("user.dir")+"/downloads-android/";
	

	public static List<String> mailAttachmemnts = new ArrayList<String>();
	
	public static String htmlReport = "emailable-report.html";
	public static String performanceFile = "PGSDK-Android-PerformanceReport.xls";
	public static String eventSuccessFile = "PGSDK-Android-EventReport.xls";
	
	public static String maxCPU;
	public static String maxMemory;
	
	public static String minCPU;
	public static String minMemory;
	
	public static String avgCPU;
	public static String avgMemory;
	
	public static String networkIn;
	public static String networkOut;
	
	public static String eventLogged1;
	public static String eventLogged2;
	public static String locationEvent;
	public static String locationEventFlushed;
	public static String poiEvent;
	public static String poiEventFlushed;
	public static String scanAppEvent;
	public static String scanAppEventFlushed;
	public static String runningAppEvent;
	public static String runningAppEventFlushed;
	public static String clickEvent;
	public static String clickEventFlushed;
	public static String fbEvent;
	public static String fbEventFlushed;
	public static String eventLoggedTotal;
	public static String eventLoggedServer;
	
	public static int downloadedApps;
	
	public static String mailFrom = "provide-email-id-to-send-email";
	public static String password = "provide-password-of-sender's-email-id";
	public static String[] mailTo = {"provide-email-ids-to-send-reports","harsh@letsgomo.com"};
	
	public static String sTime;
	public static String eTime;
	public static String mName;
	public static List<String> timeStamp = new ArrayList<String>();
	public static String currentTime;
	
	public static List<String> methodNameList = new ArrayList<String>();
	public static String Method_Name;
	
	public static List<String> methodDescription = new ArrayList<String>();
	public static List<String> methodDescriptionList = new ArrayList<String>(); //created for reset advID test
	public static String oldAdvtID;
	
	public static String runTimeMatricsJSON;
	
	public static long startTime = 0;
	public static long foregroundStartTime = 0;
	public static long foregroundEndTime = 0;
	public static long totalForegroundTime = 0;
	public static long backkgroundStartTime = 0;
	public static long backgroundEndTime = 0;
	public static long backgroundStartTime = 0;
	public static long totalBackgroundTime = 0;
	
	public static long noInternetDuration = 0;
	public static long noInternetstart = 0;
	public static long noInternetEnd = 0;
	
	public static long wifiDuration = 0;
	public static long wifiStart = 0;
	public static long wifiEnd = 0;
	
	public static long totalSessionDuration = 0;
	
}
