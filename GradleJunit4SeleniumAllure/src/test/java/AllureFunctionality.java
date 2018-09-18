import org.junit.Ignore;
import org.junit.Test;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class AllureFunctionality {

	// Allure Disabled Test Functionality

	@Ignore("Test Was Skipped To Test Allure")
	@Test
	public void disabledTest() {

	}

	// Allure Severity Functionality Test

	@Test
	@Severity(SeverityLevel.CRITICAL)
	public void importantTest() {

	}

	@Test
	@Severity(SeverityLevel.BLOCKER)
	public void blockingTest() {

	}

	@Test
	@Severity(SeverityLevel.MINOR)
	public void minorTest() {

	}

	@Test
	@Severity(SeverityLevel.TRIVIAL)
	public void trivialTest() {

	}
}
