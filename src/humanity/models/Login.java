package humanity.models;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {

	private static String email = "koja@yahoo.com";
	private static String pass = "koja123";

	public static void login(WebDriver driver) {
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(pass);
		WebElement loginBtn = driver.findElement(By.name("login"));
		loginBtn.sendKeys(Keys.ENTER);
	}
}
