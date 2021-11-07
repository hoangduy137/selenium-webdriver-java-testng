package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_TextArea {
	WebDriver driver; // là 1 thư viện
	String projectPath = System.getProperty("user.dir");

	Topic_0405_Xpath_CSS toppic0405XpathCss = new Topic_0405_Xpath_CSS();
	String emailRegister = toppic0405XpathCss.generateRandomString(6) + "@gmail.com";

	String emailLogin, Password;
	String customerName = "Test";
	String gender = "male";
	String dateofBirthBefore = "05/07/1997";
	String dateofBirthAfter = "1997-05-07";

	String address = "308 Dien Bien Phu";
	String addressUpdate = "308 Dien Bien Phu Quan 3 HCM";
	String city = "Ho Chi Minh";
	String cityUpdate = "Ho Chi Minh Update";
	String stateUpdate = "VietNam";
	String state = "Thu Duc";
	String pin = "123456";
	String pinUpdate = "112233";
	String mobilePhone = "0987654321";
	String mobilePhoneUpdate = "09000000";
	String email = emailRegister;
	String emailUpdate = emailRegister;
	String password = "Test@123";
	String customerId;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01() {

		driver.get("http://demo.guru99.com/v4/");
		String urlLogin = driver.getCurrentUrl();

		driver.findElement(By.xpath("//a[(text()='here')]")).click();
		driver.findElement(By.name("emailid")).sendKeys(emailRegister);
		driver.findElement(By.name("btnLogin")).click();
		emailLogin = driver.findElement(By.xpath("//td[(text()='User ID :')]/following-sibling::td")).getText();
		Password = driver.findElement(By.xpath("//td[(text()='Password :')]/following-sibling::td")).getText();
//		System.out.println("emailLogin: " + emailLogin + "\n");
//		System.out.println("Password: " + Password + "\n" );
		driver.get(urlLogin);

		// login
		driver.findElement(By.name("uid")).sendKeys(emailLogin);
		driver.findElement(By.name("password")).sendKeys(Password);
		driver.findElement(By.name("btnLogin")).click();

		// kiểm tra login thành công
		Assert.assertTrue(
				driver.findElement(By.xpath("//marquee[(text()=\"Welcome To Manager's Page of Guru99 Bank\")]"))
						.isDisplayed());

		driver.findElement(By.xpath("//a[(text()='New Customer')]")).click();

		driver.findElement(By.name("name")).sendKeys(customerName);
		driver.findElement(By.xpath("//input[@value='m']")).click();

		WebElement date = driver.findElement(By.name("dob"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].removeAttribute('type')", date);
		date.sendKeys(dateofBirthBefore);

		driver.findElement(By.name("addr")).sendKeys(address);
		driver.findElement(By.name("city")).sendKeys(city);
		driver.findElement(By.name("state")).sendKeys(state);
		driver.findElement(By.name("pinno")).sendKeys(pin);
		driver.findElement(By.name("telephoneno")).sendKeys(mobilePhone);
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		customerId = driver.findElement(By.xpath("//td[(text()='Customer ID')]/following-sibling::td")).getText();
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[(text()='Customer Name')]/following-sibling::td")).getText(),
				customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[(text()='Gender')]/following-sibling::td")).getText(),
				gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[(text()='Birthdate')]/following-sibling::td")).getText(),
				dateofBirthAfter);
		Assert.assertEquals(driver.findElement(By.xpath("//td[(text()='Address')]/following-sibling::td")).getText(),
				address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[(text()='City')]/following-sibling::td")).getText(),
				city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[(text()='State')]/following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[(text()='Pin')]/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[(text()='Mobile No.')]/following-sibling::td")).getText(),
				mobilePhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[(text()='Email')]/following-sibling::td")).getText(),
				email);

		driver.findElement(By.xpath("//a[(text()='Edit Customer')]")).click();
		toppic0405XpathCss.SleepInSeconds(2);
		driver.findElement(By.name("cusid")).sendKeys(customerId);
		System.out.println(customerId);
		driver.findElement(By.name("AccSubmit")).click();

		driver.findElement(By.name("name")).getAttribute("value");
		Assert.assertEquals(driver.findElement(By.name("name")).getAttribute("value"), customerName);
		Assert.assertEquals(driver.findElement(By.name("addr")).getAttribute("value"), address);

		driver.findElement(By.name("addr")).clear();
		driver.findElement(By.name("city")).clear();
		driver.findElement(By.name("state")).clear();
		driver.findElement(By.name("pinno")).clear();
		driver.findElement(By.name("telephoneno")).clear();
		driver.findElement(By.name("emailid")).clear();

		driver.findElement(By.name("addr")).sendKeys(addressUpdate);
		driver.findElement(By.name("city")).sendKeys(cityUpdate);
		driver.findElement(By.name("state")).sendKeys(stateUpdate);
		driver.findElement(By.name("pinno")).sendKeys(pinUpdate);
		driver.findElement(By.name("telephoneno")).sendKeys(mobilePhoneUpdate);
		driver.findElement(By.name("emailid")).sendKeys(emailUpdate);
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//td[(text()='Address')]/following-sibling::td")).getText(),
				addressUpdate);
		Assert.assertEquals(driver.findElement(By.xpath("//td[(text()='City')]/following-sibling::td")).getText(),
				cityUpdate);
		Assert.assertEquals(driver.findElement(By.xpath("//td[(text()='State')]/following-sibling::td")).getText(),
				stateUpdate);
		Assert.assertEquals(driver.findElement(By.xpath("//td[(text()='Pin')]/following-sibling::td")).getText(),
				pinUpdate);
		Assert.assertEquals(driver.findElement(By.xpath("//td[(text()='Mobile No.')]/following-sibling::td")).getText(),
				mobilePhoneUpdate);
		Assert.assertEquals(driver.findElement(By.xpath("//td[(text()='Email')]/following-sibling::td")).getText(),
				emailUpdate);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
