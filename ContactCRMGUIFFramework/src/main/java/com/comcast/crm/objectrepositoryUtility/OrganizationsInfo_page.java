package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsInfo_page {

	WebDriver driver;
	public OrganizationsInfo_page(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this );
	}
		
		@FindBy(className ="dvHeaderText")
		private WebElement headerMsg; 
		
		public WebElement getHeaderMsg() {
			return headerMsg;
		}
		
		

	}



