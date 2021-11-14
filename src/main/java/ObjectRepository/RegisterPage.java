package ObjectRepository;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	public WebDriver driver;
	JavascriptExecutor js;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
	}

	@FindBy(css = "input[data-testid='phone-email-input']")
	WebElement phoneEmailField;
	@FindBy(css = ".css-t9c9fq.ed3tosx8")
	WebElement phoneEmailAlert;	
	@FindBy(css = "button[data-testid='phone-email-submit']")
	WebElement daftarButton;
	@FindBy(css = "h2[data-unify='Typography']")
	WebElement headerEmailVerification;
	@FindBy(css = ".css-a5zncl")
	WebElement bodyEmailVerification;
	@FindBy(css = ".css-ftt357-unf-btn.e1ggruw0")
	WebElement ubahButton;
	@FindBy(css = "css-evb2vf-unf-btn.e1ggruw00")
	WebElement yaButton;
	@FindBy(css = "div[data-unify='Card']")
	WebElement sendButton;
	@FindBy(css = "input[class='css-1ca56s1']")
	WebElement otpField;
	@FindBy(css = "p[class='css-2zwjpr-unf-heading-unf-heading e1qvo2ff8']")
	WebElement otpToastAl;
	@FindBy(css = ".register__logo")
	WebElement registLogo;
	@FindBy(css = "a[href*='terms']")
	WebElement termsLink;
	@FindBy(css = "a[href*='privacy']")
	WebElement privacyLink;
	@FindBy(css = "a[href*='help']")
	WebElement helpLink;
	@FindBy(css = ".css-1o1bmgk")
	WebElement verificationText;
	
	public WebElement PhoneEmailField() {
		return phoneEmailField;
	}
	
	public WebElement PhoneEmailAlert() {
		return phoneEmailAlert;
	}
	
	public WebElement DaftarButton() {
		return daftarButton;
	}

	public String HeaderEmailVerification() {		
		String headerVerification = (String) js.executeScript("return arguments[0].innerText", headerEmailVerification);
		return headerVerification;
	}
	
	public String BodyEmailVerification() {
		String bodyVerification = (String) js.executeScript("return arguments[0].innerText", bodyEmailVerification);
		return bodyVerification;
	}
	
	public WebElement UbahButton() {
		WebElement ubahButton = (WebElement) js.executeScript("return document.getElementsByClassName('css-ftt357-unf-btn e1ggruw00')[0]");
		return ubahButton;
	}
	
	public WebElement YaButton() {
		WebElement yaButton = (WebElement) js.executeScript("return document.getElementsByClassName('css-evb2vf-unf-btn e1ggruw00')[0]");
		return yaButton;
	}
	
	public WebElement SendButton() {
		return sendButton;
	}
	
	public WebElement OtpField() {
		return otpField;
	}
	
	public String OtpToastAlert() {
		String otpToastAlert = (String) js.executeScript("return arguments[0].innerText", otpToastAl);
		return otpToastAlert;
	}
	
	public WebElement RegistLogo() {
		return registLogo;
	}
	
	public WebElement TermsLink() {
		return termsLink;
	}
	
	public WebElement PrivacyLink() {
		return privacyLink;
	}
	
	public WebElement HelpLink() {
		return helpLink;
	}
	
	public WebElement verificationText() {
		return verificationText;
	}
}
