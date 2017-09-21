package transavia.com.forms;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import transavia.com.core.BasePage;
import transavia.com.pages.AdvancedSearchPage;
import transavia.com.pages.DestinationsPage;
import transavia.com.pages.HandLuggagePage;
import transavia.com.pages.LogInPage;

public abstract class NavigationBar extends BasePage {

    @FindBy(xpath = "//*[@class='header_bar']//*[contains(@class,'primary-navigation_link') and contains(@href,'service')]")
    private WebElement serviceLink;

    @FindBy(xpath = "//*[@class='header_bar']//*[contains(@class,'primary-navigation_link') and contains(@href,'book-a-fligh')]")
    private WebElement planAndBookLink;

    private final By HAND_LUGGAGE_LINK = By.xpath("//*[contains(@class,'sub-navigation-level-2_link') and contains(@href,'hand-luggage')]");
    private final By ADVANCED_SEARCH_LINK = By.xpath("//*[contains(@class,'sub-navigation-level-2_link') and contains(@href,'advanced-search')]");

    public abstract LogInPage goToLoginPage();
    public abstract DestinationsPage goToDestinationsPage();

    public HandLuggagePage goToHandLuggagePage() {
        serviceLink.click();
        driverManager.find(HAND_LUGGAGE_LINK).click();
        return new HandLuggagePage();
    }

    public AdvancedSearchPage goToAdvancedSearchPage() {
        planAndBookLink.click();
        driverManager.find(ADVANCED_SEARCH_LINK).click();
        return new AdvancedSearchPage();
    }
}
