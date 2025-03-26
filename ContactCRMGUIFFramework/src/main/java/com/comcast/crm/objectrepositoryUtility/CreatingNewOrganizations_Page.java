package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizations_Page {
	
	WebDriver driver;
	public CreatingNewOrganizations_Page(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="accountname")
	private WebElement orgNameEdt;

    @FindBy(xpath="//input[@title='Save [Alt+S]']")
    private WebElement saveBtn;
    
    @FindBy(name="industry")
    private WebElement industryDropDown;

    
    
    
    
	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}
	
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	// create business method because two execution is done at a same time
	
	
	
	
		public void createOrg(String orgName ,String industry) {
			orgNameEdt.sendKeys(orgName);
			Select sel = new Select(industryDropDown);
			sel.selectByVisibleText(industry);
			saveBtn.click();
			
		}
	}
	
	
	
	
	
	

