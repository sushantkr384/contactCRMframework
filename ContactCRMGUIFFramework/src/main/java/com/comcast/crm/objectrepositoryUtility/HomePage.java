package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
//rule no -3  object initilization
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	// find contact link
	@FindBy(linkText="Contacts")
	private WebElement contactLink;
	
    // 4th rule - make constructor
	public WebElement getContactLink() {
		return contactLink;
	}
	
	
	// find organisation link
	@FindBy(linkText="Organizations")
	private WebElement OrganizationsLink;

	public WebElement getOrganizationsLink() { 
		return OrganizationsLink;
	}
	
	
	//campaigns link
	@FindBy(linkText="Campaigns")
	private WebElement campaignLink;
	
	public WebElement getcampaignLink() { 
		return campaignLink;
	}
	
	
	// find the more link
	@FindBy(linkText="More")
	private WebElement moreLink;
	
	public WebElement getmoreLink() { 
		return moreLink;
	}
	
	
	//find the signout link text
	@FindBy(linkText="Sign Out")
	private WebElement signOutLink;
	
	public WebElement getsignOutLink() { 
		return signOutLink;
	}
	
	
	@FindBy(linkText="Organizations")
	private WebElement OpportunitiesLink;

	public WebElement getOpportunitiesLink() {
		return OrganizationsLink;
	}
	
	// find 
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	public WebElement getadminImg() {
		return adminImg;
	}
	

	
	
//	public void navigateToCampaginPage() {
//		Actions act = new Actions(driver);
//		act.moveToElement(moreLink).perform();
//		campaignLink.click();
//	}
//	
//	public void logout() {
//		Actions act =new Actions(driver);
//		act.moveToElement(adminImg).perform();
//		signOutLink.click();
//	}
	
	
	
	
	// 5th rule -- if required business library make it ,business library is required if multiple action are  
	

}
