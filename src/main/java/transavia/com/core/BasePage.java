package transavia.com.core;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class BasePage {

    protected final DriverManager driverManager = DriverManager.getInstance();

    public BasePage() {
        PageFactory.initElements(new AjaxElementLocatorFactory(driverManager.getDriver(), 60), this);
    }
}
