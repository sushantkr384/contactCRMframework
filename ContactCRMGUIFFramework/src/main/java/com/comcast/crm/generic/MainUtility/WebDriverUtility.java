package com.comcast.crm.generic.MainUtility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class WebDriverUtility {
	
	WebDriver driver;
	 public WebDriver getDriver(String browser) {
		 if(browser.equals("Chrome")) {
				driver=new ChromeDriver();
			}
			else if(browser.equals("Firefox")) {
				driver=new FirefoxDriver();
			}
			else if(browser.equals("edge")) {
				driver=new EdgeDriver();
			}
			else {
				driver=new ChromeDriver();
			}
		 return driver;
	 }
		 
		 public void openurl(String url){
			 driver.get(url);
		 }

		 public  void WaitForPageToLoad(WebDriver driver) {
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}
		 
		 public void MaximizePage(WebDriver driver) {
			 driver.manage().window().maximize();
			 
		 }
		 
		 public void movecursorto(WebElement element){
			 Actions act = new Actions(driver);
				act.moveToElement(element).perform();
		 }
		 
		 public void select(WebElement element,String text){
			 Select sel= new Select(element);
			 sel.selectByVisibleText(text);
		 }
		 public void closebrowser(){
			 driver.quit();
			 
			 
		}

}
