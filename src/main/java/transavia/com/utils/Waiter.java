package transavia.com.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.function.Function;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Waiter {

    private final WebDriver driver;

    public Waiter(WebDriver driver) {
        this.driver = driver;
    }

    public void untilVisible(By element) {
        waitUntil(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void untilInvisible(By element) {
        waitUntil(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    private void waitUntil(Function condition) {
        FluentWait wait = new FluentWait<>(driver)
                .withTimeout(60, SECONDS)
                .pollingEvery(100, MILLISECONDS);
        wait.until(condition);
    }

    public void untilTextChanged(By locator, String value) {
        waitUntil(ExpectedConditions.not(ExpectedConditions.textToBe(locator, value)));
    }
}
