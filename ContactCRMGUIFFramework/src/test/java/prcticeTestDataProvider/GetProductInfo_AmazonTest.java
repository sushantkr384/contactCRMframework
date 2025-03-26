package prcticeTestDataProvider;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class GetProductInfo_AmazonTest {
	@Test(dataProvider="getData")
	public void getProductInfoTest(String brandName , String productName) { 
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		driver.get("http://amazon.in");
		
		// search product
	driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
		
		// capture the product
		String x ="//span[text()='"+productName+"']/../../../../div[3]div[1]div/div[1]/div[1]/a/span/span[2]/span[2]";
		String price = driver.findElement(By.xpath((x)).getText();
		System.out.println(price);
		driver.quit();
		
		
		@DataProvider
		public Object[][] getData(){
			Object[][] objArr = new Object[3][2];
			Object[0][0] = "iphone";
			Object[0][1] = "Apple iPhone 15 (128 GB) - Black";
			
			
			Object[1][0] = "iphone";
			Object[1][1] = "Apple iPhone 15 (128 GB) - Pink";
			
			Object[2][0] = "iphone";
			Object[2][1] = "Apple iPhone 15 (128 GB) - Black";
			
			return objArr;
		}
		
		
	}

}
