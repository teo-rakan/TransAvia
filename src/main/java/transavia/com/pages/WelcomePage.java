package transavia.com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import transavia.com.core.BasePage;

import java.util.List;

public class WelcomePage extends BasePage {

    @FindBy(xpath = "//*[@class='component_language-switch']//*[contains(@href,'en-EU')]")
    private List<WebElement> countryLinks;

    public HomePage getHomePage() {
        if (!countryLinks.isEmpty())
            countryLinks.get(0).click();
        return new HomePage();
    }

}
