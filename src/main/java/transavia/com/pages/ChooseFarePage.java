package transavia.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import transavia.com.core.BasePage;

public class ChooseFarePage extends BasePage {

    @FindBy(xpath = "//*[@class='name' and contains(text(),'Plus')]/ancestor::th")
    private WebElement plusFare;

    @FindBy(className = "grand-total__price-container")
    private WebElement totalPriceContainer;

    public ChooseFarePage choosePlusFare() {
        By totalSumLocator = By.className(totalPriceContainer.getAttribute("class"));
        String oldSum = totalPriceContainer.getText();
        plusFare.click();
        driverManager.getWaiter().untilTextChanged(totalSumLocator, oldSum);
        return this;
    }

    public int getFareSum() {
        String additionalFare = plusFare.findElement(By.xpath(".//*[starts-with(@class,'price')]")).getText();
        return Integer.valueOf(extractFrom(additionalFare, "\\d+(?=$)"));
    }

    public int getTotalPrice() {
        return Integer.valueOf(extractFrom(totalPriceContainer.getText(), "\\d+(?=.00$)"));
    }
}
