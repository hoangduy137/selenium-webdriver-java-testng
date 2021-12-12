package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_RadioButton_CheckBox_Alert {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Topic_0405_Xpath_CSS topic_0405_Xpath_CSS = new Topic_0405_Xpath_CSS();
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.xpath("//li[contains(@class,'popup-login-tab-login')]")).click();
		topic_0405_Xpath_CSS.SleepInSeconds(1);
		By buttonLogin = By.xpath("//button[@class='fhs-btn-login']");
		isElementDisable(buttonLogin);
		Assert.assertFalse(driver.findElement(buttonLogin).isEnabled());
		Assert.assertTrue(driver.findElement(buttonLogin).isDisplayed());

		driver.findElement(By.id("login_username")).sendKeys("0987654321");
		driver.findElement(By.id("login_password")).sendKeys("0987654321");

		isElementDisable(buttonLogin);
		Assert.assertTrue(driver.findElement(buttonLogin).isEnabled());
		Assert.assertTrue(driver.findElement(buttonLogin).isDisplayed());

		System.out.println(driver.findElement(buttonLogin).getCssValue("background-color"));
		// System.out.println(driver.findElement(buttonLogin).getCssValue("color"));

		driver.navigate().refresh();
		topic_0405_Xpath_CSS.SleepInSeconds(3);

		driver.findElement(By.xpath("//li[contains(@class,'popup-login-tab-login')]")).click();
		removeDisableAtrributeByJs(buttonLogin);
		driver.findElement(buttonLogin).click();

		Assert.assertEquals(driver
				.findElement(By.xpath("//div[@class='fhs-input-box checked-error']//div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");

	}

	// @Test

	public void TC_02_DefaultCheckboxorRadioButton() {

		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

//scroll len moi co the click vao element dc
		WebElement element = driver.findElement(By.id("demo-runner"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);

		WebElement checkBox = driver.findElement(By.id("eq5"));
		checkBox.click();
		Assert.assertTrue(checkBox.isSelected()); // kiem tra da click hay chuaw

		topic_0405_Xpath_CSS.SleepInSeconds(2);

		// kiem tra selected hay chua roi bo select
		if (checkBox.isSelected()) {
			checkBox.click();
		}

		Assert.assertFalse(checkBox.isSelected());

		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		WebElement element01 = driver.findElement(By.id("demo-runner"));
		JavascriptExecutor js01 = (JavascriptExecutor) driver;
		js01.executeScript("arguments[0].scrollIntoView(true);", element01);

		WebElement radioCheckBox = driver.findElement(By.id("engine3"));
		radioCheckBox.click();

		WebElement radioCheckBox1 = driver.findElement(By.id("engine1"));
		radioCheckBox1.click();

		topic_0405_Xpath_CSS.SleepInSeconds(2);

		if (!radioCheckBox.isSelected()) {
			radioCheckBox.click();
			Assert.assertTrue(radioCheckBox.isSelected());

		}

	}

	// @Test
	public void TC_03_CustomCheckboxorRadioButton() {

		driver.get("https://material.angular.io/components/radio/examples");

		WebElement element = driver.findElement(By.xpath("//div[text()='Radios with ngModel']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);

		WebElement buttonSummer = driver
				.findElement(By.xpath("//mat-radio-button[@id='mat-radio-4']//span[@class='mat-radio-inner-circle']"));
		js.executeScript("arguments[0].click()", buttonSummer);

		boolean checkRadio = driver
				.findElement(By.xpath("//mat-radio-button[@id='mat-radio-4' and contains(@class,'mat-radio-checked')]"))
				.isDisplayed();
		// System.out.println(checkRadio);
		if (checkRadio = false) {
			js.executeScript("arguments[0].click()", buttonSummer);
		}

		driver.get("https://material.angular.io/components/checkbox/examples");
		WebElement element01 = driver.findElement(By.xpath("//div[text()='Configurable checkbox']"));
		js.executeScript("arguments[0].scrollIntoView(true);", element01);

		driver.findElement(By.xpath("//input[@id='mat-checkbox-1-input']/parent::span")).click();
		driver.findElement(By.xpath("//input[@id='mat-checkbox-2-input']/parent::span")).click();
		topic_0405_Xpath_CSS.SleepInSeconds(1);

		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='mat-checkbox-1-input']")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='mat-checkbox-2-input']")).isSelected());

		driver.findElement(By.xpath("//input[@id='mat-checkbox-1-input']/parent::span")).click();
		driver.findElement(By.xpath("//input[@id='mat-checkbox-2-input']/parent::span")).click();
		topic_0405_Xpath_CSS.SleepInSeconds(2);

		Assert.assertFalse(driver.findElement(By.xpath("//input[@id='mat-checkbox-1-input']")).isSelected());
		Assert.assertFalse(driver.findElement(By.xpath("//input[@id='mat-checkbox-2-input']")).isSelected());

	}

	// @Test
	public void TC_04_CustomCheckboxorRadioButton() {
		driver.get(
				"https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

		driver.findElement(By.id("i14")).click();
		String check = (driver.findElement(By.id("i14")).getAttribute("aria-checked"));
		System.out.println(check);

		if ("false".equals(check)) {
			driver.findElement(By.id("i14")).click();
		} else {
			Assert.assertTrue(
					driver.findElement(By.xpath("//div[contains(@class,'isChecked') and (@id='i14')]")).isDisplayed());
		}

	}

//	@Test
	public void TC_05_AcceptAlert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//span[@class='number' and text()='9']")));
		driver.findElement(By.xpath("//button[@onclick ='jsAlert()']")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		topic_0405_Xpath_CSS.SleepInSeconds(2);
		alert.accept();

		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");

	}

//	@Test
	public void TC_06_ConfirmAlert() {

		driver.get("https://automationfc.github.io/basic-form/index.html");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//span[@class='number' and text()='9']")));
		driver.findElement(By.xpath("//button[@onclick ='jsConfirm()']")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		topic_0405_Xpath_CSS.SleepInSeconds(2);
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");

	}

	// @Test
	public void TC_07_PromptAlert() {
		String text = "Hello word";

		driver.get("https://automationfc.github.io/basic-form/index.html");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//span[@class='number' and text()='9']")));
		driver.findElement(By.xpath("//button[@onclick ='jsPrompt()']")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		topic_0405_Xpath_CSS.SleepInSeconds(2);
		alert.sendKeys(text);
		topic_0405_Xpath_CSS.SleepInSeconds(2);
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: " + text);

	}

	@Test
	public void TC_08_AuthenticationAlert() {
	    String usernameAndPass = "admin";
		driver.get("https://the-internet.herokuapp.com/");
		String url = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		System.out.println(url);
		//https://admin:admin@the-internet.herokuapp.com/basic_auth
		String[] splitUrl = url.split("//");
		url = splitUrl[0] + "//" + usernameAndPass + ":" + usernameAndPass + "@" + splitUrl[1] ;
		driver.get(url);
		topic_0405_Xpath_CSS.SleepInSeconds(2);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
		
		
		
	}

	public boolean isElementDisable(By by) {

		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element is enabled: " + by);
			return true;
		} else {
			System.out.println("Element is disabled: " + by);
			return false;
		}

	}

	public void removeDisableAtrributeByJs(By by) {
		WebElement element = driver.findElement(by);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].removeAttribute('disabled')", element);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
