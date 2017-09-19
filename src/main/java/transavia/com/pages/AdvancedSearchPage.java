package transavia.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import transavia.com.core.BasePage;
import transavia.com.forms.FoundDestinationForm;
import transavia.com.forms.TakeOffForm;
import transavia.com.utils.DayOfWeek;
import transavia.com.utils.Destination;

import java.util.Date;
import java.util.Optional;

public class AdvancedSearchPage extends BasePage {

    @FindBy (id = "countryStationSelection_Origin-input")
    private WebElement fromInput;

    @FindBy (id = "countryStationSelection_Destination-input")
    private WebElement toInput;

    @FindBy (xpath = "//*[text()[normalize-space() = 'What is your budget per person?']]")
    private WebElement personBudgetLink;

    @FindBy (xpath = "//*[text()[normalize-space() = 'When will you be taking off?']]")
    private WebElement takeOffLink;

    @FindBy (xpath = "//button[text()[normalize-space() = 'Search']]")
    private WebElement searchButton;

    private final By BUDGET_INPUT = By.id("budgetSelection_EurosBudget");

    private final String INPUT_OPTIONS_PARAMETRIZED = "//*[@id='%s']/following-sibling::*//li";

    private  AdvancedSearchPage setDestination(WebElement input, String value) {
        String extractedId = input.getAttribute("id");
        By firstFromOption = By.xpath(String.format(INPUT_OPTIONS_PARAMETRIZED, extractedId));

        input.clear();
        input.sendKeys(value);
        driverManager.find(firstFromOption).click();
        return this;
    }

    public AdvancedSearchPage setDestination(String from) {
        return setDestination(fromInput, from);
    }

    public AdvancedSearchPage setDestination(String from, String to) {
        return setDestination(fromInput, from).setDestination(toInput, to);
    }

    public AdvancedSearchPage setBudget(int budget) {
        personBudgetLink.click();
        driverManager.find(BUDGET_INPUT).sendKeys(String.valueOf(budget));
        return this;
    }

    //todo replace 'lowest' with list
    public Optional<Destination> searchDestinations() {
        searchButton.click();
        return new FoundDestinationForm().getDestinationsWithLowestPrice();
    }

    public AdvancedSearchPage setSingleFlight() {
        takeOffLink.click();
        new TakeOffForm().setSingleFlight();
        return this;
    }

    public AdvancedSearchPage setTakeOffInformation(boolean single, Date monthAndYear, DayOfWeek weekDay) {
        takeOffLink.click();
        TakeOffForm panel = new TakeOffForm();
        if (single)
            panel.setSingleFlight();
        panel.setSpecificMonth(monthAndYear);
        panel.setWeekDay(weekDay);

        return this;
    }
}
