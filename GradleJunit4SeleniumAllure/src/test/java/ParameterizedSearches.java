import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import org.json.simple.JSONObject;
import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.qameta.allure.Description;
import ru.yandex.qatools.allure.annotations.Parameter;


@RunWith (Parameterized.class)
public class ParameterizedSearches extends Configuration {
	
	@BeforeClass
	public static void beforeClass () {
		
		driver = new FirefoxDriver ();
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
	
	@Parameter 
	public JSONObject json;


	public ParameterizedSearches (JSONObject json) {
		this.json = json;
	}
	

	@Parameters  
	public static Collection<Object> data () {
		return JsonHandler.data();
		
	}
	
	@Description ("uses the german searchAPI to search for terms specified by json file."
			     + "json location is given in configuration class")
	@Test
	public void multiSearchTest() {
				Attach.textOfFile(searchDataPath);
		WebElement searchInput = 
				Steps.findElementById(Identifiers.searchInputId);
				Steps.sendKeys(json.get("search_name").toString(), searchInput);
				Steps.submitToElement(searchInput);
		WebElement firstHeading = 
				Steps.findElementById(Identifiers.firstHeading);
				Attach.screenshot(driver);
				assertThat(firstHeading.getText(), is(json.get("search_name").toString()));
	}
	
}