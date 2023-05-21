package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

	public ExtentReports extent;
	public ExtentSparkReporter sparkReporter;
	public ExtentTest test;

	String reportName;

	public void onTestStart(ITestResult result) {
		

	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, " Test Passed");

	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL, " Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());

	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.SKIP, " Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
		reportName = "Test-Report-"+timestamp+".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+reportName);

		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setDocumentTitle("API Automation Report");
		sparkReporter.config().setReportName("Automation Tests Results by SDET ADDA");

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

	}

	public void onFinish(ITestContext context) {
		extent.flush();

	}

}
