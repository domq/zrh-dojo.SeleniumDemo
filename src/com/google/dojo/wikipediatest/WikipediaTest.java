package com.google.dojo.wikipediatest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.*;

public class WikipediaTest {
	private WebDriver OpenDriver() {
		WebDriver driver;
		boolean use_firefox = true;
		if (use_firefox) {
			driver = new FirefoxDriver();
		} else {
			// Downloaded as per the instructions here: https://code.google.com/p/selenium/wiki/ChromeDriver
			System.setProperty("webdriver.chrome.driver", "/Users/domq/dojo/ChromeDriver/chromedriver");
			driver = new ChromeDriver();
		}
		return driver;
	}

	@Test
	public void WikipediaPageForHouse() {
		Map<String, String> languageLinks = GetForeignLanguageLinks("House");
		assertEquals("Haus", languageLinks.get("de"));
	}
	public Map<String, String> GetForeignLanguageLinks(String pagename) {
		WebDriver driver = OpenDriver();
		driver.get("http://en.wikipedia.org/wiki/" + pagename);
		Map<String, String> links = new HashMap<String, String>();
		List<WebElement> linksInPage = driver.findElement(By.id("p-lang-list")).findElements(By.tagName("li"));
		for (WebElement link : linksInPage) {
			String interwikiClass = link.getAttribute("class");
			String interwikiPrefix = "interwiki-";
			String wikiPrefix = "/wiki/";
			if (interwikiClass.startsWith(interwikiPrefix)) {
				String key = interwikiClass.substring(interwikiPrefix.length()); // The "foo" part in "interwiki-foo"
				String href = link.findElement(By.tagName("a")).getAttribute("href");
				String value = href.substring(href.lastIndexOf(wikiPrefix) + wikiPrefix.length());
				links.put(key, value);
			}
		}
		return links;	  
	}
}
