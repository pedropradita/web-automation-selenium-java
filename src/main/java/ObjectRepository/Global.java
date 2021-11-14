package ObjectRepository;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import resources.Base;

public class Global {

	public WebDriver driver;
	public static Logger log = LogManager.getLogger(Base.class.getName());

	public Global(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String CheckAlert(WebElement object, String name, String alerttxt) {

		String title = object.getText();
		if (title.equals(alerttxt)) {
			log.info(name + " alert is enable " + title);
		} else {
			log.error(name + " alert text is different " + title);
		}

		return title;
	}
	
	public String CheckText(WebElement object, String name, String text) {

		String title = object.getText();
		if (title.equals(text)) {
			log.info(name + " text is enable " + title);
		} else {
			log.error(name + " text is different " + title);
		}

		return title;
	}

	public String CheckAlertJS(String object, String name, String alerttxt) {

		if (object.equals(alerttxt)) {
			log.info(name + " alert is enable " + object);
		} else {
			log.error(name + " alert text is different " + object);
		}

		return object;
	}

	public boolean CheckImg(WebElement object, String name) {

		boolean img = object.isDisplayed();

		if (img) {
			log.info(name + " logo is enable " + object);
		} else {
			log.error(name + " logo is disable " + object);
		}

		return img;
	}

	public String CheckUrl(WebDriver object, String name, String urltab) {

		String url = object.getCurrentUrl();
		if (url.equals(urltab)) {
			log.info(name + " URL is right " + url);
		} else {
			log.error(name + " URL is wrong " + url);
		}

		return url;
	}

	public int CheckConnection(WebDriver object) throws MalformedURLException, IOException {

		String url = object.getCurrentUrl();
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setRequestMethod("HEAD");
		conn.connect();
		int respCode = conn.getResponseCode();

		if (respCode < 400) {
			log.info("The link with " + url + " is status ok with code " + respCode);
		} else {
			log.error("The link with " + url + " is broken with code " + respCode);
		}

		return respCode;
	}

	public String CheckTitlelNewTab(String name, String titleVer) {

		Set<String> tab = driver.getWindowHandles();
		Iterator<String> ittab = tab.iterator();

		while (ittab.hasNext()) {
			driver.switchTo().window(ittab.next());
		}

		String title = driver.getTitle();

		if (title.equals(titleVer)) {
			log.info(name + " title is right " + title);
		} else {
			log.error(name + " title is wrong " + title);
		}
		return title;
	}

	public String GetRandomNumberString() {

		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		return String.format("%06d", number);
	}

	public String DateTimeNow() {
		Date thisDate = new Date();
		SimpleDateFormat dateForm = new SimpleDateFormat("ddMMYYHHmmss");
		String dtNow = dateForm.format(thisDate);
		return dtNow;
	}

	public List<WebElement> CheckClickNewTabNext(String section, List<WebElement> element, WebElement button) throws InterruptedException {
		System.out.println("Case check every link in " + section + " is display");

		List<WebElement> links = element;

		log.info("Total links in " + section + " cards is " + links.size());

		for (int i = 0; i < links.size(); i++) {

			String clickonlinktab = Keys.chord(Keys.CONTROL, Keys.ENTER);

			if (links.get(i).isDisplayed()) {
				links.get(i).sendKeys(clickonlinktab);
				WebElement link = links.get(i);
				log.info("Successfully click new tab to link " + link.getAttribute("href"));
				Thread.sleep(500);
				button.click();
			} else {
				WebElement link = links.get(i);
				log.info("The link is not display " + link.getAttribute("href"));
			}
		}

		Set<String> tab = driver.getWindowHandles();
		Iterator<String> ittab = tab.iterator();

		while (ittab.hasNext()) {
			driver.switchTo().window(ittab.next());
			log.info("Title in tab is " + driver.getTitle());
			log.info("URL in tab is " + driver.getCurrentUrl());
		}
		return element;
	}

	public List<WebElement> CheckClickNewTab(String section, List<WebElement> element) throws InterruptedException {
		System.out.println("Case check every link in " + section + " is display");

		List<WebElement> links = element;

		log.info("Total links in " + section + " cards is " + links.size());

		for (int i = 0; i < links.size(); i++) {

			String clickonlinktab = Keys.chord(Keys.CONTROL, Keys.ENTER);

			if (links.get(i).isDisplayed()) {
				links.get(i).sendKeys(clickonlinktab);
				WebElement link = links.get(i);
				log.info("Successfully click new tab to link " + link.getAttribute("href"));
			} else {
				WebElement link = links.get(i);
				log.info("The link is not display " + link.getAttribute("href"));
			}
		}

		Set<String> tab = driver.getWindowHandles();
		Iterator<String> ittab = tab.iterator();

		while (ittab.hasNext()) {
			driver.switchTo().window(ittab.next());
			log.info("Title in tab is " + driver.getTitle());
			log.info("URL in tab is " + driver.getCurrentUrl());
		}
		return element;
	}
}