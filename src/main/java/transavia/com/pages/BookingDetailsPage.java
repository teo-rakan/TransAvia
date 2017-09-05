package transavia.com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import transavia.com.core.BasePage;

public class BookingDetailsPage extends BasePage {

    @FindBy(xpath = "//*[@id='price-breakdown']/ancestor::*[@class='panel']//*[@class='price']")
    private WebElement totalPrice;

    @FindBy(xpath = "//*[contains(text(),'Payment amount')]/parent::*/following-sibling::*//*[@class='amount']")
    private WebElement paymentAmount;


    public String getTotalSum() {
        return totalPrice.getText();
    }

    public String getPaymentAmount() {
        return paymentAmount.getText();
    }
}
