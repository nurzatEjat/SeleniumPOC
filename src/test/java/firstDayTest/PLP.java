package firstDayTest;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;

public class PLP {
  private WebDriver driver;
 // private Map<String, Object> vars;
  JavascriptExecutor js;
  

  @BeforeMethod
	//@BeforeTest
	public void startTest() {
		// this steps does something
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		String Baseurl = "https://uat.totalwine.com";
		driver.get(Baseurl);
  
  }
  
@AfterMethod
public void tearDown() {
	driver.close();
    driver.quit();
  }
  @Test
  public void viewAlWine() {
    driver.get("https://uat.totalwine.com/");
    driver.manage().window().setSize(new Dimension(1500, 960));
    
  //Adding wait 
    driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    
    //Instantiate Action Class        
    Actions action = new Actions(driver);
    {
    driver.findElement(By.id("AT_headerwine_desktop")).click();

    }
    
    //Adding wait 
    driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
    
    driver.findElement(By.xpath("//*[contains(text(),'View All Wine')]")).click();
    
    
    driver.findElement(By.xpath("//*[contains(text(),'Add to cart')]")).click();
    
	waitFor(8000);
    
    driver.findElement(By.xpath("//*[contains(text(),'Add to cart')]")).click();
    waitFor(8000);  
    driver.findElement(By.id("cartItemCount")).click();
    
    waitFor(5000);
   {
      WebElement element = driver.findElement(By.cssSelector(".itemImage__2Z3-nT5O"));
      action.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.tagName("body"));
      action.moveToElement(element, 0, 0).perform();
    }
    {
     List<WebElement> elements = driver.findElements(By.cssSelector(".itemImage__2Z3-nT5O"));
      assert(elements.size() > 0);
    }
 
    driver.findElement(By.xpath("//*[contains(text(),'Secure Checkout')]")).click(); //Secure Checkout
  
    waitFor(5000);
  
    driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click(); ////button[contains(text(),'Continue')]
    
    waitFor(5000);
    driver.findElement(By.xpath("//input[contains(@type,'email')]")).sendKeys("poc@totalwine.com"); // - //input[contains(@type,'email')]
    
    waitFor(5000);

    driver.findElement(By.xpath("//input[contains(@type,'tel')]")).sendKeys("(310) 123-4567"); // - //input[contains(@type,'tel')]
    
    waitFor(5000);
    driver.findElement(By.xpath("//button[@aria-label='Save & Continue To Payment Section']")).click(); // - //button[@aria-label="Save & Continue To Payment Section"]
    
    waitFor(5000);
    driver.findElement(By.xpath("(//*[contains(text(),'Card Number')]//following::input)[1]")).sendKeys("4003000123456781"); // - (//*[contains(text(),'Card Number')]//following::input)[1]
    waitFor(5000);
    driver.findElement(By.xpath("(//*[contains(text(),'Expiration Date')]//following::input)[1]")).sendKeys("10/22"); // - (//*[contains(text(),'Expiration Date')]//following::input)[1]
      waitFor(5000);
    driver.findElement(By.xpath("(//*[contains(text(),'CVV')]//following::input)[1]")).sendKeys("123"); // - (//*[contains(text(),'CVV')]//following::input)[1]
    waitFor(5000);
   
    driver.findElement(By.xpath("(//*[contains(text(),'First Name')]//following::input)[1]")).sendKeys("test");
    waitFor(5000);
    driver.findElement(By.xpath("(//*[contains(text(),'Last Name')]//following::input)[1]")).sendKeys("selenium");
    waitFor(5000);
    driver.findElement(By.id("idAddress1")).sendKeys("6600 rockledge drive, bethesda");
    waitFor(5000);
    driver.findElement(By.xpath("//*[contains(text(),'Address')]//following::li")).click();
    waitFor(5000);
    driver.findElement(By.xpath("//button[contains(text(),'Save & Continue')]")).click();
    waitFor(5000);
    driver.findElement(By.id("Checkbox3")).click();
    waitFor(5000);
    driver.findElement(By.xpath("(//button[contains(text(),'Place Order')])[1]")).click();
    waitFor(5000);
    {
      List<WebElement> elements = driver.findElements(By.cssSelector(".thankyouContainer__3ua4ZWnm > p:nth-child(2)"));
      assert(elements.size() > 0);
    }
  
  }

public void waitFor(int ms) {
	try {
		Thread.sleep(ms);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
