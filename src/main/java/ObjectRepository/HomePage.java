package ObjectRepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	public WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "button[data-testid='btnHeaderLogin']")
	WebElement loginButton;
	@FindBy(css = "button[data-testid='btnHeaderRegister']")
	WebElement registerButton;
	@FindBy(css = "div[class='css-79elbk css-oxdscw'] a[href*='http']")
	List<WebElement> listBanner;
	@FindBy(css = ".css-sncotz.left")
	WebElement showBanner;
	@FindBy(css = ".css-1x6s881.next")
	WebElement bannerRightButton;
	@FindBy(css = "div[data-testid='sldrHomeKategoriPilihan'] a[href*='http']")
	List<WebElement> listKatPilCard;
	@FindBy(css = "button[data-testid='sldrHomeKategoriPilihanRight']")
	WebElement kurPilRightButton;
	@FindBy(css = "div[data-testid='divTripleLegoItem'] a[href*='http']")
	List<WebElement> specialCard;
	@FindBy(css = "*[class='unf-coachmark__next-button css-64apm5 ety06v15']")
	WebElement checklistButton;
	
	public WebElement LoginButton() {
		return loginButton;
	}
	
	public WebElement RegisterButton() {
		return registerButton;
	}
	
	public List<WebElement> ListBanner() {
		return listBanner;
	}
	
	public WebElement ShowBanner() {
		return showBanner;
	}
	
	public WebElement BannerRightButton() {
		return bannerRightButton;
	}
	
	public List<WebElement> ListKatPilCard() {
		return listKatPilCard;
	}	
	
	public WebElement KurPilRightButton() {
		return kurPilRightButton;
	}
	
	public List<WebElement> SpecialCard() {
		return specialCard;
	}	
	
	public WebElement ChecklistButton() {
		return checklistButton;
	}	
}
