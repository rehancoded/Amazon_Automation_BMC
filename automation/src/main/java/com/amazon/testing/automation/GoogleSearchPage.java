package com.amazon.testing.automation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleSearchPage {
	
	WebDriver driver;
	public GoogleSearchPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private String searchTextBoxName = "q";
	private String searchResultsLinks = "//*[@class='LC20lb MBeuO DKV0Md']";
	
	public void gotoPage(String url) {
		driver.get(url);
	}
	
	public void enterSearchText(String searchText) {
		WebElement search = driver.findElement(By.name(searchTextBoxName));
		search.click();
		search.clear();
		search.sendKeys(searchText);
		search.sendKeys(Keys.ENTER);
	}
	
	public void printSearchResults() {
		List<WebElement> results = driver.findElements(By.xpath(searchResultsLinks));
		for(WebElement result: results) {
			System.out.println(result.getText());
		}
	}
	
	public void clickOnLink(String link) {
		driver.findElement(By.xpath("//a[@href='" + link + "']")).click();
	}
	
}
