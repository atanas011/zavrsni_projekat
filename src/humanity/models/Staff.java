package humanity.models;

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
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import humanity.Utils;

public class Staff {

	private static final By STAFF_ID = By.id("sn_staff");
	private static final By ADDEMPLOYEE_ID = By.id("act_primary");
	private static final By SAVEEMPLOYEE_ID = By.id("_as_save_multiple");
	private static final By ALLSTAFF_LTXT = By.partialLinkText("All Staff");
	private static final By EMPLOYEE_CLASS = By.className("StaffListTable-employee-row");
	private static final By EMPLOYEE_HREF = By.xpath("//*[@id=\"7\"]/a");
	private static final By EMPLOYEE_EDIT = By.linkText("Edit Details");
	private static final By EDIT_FNAME_ID = By.id("first_name");
	private static final By EDIT_PIC_ID = By.id("in-btn");
	private static final By EDIT_UPDATE_NAME = By.name("update");
	private static final String IMG_PATH = "C:\\Users\\Lenovo\\eclipse-workspace\\zavrsni_projekat\\img\\mata.jpg";
	
	private List<WebElement> employees;
	
	public Staff(WebDriver driver) {
		employees = driver.findElements(EMPLOYEE_CLASS);
	}
	
	public static void addEmployee(WebDriver driver) {
		driver.findElement(ADDEMPLOYEE_ID).click();
		WebElement[] elements = getElements(driver);
		String[] values = {"Mata", "Zamlata", "mata@yahoo.com"};
		for (int i = 0; i < elements.length; i++) {
			elements[i] = driver.findElement(By.id("_as" + (i == 0 ? "f" : i == 1 ? "l" : "e") + 1));
			elements[i].click();
			elements[i].sendKeys(values[i]);
		}
		driver.findElement(SAVEEMPLOYEE_ID).click();
	}
	
	public static void editNameAndPic(WebDriver driver) {
		driver.findElement(EMPLOYEE_HREF).click();
		Utils.sleep(2);
		driver.findElement(EMPLOYEE_EDIT).click();
		Utils.sleep(2);
		WebElement fName = driver.findElement(EDIT_FNAME_ID);
		fName.click();
		fName.clear();
		fName.sendKeys("Mata");
		uploadPic(driver);
		driver.findElement(EDIT_UPDATE_NAME).click();
		driver.findElement(STAFF_ID).click();
	}
	
	public static void uploadPic(WebDriver driver) {
		WebElement upload = driver.findElement(EDIT_PIC_ID);
		upload.click();
		StringSelection strSelection = new StringSelection(IMG_PATH);
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

	public static int addEmployees(WebDriver driver) {
		driver.findElement(ADDEMPLOYEE_ID).click();
		WebElement[] elements;
		List<String[]> listOfValues = readData(driver);
		for (int i = 0; i < listOfValues.size(); i++) {
			elements = getElements(driver);
			for (int j = 0; j < elements.length; j++) {
				elements[j] = driver.findElement(By.id("_as" + (j == 0 ? "f" : j == 1 ? "l" : "e") + (i + 1)));
				elements[j].click();
				elements[j].sendKeys(listOfValues.get(i)[j]);
			}
		}
		driver.findElement(SAVEEMPLOYEE_ID).click();
		return listOfValues.size();
	}
	
	public static WebElement[] getElements(WebDriver driver) {  // treba li ova metoda ???
		Utils.sleep(1); // iz nekog razloga mora ovo !!!
		WebElement fName = null, lName = null, email = null;
		WebElement[] elements = {fName, lName, email};
		return elements;
	}
	
	public static List<String[]> readData(WebDriver driver) {
		File f = new File("employees.xlsx");
		try {
			InputStream in = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(in);
			Sheet sheet = wb.getSheet("employees");
			int lastRow = sheet.getLastRowNum() + 1;
			List<String[]> listOfValues = new ArrayList<>();
			for (int i = 1; i < lastRow; i++) {
				Row row = sheet.getRow(i);
				Cell fName = row.getCell(0);
				Cell lName = row.getCell(1);
				Cell email = row.getCell(2);
				String[] values = {fName.toString(), lName.toString(), email.toString()};
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

	public static By getAllstaffLtxt() {
		return ALLSTAFF_LTXT;
	}

	public List<WebElement> getEmployees() {
		return employees;
	}

	public static By getStaffId() {
		return STAFF_ID;
	}
}
