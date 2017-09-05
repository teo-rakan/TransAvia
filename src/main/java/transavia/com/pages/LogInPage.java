package transavia.com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import transavia.com.core.BasePage;

public class LogInPage extends BasePage {

    @FindBy(id = "retrieveBookingByLastname_RecordLocator")
    private WebElement bookingNumberInput;

    @FindBy(id = "retrieveBookingByLastname_LastName")
    private WebElement lastNameInput;

    @FindBy(id = "retrieveBookingByLastname_FlightDate-datepicker")
    private WebElement flightDateInput;

    @FindBy(xpath = "//*[@id='access-booking']//*[@type='submit']")
    private WebElement viewBookingButton;


    public BookingOverviewPage viewBooking(String bookingNumber, String lastName, String flightDate) {
        flightDateInput.sendKeys(flightDate);
        bookingNumberInput.sendKeys(bookingNumber);
        lastNameInput.sendKeys(lastName);
        viewBookingButton.click();
        return new BookingOverviewPage();
    }
}
