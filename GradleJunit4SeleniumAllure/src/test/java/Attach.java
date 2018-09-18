import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Attachment;

public class Attach {

	@Attachment
	public static String textOfElement(WebElement WebElement) {
		return WebElement.getText();
	}
	
	@Attachment
	public static String textOfFile(String path) {
		
		List<String> textAsList = new ArrayList<>();
		String text = "";

		try {
			Stream<String> filestream = Files.lines(Paths.get(path));
			textAsList = filestream.collect(Collectors.toList());
			filestream.close();
			for (String s : textAsList)
			{
			    text += s + "\n";
			}
			
		} catch (IOException e) {
			text = "File Not Found";
			e.printStackTrace();	
		}
		return text;
		
	}
	
	@Attachment
	public static String textOfSelect (List<WebElement> selectOptions) {
		String text ="";
		
		for (WebElement option : selectOptions) {
			text += option.getText()+"\t";
			
		}
		return text;
	} 
	
	@Attachment(value = "Screenshot", type = "image/png")
	public static byte[] screenshot(WebDriver driver) {

		Wait.forPageLoad();
		byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);;
	    return screenshot;
	    
	}
	
	@Attachment(value = "WebElement Shot", type = "image/png")
	public static byte[] elementshot (WebElement WebElement) {

		Wait.forPageLoad();
		byte[] screenshot = WebElement.getScreenshotAs(OutputType.BYTES);;
	    return screenshot;
	}
	
	
}
