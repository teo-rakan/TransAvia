package transavia.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import transavia.com.core.BasePage;

public class HomePage extends BasePage {

    @FindBy(xpath = "//*[@class='header_bar']//*[contains(@class,'primary-navigation_link') and contains(@href,'service')]")
    private WebElement serviceLink;

    //todo check login link for submenu in case of min window
    @FindBy(xpath = "//*[contains(@class,'secondary-navigation_link') and contains(@href,'account/logon')]")
    private WebElement loginLink;

    //todo check destination link for submenu in case of min window
    @FindBy(xpath = "//*[contains(@class,'header_bar')]//*[contains(@href,'/destinations') and not(contains(@class, 'sub-navigation-level-2_link'))]")
    private WebElement destinationLink;

    @FindBy(xpath = "//*[@class='HV-gs-type-e--bp0']/*[contains(@href, 'combi')]")
    private WebElement multipleDestinationsLink;

    @FindBy(xpath = "//*[@class='header_bar']//*[contains(@class,'primary-navigation_link') and contains(@href,'book-a-fligh')]")
    private WebElement planAndBookLink;

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

    //todo return this button later
    //@FindBy(xpath = "//*[@class='panel_section-button-container']")
    //private WebElement searchButton;

    private final String ADD_PASSENGER_PARAMETRIZED = "//*[contains(@class, '%s')]//*[contains (@class, 'increase')]";
    private final By PASSENGERS_PANEL = By.className("passengers");
    private final By DEPARTURE_STATION_OPTIONS = By.xpath("//*[@id='routeSelection_DepartureStation-input']/following-sibling::*//li[contains(@class, 'item')]");
    private final By ARRIVAL_STATION_OPTIONS = By.xpath("//*[@id='routeSelection_ArrivalStation-input']/following-sibling::*//li[contains(@class, 'item')]");
    private final By HAND_LUGGAGE_LINK = By.xpath("//*[contains(@class,'sub-navigation-level-2_link') and contains(@href,'hand-luggage')]");
    private final By ADVANCED_SEARCH_LINK = By.xpath("//*[contains(@class,'sub-navigation-level-2_link') and contains(@href,'advanced-search')]");
    private final By DATE_PICKER_CALENDAR = By.xpath("//*[contains(@class,'is-visible')]//*[@class='ui-datepicker-calendar']");

    //TODO drop waits
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
        fromInput.sendKeys(Keys.ENTER);
        return new SearchResultPage();
    }

    public LogInPage goToLoginPage() {
        loginLink.click();
        return new LogInPage();
    }

    public MultipleSearchPage addMultipleDestinations() {
        multipleDestinationsLink.click();
        return new MultipleSearchPage();
    }

    public HandLuggagePage goToHandLuggagePage() {
        serviceLink.click();
        driverManager.find(HAND_LUGGAGE_LINK).click();
        return new HandLuggagePage();
    }

    public DestinationsPage goToDestinationsPage() {
        destinationLink.click();
        return new DestinationsPage();
    }

    public AdvancedSearchPage goToAdvancedSearchPage() {
        planAndBookLink.click();
        driverManager.find(ADVANCED_SEARCH_LINK).click();
        return new AdvancedSearchPage();
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
}
