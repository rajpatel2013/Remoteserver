package Testcases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Reporterlogs {

	static Logger reporter = Logger.getLogger(Reporterlogs.class);

	public static void main(String[] args) {

		PropertyConfigurator.configure("log4j.properties");
		reporter.debug("Log4j appender configuration is successful !!");

	}

}
