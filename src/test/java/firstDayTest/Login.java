package firstDayTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import utility.BasePage;



public class Login extends BasePage{

	

	@Test(priority=2)
	public void searchWine() {

		WebElement searchBox = driver.findElement(By.id("at_searchProducts"));

		searchBox.sendKeys("wine");

		searchBox.sendKeys(Keys.ENTER);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// verify view all wine
		
		boolean result =verifyElement(By.xpath("//*[contains(text(),'View All Wine')]"));
		
		Assert.assertTrue(result, "view all wine button doesn't exists");

	}

	@Test(priority=1, enabled=false)
	public void firstTest() {

		WebElement signInHeader = driver.findElement(By.id("userNameGreeting"));

		signInHeader.click();

		WebElement signInModal = driver.findElement(By.id("iframe-signin-overlay"));

		driver.switchTo().frame(signInModal);

		WebElement username = driver.findElement(By.id("j_username"));

		username.sendKeys("checkout1@totalwine.com");

		WebElement password = driver.findElement(By.id("j_password"));

		password.sendKeys("Welcome@123");

		password.sendKeys(Keys.ENTER);

		driver.switchTo().defaultContent();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boolean result = verifyElement(By.xpath("//a[contains(text(),'Account home')]"));

		Assert.assertTrue(result, "Account page not displayed");

	}

	
	
	
	//By locator = By.xpath(xpathExpression);
	public boolean verifyElement(By locator) {

		// verify element is present
		// boolean result = driver.findElement(By.xpath("//a[contains(text(),'Account
		// home')]")).isDisplayed();
		//List<WebElement> elemlist = driver.findElements(By.xpath("//a[contains(text(),'Account home')]"));
		List<WebElement> elemlist = driver.findElements(locator);
		boolean result;
		if (elemlist.size() == 0) {
			result = false;

		} else {
			result = true;
		}

		return result;
	}

}
