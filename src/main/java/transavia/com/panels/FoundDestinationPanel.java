package transavia.com.panels;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import transavia.com.core.BasePage;
import transavia.com.utils.Destination;

import java.util.List;
import java.util.Optional;

public class FoundDestinationPanel extends BasePage {

    @FindBy(className = "AS-destinations-list_item")
    private List<WebElement> destinationPanels;

    private final By city = By.xpath("//*[@class='HV-gs-type-g--bp0']");
    private final By country = By.xpath("//*[@class='HV-gs-type-g--bp0']/following-sibling::*");
    private final By price = By.xpath("//*[@class='integer']");

    public Optional<Destination> getDestinationsWithLowestPrice () {
        return destinationPanels.stream()
                .map(this::mapToDestination)
                .min(this::compareByPrice);
    }

    private Destination mapToDestination(WebElement panel) {
        Destination destination = new Destination();
        destination.setCity(panel.findElement(city).getText());
        destination.setCountry(panel.findElement(country).getText());
        destination.setPrice(Integer.valueOf(panel.findElement(price).getText()));
        return destination;
    }

    private int compareByPrice(Destination first, Destination second) {
        Integer firstPrice = first.getPrice();
        Integer secondPrice = second.getPrice();
        return secondPrice.compareTo(firstPrice);
    }
}
