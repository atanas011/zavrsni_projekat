package humanity.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import humanity.Utils;
import humanity.models.Home;
import humanity.models.Login;
import humanity.models.Staff;

public class TestHumanity {
	
	private static WebDriver driver;
	
	@BeforeSuite
	public static void createDriver() {
		System.setProperty("webdriver.chrome.driver", "C:/WebDrivers/bin/chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@BeforeClass
	public static void set() {
		driver.get(Home.URL);
		driver.manage().window().maximize();
		Home.xAnnouncement(driver);
		Home.xCookies(driver);
//		Home.xBot(driver);
		Utils.zoomOut();
	}
	
//	@Test
//	/* Doci do stranice ABOUT US i napraviti screenshot strane. */
//	public static void testAbout() {
//		Home.openAbout(driver);
//		Utils.takeScreenshot(driver, 300);
//	}
	
	@Test
	/* Proveriti da li postojeci user moze uspesno da se loguje. */
	public static void testLogin() {
		Home.goToLogin(driver);
		Login.login(driver);
		Utils.sleep(2);
//		String act = driver.getCurrentUrl();
//		String exp = Dashboard.getDashUrl();
//		Assert.assertEquals(act, exp);
	}
	
//	@Test
//	/* Proveriti sve elemente iz gornjeg menija - da li naziv iz menija */
//	/* odgovara strani na koju sajt redirektuje prilikom kliktanja na dugme. */
//	public static void testNavLinks() {
//		SoftAssert sa = new SoftAssert();
//		Dashboard dash = new Dashboard(driver);
//		for (int i = 0; i < dash.getElements().size(); i++) {
//			if(i == 6)
//				continue;
//			dash.getElements().get(i).click();
//			if(i == 5)
//				Utils.sleep(1);
//			String act = driver.getCurrentUrl();
//			String exp = Dashboard.urls[i];
//			sa.assertEquals(act, exp);
//			Utils.sleep(1);
//		}
//		sa.assertAll();
//	}
	
//	@Test (priority = 1)
//	/* Provera linka "Availability" */
//	public static void testAvailabilityLink() {
//		driver.findElement(Dashboard.getAvailabilityId()).click();
//		Utils.explicitlyWait(driver, 9, Dashboard.getLogoClass());
//		String act = driver.getCurrentUrl();
//		String exp = Dashboard.getAvailabilityUrl();
//		Assert.assertEquals(act, exp);
//		Utils.sleep(1);
//	}
	
	@Test (priority = 1)
	/* Dodati novog zaposlenog i proveriti uspesnost dodavanja. */
	public static void testAddEmployee() {
		driver.findElement(Staff.getStaffId()).click();
		Utils.sleep(2);
		Staff oldStaff = new Staff(driver);
//		System.out.println(oldStaff.getEmployees().size());
		Staff.addEmployee(driver);
		Staff.save(driver);
		Utils.sleep(2);
		driver.findElement(Staff.getAllstaffLtxt()).click();
		Utils.sleep(2);
		Staff newStaff = new Staff(driver);
//		System.out.println(newStaff.getEmployees().size());
		Assert.assertTrue(newStaff.getEmployees().size() == oldStaff.getEmployees().size() + 1);
	}
	
	@AfterSuite
	public void tearDown() {
//		Utils.sleep(3);
		driver.quit();
	}
}
