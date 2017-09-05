package transavia.com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import transavia.com.core.BasePage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandLuggagePage extends BasePage {

    @FindBy (xpath = "//*[contains(@src, 'youtube')]")
    private WebElement videoFrame;

    public YoutubePage goToYoutubeSite() {
        String embeddedLink = videoFrame.getAttribute("src");

        return new YoutubePage(getShortURLFromEmbedded(embeddedLink));
    }

    private String getShortURLFromEmbedded(String URL) {
        Matcher matcher = Pattern.compile("(?<=embed/)[A-Za-z]+").matcher(URL);

        return matcher.find() ? "https://youtu.be/" + matcher.group() : null;
    }
}
