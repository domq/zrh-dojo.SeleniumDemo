package com.google.dojo.seleniumdemo;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.*;

public class YouTubeSearch {
	   @Test
	   public void YTSearchGangnam() {
		   WebDriver driver;
		   boolean use_firefox = false;
		   if (use_firefox) {
			   driver = new FirefoxDriver();
		   } else {
			   // Downloaded as per the instructions here: https://code.google.com/p/selenium/wiki/ChromeDriver
			   System.setProperty("webdriver.chrome.driver", "/Users/domq/dojo/ChromeDriver/chromedriver");
			   driver = new ChromeDriver();
		   }	       

	        driver.get("http://www.youtube.com");
	        
	        // Find the text input element by its name
	        WebElement searchBox = driver.findElement(By.id("masthead-search-term"));
	        

	        // Enter something to search for
	        searchBox.sendKeys("gangnam style");


	        // Now submit the form. WebDriver will find the form for us from the element
	        searchBox.submit();
	        
	        WebElement searchResults = driver.findElement(By.id("search-results"));
	        WebElement firstResult = searchResults.findElements(By.className("yt-lockup-video")).get(0);
	        assertTrue(firstResult.getText().contains("officialpsy"));
	   }
}
