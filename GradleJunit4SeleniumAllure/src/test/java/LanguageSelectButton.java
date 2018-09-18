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
import org.openqa.selenium.support.ui.Select;

import io.qameta.allure.Description;

public class LanguageSelectButton extends Configuration {

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

	@Description("checks wether the options of the - Select Menu - "
			+ "include the in - searchterm class - spezified languages")
	@Test
	public void optionsIncludeLanguages() {
		Select languageSelect = new Select(driver.findElement(By.id(Identifiers.languageId)));
		List<WebElement> languageOptions = languageSelect.getOptions();
		String languages = Attach.textOfSelect(languageOptions);

		for (String language : Searchterm.language) {
			assertThat(languages, Matchers.containsString(language));
		}
	}

	@Description("checks wether all languages of the - Select Menu - can be selected")
	@Test
	public void languagesSelectable() {
		Select languageSelect = new Select(driver.findElement(By.id(Identifiers.languageId)));

		for (String language : Searchterm.language) {
			languageSelect.selectByVisibleText(language);
			assertThat(languageSelect.getFirstSelectedOption().getText(), Matchers.is(language));
		}
	}

	@Description("verifies if a search in polish is possible after selecting Polski")
	@Test
	public void searchInPolish() {
		Select languageSelect = new Select(driver.findElement(By.id(Identifiers.languageId)));
		languageSelect.selectByVisibleText("Polski");

		WebElement searchInput = Steps.findElementById(Identifiers.searchInputId);
		Steps.sendKeys(Searchterm.existsPL, searchInput);
		Steps.submitToElement(searchInput);
		WebElement firstHeading = Steps.findElementById(Identifiers.firstHeading);
		Attach.screenshot(driver);
		assertThat(firstHeading.getText(), Matchers.startsWith(Searchterm.existsPL));

	}

}
