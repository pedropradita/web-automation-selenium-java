package script;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ObjectRepository.Global;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import resources.Base;

public class HomeTest extends Base {

	public WebDriver driver;
	public static Logger log = LogManager.getLogger(Base.class.getName());
	Global g;
	HomePage h;
	LoginPage l;
	JavascriptExecutor js;

	@BeforeMethod
	public void BefMet() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));

		g = new Global(driver);
		h = new HomePage(driver);
		l = new LoginPage(driver);
		js = (JavascriptExecutor) driver;

	}

	@AfterMethod
	public void AfMet() {
		driver.close();
		driver.quit();
	}

	@Test(groups = "Regression")
	public void Banner() throws InterruptedException{
		g.CheckClickNewTabNext("Banner", h.ListBanner(),h.BannerRightButton());
	}
	
	@Test(groups = "Regression")
	public void SpecialCard() throws InterruptedException{
		
		js.executeScript("scroll(0,1500)");
		js.executeScript("arguments[0].click();", h.ChecklistButton());
		js.executeScript("scroll(0,1400)");
		
		g.CheckClickNewTab("Pengguna baru", h.SpecialCard());
	}
}