package com.amazon.testing.automation;

import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.asserts.SoftAssert;

public class AmazonSearchTest extends BaseTest {
	
	
	@Test
	public void searchTest() {
		
		List<String> priceList = new ArrayList<>();
		
		SoftAssert softAssert = new SoftAssert();
		
		GoogleSearchPage googleSearch = new GoogleSearchPage(driver);
		AmazonHomePage amazonHome = new AmazonHomePage(driver);
		AmazonSearchResultsPage amazonSearch = new AmazonSearchResultsPage(driver);
		
		googleSearch.gotoPage("https://www.google.com");
		googleSearch.enterSearchText("amazon");
		googleSearch.printSearchResults();
		googleSearch.clickOnLink("https://www.amazon.in/");
		
		amazonHome.gotoSignInPage();
		//Login to Amazon account
		amazonHome.signIn("shaikrehan3008@gmail.com", "Rehan@3008");
		
		amazonHome.selectCategory("Electronics");
		amazonHome.search("dell computers");
		
		String lowPrice = "30000";
		String highPrice = "50000";
		int lowPriceInNum 	= Integer.parseInt(lowPrice);
		int highPriceInNum 	= Integer.parseInt(highPrice);
		amazonSearch.applyPriceFilter(lowPrice + "-" + highPrice);
		priceList.addAll(amazonSearch.getSearchResultsPrice(priceList));
		amazonSearch.gotoSearchResultsPage("2");
		priceList.addAll(amazonSearch.getSearchResultsPrice(priceList));
		
		Iterator<String> itr = priceList.iterator();
		while(itr.hasNext()) {
			String price = itr.next();
			int priceInNum 		= Integer.parseInt(price);
			
			softAssert.assertTrue(priceInNum>lowPriceInNum, "Item with cost " + priceInNum + " fetched in search results");
			softAssert.assertTrue(priceInNum<highPriceInNum, "Item with cost " + priceInNum + " fetched in search results");
		}
		
		softAssert.assertAll();
	}
}
