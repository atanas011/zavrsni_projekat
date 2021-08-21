package humanity.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import humanity.Utils;
import humanity.models.Dashboard;
import humanity.models.Home;
import humanity.models.Login;
import humanity.models.Settings;
import humanity.models.Staff;

public class TestHumanity {
	
	private static WebDriver driver;
	
	@BeforeSuite
	public static void createDriver() {
		System.setProperty("webdriver.chrome.driver", "C:/WebDrivers/bin/chromedriver.exe");
		driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@BeforeClass
	public static void set() {
		driver.get(Home.URL);
		Home.xAnnouncement(driver);
		Home.xCookies(driver);
//		Home.xBot(driver);
		Utils.zoomOut();
	}
	
	@Test
//	@Ignore
	/* Doci do stranice ABOUT US i napraviti screenshot strane. */
	public static void testAbout() {
		Home.openAbout(driver);
		Utils.takeScreenshot(driver, 300);
	}
	
	@Test
	/* Proveriti da li postojeci user moze uspesno da se loguje. */
	public static void testLogin() {
		Home.goToLogin(driver);
		Login.login(driver);
		Utils.sleep(2);
		String act = driver.getCurrentUrl();
		String exp = Dashboard.getDashUrl();
		Assert.assertEquals(act, exp);
	}
	
	@Test
//	@Ignore
	/* Proveriti sve elemente iz gornjeg menija - da li naziv iz menija */
	/* odgovara strani na koju sajt redirektuje prilikom kliktanja na dugme. */
	public static void testNavLinks() {
		SoftAssert sa = new SoftAssert();
		Dashboard dash = new Dashboard(driver);
		for (int i = 0; i < dash.getElements().size(); i++) {
			if(i == 6)
				continue;
			dash.getElements().get(i).click();
			if(i == 5)
				Utils.sleep(1);
			String act = driver.getCurrentUrl();
			String exp = Dashboard.urls[i];
			sa.assertEquals(act, exp);
			Utils.sleep(1);
		}
		sa.assertAll();
	}
	
	@Test (priority = 1)
//	@Ignore
	/* Provera linka "Availability" */
	public static void testAvailabilityLink() {
		driver.findElement(Dashboard.getAvailabilityId()).click();
		Utils.explicitlyWait(driver, 9, Dashboard.getLogoClass());
		String act = driver.getCurrentUrl();
		String exp = Dashboard.getAvailabilityUrl();
		Assert.assertEquals(act, exp);
		Utils.sleep(1);
		driver.navigate().to(Dashboard.getDashUrl());
	}
	
	@Test (priority = 2)
//	@Ignore
	/* Dodati novog zaposlenog i proveriti uspesnost dodavanja. */
	public static void testAddEmployee() {
		driver.findElement(Staff.getStaffId()).click();
		Utils.sleep(2);
		Staff oldStaff = new Staff(driver);
		Staff.addEmployee(driver);
		Utils.sleep(4);
		driver.findElement(Staff.getAllstaffLtxt()).click();
		Utils.sleep(2);
		Staff newStaff = new Staff(driver);
		Assert.assertTrue(newStaff.getEmployees().size() == oldStaff.getEmployees().size() + 1);
	}
	
	@Test (priority = 2)
//	@Ignore
	/* Zaposlenom promeniti ime. Zaposlenom dodati fotografiju. */
	public static void testEditNameAndPic() {
		driver.findElement(Staff.getStaffId()).click();
		Utils.sleep(2);
		Staff.editNameAndPic(driver);
	}
	
	@Test (priority = 3)
//	@Ignore
	/* Dodati minimum 5 novih zaposlenih i proveriti uspesnost dodavanja. */
	public static void testAddEmployees() {
		driver.findElement(Staff.getStaffId()).click();
		Utils.sleep(2);
		Staff oldStaff = new Staff(driver);
//		System.out.println(oldStaff.getEmployees().size());
		int num = Staff.addEmployees(driver);
		Utils.sleep(4);
		driver.findElement(Staff.getAllstaffLtxt()).click();
		Utils.sleep(2);
		Staff newStaff = new Staff(driver);
//		System.out.println(newStaff.getEmployees().size());
		Assert.assertTrue(newStaff.getEmployees().size() == oldStaff.getEmployees().size() + num);
	}
	
	@Test (priority = 3)
//	@Ignore
	/* Odcekirati (disable) notifikacije. */
	public static void testUncheckNots() {
		Settings.uncheckNots(driver);
	}
	
//	@Test (priority = 1)
//	/* Promeniti jezik. */
//	/* OVO NE RADI NI RUCNO.*/
//	public static void testChangeLang() {
//		Settings.changeLang(driver);
//	}
	
	/* Omoguciti da se barata podesavanjima profila (izmene po vasem izboru, najmanje 2). */
	/* OVO JE OMOGUCENO PO DEFAULT-u. */
	
	@AfterSuite
	public void tearDown() {
		Utils.sleep(1);
		driver.quit();
	}
}
