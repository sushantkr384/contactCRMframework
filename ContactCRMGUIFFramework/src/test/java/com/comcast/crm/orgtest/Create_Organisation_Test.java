package com.comcast.crm.orgtest;

import org.openqa.selenium.WebDriver;
import com.comcast.crm.generic.MainUtility.ExcelUtility;
import com.comcast.crm.generic.MainUtility.FileUtility;
import com.comcast.crm.generic.MainUtility.WebDriverUtility;
import com.comcast.crm.generic.MainUtility.JavaUtility;
import com.comcast.crm.objectrepositoryUtility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryUtility.CreatingNewOrganizations_Page;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.Login_Page;
import com.comcast.crm.objectrepositoryUtility.OrganizationsInfo_page;
import com.comcast.crm.objectrepositoryUtility.Organizations_page;
public class Create_Organisation_Test {

	public static void main(String[] args) throws Throwable {

		FileUtility flib =new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		WebDriverUtility wlib = new WebDriverUtility();
		JavaUtility jlib = new JavaUtility();

		String BROWSER = flib.getDataFromPropertiesfile("browser");
		String URL = flib.getDataFromPropertiesfile("url");
		String USERNAME = flib.getDataFromPropertiesfile("username");
		String PASSWORD = flib.getDataFromPropertiesfile("password");


		WebDriver driver = wlib.getDriver(BROWSER);
		Login_Page lp1 = new Login_Page(driver);
		
		
		
		String orgName = elib.getDataFromExcel("contact", 7, 2)+jlib.getRandomNumber();
		wlib.WaitForPageToLoad(driver);
		wlib.openurl(URL);

		
		// login to app
	      lp1.getUsernameEdt().sendKeys(USERNAME);	 
		  lp1.getPasswordEdt().sendKeys(PASSWORD);	
          lp1.getLoginBtn().click();
		
		
		 
		 
		 
		 
		// step:2 navigate to organization module
		
		HomePage hp = new HomePage(driver);
		hp.getOrganizationsLink().click();
		
		
		
		// step:3 click on "create organisation button"
		Organizations_page op = new Organizations_page(driver);
		op.getCreateNewOrgbtn().click();
		

		// step:4 enter all the details and create the new organisatio
		 CreatingNewOrganizations_Page cnop = new CreatingNewOrganizations_Page(driver);
		 cnop.getOrgNameEdt().sendKeys(orgName);
		 
		//save
		 CreatingNewContactPage cnp = new CreatingNewContactPage(driver);
		 cnp.getSaveBtn().click();
		 
		 
		 
		 

		// verify header msg expected result
		 OrganizationsInfo_page oip= new OrganizationsInfo_page(driver);
		 String actOrgName = oip.getHeaderMsg().getText();
		 
		 

		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + "is craeted== pass");
		} else {
			System.out.println(orgName + "is not craeted== fail");
		}
		
		
//logout
		    wlib.movecursorto(hp.getadminImg());
		    hp.getsignOutLink().click();
		    
		    driver.quit();

	}

}
