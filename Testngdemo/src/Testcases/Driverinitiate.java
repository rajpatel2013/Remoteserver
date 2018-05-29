package Testcases;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Driverinitiate {

	public WebDriver driver;
	public String URL, Node;
	protected ThreadLocal<RemoteWebDriver> threadDriver = null;

	@Parameters({ "browser", "servername" })
	@BeforeTest
	public void launchbrowser(String browser, boolean remoteserver) throws MalformedURLException {
		String URL = "http://www.calculator.net";

		if (browser.equalsIgnoreCase("firefox") && remoteserver == true) {
			System.out.println(" Executing on FireFox");
			String Node = "http://192.168.1.3:5566/wd/hub";
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setBrowserName("firefox");

			driver = new RemoteWebDriver(new URL(Node), cap);
			// Puts an Implicit wait, Will wait for 10 seconds before throwing
			// exception
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Launch website
			driver.navigate().to(URL);
			driver.manage().window().maximize();

		}
		else if (browser.equalsIgnoreCase("firefox") && remoteserver == false) {
			System.out.println(" Executing on firefox");
			FirefoxDriver driver=new FirefoxDriver ();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Launch website
			driver.navigate().to(URL);
			driver.manage().window().maximize();
		}
		
		else if (browser.equalsIgnoreCase("chrome") && remoteserver == true) {
			System.out.println(" Executing on CHROME");
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");
			String Node = "http://192.168.1.3:5567/wd/hub";
			driver = new RemoteWebDriver(new URL(Node), cap);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Launch website
			driver.navigate().to(URL);
			driver.manage().window().maximize();
		} 
		
		else if (browser.equalsIgnoreCase("chrome") && remoteserver == false) {
			System.out.println(" Executing on chrome");
			System.setProperty("webdriver.chrome.driver", "/Users/rajankumarpatel/Documents/workspace/Testngdemo/chromedriver");
			ChromeDriver driver=new ChromeDriver ();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Launch website
			driver.navigate().to(URL);
			driver.manage().window().maximize();
		}
		
		
		else if (browser.equalsIgnoreCase("ie")) {
			System.out.println(" Executing on IE");
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setBrowserName("ie");
			String Node = "http://192.168.1.3:5568/wd/hub";
			driver = new RemoteWebDriver(new URL(Node), cap);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Launch website
			driver.navigate().to(URL);
			driver.manage().window().maximize();
		} 
		
		else if (browser.equalsIgnoreCase("IE") && remoteserver == false) {
			System.out.println(" Executing on IE");
			InternetExplorerDriver driver=new InternetExplorerDriver ();
			System.setProperty("webdriver.ie.driver", "/Users/rajankumarpatel/Documents/workspace/Testngdemo/IEDriverServer");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Launch website
			driver.navigate().to(URL);
			driver.manage().window().maximize();
		}
		
		else {
			throw new IllegalArgumentException("The Browser Type is Undefined");
		}
	}

	@Test
	public void calculatepercent() {
		// Click on Math Calculators
		driver.findElement(By.xpath("//a[contains(text(),'Math')]")).click();
		// Click on Percent Calculators
		driver.findElement(By.xpath("//a[contains(text(),'Percentage Calculator')]")).click();
		// Enter value 17 in the first number of the percent Calculator
		driver.findElement(By.id("cpar1")).sendKeys("17");
		// Enter value 35 in the second number of the percent Calculator
		driver.findElement(By.id("cpar2")).sendKeys("35");

		// Click Calculate Button
		driver.findElement(By.xpath("(//input[contains(@value,'Calculate')])[1]")).click();
		// Get the Result Text based on its xpath
		String result = driver.findElement(By.xpath(".//*[@id='content']/p[2]/font/b")).getText();
		// Print a Log In message to the screen
		System.out.println(" The Result is " + result);
		if (result.equals("5.95")) {
			System.out.println(" The Result is Pass");
		} else {
			System.out.println(" The Result is Fail");
		}
	}

	@AfterTest
	public void closeBrowser() {
		// driver.quit();
	}
}
