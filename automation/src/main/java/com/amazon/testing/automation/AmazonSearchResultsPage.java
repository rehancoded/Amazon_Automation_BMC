package com.amazon.testing.automation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonSearchResultsPage {
	
	WebDriver driver;
	
	public AmazonSearchResultsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private String filterLowPriceTextBoxId 		= 	"low-price";
	private String filterHighPriceTextBoxId 	= 	"high-price";
	private String filterPriceGoButtonClassName	= 	"a-button-input";
	private String resultsPriceClassName 		=	"a-price-whole";
	
	public void applyPriceFilter(String priceFilter) {
		String[] price = priceFilter.split("-");
		WebElement lowPrice = driver.findElement(By.id(filterLowPriceTextBoxId));
		lowPrice.click();
		lowPrice.clear();
		lowPrice.sendKeys(price[0]);
		
		WebElement highPrice = driver.findElement(By.id(filterHighPriceTextBoxId));
		highPrice.click();
		highPrice.clear();
		highPrice.sendKeys(price[1]);
		
		driver.findElement(By.className(filterPriceGoButtonClassName)).click();;
	}
	
	public List<String> getSearchResultsPrice(List<String> priceList) {
		List<WebElement> priceElements = driver.findElements(By.className(resultsPriceClassName));
		String price[];
		for(WebElement ele : priceElements) {
			price = ele.getText().split(",");
			priceList.add(price[0] + price[1]);
		}
		return priceList;
	}
	
	public void gotoSearchResultsPage(String pageNo) {
		WebElement ele = driver.findElement(By.xpath("//a[@aria-label='Go to page " + pageNo + "']"));
		ele.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
}
