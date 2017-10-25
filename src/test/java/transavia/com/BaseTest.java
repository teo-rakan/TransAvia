package transavia.com;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import transavia.com.core.DriverManager;

import java.io.IOException;

public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void openHomePage() {
        DriverManager.getInstance().open("https://www.transavia.com");
    }

//    @AfterMethod
//    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
//        if (testResult.getStatus() == ITestResult.FAILURE) {
//            DriverManager.getInstance().takeScreenshot();
//        }
//    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        DriverManager.getInstance().quit();
    }
}
