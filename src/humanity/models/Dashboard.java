package humanity.models;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Dashboard {
	
	private static String URL = "https://vitezkoja1.humanity.com/";
	private static final String DASH_URL = URL + "app/dashboard/";
	private static final String SHIFT_URL = URL + "app/schedule/employee/week/overview/overview/10%2c5%2c2021/";
	private static final String TIME_URL = URL + "app/timeclock/";
	private static final String LEAVE_URL = URL + "app/requests/vacation/";
	private static final String TRAINING_URL = URL + "app/training/";
	private static final String STAFF_URL = URL + "app/staff/list/load/true/";
	private static final String AVAILABILITY_URL = URL + "fe/availability/#/requests/week/2021-06-06/1";
	private static final String PAYROLL_URL = URL + "app/payroll/scheduled-hours/";
	private static final String REPORTS_URL = URL + "app/reports/dashboard/";
	private static final String SETTINGS_URL = URL + "app/admin/settings/";
	public static String[] urls = {DASH_URL, SHIFT_URL, TIME_URL, LEAVE_URL, TRAINING_URL, STAFF_URL, AVAILABILITY_URL, PAYROLL_URL, REPORTS_URL, SETTINGS_URL};
	private static final By AVAILABILITY_ID = By.id("sn_availability");
	private static final By LOGO_CLASS = By.className("Navigation-logo");
	private static final By NAV_CLASS = By.className("lft");
	
	private List<WebElement> elements;
	
	public Dashboard(WebDriver driver) {
		elements = driver.findElements(NAV_CLASS); // sme samo 1 klasa !!!
//		System.out.println(elements.size());
	}
	
	public List<WebElement> getElements() {
		return elements;
	}
	
	public static By getAvailabilityId() {
		return AVAILABILITY_ID;
	}

	public static String getAvailabilityUrl() {
		return AVAILABILITY_URL;
	}
	
	public static String getDashUrl() {
		return DASH_URL;
	}

	public static By getLogoClass() {
		return LOGO_CLASS;
	}
	
//	Ovo nema nikakvog smisla !!!
//	Prvo popunjava urls, a onda proverava da li su to oni.
//	Naravno da jesu.
//	List<String> urls;
//	urls = new ArrayList<>();
//	public void addURLs() {...}
}
