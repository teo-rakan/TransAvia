package transavia.com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import transavia.com.core.BasePage;

public class BookingDetailsPage extends BasePage {

    @FindBy(xpath = "//*[@id='top']//*[@class='grand-total__price-container']//*[@class='front']")
    private WebElement totalPrice;

    @FindBy(xpath = "//*[@id='top']//*[@class='details-list']//*[@class='amount']")
    private WebElement paymentAmount;


    public String getTotalSum() {
        return totalPrice.getText();
    }

    public String getPaymentAmount() {
        return paymentAmount.getText();
    }
}
