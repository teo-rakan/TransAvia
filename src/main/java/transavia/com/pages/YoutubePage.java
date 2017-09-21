package transavia.com.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import transavia.com.core.BasePage;

public class YoutubePage extends BasePage {

    @FindBy(className = "title")
    private WebElement videoTitle;

    @FindBy(id = "owner-name")
    private WebElement authorName;

    YoutubePage(String URL) {
        driverManager.open(URL);
    }

    public String getVideoTitle() {
        return videoTitle.getText();
    }

    public String getAuthorName() {
        return authorName.getText();
    }
}
