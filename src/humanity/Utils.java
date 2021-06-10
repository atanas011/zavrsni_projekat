package humanity;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Utils {

	public static void sleep(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void zoomOut() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_MINUS);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public static void mouseOver(WebDriver driver, WebElement element) {
		Actions builder = new Actions(driver);
		Action mouseOver = builder.moveToElement(element).build();
		mouseOver.perform();
	}
	
	public static WebElement explicitlyWait(WebDriver driver, int sec, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, sec);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}

	public static void takeScreenshot(WebDriver driver, int ms) {
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(ms)).takeScreenshot(driver);
		try {
			ImageIO.write(screenshot.getImage(), "jpg", new File("./img/screenshot.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public static void takeSnapShot(WebDriver driver) {
//		TakesScreenshot scrShot = ((TakesScreenshot) driver);
//		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
//		File DestFile = new File("./img/screenshot.jpg");
//		try {
//			FileUtils.copyFile(SrcFile, DestFile);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
