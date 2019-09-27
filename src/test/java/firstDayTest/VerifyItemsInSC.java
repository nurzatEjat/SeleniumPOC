package firstDayTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import utility.BasePage;

public class VerifyItemsInSC extends BasePage{
	
	
	
	
@Test 
public void shoppingCartValidation() {
	
	
	WebElement search = driver.findElement(By.id("at_searchProducts"));
	
	search.sendKeys("chardonnay"+ Keys.ENTER);
	
	
	
	WebDriverWait wait = new WebDriverWait(driver, 5);
	
	WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(text(),' Add to cart')])[1]")));
	
	addToCart.click();
	
	WebElement atcModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//section//*[contains(@class,'mini-cart-popup anAddToCart')])[1]")));

	atcModal.click();
	
	try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//cartItemCount
	
	WebElement cartIcon = driver.findElement(By.id("cartItemCount"));
	cartIcon.click();
	

	
	WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Remove')]")));

	removeButton.click();
	
	WebElement removeYes = driver.findElement(By.id("agegate-secondary"));
	removeYes.click();
	
	try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
	
	
	
}



	
	

}
