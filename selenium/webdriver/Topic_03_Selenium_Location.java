package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Selenium_Location {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://live.demoguru99.com/index.php/customer/account/login");

	}

	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("email")).sendKeys("lhd.duy1307@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.id("send2")).click();
		// verify email & pws error message
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-pass")).isDisplayed());

	}

	@Test
	public void TC_02_Class() {
		driver.navigate().refresh();

		driver.findElement(By.className("f-left")).click();
		Assert.assertTrue(driver.findElement(By.className("fieldset")).isDisplayed());

	}

	@Test
	public void TC_03_Name() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/forgotpassword/");

		driver.findElement(By.name("email")).sendKeys("TEST");
		driver.findElement(By.className("required")).click();

	}

	@Test
	public void TC_04_TagName() {
		driver.navigate().refresh();

		List<WebElement> ForgetPwsLinks = driver.findElements(By.tagName("a"));
		for (WebElement webElement : ForgetPwsLinks) {
			System.out.println(webElement.getText());
		}

	}

	@Test
	public void TC_05_PartialLinkText() {

		driver.navigate().refresh();
		driver.findElement(By.partialLinkText("Back to Login")).click();

	}

	@Test
	public void TC_06_LinkText() {
		driver.findElement(By.linkText("Forgot Your Password?")).click();
		Assert.assertTrue(driver.findElement(By.className("required-entry")).isDisplayed());

	}

	@Test
	public void TC_07_CSS() {
		driver.findElement(By.cssSelector("#search")).sendKeys("Test01@gmail.com");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Assert.assertTrue(driver.findElement(By.className("note-msg")).isDisplayed());

	}

	@Test
	public void TC_08_Xpath() {
		driver.get("https://live.demoguru99.com/index.php/customer/account/login");
		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("Hoangduy@gmail.com");
		driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("123456789");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
