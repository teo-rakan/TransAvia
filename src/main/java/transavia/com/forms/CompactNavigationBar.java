package transavia.com.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import transavia.com.pages.DestinationsPage;
import transavia.com.pages.LogInPage;

public class CompactNavigationBar extends NavigationBar {

    @FindBy(xpath = "//*[contains(@class,'navigation--bbl__icon-account-alt')]/parent::*")
    private WebElement loginLink;

    @FindBy(xpath = "//*[@href ='#navigation']")
    private WebElement navigationMenu;

    private By destinationLink = By.xpath("//*[@id='navigation']//*[contains(@href,'/destinations/') and contains(@class, 'secondary-navigation_link')]");

    @Override
    public LogInPage goToLoginPage() {
        loginLink.click();
        return new LogInPage();
    }

    @Override
    public DestinationsPage goToDestinationsPage() {
        navigationMenu.click();
        driverManager.find(destinationLink).click();
        return new DestinationsPage();
    }
}
