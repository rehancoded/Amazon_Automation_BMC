package com.amazon.testing.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AmazonHomePage {
	
	WebDriver driver;
	
	public AmazonHomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	private String loginButtonId 			= "nav-link-accountList";
	private String loginIdTextboxName		= "email";
	private String continueButtonId			= "continue";
	private String loginPasswordTextboxId	= "ap_password";
	private String signInButtonId			= "signInSubmit";
	
	private String categoryDropDownId 	= "searchDropdownBox";
	private String searchTextBoxId 		= "twotabsearchtextbox";
	private String searchButtonId 		= "nav-search-submit-button";
	
	public void gotoSignInPage() {
		driver.findElement(By.id(loginButtonId)).click();
	}
	
	public void signIn(String id, String password) {
		driver.findElement(By.name(loginIdTextboxName)).sendKeys(id);
		driver.findElement(By.id(continueButtonId)).click();
		driver.findElement(By.id(loginPasswordTextboxId)).sendKeys(password);
		driver.findElement(By.id(signInButtonId)).click();
	}
	
	public void selectCategory(String category) {
		WebElement ele = driver.findElement(By.id(categoryDropDownId));
		Select sel = new Select(ele);
		sel.selectByVisibleText(category);
	}
	
	public void search(String searchText) {
		WebElement ele = driver.findElement(By.id(searchTextBoxId));
		ele.click();
		ele.clear();
		ele.sendKeys(searchText);
		
		driver.findElement(By.id(searchButtonId)).click();
	}
	
}
