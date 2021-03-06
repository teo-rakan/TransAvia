package transavia.com.forms;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import transavia.com.core.BasePage;
import transavia.com.utils.DayOfWeek;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TakeOffForm extends BasePage {

    @FindBy (id ="data-flight-type")
    private WebElement flightTypeSelect;

    @FindBy (id = "timeFrameSelection_SingleFlight_SpecificMonth")
    private WebElement specificMonthSelect;

    @FindBy (id ="timeFrameSelection_ReturnFlight_DepartureDayOfTheWeek")
    private WebElement departureDayOfWeek;

    public void setSingleFlight() {
        Select select = new Select(flightTypeSelect);
        select.selectByValue("Single");
    }

    public TakeOffForm setSpecificMonth(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-01");
        Select select = new Select(specificMonthSelect);
        select.selectByValue(formatter.format(date));
        return this;
    }

    //Available values: "", "MO", "TU", "WE", "TH" , "FR", "SA", "SU"
    public TakeOffForm setWeekDay(DayOfWeek weekDay) {
        String dayValue = weekDay.equals(DayOfWeek.ANY) ? "" : weekDay.toString().substring(0,2).toUpperCase();
        Select select = new Select(departureDayOfWeek);
        select.selectByValue(dayValue);
        return this;
    }
}
