package productPrice;


import static io.restassured.RestAssured.get;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import io.restassured.response.Response;


public class GetProductsPricebyAPI {
	WebDriver driver;

	
	@Test
	public void test() {
		List<String> CSVList = new ArrayList<>();
		try {
			int i=1;
			String line = "", price ="";
			BufferedReader br = new BufferedReader(new FileReader("src/main/resources/backupStocklevel.csv"));
			while( (line = br.readLine())!=null) {
				price=GetPrice(line.split(",")[0], line.split(",")[1]);
				CSVList.add(line+","+price);
				System.out.println("line: "+i+"  -> "+line+","+price);
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
			CSVWriter(CSVList);
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
		
		String Baseurl = "https://qa.totalwine.com/twmwebservices/v2/twm/products/"+sku+"?shoppingMethod=INSTORE_PICKUP&storeId="+store;
		String price="NA";
		try {
		Response rep = get(Baseurl);
		
		if(rep.jsonPath().getString("price[0].price")!=null) {
			price = rep.jsonPath().getString("price[0].price");
			if(price.contains("+")) {
				price = price.substring(0, price.indexOf('+')) ;
			}
		}
}catch(Exception e) {
	System.out.println("*****Has problem with this product ->"+sku+" store ID: "+store+" *******");
	e.printStackTrace();
}
		return price;
	}

	
	
	
	

	
	void wait(int seconds){
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			System.out.print("exception occurred");
		}
	}
}
