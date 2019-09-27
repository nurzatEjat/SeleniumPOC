package productPrice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetProductsPrice {
	WebDriver driver;

	
	@Test
	public void test() {
		List<String> CSVList = new ArrayList<>();
		try {
			int i=1;
			String line = "", price ="";
			BufferedReader br = new BufferedReader(new FileReader("src/main/resources/backupStocklevel-1.csv"));
			while( (line = br.readLine())!=null) {
				price=GetPrice(line.split(",")[0], line.split(",")[1]);
				CSVList.add(line+","+price);
				System.out.println("line: "+i+"  -> "+line+","+price);
				i++;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CSVWriter(CSVList);
		
	}
	

	void CSVWriter(List<String> list) {
		 FileWriter fileWriter = null;
         
	        try {
	            fileWriter = new FileWriter("src/main/resources/backupStocklevelNew.csv");
	            String[] productArray = null;
	             
	            //Write a new student object list to the CSV file
	            for (String data : list) {
	            	productArray = data.split(",");
	                for(String product : productArray) {
	                	fileWriter.append(product);
	                	fileWriter.append(",");
	                }

	                fileWriter.append("\n");
	            }
	 
	             
	             
	            System.out.println("CSV file was created successfully !!!");
	             
	        } catch (Exception e) {
	            System.out.println("Error in CsvFileWriter !!!");
	            e.printStackTrace();
	        } finally {
	             
	            try {
	                fileWriter.flush();
	                fileWriter.close();
	            } catch (IOException e) {
	                System.out.println("Error while flushing/closing fileWriter !!!");
	                e.printStackTrace();
	            }
	             
	        }
	}
	
	public String GetPrice(String sku, String store) {
		
		String Baseurl = "https://qa.totalwine.com/p/"+sku+"?s="+store+"&igrules=true";
		driver.get(Baseurl);
//		driver.manage().window().maximize();
//		driver.navigate().refresh();
//		WebDriverWait wait=new WebDriverWait(driver,20);

//		WebElement priceElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='priceContainer']//*[contains(*,'$')]")));
		List<WebElement> priceElem = driver.findElements(By.xpath("//*[@id='priceContainer']//*[contains(*,'$')]"));
		String price="";
		if(priceElem.size()!=0) {
			price =priceElem.get(0).getText();
			if(price.contains("+")) {
				price = price.replace("+CRV", ""); 
			}
		
		}else {
			price =  "NA";
		}
		return price;
	}

	
	
	
	
	@BeforeMethod
	public void startTest() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
//		driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();  
		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");  
		driver = new ChromeDriver(options); 
		driver.get("https://qa.totalwine.com");
		driver.navigate().refresh();
		wait(5);
	}

	@AfterMethod
	public void closeTest() {
		driver.close();
		driver.quit();

	}
	
	void wait(int seconds){
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			System.out.print("exception occurred");
		}
	}
}
