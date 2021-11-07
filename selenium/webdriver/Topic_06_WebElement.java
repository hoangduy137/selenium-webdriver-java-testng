package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebElement {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.id("mail");
	By under18AgeRadio = By.id("under_18");
	By educationTextarea = By.id("edu");
	By user5Hover = By.xpath("//h5[text()='Name: User5']");

	By jobRole01SingleDropdown = By.id("job1");
	By jobRole02MultipleDropdown = By.id("job2");
	By developmentChecbox = By.id("development");
	By slider01Slider = By.id("slider-1");
	By pwsTextboxDisable = By.id("password");
	By radioIsButtonDisable = By.id("radio-disabled");
	By biographyTextareaDisable = By.id("bio");
	By jobRole03MultipleDropdown = By.id("job3");
	By checkboxIsDisabled = By.id("check-disbaled");
	By slider02SliderDisable = By.id("slider-2");
	By javaCheckbox = By.id("java");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// driver.get("https://automationfc.github.io/basic-form/index.html");
	}

	// @Test
	public void TC_01_IsDisplayed() {

		isElementDisplay(emailTextbox);
		isElementDisplay(under18AgeRadio);
		isElementDisplay(educationTextarea);

		isElementDisplay(user5Hover);

	}

	public boolean isElementDisplay(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element [" + by + "] is displayed");
			return true;
		} else {
			System.out.println("Element [" + by + "] is not displayed");
			return false;

		}

	}

	// @Test
	public void TC_02_IsEnabled() {
		driver.navigate().refresh();

		isElementEnabled(emailTextbox);
		isElementEnabled(under18AgeRadio);
		isElementEnabled(educationTextarea);
		isElementEnabled(jobRole01SingleDropdown);
		isElementEnabled(jobRole02MultipleDropdown);
		isElementEnabled(developmentChecbox);
		isElementEnabled(slider01Slider);

		isElementEnabled(pwsTextboxDisable);
		isElementEnabled(radioIsButtonDisable);
		isElementEnabled(biographyTextareaDisable);
		isElementEnabled(jobRole03MultipleDropdown);
		isElementEnabled(checkboxIsDisabled);
		isElementEnabled(slider02SliderDisable);

	}

	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element [" + by + "] is enabled");
			return true;
		} else {
			System.out.println("Element [" + by + "] is disable");
			return false;

		}

	}

//	@Test
	public void TC_03_IsSelected() {
		driver.navigate().refresh();
		driver.findElement(under18AgeRadio).click();
		driver.findElement(javaCheckbox).click();

		isSelected(under18AgeRadio);
		isSelected(javaCheckbox);

		driver.findElement(javaCheckbox).click();
		isSelected(javaCheckbox);

	}

	public boolean isSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element [" + by + "] is selected");
			return true;
		} else {
			System.out.println("Element [" + by + "] is de-selected");
			return false;

		}

	}

	@Test
	public void TC_04_RegisterFunctionAtMailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		WebElement pws = driver.findElement(By.id("new_password"));
		By signUp = By.id("create-account");

		
		By newletterCheckboxSelected = By.xpath("//input[@name='marketing_newsletter']");
		WebElement newletterCheckbox = driver.findElement(By.xpath("//input[@name='marketing_newsletter']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", newletterCheckbox);

		// driver.findElement(newletterCheckbox).click();

		driver.findElement(By.id("email")).sendKeys("test123@gmail.com");
		driver.findElement(By.id("new_username")).sendKeys("Test123");

		// nhập số
		pws.sendKeys("123");
		By oneNumber = By.xpath("//li[@class='number-char completed' and text()='One number']");
		isElementDisplay(oneNumber);
		isElementEnabled(signUp);
		isSelected(newletterCheckboxSelected);

		// nhập chữ thường
		pws.clear();
		pws.sendKeys("abc");
		Assert.assertTrue(driver
				.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']"))
				.isDisplayed());
		isElementEnabled(signUp);
		isSelected(newletterCheckboxSelected);

		// nhập kí tự đặc biệt
		pws.clear();
		pws.sendKeys("!@#");
		Assert.assertTrue(
				driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']"))
						.isDisplayed());
		isElementEnabled(signUp);
		isSelected(newletterCheckboxSelected);

		// nhập nhiều hơn 8 kí tự
		pws.clear();
		pws.sendKeys("​​​​​​​​​");// kí tự khoảng trống đặc biệt
		Assert.assertTrue(
				driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']"))
						.isDisplayed());
		isElementEnabled(signUp);
		isSelected(newletterCheckboxSelected);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
