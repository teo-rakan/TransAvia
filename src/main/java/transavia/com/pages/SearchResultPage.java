package transavia.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import transavia.com.core.BasePage;

import java.security.Key;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchResultPage extends BasePage {

    @FindBy(xpath = "//*[contains(text(), 'Inbound flight')]/ancestor::*[contains(@class, 'c-flight-results-panel')]//*[contains(@class,' flight-result ')]")
    private List<WebElement> inboundButtons;

    @FindBy(xpath = "//*[contains(text(), 'Outbound flight')]/ancestor::*[contains(@class, 'c-flight-results-panel')]//*[@class='resultsPanelWrapper']//form")
    private List<WebElement> outboundButtons;

    @FindBy(className = "grand-total__price-container")
    private WebElement totalPriceContainer;

    @FindBy(name = "next_button")
    private WebElement nextButton;

    @FindBy(xpath = "//*[contains(@class,'notification-inline notification-error')]")
    private WebElement errorMessage;

    //todo special for outbound?
    @FindBy(className = "day-with-availability")
    private List<WebElement> availableDays;

    private final By TOTAL = By.className("grand-total__price-container");
    private final String SELECTED_PRICE_PARAMETRIZED = "//*[contains(text(), '%s')]/ancestor::*[contains(@class, 'c-flight-results-panel')]//*[contains(@class, ' selected')]//*[//*[starts-with(@class,'price')]]";


    //todo check empty list
    public SearchResultPage selectFirstOutbound() {
        String oldValue = totalPriceContainer.getText();
        WebElement first = outboundButtons.get(0);
        first.click();
        driverManager.getWaiter().untilTextChanged(TOTAL, oldValue);
        return this;
    }

    //todo check empty list
    public SearchResultPage selectFirstInbound() {
        String oldValue = totalPriceContainer.getText();
        WebElement first = inboundButtons.get(0);
        first.findElement(By.xpath("./button")).sendKeys(Keys.ENTER);
        driverManager.getWaiter().untilTextChanged(TOTAL, oldValue);
        return this;
    }

    public ChooseFarePage next() {
        nextButton.click();
        return new ChooseFarePage();
    }

    public String getTotalPrice() {
        String val = totalPriceContainer.getText();
        Pattern orderReferencePattern = Pattern.compile("\\d+\\.\\d+(?=$)");
        Matcher matcher = orderReferencePattern.matcher(val);

        return matcher.find() ? matcher.group() : null;
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    private Integer getPriceFromSelectedItem(String text) {
        Pattern orderReferencePattern = Pattern.compile("\\d+(?= Selected)");
        Matcher matcher = orderReferencePattern.matcher(text);

        return matcher.find() ? Integer.valueOf(matcher.group()) : null;
    }

    public int getInboundPrice() {
        By selectedInboundPrice = By.xpath(String.format(SELECTED_PRICE_PARAMETRIZED, "Inbound flight"));
        return getPriceFromSelectedItem(driverManager.find(selectedInboundPrice).getText());
    }

    public int getOutboundPrice() {
        By selectedOutboundPrice = By.xpath(String.format(SELECTED_PRICE_PARAMETRIZED, "Outbound flight"));
        return getPriceFromSelectedItem(driverManager.find(selectedOutboundPrice).getText());
    }

    public int getAvailableDayCount() {
        return availableDays.size();
    }
}
