package script;

import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ObjectRepository.Global;
import ObjectRepository.HomePage;
import ObjectRepository.RegisterPage;
import resources.Base;

public class RegisterTest extends Base {

	public WebDriver driver;
	public static Logger log = LogManager.getLogger(Base.class.getName());
	Global g;
	HomePage h;
	RegisterPage r;
	JavascriptExecutor js;
	WebDriverWait w;

	@BeforeMethod
	public void BefMet() throws IOException {

		driver = initializeDriver();
		driver.get(prop.getProperty("url"));

		g = new Global(driver);
		h = new HomePage(driver);
		r = new RegisterPage(driver);
		js = (JavascriptExecutor) driver;
		w = new WebDriverWait(driver, 10);

		h.RegisterButton().click();

	}

	@AfterMethod
	public void AfMet() {
		driver.close();
		driver.quit();
	}

	@Test(groups = "Regression")
	public void RegisterUrl() throws InterruptedException, MalformedURLException, IOException {

		System.out.println("Case register url");

		String url = "https://www.tokopedia.com/register";

		w.until(ExpectedConditions.urlToBe(url));

		Assert.assertEquals(g.CheckUrl(driver, "Register", url), url);
		Assert.assertTrue(g.CheckConnection(driver) < 400);
	}

	@Test(groups = "Regression")
	public void RegisterLogo() throws InterruptedException, MalformedURLException, IOException {

		System.out.println("Case register logo");

		Assert.assertTrue(g.CheckImg(r.RegistLogo(), "Register logo"));
	}

	@Test(groups = "Regression")
	public void TermLink() throws InterruptedException, MalformedURLException, IOException {

		System.out.println("Case term link");

		String url = "https://www.tokopedia.com/terms";
		String titleVer = "Term & Condition | Tokopedia";

		r.TermsLink().click();

		Assert.assertEquals(g.CheckTitlelNewTab("Term", titleVer), titleVer);
		Assert.assertEquals(g.CheckUrl(driver, "Term link", url), url);
		Assert.assertTrue(g.CheckConnection(driver) < 400);
	}

	@Test(groups = "Regression")
	public void PrivacyLink() throws InterruptedException, MalformedURLException, IOException {

		System.out.println("Case privacy link");

		String url = "https://www.tokopedia.com/privacy";
		String titleVer = "Privacy Policy | Tokopedia";

		r.PrivacyLink().click();

		Assert.assertEquals(g.CheckTitlelNewTab("Privacy", titleVer), titleVer);
		Assert.assertEquals(g.CheckUrl(driver, "Privacy link", url), url);
		Assert.assertTrue(g.CheckConnection(driver) < 400);
	}
	
	@Test(groups = "Regression")
	public void HelpLink() throws InterruptedException, MalformedURLException, IOException {

		System.out.println("Case help link");

		String url = "https://www.tokopedia.com/help";
		String titleVer = "Tokopedia Care";

		r.HelpLink().click();

		Assert.assertEquals(g.CheckTitlelNewTab("Help", titleVer), titleVer);
		Assert.assertEquals(g.CheckUrl(driver, "Help link", url), url);
		Assert.assertTrue(g.CheckConnection(driver) < 400);
	}

	@Test(groups = "Regression")
	public void RegisterAlertNull() throws InterruptedException {

		System.out.println("Case null register alert");

		String email = "a";
		String alert = "Nomor Ponsel atau Email harus diisi";

		r.PhoneEmailField().sendKeys(email);
		r.PhoneEmailField().sendKeys(Keys.BACK_SPACE);

		Assert.assertEquals(g.CheckAlert(r.PhoneEmailAlert(), "Null register", alert), alert);
	}

	@Test(groups = "Regression")
	public void RegisterAlertInvalid() {

		System.out.println("Case invalid register alert");

		String invalid = "a";
		String alert = "Format email salah";

		r.PhoneEmailField().sendKeys(invalid);

		Assert.assertEquals(g.CheckAlert(r.PhoneEmailAlert(), "Invalid register", alert), alert);
	}

	@Test(groups = "Regression")
	public void RegisterAlertPhoneShort() {

		System.out.println("Case short phone number register alert");

		String invalid = "1";
		String alert = "Nomor ponsel terlalu pendek, minimum 8 angka";

		r.PhoneEmailField().sendKeys(invalid);

		Assert.assertEquals(g.CheckAlert(r.PhoneEmailAlert(), "Short phone number register", alert), alert);
	}

	@Test(groups = "Regression")
	public void RegisterAlertPhoneLong() {

		System.out.println("Case long phone number register alert");

		String invalid = "1234567891011121";
		String alert = "Nomor ponsel terlalu panjang, maksimum 15 angka";

		r.PhoneEmailField().sendKeys(invalid);

		Assert.assertEquals(g.CheckAlert(r.PhoneEmailAlert(), "Long phone number register", alert), alert);
	}

	@Test(groups = "Regression")
	public void RegisterVerificationPopup() {

		System.out.println("Case verification popup register");

		String email = "regist" + g.DateTimeNow() + "@a.aa";
		String bodyVer = "Apakah email yang Anda masukkan sudah benar?";

		r.PhoneEmailField().sendKeys(email);
		r.DaftarButton().click();

		Assert.assertEquals(g.CheckAlertJS(r.HeaderEmailVerification(), "Header email verification", email), email);
		Assert.assertEquals(g.CheckAlertJS(r.BodyEmailVerification(), "Body email verification", bodyVer), bodyVer);
		Assert.assertTrue(r.UbahButton().isEnabled());
		Assert.assertTrue(r.YaButton().isEnabled());
	}

	@Test(groups = "Regression")
	public void VerificationEmail() throws InterruptedException {

		System.out.println("Case verification text register");

		String email = "regista" + g.DateTimeNow() + "@gmail.com";
		String verText = "Pilih Metode Verifikasi";

		r.PhoneEmailField().sendKeys(email);
		r.DaftarButton().click();
		Thread.sleep(3000);
		js.executeScript("arguments[0].click();", r.YaButton());
		
		Assert.assertEquals(g.CheckText(r.verificationText(), "Verification", verText), verText);
		
	}
}