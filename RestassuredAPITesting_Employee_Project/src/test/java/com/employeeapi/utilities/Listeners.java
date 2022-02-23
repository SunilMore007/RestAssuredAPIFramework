package com.employeeapi.utilities;

import java.io.File;
import java.io.IOException;

import org.junit.BeforeClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners implements ITestListener {
	ExtentReports extent;
	ExtentTest test;
	ExtentHtmlReporter reporter;

	public void onStart(ITestContext testcontext) {

	//	reporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir") + "./Reports/myReport.html"));
		reporter = new ExtentHtmlReporter("./Reports/myReport.html");
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setDocumentTitle("RestAssuredAPI Testing");
		reporter.config().setTestViewChartLocation(ChartLocation.TOP);
		reporter.config().setChartVisibilityOnOpen(true);
		reporter.config().setEncoding("utf-8");
		
		extent = new ExtentReports();

		extent.attachReporter(reporter);
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Host Name", "Localhost");
		extent.setSystemInfo("User", "Sunil");

	}

	public void onTestSuccess(ITestResult result) {

		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test Case passed is " + result.getName());
	}

	public void onTestFailure(ITestResult result) {

		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Case Failed is " + result.getName());
		test.log(Status.FAIL, "Test Case Failed is " + result.getThrowable());

	}

	public void onFinish(ITestContext testcontext) {
		extent.flush();
	}

}
