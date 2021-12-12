package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_DefaultDropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;
	Topic_0405_Xpath_CSS topic_0405_Xpath_CSS = new Topic_0405_Xpath_CSS();

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01() {
		String country = "Vietnam";
		driver.get("https://www.rode.com/wheretobuy");
		select = new Select(driver.findElement(By.name("where_country")));
		Assert.assertFalse(select.isMultiple()); // kiem tra dropdown ko phai la multi
		select.selectByVisibleText(country);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), country);
		// System.out.println(select.getFirstSelectedOption().getText());
		driver.findElement(By.id("search_loc_submit")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[(text()=', Vietnam')]")).getText(), ", " + country);

		List<WebElement> storeName = driver
				.findElements(By.xpath("//div[@id='search_results']//div[@class='store_name']"));
		for (WebElement webElement : storeName) {
			System.out.println(webElement.getText() + "\n");
		}

	}

@Test
	public void TC_02() {
		String password = " Test@123";
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		topic_0405_Xpath_CSS.SleepInSeconds(1);
		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys("TEST");
		driver.findElement(By.id("LastName")).sendKeys("Auto");
		driver.findElement(By.xpath("//select[@name='DateOfBirthDay']//option[(text()='1')]")).click();
		driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']//option[(text()='May')]")).click();
		driver.findElement(By.xpath("//select[@name='DateOfBirthYear']//option[(text()='1980')]")).click();
		String emailRegister = topic_0405_Xpath_CSS.generateRandomString(6) + "@gmail.com";
		driver.findElement(By.id("Email")).sendKeys(emailRegister);

		driver.findElement(By.xpath("//label[(text()='Password:')]/following-sibling::input")).sendKeys(password);
		driver.findElement(By.xpath("//label[(text()='Confirm password:')]/following-sibling::input"))
				.sendKeys(password);

		// verify
		int quanlityDay = 32, quanlityMonth = 13, quanlityYear = 112;

		List<WebElement> day = driver.findElements(By.xpath("//select[@name='DateOfBirthDay']//option"));
		Assert.assertEquals(day.size(), quanlityDay);
		List<WebElement> month = driver.findElements(By.xpath("//select[@name='DateOfBirthMonth']//option"));
		Assert.assertEquals(month.size(), quanlityMonth);
		List<WebElement> year = driver.findElements(By.xpath("//select[@name='DateOfBirthYear']//option"));
		Assert.assertEquals(year.size(), quanlityYear);

		driver.findElement(By.id("register-button")).click();
		topic_0405_Xpath_CSS.SleepInSeconds(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[(text()='Your registration completed')]")).isDisplayed());
		// driver.findElement(By.xpath("//div[(text()='Your registration
		// completed')]")).isDisplayed();

		driver.findElement(By.xpath("//div[@class='header-links']//a[(text()='My account')]")).click();
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1");
		

		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "May");
		

		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1980");
		

	}

	@Test
	public void TC_03() {
		String day = "1", month = "May", year = "1980", password = "Test@123";
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		topic_0405_Xpath_CSS.SleepInSeconds(1);
		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys("TEST");
		driver.findElement(By.id("LastName")).sendKeys("Auto");
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(day);
		Assert.assertEquals(select.getOptions().size(),32 );

		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getOptions().size(),13 );

		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getOptions().size(),112 );

		String emailRegister = topic_0405_Xpath_CSS.generateRandomString(6) + "@gmail.com";
		driver.findElement(By.id("Email")).sendKeys(emailRegister);
		
		driver.findElement(By.xpath("//label[(text()='Password:')]/following-sibling::input")).sendKeys(password);
		driver.findElement(By.xpath("//label[(text()='Confirm password:')]/following-sibling::input"))
				.sendKeys(password);
		
		driver.findElement(By.id("register-button")).click();
		topic_0405_Xpath_CSS.SleepInSeconds(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[(text()='Your registration completed')]")).isDisplayed());
		

		driver.findElement(By.xpath("//div[@class='header-links']//a[(text()='My account')]")).click();
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1");
		

		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "May");
		

		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1980");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
