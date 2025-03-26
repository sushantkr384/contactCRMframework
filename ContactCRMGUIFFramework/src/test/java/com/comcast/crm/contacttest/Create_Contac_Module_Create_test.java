 package com.comcast.crm.contacttest;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.MainUtility.ExcelUtility;
import com.comcast.crm.generic.MainUtility.FileUtility;
import com.comcast.crm.objectrepositoryUtility.ContactPage;
import com.comcast.crm.objectrepositoryUtility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.Login_Page;

public class Create_Contac_Module_Create_test {

	public static void main(String[] args) throws Throwable, IOException {
		
		// create object
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();

		String BROWSER = flib.getDataFromPropertiesfile("browser");
		String URL = flib.getDataFromPropertiesfile("url");
		String USERNAME = flib.getDataFromPropertiesfile("username");
		String PASSWORD = flib.getDataFromPropertiesfile("password");

		// generate the random number

		Random random = new Random();
		int value = random.nextInt();

		// read test script data from excel file

		String lastname = elib.getDataFromExcel("contact", 1, 2)+value;

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
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		driver.get(URL);
//
//		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
//		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
//		driver.findElement(By.id("submitButton")).click();
		Login_Page lp=new Login_Page(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
//		lp.getUsernameEdt().sendKeys(USERNAME);
//		lp.getPasswordEdt().sendKeys(PASSWORD);
//		lp.getLoginBtn().click();

		// step:2 navigate to contact module
		//driver.findElement(By.linkText("Contacts")).click();
		HomePage hp=new HomePage(driver);
		hp.getContactLink().click();

		// step:3 click on "create contact button"
		//driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		ContactPage cp=new ContactPage(driver);
		cp.getCreateContactBtn().click();
		// step:4 enter all the details and create the new organisation
				
		CreatingNewContactPage cnc= new CreatingNewContactPage(driver);
		cnc.getLastNameEdt().sendKeys(lastname);
		cnc.getSaveBtn().click();
		
		// a  enter last anme
//		driver.findElement(By.name("lastname")).sendKeys(lastname);
//		// b  save
//		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

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
