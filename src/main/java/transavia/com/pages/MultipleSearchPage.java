package transavia.com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import transavia.com.core.BasePage;

public class MultipleSearchPage extends BasePage {

    @FindBy(id = "openJawRouteSelection_DepartureStationOutbound-input")
    private WebElement outboundFromInput;

    @FindBy(id = "openJawRouteSelection_ArrivalStationOutbound-input")
    private WebElement outboundToInput;

    @FindBy(id = "dateSelection_OutboundDate-datepicker")
    private WebElement outboundDateInput;

    @FindBy(id = "openJawRouteSelection_DepartureStationInbound-input")
    private WebElement inboundFromInput;

    @FindBy(id = "openJawRouteSelection_ArrivalStationInbound-input")
    private WebElement inboundToInput;

    @FindBy(id = "dateSelection_InboundDate-datepicker")
    private WebElement inboundDateInput;

    @FindBy(xpath = "//*[@id='flights']//*[@class='panel_section-button-container']//button[@type='submit']")
    private WebElement searchButton;

    public MultipleSearchPage setOutboundInformation(String from, String to, String date) {
        outboundFromInput.sendKeys(from);
        outboundDateInput.clear();
        outboundDateInput.sendKeys(date);
        outboundToInput.sendKeys(to);
        return this;
    }

    public MultipleSearchPage setInboundInformation(String from, String to, String date) {
        inboundFromInput.sendKeys(from);
        inboundDateInput.clear();
        inboundDateInput.sendKeys(date);
        inboundToInput.sendKeys(to);
        return this;
    }

    public SearchResultPage search() {
        searchButton.click();
        return new SearchResultPage();
    }
}
