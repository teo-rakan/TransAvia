package transavia.com;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import transavia.com.core.DriverManager;

public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void openHomePage() {
        DriverManager.getInstance().open("https://www.transavia.com");
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        DriverManager.getInstance().quit();
    }
}
