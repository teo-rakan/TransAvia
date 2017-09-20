package transavia.com;

import org.testng.Assert;
import org.testng.annotations.Test;
import transavia.com.pages.WelcomePage;
import transavia.com.utils.DayOfWeek;
import transavia.com.utils.Destination;

import java.util.Calendar;
import java.util.Date;

public class AdvancedSearchTest extends BaseTest {
    @Test(alwaysRun = true)
    public void findDestinationUsingPerfectDestinationFeature() {
        boolean destinationsWereFound = new WelcomePage()
                .getHomePage()
                .goToDestinationsPage()
                .findPerfectDestination()
                .setDestination("Innsbruck, Austria")
                .setSingleFlight()
                .setBudget(200)
                .searchDestinations().isPresent();

        Assert.assertEquals(true, destinationsWereFound);
    }

    //Sometimes this test fails due to the page reloading
    @Test(alwaysRun = true)
    public void findCheapestFlightUsingAdvancedSearch() {
        Destination destination = new WelcomePage()
                .getHomePage()
                .goToAdvancedSearchPage()
                .setDestination("Netherlands", "France")
                .setTakeOffInformation(true, getDate(Calendar.MARCH, 2018), DayOfWeek.ANY)
                .searchDestinations().get();

        Assert.assertEquals(destination.getLocation(), "Nice, France");
        Assert.assertEquals(destination.getPrice(), 29);
    }

    //todo move to util
    private Date getDate(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }
}
