package Utility;



import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configpropertyload {

	
	
	public static String loadPropertyFile() {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.CONFIG_FILEPATH);

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println(prop.getProperty("ENV"));
			

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;

	  }

	}



