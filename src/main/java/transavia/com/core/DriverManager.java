package transavia.com.core;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.SystemClock;
import transavia.com.utils.PropertyManager;
import transavia.com.utils.Waiter;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    private static DriverManager instance;

    WebDriver driver;

    public static DriverManager getInstance() {
        if (instance == null) {
            synchronized (DriverManager.class) {
                if (instance == null)
                    instance = new DriverManager();
            }
        }
        return instance;
    }

    WebDriver getDriver() {
        if (null == driver) {
            createDriver();
        }
        return driver;
    }

    private void createDriver() {
        String browserName = PropertyManager.get("browser.name");
        String urlAddress = PropertyManager.get("url.address");
        URL url;

        try {
            url = new URL(urlAddress);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Illegal URL address: " + urlAddress, e);
        }

        //todo drop it
        System.setProperty("webdriver.chrome.driver", "D:/Software/chromedriver.exe");

        driver = new RemoteWebDriver(url, getCapabilitiesFor(browserName));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private DesiredCapabilities getCapabilitiesFor(String browserName) {
        DesiredCapabilities capabilities;

        switch (browserName) {
            case ("chrome") :
            case ("safari") :
            case ("firefox") :
                capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
                break;
            case ("ie") :
                capabilities = DesiredCapabilities.internetExplorer();
                break;
            case ("edge") :
                capabilities = DesiredCapabilities.edge();
                break;
            default: {
                throw new IllegalArgumentException("Illegal browser name: " + browserName + System.lineSeparator()
                        + "Supported browsers: chrome, firefox, safari, ie, edge.");
            }
        }
        return capabilities;
    }

    public void open(String URL) {
        getDriver().get(URL);
    }

    public void quit() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }

    public WebElement find(By locator) {
        getWaiter().untilVisible(locator);
        return getDriver().findElement(locator);
    }


    public List<WebElement> findAll(By locator) {
        getWaiter().untilVisible(locator);
        return findAllWithoutWaiting(locator);
    }

    public List<WebElement> findAllWithoutWaiting(By locator) {
        return getDriver().findElements(locator);
    }

    public Waiter getWaiter() {
        return new Waiter(getDriver());
    }

    public void takeScreenshot() {
        WebDriver augmentedDriver = new Augmenter().augment(getDriver());
        File scrFile = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
        String file_name = "errorScreenshots\\screenshot_" + System.currentTimeMillis() + ".jpg";
        try {
            FileUtils.copyFile(scrFile, new File(file_name));
        } catch (IOException e) {
            throw new RuntimeException("Cannot take a screenshot");
        }
    }
}


