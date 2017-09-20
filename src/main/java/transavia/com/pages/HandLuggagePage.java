package transavia.com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import transavia.com.core.BasePage;

public class HandLuggagePage extends BasePage {

    @FindBy (xpath = "//*[contains(@src, 'youtube')]")
    private WebElement videoFrame;

    public YoutubePage goToYoutubeSite() {
        String embeddedLink = videoFrame.getAttribute("src");

        return new YoutubePage("https://youtu.be/" + extractFrom(embeddedLink, "(?<=embed/)[A-Za-z]+"));
    }
}
