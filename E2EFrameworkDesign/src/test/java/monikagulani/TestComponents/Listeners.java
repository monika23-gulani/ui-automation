package monikagulani.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import monikagulani.resources.ExtentReportFetch;

public class Listeners extends BaseTest implements ITestListener {
	ExtentReports extent = ExtentReportFetch.getReportObj();
	
	ExtentTest test;
	ThreadLocal<ExtentTest> threadobj = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		// not implemented
		test = extent.createTest(result.getMethod().getMethodName());
		threadobj.set(test);
	}

	/**
	 * Invoked each time a test succeeds.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SUCCESS
	 */

	@Override
	public void onTestSuccess(ITestResult result) {
		// not implemented
		test.log(Status.PASS, "test passed");
	}

	/**
	 * Invoked each time a test fails.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#FAILURE
	 */

	@Override
	public void onTestFailure(ITestResult result) {
		// not implemented
		test.log(Status.FAIL, "test failed");
		threadobj.get().fail(result.getThrowable());
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filePath = null;
		try
		{
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		try {
			threadobj.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Invoked each time a test is skipped.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SKIP
	 */

	@Override
	public void onTestSkipped(ITestResult result) {
		// not implemented
	}

	/**
	 * Invoked each time a method fails but has been annotated with
	 * successPercentage and this failure still keeps it within the success
	 * percentage requested.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SUCCESS_PERCENTAGE_FAILURE
	 */

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not implemented
	}
	
	@Override
	public void onFinish(ITestContext context) {
	    // not implemented
		extent.flush();
	  }

}
