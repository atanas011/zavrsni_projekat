package humanity.models;

import java.util.List;

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
	
	private List<WebElement> employees;
	
	public Staff(WebDriver driver) {
		employees = driver.findElements(EMPLOYEE_CLASS);
	}
	
	public static void addEmployee(WebDriver driver) {
		WebElement[] elements = getElements(driver);
		String[] values = {"Mata", "Zamlata", "mata12@yahoo.com"};
		for (int i = 0; i < elements.length; i++) {
			elements[i] = driver.findElement(By.id("_as" + (i == 0 ? "f" : i == 1 ? "l" : "e") + 1));
			elements[i].click();
			elements[i].sendKeys(values[i]);
		}
	}

	public static void addEmployees(WebDriver driver) {
		WebElement[] elements = getElements(driver);
//		String[] values = {"Mata", "Zamlata", "mata@yahoo.com"};
		for (int i = 1; i < 2; i++) {
			for (int j = 0; j < elements.length; j++) {
				elements[j] = driver.findElement(By.id("_as" + (j == 0 ? "f" : j == 1 ? "l" : "e") + i));
				elements[j].click();
//				elements[j].sendKeys(values[j]);
			}
		}
		save(driver);
	}
	
	public static WebElement[] getElements(WebDriver driver) {
		driver.findElement(ADDEMPLOYEE_ID).click();
		Utils.sleep(1); // iz nekog razloga mora ovo !!!
		WebElement fName = null, lName = null, email = null;
		WebElement[] elements = {fName, lName, email};
		return elements;
	}
	
	public static void save(WebDriver driver) {
		driver.findElement(SAVEEMPLOYEE_ID).click();
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
