package transavia.com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import transavia.com.core.BasePage;

public class BookingOverviewPage extends BasePage {
    @FindBy(xpath = "//*[contains(@class,'panel_section--narrower-top')]//*[@class='nowrap'][1]")
    private WebElement departureStation;

    @FindBy(xpath = "//*[contains(@class,'panel_section--narrower-top')]//*[@class='nowrap'][last()]")
    private WebElement arrivalStation;

    @FindBy(xpath = "//*[text()[normalize-space() = 'Departure']]/following-sibling::*//time")
    private WebElement departureTime;

    @FindBy(xpath = "//*[text()[normalize-space() = 'Arrival']]/following-sibling::*//time")
    private WebElement arrivalTime;

    @FindBy(xpath = ".//*[@id='top']//*[contains(@class, 'panel_section')]//*[contains(@href, 'booking-details')]")
    private WebElement bookingDetailsLink;

    public String getFrom() {
        return departureStation.getText();
    }

    public String getTo() {
        return arrivalStation.getText();
    }

    public String getDepartureTime() {
        return departureTime.getText();
    }

    public String getArrivalTime() {
        return arrivalTime.getText();
    }

    public BookingDetailsPage viewDetails() {
        bookingDetailsLink.click();
        return new BookingDetailsPage();
    }
}
