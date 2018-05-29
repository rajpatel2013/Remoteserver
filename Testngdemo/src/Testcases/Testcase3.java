package Testcases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Testcases.ListenerTest.class)

public class Testcase3 extends Testrunner{
	
	static String name ="Checkout";
    static String Testname ="Checkout";
    static String Testclass ="Testcases.Testcase3";
    static String Testresult;

	

	@Test
	public void printTestCaseName3() {

		System.out.println("Third test case");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result)
	{
		try
		  {
		     if(result.getStatus() == ITestResult.SUCCESS)
		     {

		         
		         Testresult="PASS";
		         
		         
		     }

		     else if(result.getStatus() == ITestResult.FAILURE)
		     {
		        
		         Testresult="FAIL";
		         
		     }

		      else if(result.getStatus() == ITestResult.SKIP ){

		         
		         Testresult="SKIP";
		        

		     }
		     
		    Testrunner.inserttestresultintoDB(name, Testname, Testresult, Testclass);
	}
	   catch(Exception e)
	   {
	     e.printStackTrace();
	   }

	}

}
