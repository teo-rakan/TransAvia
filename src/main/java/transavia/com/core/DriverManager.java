package transavia.com.core;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import transavia.com.utils.PropertyManager;
import transavia.com.utils.Waiter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    private static DriverManager instance;

    private WebDriver driver;

    public static DriverManager getInstance() {
        if (instance == null) {
            synchronized (DriverManager.class) {
                if (instance == null)
                    instance = new DriverManager();
            }
        }
        return instance;
    }

    //todo private + create AjaxElementLocatorFactory in DriverManager?
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

        driver = new RemoteWebDriver(url, getCapabilitiesFor(browserName));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private DesiredCapabilities getCapabilitiesFor(String browserName) {
        DesiredCapabilities capabilities;

        switch (browserName) {
            case ("chrome") :
            case ("firefox") :
                capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
                break;
            case ("ie") :
                capabilities = DesiredCapabilities.internetExplorer();
                break;
            case ("edge") :
                capabilities = DesiredCapabilities.edge();
                break;
            default:
                throw new IllegalArgumentException("Illegal browser name: " + browserName + System.lineSeparator()
                        + "Supported browsers: chrome, firefox, ie, edge.");
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

    public List<WebElement> findAllWithoutWaiting(By locator) {
        return getDriver().findElements(locator);
    }

    public Waiter getWaiter() {
        return new Waiter(getDriver());
    }

}


