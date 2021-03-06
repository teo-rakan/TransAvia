package transavia.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import transavia.com.core.BasePage;
import transavia.com.forms.CompactNavigationBar;
import transavia.com.forms.NavigationBar;
import transavia.com.forms.StandardNavigationBar;

public class HomePage extends BasePage {

    //todo container for safe initialization
    private NavigationBar navigationBar;

    @FindBy(id = "routeSelection_DepartureStation-input")
    private WebElement fromInput;

    @FindBy(id = "routeSelection_ArrivalStation-input")
    private WebElement toInput;

    @FindBy(id = "dateSelection_OutboundDate-datepicker")
    private WebElement departOnDatePicker;

    @FindBy(xpath = "//*[@for='dateSelection_IsReturnFlight']")
    private WebElement returnOnCheckbox;

    @FindBy(id = "booking-passengers-input")
    private WebElement passengersInput;

    @FindBy(xpath = "//*[@id='desktop']//button[@type='submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@class='HV-gs-type-e--bp0']/*[contains(@href, 'combi')]")
    private WebElement multipleDestinationsLink;

    private final String ADD_PASSENGER_PARAMETRIZED = "//*[contains(@class, '%s')]//*[contains (@class, 'increase')]";
    private final By PASSENGERS_PANEL = By.className("passengers");
    //todo parametrized using id?
    private final By DEPARTURE_STATION_OPTIONS = By.xpath("//*[@id='routeSelection_DepartureStation-input']/following-sibling::*//li[contains(@class, 'item')]");
    private final By ARRIVAL_STATION_OPTIONS = By.xpath("//*[@id='routeSelection_ArrivalStation-input']/following-sibling::*//li[contains(@class, 'item')]");
    private final By DATE_PICKER_CALENDAR = By.xpath("//*[contains(@class,'is-visible')]//*[@class='ui-datepicker-calendar']");


    public HomePage setDestination(String from, String to) {
        fromInput.sendKeys(from);
        driverManager.find(DEPARTURE_STATION_OPTIONS).click();
        toInput.sendKeys(to);
        driverManager.find(ARRIVAL_STATION_OPTIONS).click();
        return this;
    }

    private HomePage addPassenger(String type) {
        passengersInput.click();
        By addPassengerButton = By.xpath(String.format(ADD_PASSENGER_PARAMETRIZED, type));
        driverManager.find(addPassengerButton).click();
        return this;
    }

    public HomePage addAdult() {
        return addPassenger("adults");
    }

    public HomePage addChild() {
        return addPassenger("children");
    }

    //todo check selection
    public HomePage setSingleFlight() {
        //if (returnOnCheckbox.isSelected())
            returnOnCheckbox.click();
        return this;
    }

    public SearchResultPage search() {
        searchButton.click();
        return new SearchResultPage();
    }

    public LogInPage goToLoginPage() {
        return getNavigationBar().goToLoginPage();
    }

    public MultipleSearchPage addMultipleDestinations() {
        multipleDestinationsLink.click();
        return new MultipleSearchPage();
    }

    public HandLuggagePage goToHandLuggagePage() {
        return getNavigationBar().goToHandLuggagePage();
    }

    public DestinationsPage goToDestinationsPage() {
        return getNavigationBar().goToDestinationsPage();
    }

    public AdvancedSearchPage goToAdvancedSearchPage() {
        return getNavigationBar().goToAdvancedSearchPage();
    }

    public HomePage checkPassengersPanel() {
        passengersInput.click();
        driverManager.find(PASSENGERS_PANEL).findElement(By.xpath("./following-sibling::*/button")).click();
        driverManager.getWaiter().untilInvisible(PASSENGERS_PANEL);
        return this;
    }

    public HomePage checkDepartOnDatePickerWithDay(int day) {
        departOnDatePicker.findElement(By.xpath("./following-sibling::*")).click();
        driverManager.find(DATE_PICKER_CALENDAR).findElement(By.xpath(".//*[@data-day='" + day + "']")).click();
        driverManager.getWaiter().untilInvisible(DATE_PICKER_CALENDAR);
        return this;
    }

    public String getDepartOnDate() {
        return departOnDatePicker.getAttribute("value");
    }

    //todo drop finding by text "log in"
    private NavigationBar getNavigationBar() {
       return driverManager.findAllWithoutWaiting(By.linkText("Log in")).isEmpty()
               ? new CompactNavigationBar()
               : new StandardNavigationBar();
    }
}
