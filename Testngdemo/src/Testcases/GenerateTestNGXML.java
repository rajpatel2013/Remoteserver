package Testcases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class GenerateTestNGXML {
	// WHEN RUN TESTSUITE
	protected String TTESTSUITENAME;
	protected String ID;
	protected String TESTNAME;
	protected String RUNMODE;
	protected String TESTCLASS;

	// WHEN RUN FAILED TESTCASES

	protected static String TESTRESULT;
	protected String TESTSUITE;
	protected String FAILEDTESTNAME;
	protected String FAILEDTESTCLASS;

	public void runTestNGTest() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = null;
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3380/MyAutomation", "root", "");
			System.out.println("Database is connected !");

			java.sql.Statement stmt = conn.createStatement();
			String sql1 = "SElECT Testsuite from Testsuiterunmode where Testsuiterunmode='Y'";
			java.sql.ResultSet rs1 = stmt.executeQuery(sql1);

			List<String> Suitelist = new ArrayList<String>();
			while (rs1.next()) {
				TTESTSUITENAME = rs1.getString("Testsuite");
				System.out.println(TTESTSUITENAME);
				Suitelist.add(TTESTSUITENAME);
			}

			String sql = "SELECT TestcaseID,Testcasename,Testclassname ,Runmode from Testrunconfig where Runmode='Y'";
			java.sql.ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				ID = rs.getString("TestcaseID");
				TESTNAME = rs.getString("Testcasename");
				RUNMODE = rs.getString("Runmode");
				TESTCLASS = rs.getString("Testclassname");

				// Create an instance on TestNG
				TestNG myTestNG = new TestNG();

				// Create an instance of XML Suite and assign a name for it.
				XmlSuite mySuite = new XmlSuite();
				mySuite.setName("Suite");

				// Create an instance of XmlTest and assign a name for it.
				XmlTest myTest = new XmlTest(mySuite);
				myTest.setName("Test");

				// Add any parameters that you want to set to the Test.
				// myTest.setParameters(testngParams);

				// Create a list which can contain the classes that you want to
				// run.
				List<XmlClass> myClasses = new ArrayList<XmlClass>();

				if (Suitelist.contains(TESTNAME) && RUNMODE.equalsIgnoreCase("Y")) {
					myClasses.add(new XmlClass(TESTCLASS));
					System.out.println(myClasses);
				}

				// Assign that to the XmlTest Object created earlier.
				myTest.setXmlClasses(myClasses);

				// Create a list of XmlTests and add the Xmltest you created
				// earlier to
				// it.
				List<XmlTest> myTests = new ArrayList<XmlTest>();
				myTests.add(myTest);

				// add the list of tests to your Suite.
				mySuite.setTests(myTests);

				// Add the suite to the list of suites.
				List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
				mySuites.add(mySuite);

				// Set the list of Suites to the testNG object you created
				// earlier.
				myTestNG.setXmlSuites(mySuites);

				TestListenerAdapter tla = new TestListenerAdapter();
				myTestNG.addListener(tla);

				// invoke run() - this will run your class.
				myTestNG.run();

			}

		} catch (Exception e) {
			System.out.println("Really poor exception handling " + e.toString());
		}
	}

	public void rerunfailedtestcase() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = null;
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3380/MyAutomation", "root", "");
			

			java.sql.Statement stmt = conn.createStatement();
			String sql = "SELECT Testsuitename,Testcasename,Testclassname ,Testcaseresult from Testresult where Testcaseresult='FAIL'";
			java.sql.ResultSet rs = stmt.executeQuery(sql);
			System.out.println(rs);

			while (rs.next()) {

				TESTSUITE = rs.getString("Testsuitename");
				FAILEDTESTNAME = rs.getString("Testcasename");
				TESTRESULT = rs.getString("Testcaseresult");
				FAILEDTESTCLASS = rs.getString("Testclassname");

				if (!TESTRESULT.equalsIgnoreCase("FAIL")) {
					conn.close();
				}

				else {

					// Create an instance on TestNG
					TestNG myTestNG = new TestNG();

					// Create an instance of XML Suite and assign a name for it.
					XmlSuite mySuite = new XmlSuite();
					mySuite.setName("FaieldTestSuite");

					// Create an instance of XmlTest and assign a name for it.
					XmlTest myTest = new XmlTest(mySuite);
					myTest.setName("FailedTestCases");

					// Add any parameters that you want to set to the Test.
					// myTest.setParameters(testngParams);

					// Create a list which can contain the classes that you want
					// to
					// run.
					List<XmlClass> myClasses = new ArrayList<XmlClass>();

					myClasses.add(new XmlClass(FAILEDTESTCLASS));
					System.out.println(myClasses);

					// Assign that to the XmlTest Object created earlier.
					myTest.setXmlClasses(myClasses);

					// Create a list of XmlTests and add the Xmltest you created
					// earlier to
					// it.
					List<XmlTest> myTests = new ArrayList<XmlTest>();
					myTests.add(myTest);

					// add the list of tests to your Suite.
					mySuite.setTests(myTests);

					// Add the suite to the list of suites.
					List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
					mySuites.add(mySuite);

					// Set the list of Suites to the testNG object you created
					// earlier.
					myTestNG.setXmlSuites(mySuites);

					TestListenerAdapter tla = new TestListenerAdapter();
					myTestNG.addListener(tla);

					// invoke run() - this will run your class.
					myTestNG.run();
					
				}
			}

		} catch (Exception e) {
			System.out.println("Really poor exception handling " + e.toString());
		}
	}

	public static boolean getTestCaseResult() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = null;
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3380/MyAutomation", "root", "");
			System.out.println("Database is connected !");

			java.sql.Statement stmt = conn.createStatement();
			String sql = "SELECT Testsuitename,Testcasename,Testclassname ,Testcaseresult from Testresult where Testcaseresult='FAIL'";
			java.sql.ResultSet rs = stmt.executeQuery(sql);
			System.out.println(rs);

			while (rs.next()) {

				TESTRESULT = rs.getString("Testcaseresult");
				if (TESTRESULT.equalsIgnoreCase("FAIL")) {
					return true;

				}
				conn.close();

			}
		} catch (Exception e) {
			System.out.println("Really poor exception handling " + e.toString());
		}
		return false;

	}

	public void deleteTestCaseResult() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = null;
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3380/MyAutomation", "root", "");
			System.out.println("Database is connected !");

			java.sql.Statement stmt = conn.createStatement();
			String sql = "DELETE FROM Testresult WHERE Testcaseresult in ('PASS','FAIL')";
			int rs = stmt.executeUpdate(sql);
			System.out.println(rs);
			conn.close();
		} catch (Exception e) {
			System.out.println("Really poor exception handling " + e.toString());
		}
		
	}
}