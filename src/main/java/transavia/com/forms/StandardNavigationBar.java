package transavia.com.forms;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import transavia.com.pages.DestinationsPage;
import transavia.com.pages.LogInPage;

public class StandardNavigationBar extends NavigationBar {

    @FindBy(xpath = "//*[contains(@class,'secondary-navigation_link') and contains(@href,'account/logon')]")
    private WebElement loginLink;

    @FindBy(xpath = "//*[contains(@class,'header_bar')]//*[contains(@href,'/destinations') and not(contains(@class, 'sub-navigation-level-2_link'))]")
    private WebElement destinationLink;

    @Override
    public LogInPage goToLoginPage() {
        loginLink.click();
        return new LogInPage();
    }

    @Override
    public DestinationsPage goToDestinationsPage() {
        destinationLink.click();
        return new DestinationsPage();
    }
}
