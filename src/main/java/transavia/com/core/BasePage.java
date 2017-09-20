package transavia.com.core;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasePage {

    protected final DriverManager driverManager = DriverManager.getInstance();

    protected BasePage() {
        PageFactory.initElements(new AjaxElementLocatorFactory(driverManager.getDriver(), 60), this);
    }

    protected String extractFrom(String text, String pattern) {
        Pattern orderReferencePattern = Pattern.compile(pattern);
        Matcher matcher = orderReferencePattern.matcher(text);

        return matcher.find() ? matcher.group() : "";
    }
}
