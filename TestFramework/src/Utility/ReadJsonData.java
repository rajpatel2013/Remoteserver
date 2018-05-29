package Utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJsonData {

	public static String Common_name;
	public static String Dev_name;
	public static String Test_name;
	public static String method;
	public static String Enviornment;
	public static String key;
	public static String Env;

	public static void getDataFromJsonFileForCommonTestEnv(String key, String env)
			throws org.json.simple.parser.ParseException, JSONException, FileNotFoundException, IOException {

		Enviornment = Configpropertyload.loadPropertyFile();
		Enviornment = env;

		JSONParser parser = new JSONParser();

		if (key.equalsIgnoreCase("SKU") && env.equalsIgnoreCase("COMMONENV")) {

			JSONArray a = (JSONArray) parser.parse(new FileReader(Constants.COMMONJSON_FILEPATH));

			for (Object o : a) {
				JSONObject person = (JSONObject) o;
				Common_name = (String) person.get("SKU");
				System.out.println(Common_name);
				if (Common_name.isEmpty()) {
					System.out.println("Common file has name is empty");
					JSONArray b = (JSONArray) parser.parse(new FileReader(Constants.DEVJSON_FILEPATH));
					for (Object b1 : b) {
						JSONObject person1 = (JSONObject) b1;
						Dev_name = (String) person1.get("SKU");
						System.out.println(Dev_name);
					}
				}

				if (Common_name.isEmpty() && Dev_name.isEmpty()) {
					System.out.println("Dev file has name is empty");
					JSONArray c = (JSONArray) parser.parse(new FileReader(Constants.TESTJSON_FILEPATH));
					for (Object b2 : c) {
						JSONObject person2 = (JSONObject) b2;
						Test_name = (String) person2.get("SKU");
						System.out.println(Test_name);
					}
				}

				if (Common_name.isEmpty() && Dev_name.isEmpty() && Test_name.isEmpty()) {
					System.out.println("ALERT : **** ALl files are empty *** ");
				}
			}
		} else if (key.equalsIgnoreCase("SKU") && env.equalsIgnoreCase("DEVENV")) {

			JSONArray b = (JSONArray) parser.parse(new FileReader(Constants.DEVJSON_FILEPATH));
			for (Object b1 : b) {
				JSONObject person4 = (JSONObject) b1;
				Dev_name = (String) person4.get("SKU");
				System.out.println(Dev_name);
				if (Dev_name.isEmpty()) {
					System.out.println("Dev file has name is empty");
				}
			}

		} else if (key.equalsIgnoreCase("SKU") && env.equalsIgnoreCase("TESTENV")) {

			JSONArray f = (JSONArray) parser.parse(new FileReader(Constants.TESTJSON_FILEPATH));
			for (Object g : f) {
				JSONObject person5 = (JSONObject) g;
				Test_name = (String) person5.get("SKU");
				System.out.println(Test_name);
				if (Test_name.isEmpty()) {
					System.out.println("Test file has name is empty");
				}
			}
		}

	}

}
