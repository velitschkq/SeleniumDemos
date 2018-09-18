import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class TypeaheadSuggestions extends Configuration {

	@BeforeClass
	public static void beforeClass() {

		driver = new FirefoxDriver();
		waitConfiguration(driver);

	}

	@AfterClass
	public static void afterClass() {
		driver.quit();
	}

	@Before
	public void before() {
		driver.get(Identifiers.home);

	}

	@Step
	public void getTextOfSuggestionLinks(int count, String[] url, String[] textOfSuggestionLinks) {
		for (int i = 1; i <= count; i++) {
			WebElement suggestionLink = driver
					.findElement(By.cssSelector(Identifiers.typeahead1.replace("x", String.valueOf(i))));
			url[i - 1] = suggestionLink.getAttribute("href");
			textOfSuggestionLinks[i - 1] = suggestionLink.getText();
		}
	}

	@Step
	public void workingHrefs(int count, String[] url, String[] textOfSuggestionLinks) {
		for (int i = 0; i < count; i++) {
			driver.get(url[i]);
			WebElement firstHeading = driver.findElement(By.id(Identifiers.firstHeading));
			assertThat(textOfSuggestionLinks[i], Matchers.startsWith(firstHeading.getText()));
		}
	}

	@Description("verifies if the typeahead suggestion works")
	@Story("Given: user is on Homepage" + "\n When: user types - Deutschland - into the searchInput"
			+ "\n Then: five suggestion-links should appear in a dropdown menu" + "\n \t links should be working")
	@Test
	public void suggestionsPresentWorkingHrefs() {

		WebElement searchInput = Steps.findElementById(Identifiers.searchInputId);
		Steps.sendKeys("Deutschland", searchInput);

		// gets number of suggestionLinks and sets arraySize
		List<WebElement> forms = driver.findElements(By.cssSelector(".suggestion-link"));
		int count = forms.size() - 1;

		String[] url = new String[count];
		String[] textOfSuggestionLinks = new String[count];

		getTextOfSuggestionLinks(count, url, textOfSuggestionLinks);
		workingHrefs(count, url, textOfSuggestionLinks);

	}

}
