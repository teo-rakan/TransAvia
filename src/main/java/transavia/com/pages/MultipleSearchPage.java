package transavia.com.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import transavia.com.core.BasePage;

//TODO merge with simple search page?
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

    //todo return this button later
    //@FindBy(xpath = "//*[@id='flights']//button[@type='submit']")
    //private WebElement searchButton;

    public MultipleSearchPage setOutboundInformation(String from, String to, String date) {
        outboundFromInput.sendKeys(from);
        outboundToInput.sendKeys(to);
        outboundDateInput.clear();
        outboundDateInput.sendKeys(date);
        return this;
    }

    public MultipleSearchPage setInboundInformation(String from, String to, String date) {
        inboundFromInput.sendKeys(from);
        inboundToInput.sendKeys(to);
        inboundDateInput.clear();
        inboundDateInput.sendKeys(date);
        return this;
    }

    public SearchResultPage search() {
        outboundDateInput.sendKeys(Keys.ENTER);
        return new SearchResultPage();
    }
}
