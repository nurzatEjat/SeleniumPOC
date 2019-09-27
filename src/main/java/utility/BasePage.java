package utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BasePage {
	
public static WebDriver driver;
	
	@BeforeMethod
	//@BeforeTest
	public void startTest() {
		// this steps does something
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		String Baseurl = "https://qa.totalwine.com";
		driver.get(Baseurl);

		driver.manage().window().maximize();

		driver.navigate().refresh();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.print("exception occurred");
		}
	}

	@AfterMethod
	//@AfterTest
	public void closeTest() {
		driver.close();
		driver.quit();

	}
	

}
