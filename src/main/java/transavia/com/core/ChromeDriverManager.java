package transavia.com.core;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class ChromeDriverManager extends DriverManager {

    @Override
    protected void createDriver() {
        //todo drop it
        System.setProperty("webdriver.chrome.driver", "E:/Soft/chromedriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = new ChromeDriver(capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
