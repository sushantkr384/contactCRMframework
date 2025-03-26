package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateOrganisation_WithIndustryTest {

	public static void main(String[] args) throws Throwable {
		
		// read common data from json file
		FileInputStream propertyFile = new FileInputStream("./configAppData/commondata (2).properties");
		Properties pObj= new Properties();
		pObj.load(propertyFile);
		
		String BROWSER=pObj.getProperty("browser");
		String URL=pObj.getProperty("url");
		String USERNAME=pObj.getProperty("username"); 
		String PASSWORD=pObj.getProperty("password");
		
		
		// generate the random number
		
		Random random = new Random();
		int value=random.nextInt();
		
		// read test script data from excel file
		
		FileInputStream fis11 = new FileInputStream("./testData/testData.xlsx");
		Workbook wb = WorkbookFactory.create(fis11);
		Sheet sh =wb.getSheet("org");
		Row row=sh.getRow(3);
		String orgName =row.getCell(2).toString()+value;
		String industry =row.getCell(3).toString();
		String type =row.getCell(4).toString();
		
		
		WebDriver driver = null;
		
		// polymorphism program run here
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if(BROWSER.equals("firefox")){
			driver= new FirefoxDriver();
		}
		else if(BROWSER.equals("edge")){
			driver= new EdgeDriver();
		}
		else {
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
		
		// step:5 to select th e value for the drop down
		WebElement wbsele1=driver.findElement(By.xpath("//select[@name='industry']"));
		Select sell1 =new Select(wbsele1);
		sell1.selectByVisibleText(industry);
		
		// step:5 to select the type drop down
		WebElement wbsele2=driver.findElement(By.xpath("//select[@name='accounttype']"));
		Select sell2 =new Select(wbsele2);
		sell2.selectByVisibleText(type);
		
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		
		// step:6 verify the dropdown industry and tpye info
		
		
		String asctualIndustry=driver.findElement(By.xpath("//option[@value='Energy']")).getText();
		
		if(asctualIndustry.equals(industry)) {
			System.out.println(industry+" information is verified== pass");
		}else {
			System.out.println(orgName+" information is not verified== fail");
		}
		
		String acttype=driver.findElement(By.xpath("//option[@value='Press']")).getText();
		if(acttype.equals(type)) {
			System.out.println(type+" information is verified== pass");
		}else {
			System.out.println(type+" information is not verified== fail"); 
		}
		driver.quit();
	}
}
