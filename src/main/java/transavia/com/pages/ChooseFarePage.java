package transavia.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import transavia.com.core.BasePage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChooseFarePage extends BasePage {

    @FindBy(xpath = "//*[@class='name' and contains(text(),'Plus')]/ancestor::th")
    private WebElement plusFare;

    @FindBy(className = "grand-total__price-container")
    private WebElement totalPriceContainer;

    public int getFareSum() {
        String additionalFare = plusFare.findElement(By.xpath(".//*[starts-with(@class,'price')]")).getText();
        Pattern orderReferencePattern = Pattern.compile("\\d+(?=$)");
        Matcher matcher = orderReferencePattern.matcher(additionalFare);
        return matcher.find() ? Integer.valueOf(matcher.group()) : null;
    }

    public ChooseFarePage choosePlusFare() {
        By totalSumLocator = By.className(totalPriceContainer.getAttribute("class"));
        String oldSum = totalPriceContainer.getText();
        plusFare.click();
        driverManager.getWaiter().untilTextChanged(totalSumLocator, oldSum);
        return this;
    }

    public int getTotalPrice() {
        String val = totalPriceContainer.getText();
        Pattern orderReferencePattern = Pattern.compile("\\d+(?=.00$)"); //todo double
        Matcher matcher = orderReferencePattern.matcher(val);

        return matcher.find() ? Integer.valueOf(matcher.group()) : null;
    }
}
