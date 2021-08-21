package humanity;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
			ImageIO.write(screenshot.getImage(), "jpg", new File("./img/screenshot.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int getDate() {
		GregorianCalendar date = new GregorianCalendar();
		return date.get(Calendar.DATE);
	}
	
	public static List<String[]> readData(WebDriver driver, String fileName, String sheetName) {
		File f = new File(fileName);
		try {
			InputStream in = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(in);
			Sheet sheet = wb.getSheet(sheetName);
			
			int lastRow = sheet.getLastRowNum(); // ***
			int lastCol = sheet.getRow(0).getLastCellNum();
			
			String[] values = new String[lastRow];
			List<String[]> listOfValues = new ArrayList<>();
			
			for (int i = 1; i <= lastRow; i++) {
				Row row = sheet.getRow(i);
				for (int j = 0; j < lastCol; j++) {
					values[j] = row.getCell(j).toString();
				}
				listOfValues.add(values);
				values = new String[values.length];
			}
			wb.close();
			return listOfValues;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void uploadFile(String filePath) {
		StringSelection strSelection = new StringSelection(filePath);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(strSelection, null);
		try {
			Robot robot = new Robot();
			robot.delay(300);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(200);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	// *** getLastRowNum() gets the last row on the sheet.
	// Note: rows which had content before and were set to empty later
	// might still be counted as rows by Excel and Apache POI,
	// so the result of this method will include such rows
	// and thus the returned value might be higher than expected!
	
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
