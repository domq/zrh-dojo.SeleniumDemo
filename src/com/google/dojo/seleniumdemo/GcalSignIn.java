package com.google.dojo.seleniumdemo;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.*;

public class GcalSignIn {
	   @Test
	   public void GcalSignInWrongCredentials() {
	        WebDriver driver = new FirefoxDriver();

	        driver.get("http://www.google.com/calendar");
	        
	        // Find the text input element by its name
	        WebElement emailInput = driver.findElement(By.name("Email"));
	        WebElement passwdInput = driver.findElement(By.name("Passwd"));

	        // Enter something to search for
	        emailInput.sendKeys("random9999@gmail.com");
	        passwdInput.sendKeys("Cheese!");

	        // Now submit the form. WebDriver will find the form for us from the element
	        emailInput.submit();
	        assertTrue(driver.getPageSource().contains("The username or password you entered is incorrect."));
	   }
}
