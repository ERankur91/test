package com.test.android;

import java.util.ArrayList;

public class MethodTimeTracker {
	
	public static MethodTimeTracker methodTimeTracker;
	ArrayList<MethodTimeClass> timeValueList = new ArrayList<MethodTimeClass>();
	
	public static MethodTimeTracker get(){
		if(methodTimeTracker == null){
			methodTimeTracker = new MethodTimeTracker();
		}
		return methodTimeTracker;
	}
	
	public ArrayList<MethodTimeClass> getAllValues(){
		return timeValueList;
	}
	
	public void track(String start, String end, String methodName, String durationTestRun) {
		timeValueList.add(new MethodTimeClass(start, end, methodName, durationTestRun));
	}
	
	public void clear(){
		timeValueList.clear();
	}


	
	
	
	public class MethodTimeClass{
		String start;
		String end;
		String methodName;
		String durationTestRun;
		MethodTimeClass(String start, String end, String methodName, String durationTestRun) {
			this.start = start;
			this.end = end;
			this.methodName = methodName;
			this.durationTestRun = durationTestRun;
		}
		
		public String getStart() {
			return start;
		}


		public String getEnd() {
			return end;
		}

		public String getMethodName() {
			return methodName;
		}
		
		public String getDurationTestRun() {
			return durationTestRun;
		}
	}

}
