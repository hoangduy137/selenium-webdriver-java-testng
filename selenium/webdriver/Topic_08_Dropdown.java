package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;
	WebDriverWait explicitWait; // wait tường mình chờ cho trạng thái element
	Topic_0405_Xpath_CSS topic_0405_Xpath_CSS = new Topic_0405_Xpath_CSS();

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);// wait ngầm định chờ cho element dc tìm thấy
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 30); // chờ cho element dc load lên
	}

	// @Test
	public void TC_01_JQuery() {

		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		selectItemInDropdown("//span[@id='number-button']", "//li[@class='ui-menu-item']", "19");
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),
				"19");
		topic_0405_Xpath_CSS.SleepInSeconds(1);

		selectItemInDropdown("//span[@id='number-button']", "//li[@class='ui-menu-item']", "1");
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),
				"1");
		topic_0405_Xpath_CSS.SleepInSeconds(1);

		selectItemInDropdown("//span[@id='number-button']", "//li[@class='ui-menu-item']", "5");
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),
				"5");
		topic_0405_Xpath_CSS.SleepInSeconds(1);

		selectItemInDropdown("//span[@id='number-button']", "//li[@class='ui-menu-item']", "10");
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),
				"10");

		topic_0405_Xpath_CSS.SleepInSeconds(1);

		selectItemInDropdown("//span[@id='number-button']", "//li[@class='ui-menu-item']", "15");
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),
				"15");
		topic_0405_Xpath_CSS.SleepInSeconds(1);

		selectItemInDropdown("//span[@id='number-button']", "//li[@class='ui-menu-item']", "7");
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),
				"7");
		topic_0405_Xpath_CSS.SleepInSeconds(1);

		selectItemInDropdown("//span[@id='number-button']", "//li[@class='ui-menu-item']", "17");
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),
				"17");
		topic_0405_Xpath_CSS.SleepInSeconds(1);
	}

//	@Test
	public void TC_02_ReactJS() {

		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		// System.out.println(driver.findElement(By.xpath("//div[@class='divider default
		// text']")).getText());

		selectItemInDropdown("//div[@class='ui fluid selection dropdown']", "//span[@class='text']", "Christian");
		driver.findElement(By.xpath("//div[@class='divider text']")).getText();
		System.out.println(driver.findElement(By.xpath("//div[@class='divider text']")).getText());
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Christian");

		topic_0405_Xpath_CSS.SleepInSeconds(1);
		selectItemInDropdown("//div[@class='ui fluid selection dropdown']", "//span[@class='text']", "Jenny Hess");
		System.out.println(driver.findElement(By.xpath("//div[@class='divider text']")).getText());
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Jenny Hess");

		topic_0405_Xpath_CSS.SleepInSeconds(1);
		selectItemInDropdown("//div[@class='ui fluid selection dropdown']", "//span[@class='text']",
				"Stevie Feliciano");
		System.out.println(driver.findElement(By.xpath("//div[@class='divider text']")).getText());
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Stevie Feliciano");

	}

	// @Test
	public void TC_03_VueJS() {

		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "First Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "First Option");
		topic_0405_Xpath_CSS.SleepInSeconds(1);

		selectItemInDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Second Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");
		topic_0405_Xpath_CSS.SleepInSeconds(1);

		selectItemInDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Third Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Third Option");
		topic_0405_Xpath_CSS.SleepInSeconds(1);

	}

	// @Test
	public void TC_04_Angular() {
		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
	}

	@Test
	public void TC_05_EditTable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		editTable("//input[@class='search']", "//div[contains(@class,'item')]", "Australia");
		Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'divider text')]")).getText(),
				"Australia");
		topic_0405_Xpath_CSS.SleepInSeconds(1);

		editTable("//input[@class='search']", "//div[contains(@class,'item')]", "Aland Islands");
		Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'divider text')]")).getText(),
				"Aland Islands");
		topic_0405_Xpath_CSS.SleepInSeconds(1);

		editTable("//input[@class='search']", "//div[contains(@class,'item')]", "Benin");
		Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'divider text')]")).getText(), "Benin");
		topic_0405_Xpath_CSS.SleepInSeconds(1);

	}

	public void selectItemInDropdown(String parentXpath, String childXpath, String expectedItem) {
		driver.findElement(By.xpath(parentXpath)).click(); // click để mở các item con

		// gọi hàm wait khác class
		topic_0405_Xpath_CSS.SleepInSeconds(2);

		// Chờ cho tất cả các item con dc hiển thị trong Dom trong x giây
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

		// Lấy hết tất cả các item
		List<WebElement> childItems = driver.findElements(By.xpath(childXpath));
		for (WebElement tempElement : childItems) {
			// Kiểm tra đúng với expected
			if (tempElement.getText().trim().equals(expectedItem)) {

				if (tempElement.isDisplayed()) {
					Actions ac = new Actions(driver);
					ac.moveToElement(tempElement).perform();
					ac.click(tempElement).perform();

				} else {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].scrollIntoView(true);", tempElement);
					Actions ac = new Actions(driver);
					ac.moveToElement(tempElement).perform();
					ac.click(tempElement).perform();

				}
				// System.out.println(childItems);
//				System.out.println("" + tempElement.getText() + "\n");
//				WebElement element = driver.findElement(By.xpath(childXpath));
//				JavascriptExecutor js = (JavascriptExecutor) driver;
//				js.executeScript("arguments[0].scrollIntoView(true);", element);
				// ((JavascriptExecutor)
				// driver).executeScript("arguments[0].scrollIntoView(true);", tempElement);
//				explicitWait.until(ExpectedConditions.refreshed(tempElement, "class", "ui-state-active"));

//			ac.moveToElement(tempElement).click().perform();// như vậy nhanh quá sẽ bị lỗi
//				tempElement.click();
				break;
			}
		}
	}

	public void editTable(String parentXpath, String childXpath, String expectedItem) {
		driver.findElement(By.xpath(parentXpath)).clear();
		topic_0405_Xpath_CSS.SleepInSeconds(1);

		driver.findElement(By.xpath(parentXpath)).sendKeys(expectedItem);
		topic_0405_Xpath_CSS.SleepInSeconds(1);

		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

		List<WebElement> childItems = driver.findElements(By.xpath(childXpath));
		for (WebElement actualItem : childItems) {

			if (actualItem.isDisplayed()) {
				Actions ac = new Actions(driver);
				ac.moveToElement(actualItem).perform();
				ac.click(actualItem).perform();

			} else {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", actualItem);
				Actions ac = new Actions(driver);
				ac.moveToElement(actualItem).perform();
				ac.click(actualItem).perform();

			}
			break;

		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
