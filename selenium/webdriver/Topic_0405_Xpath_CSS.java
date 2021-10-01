package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class Topic_0405_Xpath_CSS {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	String Firstname = "TEST";
	String Email = "abc@gmail.com";
	String Reemail = "abc@gmail.com";
	String Pws = "12345678";
	String Repws = "12345678";
	String Phone = "0987654321";
	String Emailtmp = generateRandomString(6) + "@gmail.com";
	String MiddleNametmp = generateRandomString(5);
	String LastNametmp = generateRandomString(8);

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

	}

	@Test
	public void TC_01_RegisterWithEmptyData() {
		String Mes_Firstname_Error = "Vui lòng nhập họ tên";
		String Mes_Email_Error = "Vui lòng nhập email";
		String Mes_Reemail_Error = "Vui lòng nhập lại địa chỉ email";
		String Mes_Pws_Error = "Vui lòng nhập mật khẩu";
		String Mes_Repws_Error = "Vui lòng nhập lại mật khẩu";
		String Mes_Phone_Error = "Vui lòng nhập số điện thoại.";

		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		// Thread.sleep(2000);
		Assert.assertEquals(
				driver.findElement(By.xpath("//div[@class='field']//label[@id='txtFirstname-error']")).getText(),
				Mes_Firstname_Error);
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), Mes_Email_Error);
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(),
				Mes_Reemail_Error);
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), Mes_Pws_Error);
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(),
				Mes_Repws_Error);
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), Mes_Phone_Error);
	}

	@Test
	public void TC_02_RegisterWithInvalidEmail() {
		driver.navigate().refresh();

		String Mes_Email_Invalid = "Vui lòng nhập email hợp lệ";
		String Mes_Reemail_Invalid = "Email nhập lại không đúng";

		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys(Firstname);
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys(Email + "@");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys(Reemail + "@");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(Pws);
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys(Repws);
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys(Phone);
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), Mes_Email_Invalid);
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(),
				Mes_Reemail_Invalid);

	}

	@Test
	public void TC_03_RegisterWithIncorrectConfirmEmail() {
		driver.navigate().refresh();
		String Mes_Reemail_Invalid = "Email nhập lại không đúng";

		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys(Firstname);
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys(Email);
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys(Reemail + ".vn");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(Pws);
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys(Repws);
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys(Email);
		SleepInSeconds(1);
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		SleepInSeconds(1);
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(),
				Mes_Reemail_Invalid);
	}

	@Test
	public void TC_04_RegisterWithPws6Character() {
		driver.navigate().refresh();
		String Mes_Pws_Invalid = "Mật khẩu phải có ít nhất 6 ký tự";
		String Mes_Repws_Invalid = "Mật khẩu phải có ít nhất 6 ký tự";

		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys(Firstname);
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys(Email);
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys(Reemail);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(Pws.substring(0, 5));
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys(Repws.substring(0, 5));
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys(Phone);
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(),
				Mes_Pws_Invalid);
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(),
				Mes_Repws_Invalid);
	}

	@Test
	public void TC_05_RegisterWithIncorrectConfirmPws() {
		driver.navigate().refresh();
		String Mes_Repws_Invalid = "Mật khẩu bạn nhập không khớp";

		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys(Firstname);
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys(Email);
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys(Reemail);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(Pws);
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys(Repws.substring(0, 7));
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys(Phone);
		SleepInSeconds(2);
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(),
				Mes_Repws_Invalid);

	}

	@Test
	public void TC_06_RegisterWithInvalidPhone() {
		driver.navigate().refresh();
		String Mes_Phone_Error = "Vui lòng nhập con số";

		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys(Firstname);
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys(Email);
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys(Reemail);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(Pws);
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys(Repws);
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys(".e");
		SleepInSeconds(1);
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), Mes_Phone_Error);

	}

	@Test
	public void TC_07_LoginWithEmptyEmailPws() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(),
				"This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(),
				"This is a required field.");

	}

	@Test
	public void TC_08_LoginInvalidWithEmail() {
		driver.navigate().refresh();

		String MesEmailInvalid = "Please enter a valid email address. For example johndoe@domain.com.";
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(Email + "123");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(Pws);
		driver.findElement(By.xpath("//button[@name='send']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(),
				MesEmailInvalid);

	}

	@Test
	public void TC_09_LoginWithPws6Character() {
		driver.navigate().refresh();

		String MesPwsInValid = "Please enter 6 or more characters without leading or trailing spaces.";
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(Email);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(Pws.substring(0, 5));
		driver.findElement(By.xpath("//button[@name='send']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(),
				MesPwsInValid);

	}

	@Test
	public void TC_10_LoginIncorrectPwsEmail() {
		driver.navigate().refresh();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(Email);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(Pws);
		driver.findElement(By.xpath("//button[@name='send']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']/li[@class='error-msg']")).getText(),
				"Invalid login or password.");

	}

	@Test
	public void TC_11_CreateNewAccount() {
		driver.navigate().refresh();

		String MesSuccess = "Thank you for registering with Main Website Store.";

		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(Firstname);
		driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys(MiddleNametmp);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(LastNametmp);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(Emailtmp);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(Pws);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(Repws);
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), MesSuccess);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='dashboard']//h1")).getText(), "MY DASHBOARD");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(),
				"Hello, " + Firstname + " " + MiddleNametmp + " " + LastNametmp + "!");

		List<WebElement> BoxContents = driver.findElements(By.xpath("//div[@class='col-1']//p"));
		// System.out.println(BoxContents.size());
		Assert.assertEquals(BoxContents.get(0).getText().split("\\n")[0],
				Firstname + " " + MiddleNametmp + " " + LastNametmp);
		Assert.assertEquals(BoxContents.get(0).getText().split("\\n")[1], Emailtmp);

		// System.out.println( BoxContents.get(0).getText().split("\\n")[0]); Cat chuoi
		// string voi ki tu xuong dong

//		for ( int i= 0; i<BoxContents.size();i++) {
//			System.out.println(BoxContents.get(0).getText());
//			
//		}
//		

		driver.findElement(By.xpath("//header/div/div/div/a/span[@class='label']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//li[@class=' last']/a")).click();
		// System.out.println(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText());
		// System.out.println(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText());

	}

	private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

	private int getRandomNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(CHAR_LIST.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}

	public String generateRandomString(int n) {
		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < n; i++) {
			int number = getRandomNumber();
			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	@Test
	public void TC_12_LoginSuccess() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(Emailtmp);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(Pws);
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='dashboard']//h1")).getText(), "MY DASHBOARD");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']/p[@class='hello']")).getText(),
				"Hello, " + Firstname + " " + MiddleNametmp + " " + LastNametmp + "!");

		List<WebElement> BoxContents = driver.findElements(By.xpath("//div[@class='col-1']//p"));
		Assert.assertEquals(BoxContents.get(0).getText().split("\\n")[0],
				Firstname + " " + MiddleNametmp + " " + LastNametmp);
		Assert.assertEquals(BoxContents.get(0).getText().split("\\n")[1], Emailtmp);

	}

	public void SleepInSeconds(long s) {
		try {
			Thread.sleep(s * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
