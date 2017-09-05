package transavia.com.panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import transavia.com.core.BasePage;
import transavia.com.utils.DayOfWeek;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TakeOffPanel extends BasePage {

    @FindBy (id ="data-flight-type")
    private WebElement flightTypeSelect;

    @FindBy (id = "timeFrameSelection_SingleFlight_SpecificMonth")
    private WebElement specificMonthSelect;

    @FindBy (id ="timeFrameSelection_ReturnFlight_DepartureDayOfTheWeek")
    private WebElement departureDayOfWeek;

    private final String OPTIONS_PARAMETRIZED = "//*[@id='%s']/option[@value='%s']";

    public void setSingleFlight() {
        Select select = new Select(flightTypeSelect);
        select.selectByValue("Single");
    }

    public TakeOffPanel setSpecificMonth(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-01");
        //String monthSelectId = specificMonthSelect.getAttribute("id");
        //By option = By.xpath(String.format(OPTIONS_PARAMETRIZED, monthSelectId, formatter.format(date)));
        //specificMonthSelect.click();
        //driverManager.find(option).click();

        Select select = new Select(specificMonthSelect);
        select.selectByValue(formatter.format(date));
        return this;
    }

    public TakeOffPanel setWeekDay(DayOfWeek weekDay) {
        String dayValue = weekDay.equals(DayOfWeek.ANY) ? "" : weekDay.toString().substring(0,2).toUpperCase();
        //departureDayOfWeek.sendKeys(dayValue);
        Select select = new Select(departureDayOfWeek);
        select.selectByValue(dayValue);
        return this;
    }
}
