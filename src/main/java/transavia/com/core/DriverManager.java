package transavia.com.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import transavia.com.utils.Waiter;

public abstract class DriverManager {

    private static DriverManager instance;

    WebDriver driver;

    protected abstract void createDriver();

    WebDriver getDriver() {
        if (null == driver) {
            createDriver();
        }
        return driver;
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            synchronized (DriverManager.class) {
                if (instance == null)
                    instance = new ChromeDriverManager();
            }
        }
        return instance;
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

    public Waiter getWaiter() {
        return new Waiter(getDriver());
    }
}


