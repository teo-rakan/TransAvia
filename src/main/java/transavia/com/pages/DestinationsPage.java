package transavia.com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import transavia.com.core.BasePage;

public class DestinationsPage extends BasePage {

    @FindBy(xpath = "//*[@id='top']//*[contains(@href,'advanced-search/search/')]")
    private WebElement perfectDestinationButton;

    public AdvancedSearchPage findPerfectDestination() {
        perfectDestinationButton.click();
        return new AdvancedSearchPage();
    }
}
