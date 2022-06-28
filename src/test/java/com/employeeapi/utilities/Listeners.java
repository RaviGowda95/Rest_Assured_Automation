package com.employeeapi.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter{


	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext testContext) {

		htmlReporter=new ExtentHtmlReporter (System.getProperty("user.dir")+ "/Reports/myReport.html");//specify location of report

		htmlReporter.config().setDocumentTitle("Automation Report");  //Title of report
		htmlReporter.config().setReportName("Rest API Testing Report");  //Name of the report
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Project Name ","Employee Database API Project");
		extent.setSystemInfo("HostName ","LocalHost");
		extent.setSystemInfo("Environment","QA"); 
		extent.setSystemInfo("User","Ravi"); 

	}
	
	public void onTestSuccess(ITestResult result) {
		
		test = extent.createTest(result.getName()); // creating new entry in the report
		test.log(Status.PASS, "Test Case Passed Is :"  + result.getName());
		
	}
	
	public void onTestFailure(ITestResult result) {
		 
		test = extent.createTest(result.getName()); // creating new entry in the report
		test.log(Status.FAIL, "Test Case Failed Is :"  + result.getName());   ///to add name in extent report
		test.log(Status.FAIL, "Test Case Failed Is :"  + result.getThrowable()); //to add error/exception in report
		
	}

	public void onTestSkipped(ITestResult result) {
		
		test = extent.createTest(result.getName()); // creating new entry in the report
		test.log(Status.SKIP, "Test Case Skipped Is :"  + result.getName());
		
	}
	
	public void onFinish(ITestContext testContext) {
		
		extent.flush();
	}


}
