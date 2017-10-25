package transavia.com;

import org.testng.Assert;
import org.testng.annotations.Test;
import transavia.com.pages.BookingDetailsPage;
import transavia.com.pages.BookingOverviewPage;
import transavia.com.pages.WelcomePage;
import transavia.com.utils.JIRATestKey;

public class ViewBookingTest extends BaseTest {

    private final String BOOKING_NUMBER = "MF8C9R";
    private final String LAST_NAME = "kukharau";
    private final String FLIGHT_DATE = "09-06-2016";  //todo as date&

    @Test(alwaysRun = true)
    @JIRATestKey(key = "TEST-6")
    public void checkBookingDetails() {
        BookingOverviewPage bookingOverviewPage = new WelcomePage()
                .getHomePage()
                .goToLoginPage()
                .viewBooking(BOOKING_NUMBER, LAST_NAME, FLIGHT_DATE);

        Assert.assertEquals("Pisa", bookingOverviewPage.getFrom().trim());
        Assert.assertEquals("Amsterdam (Schiphol)", bookingOverviewPage.getTo().trim());
        Assert.assertEquals("21:25",  bookingOverviewPage.getDepartureTime().trim());
        Assert.assertEquals("23:35",  bookingOverviewPage.getArrivalTime().trim());
    }

    @Test(alwaysRun = true)
    @JIRATestKey(key = "TEST-7")
    public void checkBookingTotalSumAndPaymentAmount() {
        BookingDetailsPage bookingDetailsPage = new WelcomePage()
                .getHomePage()
                .goToLoginPage()
                .viewBooking(BOOKING_NUMBER, LAST_NAME, FLIGHT_DATE)
                .viewDetails();

        Assert.assertEquals(bookingDetailsPage.getTotalSum(), bookingDetailsPage.getPaymentAmount());
    }
}
