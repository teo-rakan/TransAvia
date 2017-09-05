package transavia.com;

import org.testng.Assert;
import org.testng.annotations.Test;
import transavia.com.pages.WelcomePage;
import transavia.com.pages.YoutubePage;

public class YoutubeTest extends BaseTest {

    @Test(alwaysRun = true)
    public void checkHandLuggageVideo() {
        YoutubePage youtubePage = new WelcomePage()
                .getHomePage()
                .goToHandLuggagePage()
                .goToYoutubeSite();

        //todo check links http://www.youtube.com/watch?v=fQMuhniqWAg
        Assert.assertEquals("Luggage", youtubePage.getVideoTitle().trim());
        Assert.assertEquals("Transavia", youtubePage.getAuthorName().trim());
    }
}
