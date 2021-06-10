package humanity.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import humanity.Utils;

public class Home {
	
	public static final String URL = "https://www.humanity.com/";
	private static final String X_ANNOUNCEMENT_XPATH = "//*[@id=\"tcp-modal\"]/div/div/div[1]/button";
	private static final String X_COOKIES_XPATH = "//*[@id=\"cf-root\"]/div/div/div/div[2]/div[2]/div[2]/button";
//	private static final By BOT_XPATH = By.xpath("//*[@id=\"intercom-container\"]/div/div/div[1]");
//	private static final String X_BOT_XPATH = "//*[@id=\"intercom-container\"]/div/div/div[2]/svg";
	private static final By ABOUT_XPATH = By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[5]/a");
	private static final By ABOUTUS_XPATH = By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[6]/ul/li[1]/a");
	private static final String LOGIN_XPATH = "//*[@id=\"navbarSupportedContent\"]/div/a[1]/p";
	
	public static void xAnnouncement(WebDriver driver) {
		driver.findElement(By.xpath(X_ANNOUNCEMENT_XPATH)).click();
	}
	
	public static void xCookies(WebDriver driver) {
		driver.findElement(By.xpath(X_COOKIES_XPATH)).click();
	}
	
	public static void openAbout(WebDriver driver) {
		WebElement about = Utils.explicitlyWait(driver, 3, ABOUT_XPATH);
		Utils.mouseOver(driver, about);
		WebElement aboutUs = Utils.explicitlyWait(driver, 3, ABOUTUS_XPATH);
		aboutUs.click();
	}
	
	public static void goToLogin(WebDriver driver) {
		driver.findElement(By.xpath(LOGIN_XPATH)).click();
	}
	
//	public static void xBot(WebDriver driver) {
//		WebElement bot = Utils.explicitlyWait(driver, BOT_XPATH);
//		Actions builder = new Actions(driver);
//		Action mouseOver = builder.moveToElement(bot).build();
//		mouseOver.perform();
//		driver.findElement(By.xpath(X_BOT_XPATH)).click();
//	}
}
