package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

public class CreateContactWithSupportDateTest {

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
				Row row = sh.getRow(4);
				String lastname= row.getCell(2).toString()+value;

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

				// step:2 navigate to contact module
				driver.findElement(By.linkText("Contacts")).click();

				// step:3 click on "create contact button"
				driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

				// step:4 enter all the details and create the new organisation
				
				Date dateObj= new Date();
				SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
				String startDate = sim.format(dateObj);
				
				
				Calendar cal =sim.getCalendar();
				cal.add(Calendar.DAY_OF_MONTH,30);
				String endDate = sim.format(cal.getTime());
			
				
						
				// a  enter last anme
				driver.findElement(By.name("lastname")).sendKeys(lastname);
				
				
				driver.findElement(By.id("jscal_field_support_start_date")).clear();
				driver.findElement(By.id("jscal_field_support_start_date")).sendKeys(startDate);
				
				driver.findElement(By.id("jscal_field_support_end_date")).clear();
				driver.findElement(By.id("jscal_field_support_end_date")).sendKeys(endDate);
				
				// b  save
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

				// verify start date
				String startActualdate = driver.findElement(By.id("mouseArea_Support Start Date")).getText();

				       if (startActualdate.contains(startDate)) {
				        System.out.println(startDate + " "+ "is craeted== pass");
						} else {
							System.out.println(startDate + " "+"is not craeted== fail");
						} 
				       
				// verify end date
				       
				       String endActualdate = driver.findElement(By.id("dtlview_Support End Date")).getText();

				       if (endActualdate.contains(endDate)) {
				        System.out.println(endDate + " "+"is craeted== pass");
						} else {
							System.out.println(endDate + " "+ "is not craeted== fail");
						} 


						driver.quit();


	}

}
