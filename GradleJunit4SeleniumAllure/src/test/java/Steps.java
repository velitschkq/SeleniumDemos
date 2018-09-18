import org.openqa.selenium.*;


import io.qameta.allure.*;


public class Steps extends Configuration{

	@Step
	public static void get (WebDriver driver, String url) {
		
		driver.get(url);
	
	}
	
	@Step
	public static WebElement findElementById (String id) {
		
		WebElement WebElement = driver.findElement(By.id(id));
		return WebElement;
	}
	
	@Step
	public static WebElement findElementByCSS (String cssSelector) {
		
		WebElement WebElement = driver.findElement(By.id(cssSelector));
		return WebElement;
	}
	
	
	@Step
	public static void sendKeys (String keys, WebElement WebElement) {
		
		WebElement.sendKeys(keys);
	
	}

	
	
	@Step
	public static void submitToElement (WebElement WebElement) {
				
		WebElement.submit();
			
	}
	
	@Step
	public static void clickOnElement (WebElement e) {
			
		e.click();
		
	}
	
	
	
	
}
	

