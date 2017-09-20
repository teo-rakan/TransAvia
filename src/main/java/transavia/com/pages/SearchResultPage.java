package transavia.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import transavia.com.core.BasePage;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchResultPage extends BasePage {

    @FindBy(xpath = "//*[@class='flight inbound']//*[contains(@class,' flight-result active')]")
    private WebElement firstInboundPanel;

    @FindBy(xpath = "//*[@class='flight outbound']//*[contains(@class,' flight-result active')]")
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

    private SearchResultPage selectFlight(WebElement elementForSelection, String panelName) {
        elementForSelection.click();
        driverManager.find(By.xpath(String.format(SELECTED_PARAMETRIZED, panelName)));
        return this;
    }

    //todo check empty list
    public SearchResultPage selectFirstOutbound() {
        return selectFlight(firstOutboundPanel, "outbound");
    }

    public SearchResultPage selectFirstInbound() {
        return selectFlight(firstInboundPanel, "inbound");
    }

    public ChooseFarePage next() {
        nextButton.click();
        return new ChooseFarePage();
    }

    public String getTotalPrice() {
        return extractPrice(totalPriceContainer.getText(), "\\d+\\.\\d+(?=$)");
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    private String extractPrice(String text, String pattern) {
        Pattern orderReferencePattern = Pattern.compile(pattern);
        Matcher matcher = orderReferencePattern.matcher(text);

        return matcher.find() ? matcher.group() : null;
    }

    private int getSelectedPrice(String flightType) {
        By selectedPrice = By.xpath(String.format(SELECTED_PRICE_PARAMETRIZED, flightType));
        return Integer.valueOf(extractPrice(driverManager.find(selectedPrice).getText(), "\\d+(?= Selected)"));
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
