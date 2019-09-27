package firstDayTest;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
//import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PLP_CSV_URL {
  private WebDriver driver;
 // private Map<String, Object> vars;
  JavascriptExecutor js;
  
  Properties prop = new Properties();

  @BeforeMethod
 	//@BeforeTest
 	public void startTest() {
 		// this steps does something
 		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
 		try {
			FileInputStream br = new FileInputStream("src/main/resources/environemnts.txt");
		
			prop.load(br);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		driver = new ChromeDriver();
 	//********  get url from text file ***********//
 		driver.get(prop.getProperty("uat"));
  }



@AfterMethod
public void tearDown() {
    driver.quit();
  }
  @Test
  public void viewAlWine() {
	//driver.get(prop.getProperty("uat"));
    driver.manage().window().setSize(new Dimension(1500, 960));
    
  //Adding wait 
    driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    
    //Instantiate Action Class        
    Actions action = new Actions(driver);
    {
    driver.findElement(By.id("AT_headerwine_desktop")).click();

    }
    
    try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    driver.findElement(By.xpath("//*[contains(text(),'View All Wine')]")).click();
    
    try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    
    driver.findElement(By.xpath("//*[contains(text(),'Add to cart')]")).click();
    
	try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    driver.findElement(By.xpath("//*[contains(text(),'Add to cart')]")).click();
    
	try {
		Thread.sleep(8000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    driver.findElement(By.id("cartItemCount")).click();
    
    try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
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
  }
}
