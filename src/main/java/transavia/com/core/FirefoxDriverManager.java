package transavia.com.core;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxDriverManager extends DriverManager {

    @Override
    protected void createDriver() {
        System.setProperty("webdriver.firefox.bin","E:/Soft/Mozilla Firefox_47/firefox.exe");
        System.setProperty("webdriver.gecko.driver", "E:/Soft/geckodriver.exe");
        //DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        driver = new FirefoxDriver();
    }
}
