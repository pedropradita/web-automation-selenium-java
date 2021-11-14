package ObjectRepository;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public WebDriver driver;
	JavascriptExecutor js;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
	}

	@FindBy(css = "input[data-testid='email-phone-input']")
	WebElement emailField;
	@FindBy(css = "button[data-testid='email-phone-submit']")
	WebElement selanjutnyaButton;
	@FindBy(id = "password-input")
	WebElement passwordField;
	@FindBy(css = ".css-ow4jg3-unf-btn.e1ggruw00")
	WebElement masukButton;
	@FindBy(css = ".css-t9c9fq.ed3tosx8")
	WebElement passwordAlert;
	@FindBy(css = "h2[data-unify='Typography']")
	WebElement headerBelumTerdaftarAlert;
	@FindBy(css = ".css-8ykhr5")
	WebElement bodyBelumTerdaftarAlert;
	@FindBy(css = "a[data-testid='forgot-password']")
	WebElement forgotLink;
	@FindBy(css = "div [class='css-17ryruu'] a[class='css-1g8zdv8-unf-link en8iawh0']")
	WebElement helpLink;
	
	public WebElement EmailField() {
		return emailField;
	}
	
	public WebElement SelanjutnyaButton() {
		return selanjutnyaButton;
	}
	
	public WebElement PasswordField() {
		return passwordField;
	}
	
	public WebElement MasukButton() {
		return masukButton;
	}
	
	public WebElement PasswordAlert() {
		return passwordAlert;
	}
	
	public String HeaderBelumTerdaftarAlert() {
		String headerAlert = (String) js.executeScript("return arguments[0].innerText", headerBelumTerdaftarAlert);
		return headerAlert;
	}
	
	public String BodyBelumTerdaftarAlert() {
		String bodyAlert = (String) js.executeScript("return arguments[0].innerText", bodyBelumTerdaftarAlert);
		return bodyAlert;
	}
	
	public WebElement ForgotLink() {
		return forgotLink;
	}
	
	public WebElement HelpLink() {
		return helpLink;
	}
}
