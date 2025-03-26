package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_Page {
	WebDriver driver;

	// rule 1: seperate java class
		// rule 2: object creation
	
	public Login_Page(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);   // this means parent object refference 
	}
	
	@FindBy(name="user_name")
	private WebElement usernameEdt;
	
	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;


	             // object initilization
	
	// rule 4: object Encapsulation --> right click select source select getters and shetters and click only getter method
	
	
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	//rule-5  buseness method 
	
	public void loginToApp(String username , String password) {
		driver.manage().window().maximize();
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
	}
}
