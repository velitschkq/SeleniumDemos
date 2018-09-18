import static org.hamcrest.core.Is.*;

import org.hamcrest.Matchers;


import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.qameta.allure.*;


@Feature ("searchAPI works properly for Inputs: blank, existing article, not existing article, bad input")
public class SearchAPI extends Configuration{

		@BeforeClass
		public static void beforeClass () {
	
		driver = new FirefoxDriver();
		waitConfiguration(driver);
			
		}
		
		@AfterClass
		public static void afterClass () {
			driver.quit();
		}
	
		
		@Before
		public void before () {
			driver.get(Identifiers.home);
			
		}
	
		@Description ("verifies if searchAPI works for input = existing article")
		@Test
		public void existsDEPass () {
			
			WebElement searchInput = 
			    Steps.findElementById(Identifiers.searchInputId);
			    Steps.sendKeys(Searchterm.existsDE, searchInput);
			    Steps.submitToElement(searchInput);
			WebElement firstHeading = 
				Steps.findElementById(Identifiers.firstHeading);
			    Attach.screenshot(driver);
			    Attach.elementshot(driver.findElement(By.cssSelector(Identifiers.wikiTable)));
			assertThat(firstHeading.getText(), is(Searchterm.existsDE));
			assertThat(driver.findElement(By.id(Identifiers.tableOfContents)).isDisplayed(), is(true));
			assertThat(driver.findElement(By.cssSelector(Identifiers.wikiTable)).isDisplayed(), is(true));
		
		}

		@Description ("verifies if searchAPI works for input = existing article")
		@Test
		public void existsDEFail () {
			
			WebElement searchInput = 
			    Steps.findElementById(Identifiers.searchInputId);
			    Steps.sendKeys(Searchterm.existsDE, searchInput);
			    Steps.submitToElement(searchInput);
			WebElement firstHeading = 
				Steps.findElementById(Identifiers.firstHeading);
			    Attach.screenshot(driver);
			assertThat(firstHeading.getText(), is("makesItFail"));
		}
		
		@Description ("verifies if searchAPI works for blank input")
		@Test
		public void blankInput () {
			WebElement searchInput = 
			    Steps.findElementById(Identifiers.searchInputId);
			    Steps.sendKeys(Searchterm.blank, searchInput);
			    Steps.submitToElement(searchInput);
			WebElement firstHeading = 
				Steps.findElementById(Identifiers.firstHeading);
			    Attach.screenshot(driver);
			assertThat(firstHeading.getText(), is("Suche"));
			assertThat(driver.findElement(By.id(Identifiers.searchInputAfterBlankInput)).isDisplayed(), is(true));
		}
		
		@Story ("Given: user is on home page"
				+ "\n When: user types st3bsgerb in the searchInput"
				+ "\n And: submits"
				+ "\n Then: a page should appear with heading - Suchergebnisse -"
				+ "\n And: the page should have a blank searchInput"
				+ "\n And: a message should appear that no article exists")
		@Description ("verifies if searchAPI works for input = no existing article")
		@Test
		public void articleDoesntExist () {
			
			WebElement searchInput = 
			    Steps.findElementById(Identifiers.searchInputId);
			    Steps.sendKeys(Searchterm.existsNot, searchInput);
			    Steps.submitToElement(searchInput);
			WebElement firstHeading = 
				Steps.findElementById(Identifiers.firstHeading);
			    Attach.screenshot(driver);
			assertThat(firstHeading.getText(), is("Suchergebnisse"));
			assertThat(driver.findElement(By.id(Identifiers.searchInputAfterBlankInput)).isDisplayed(), is(true));
			assertThat(driver.findElement(By.cssSelector(Identifiers.noArticleBodyDE)).getText(), 
					Matchers.containsString("Der Artikel „"+Utility.firstUpperCase(Searchterm.existsNot)+"“ existiert in der deutschsprachigen Wikipedia nicht. "));
			
		}
}
