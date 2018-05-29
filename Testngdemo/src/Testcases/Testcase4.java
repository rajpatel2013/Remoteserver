package Testcases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Testcases.ListenerTest.class)

public class Testcase4 extends Testrunner{
	
	static String name ="Checkout";
    static String Testname ="Checkout";
    static String Testclass ="Testcases.Testcase4";
    static String Testresult;


	

	@Test

	public void printTestCaseName4() {

		System.out.println("Four test case");
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


