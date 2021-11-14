
package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public WebDriver driver;
	public Properties prop;
	public WebDriverWait w;

	public WebDriver initializeDriver() throws IOException {
		
		prop = new Properties();

		String GlobalVariable = System.getProperty("user.dir") + "/src/test/java/script/Global.properties";

		FileInputStream fis = new FileInputStream(GlobalVariable);

		prop.load(fis);
	
//		String browserName = System.getProperty("browser");

		String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {

			WebDriverManager.chromedriver().setup();
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments(
			"--start-maximized",
			"enable-automation",
			"--no-sandbox",
			"--disable-dev-shm-usage",
			"--disable-gpu",
			"--disable-blink-features=AutomationControlled",
			"--disable-extensions",
			"--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
			
			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 

			if (browserName.contains("headless")) {
				options.addArguments(
						"--window-size=1920,1080",
						"enable-automation",
						"--no-sandbox",
						"--headless",
						"--disable-dev-shm-usage",
						"--disable-gpu",
						"--disable-blink-features=AutomationControlled",
						"--disable-extensions",
						"--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
						
						options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 

			}

			
			driver = new ChromeDriver(options);
			driver.manage().deleteAllCookies();
			
			w = new WebDriverWait(driver, 120);
			

		} else if (browserName.equals("firefox")) {

		} else if (browserName.equals("IE")) {

		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}
	
	
	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException
	{
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "/reports/" +testCaseName+".png";
		FileUtils.copyFile(source,new File(destinationFile));
		return destinationFile;
	}

	
}

