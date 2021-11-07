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

public class Topic_08_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;

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
		Assert.assertEquals(select.getFirstSelectedOption().getText(),country);
		//System.out.println(select.getFirstSelectedOption().getText());
		driver.findElement(By.id("search_loc_submit")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[(text()=', Vietnam')]")).getText(),", "+country);
		
		List<WebElement> storeName = driver.findElements(By.xpath("//div[@id='search_results']//div[@class='store_name']"));
		for (WebElement webElement : storeName) {
			System.out.println(webElement.getText() + "\n");
		}
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
	
}
