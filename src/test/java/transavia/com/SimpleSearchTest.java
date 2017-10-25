package transavia.com;

import org.testng.Assert;
import org.testng.annotations.Test;
import transavia.com.pages.*;
import transavia.com.utils.JIRATestKey;

public class SimpleSearchTest extends BaseTest {

    @Test(alwaysRun = true)
    @JIRATestKey(key = "TEST-1")
    public void checkSimpleSearchForOnePersonAndDirection() {
        int day = 29;
        HomePage homePage = new WelcomePage()
                .getHomePage()
                .setDestination("Sicily", "Munich")
                .setSingleFlight()
                .checkPassengersPanel()
                .checkDepartOnDatePickerWithDay(day);

        String departOnDay = homePage.getDepartOnDate().substring(0, 2).trim();
        int availableDayCount = homePage.search().getAvailableDayCount();

        Assert.assertEquals(departOnDay, String.valueOf(day));
        Assert.assertTrue(availableDayCount > 0);
    }

    @Test(alwaysRun = true)
    @JIRATestKey(key = "TEST-2")
    public void checkRoundTripTotalSumForThreePeopleWithLuggage() {
        SearchResultPage searchPage = new WelcomePage()
                .getHomePage()
                .setDestination("Dublin", "Paris")
                .addAdult()
                .addChild()
                .search()
                .selectFirstInbound()
                .selectFirstOutbound();

        int inboundPrice = searchPage.getInboundPrice();
        int outboundPrice = searchPage.getOutboundPrice();

        ChooseFarePage chooseFarePage = searchPage.next().choosePlusFare();

        int additionalFare = chooseFarePage.getFareSum();
        int totalPrice = chooseFarePage.getTotalPrice();
        int calculatedTotalSum = (inboundPrice + outboundPrice + additionalFare) * 3;

        Assert.assertEquals(totalPrice, calculatedTotalSum);
    }

    @Test(alwaysRun = true)
    @JIRATestKey(key = "TEST-3")
    public void checkErrorMessageForUnavailableDestinations() {
        String expectedMessage = "Unfortunately we do not fly from Dubai, United Arab Emirates to Agadir, " +
                "Morocco. However, we do fly from Dubai, United Arab Emirates to other destinations. " +
                "Please change your destination and try again.";
        String actualMessage = new WelcomePage()
                .getHomePage()
                .setDestination("Dubai", "Agadir, Morocco")
                .search()
                .getErrorMessage();

        Assert.assertEquals(actualMessage, expectedMessage);
    }

    //Sometimes this test fails due to the datepickers... maybe
    @Test(alwaysRun = true)
    @JIRATestKey(key = "TEST-4")
    public void checkMultipleFlightsTotalSum() {
        SearchResultPage searchResultPage = new WelcomePage()
                .getHomePage()
                .addMultipleDestinations()
                .setOutboundInformation("Bologna, Italy", "Eindhoven, Netherlands", "24 Nov 2017")
                .setInboundInformation("Amsterdam (Schiphol), Netherlands", "Casablanca, Morocco", "30 Nov 2017")
                .search()
                .selectFirstOutbound()
                .selectFirstInbound();

        Assert.assertEquals(searchResultPage.getTotalPrice(), "102.00");
    }

}
