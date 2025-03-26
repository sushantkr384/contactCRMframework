
package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateContact_WithOrganisation {

	public static void main(String[] args) throws IOException {
		
		// read common data from json file
		FileInputStream propertyFile = new FileInputStream("./configAppData/commondata (2).properties");
				Properties pObj = new Properties();
				pObj.load(propertyFile);

				String BROWSER = pObj.getProperty("browser");
				String URL = pObj.getProperty("url");
				String USERNAME = pObj.getProperty("username");
				String PASSWORD = pObj.getProperty("password");

				// generate the random number

				Random random = new Random();
				int value = random.nextInt();

				// read test script data from excel file

				FileInputStream fis11 = new FileInputStream("./testData/testData.xlsx");
				Workbook wb = WorkbookFactory.create(fis11);
				Sheet sh = wb.getSheet("contact");
				Row row = sh.getRow(7);
				String orgName = row.getCell(2).toString()+value;
				String lastname = row.getCell(3).getStringCellValue();

				WebDriver driver = null;

				// polymorphism program run here
				if (BROWSER.equals("chrome")) {
					driver = new ChromeDriver();
				} else if (BROWSER.equals("firefox")) {
					driver = new FirefoxDriver();
				} else if (BROWSER.equals("edge")) {
					driver = new EdgeDriver();
				} else {
					driver = new ChromeDriver();
				}

				// step:1 login to app
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				driver.get(URL);

				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();

				// step:2 navigate to organization module
				driver.findElement(By.linkText("Organizations")).click();

				// step:3 click on "create organisation button"
				driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

				// step:4 enter all the details and create the new organisation
				driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
			     driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

			     
				// verify header msg expected result
				String headerinfo = driver.findElement(By.xpath("//span[contains(@class,'dvHeade')]")).getText();

				if (headerinfo.contains(orgName)) {
					System.out.println(orgName + "is craeted== pass");
				} else {
					System.out.println(orgName + "is not craeted== fail");
				}
				
				
				// step:5 navigate to contact module
				driver.findElement(By.linkText("Contacts")).click();

				// step:6 click on "create contact button"
				driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
				
				      // a  enter last anme
				 driver.findElement(By.name("lastname")).sendKeys(lastname);
				      // click the add button
				 driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
				 
				 driver.findElement(By.name("search_text")).sendKeys(orgName);
				 driver.findElement(By.name("search")).click();
				 driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();

				 
				      // b  save
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

				// verify header msg expected result
				String actuallastName = driver.findElement(By.id("dtlview_Last Name")).getText();

				       if (actuallastName.contains(lastname)) {
				        System.out.println(lastname + "is craeted== pass");
						} else {
							System.out.println(lastname + "is not craeted== fail");
						}

				

				driver.quit();


	}

}
