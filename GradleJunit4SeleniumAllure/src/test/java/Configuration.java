import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class Configuration {

	public static WebDriver driver;
	public static String properties = System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
	public static String searchDataPath = "C:\\searchData.json";
	
	public static void waitConfiguration (WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
	}
	
	
	
	
	
	
	
	
}
