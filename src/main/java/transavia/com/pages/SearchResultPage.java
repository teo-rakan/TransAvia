package transavia.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import transavia.com.core.BasePage;

import java.util.List;

public class SearchResultPage extends BasePage {

    @FindBy(xpath = "//section[@class='flight inbound']//div[contains(@class,' flight-result')]")
    private WebElement firstInboundPanel;

    @FindBy(xpath = "//section[@class='flight outbound']//div[contains(@class,' flight-result')]")
    private WebElement firstOutboundPanel;

    @FindBy(className = "grand-total__price-container")
    private WebElement totalPriceContainer;

    @FindBy(name = "next_button")
    private WebElement nextButton;

    @FindBy(xpath = "//*[contains(@class,'notification-inline notification-error')]")
    private WebElement errorMessage;

    //todo special for outbound?
    @FindBy(className = "day-with-availability")
    private List<WebElement> availableDays;

    private final String SELECTED_PARAMETRIZED = "//*[@class='flight %s']//*[contains(@class,' selected')]";
    private final String SELECTED_PRICE_PARAMETRIZED = SELECTED_PARAMETRIZED + "//*[//*[starts-with(@class,'price')]]";

    private SearchResultPage selectFlight(String panelName) {
        driverManager.find(By.xpath(String.format(SELECTED_PARAMETRIZED, panelName)));
        return this;
    }

    public SearchResultPage selectFirstOutbound() {
        firstOutboundPanel.click();
        return selectFlight("outbound");
    }

    public SearchResultPage selectFirstInbound() {
        firstInboundPanel.click();
        return selectFlight("inbound");
    }

    public ChooseFarePage next() {
        nextButton.click();
        return new ChooseFarePage();
    }

    public String getTotalPrice() {
        return extractFrom(totalPriceContainer.getText(), "\\d+\\.\\d+(?=$)");
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    private int getSelectedPrice(String flightType) {
        By selectedPrice = By.xpath(String.format(SELECTED_PRICE_PARAMETRIZED, flightType));
        return Integer.valueOf(extractFrom(driverManager.find(selectedPrice).getText(), "\\d+(?= Selected)"));
    }

    public int getInboundPrice() {
        return getSelectedPrice("inbound");
    }

    public int getOutboundPrice() {
        return getSelectedPrice("outbound");
    }

    public int getAvailableDayCount() {
        return availableDays.size();
    }
}
