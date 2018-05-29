package Testcases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

public class Testrunner extends GenerateTestNGXML {

	
	protected String ID;
	protected String TESTNAME;
	protected String RUNMODE;
	protected String TESTCLASS;
	static String NAME;
	static String TESTCASENAME;
	static String TESTRESULT;
	static String TESTCLASSNAME;
	

	public static void main(String[] args) {

		int minretryCount = 0;
		int maxretryCount = 2;

		GenerateTestNGXML generatexml = new GenerateTestNGXML();
		generatexml.deleteTestCaseResult();
		generatexml.runTestNGTest();
		
		
		
		if(getTestCaseResult()){
		while (minretryCount <= maxretryCount) {
			
			generatexml.rerunfailedtestcase();

			minretryCount++;
			}
		}
	}
	

	public static void inserttestresultintoDB(String NAME, String TESTCASENAME, String TESTRESULT, String TESTCLASSNAME) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = null;
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3380/MyAutomation", "root", "");
			System.out.println("Database is connected !");

			
			String query = "insert into Testresult values('" + NAME + "', '" + TESTCASENAME + "', '" + TESTRESULT + "' ,'"
					+ TESTCLASSNAME + "')";
			PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);

			preparedStmt.executeUpdate();

			conn.close();

		} catch (Exception e) {
			System.out.println("Really poor exception handling " + e.toString());
		}

	}

}
