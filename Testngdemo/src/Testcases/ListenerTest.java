package Testcases;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerTest implements ITestListener{

	@Override
	public void onFinish(ITestContext arg0) {
		System.out.println("Test suite execution completed");
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult Result){
		
		
	}

	@Override
	public void onTestFailure(ITestResult Result) {
		
		System.out.println("The name of the testcase failed is :"+Result.getName());
		
		
	}

	@Override
	public void onTestSkipped(ITestResult Result) {
		
		System.out.println("The name of the testcase Skipped is :"+Result.getName());
		
		
	}

	@Override
	public void onTestStart(ITestResult Result) {
		
		System.out.println(Result.getName()+" test case started");
		
		
	}

	@Override
	public void onTestSuccess(ITestResult Result) {
		
		System.out.println("The name of the testcase passed is :"+Result.getName());
		
		
	}
	
	

}
