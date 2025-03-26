package com.comcast.crm.orgtest;

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

public class CreateOrgWithPhoneNumberTest {

	public static void main(String[] args) throws Throwable, IOException {
		// TODO Auto-generated method stub
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
		Sheet sh = wb.getSheet("org");
		Row row = sh.getRow(5);
		String orgName = row.getCell(2).toString()+value;
		String phoneNumber = row.getCell(3).toString();

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
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.id("phone")).sendKeys(phoneNumber);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		// header header phone number info expected result
		String actualPhnumber = driver.findElement(By.id("dtlview_Phone")).getText();
		if (actualPhnumber.equals(phoneNumber)) {
			System.out.println(phoneNumber + " information is craeted== pass");
		} else {
			System.out.println(phoneNumber + " information is not craeted== fail");
		}

		driver.quit();

	}

}
