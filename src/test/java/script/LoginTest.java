package script;

import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ObjectRepository.Global;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import resources.Base;

public class LoginTest extends Base {

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

		h.LoginButton().click();
	}

	@AfterMethod
	public void AfMet() {
		driver.close();
		driver.quit();
	}

	@Test(groups = "Regression")
	public void LoginAlertNull(){

		System.out.println("Case null login alert");

		String email = "aaa@aaa.aaa";
		String alert = "Kata sandi harus diisi";

		l.EmailField().sendKeys(email);
		l.SelanjutnyaButton().click();
		l.MasukButton().click();

		Assert.assertEquals(g.CheckAlert(l.PasswordAlert(), "Null password", alert), alert);
	}

	@Test(groups = "Regression")
	public void LoginAlertInvalid() {

		System.out.println("Case invalid login alert");

		String email = "aaa@aaa.aaa";
		String invalid = "1234";
		String alert = "Kata sandi terlalu pendek, minimum 6 karakter";

		l.EmailField().sendKeys(email);
		l.SelanjutnyaButton().click();
		l.PasswordField().sendKeys(invalid);
		l.MasukButton().click();

		Assert.assertEquals(g.CheckAlert(l.PasswordAlert(), "Invalid password", alert), alert);
	}

	@Test(groups = "Regression")
	public void LoginAlertBelumTerdaftar() {

		System.out.println("Case email belum tedaftar login alert");

		String email = "aaa@aaa.aa";
		String alertH = "Email belum terdaftar";
		String alertB = "Lanjut daftar dengan email ini";

		l.EmailField().sendKeys(email);
		l.SelanjutnyaButton().click();
		
		String bodyAlertR = l.BodyBelumTerdaftarAlert().split(email)[0].replaceAll("\u00a0","").trim();
	
		Assert.assertEquals(g.CheckAlertJS(l.HeaderBelumTerdaftarAlert(), "Header email belum terdaftar", alertH), alertH);
		Assert.assertEquals(g.CheckAlertJS(bodyAlertR, "Body email belum terdaftar", alertB), alertB);
	}
	
	@Test(groups = "Regression")
	public void ForgotLink() throws MalformedURLException, IOException{

		System.out.println("Case forgot link");

		String url = "https://www.tokopedia.com/reset-password";
		
		l.ForgotLink().click();
	
		Assert.assertEquals(g.CheckUrl(driver, "Forgot link", url), url);
	}
	
	@Test(groups = "Regression")
	public void HelpLink() throws MalformedURLException, IOException{

		System.out.println("Case help link");

		String url = "https://www.tokopedia.com/help";
		
		l.HelpLink().click();
	
		Assert.assertEquals(g.CheckUrl(driver, "Help link", url), url);
	}
}