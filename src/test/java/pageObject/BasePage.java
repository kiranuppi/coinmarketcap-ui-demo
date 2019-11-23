package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webDriver.Driver;


public class BasePage {

    static WebDriver driver = Driver.getCurrentDriver();
    static WebDriverWait wait = new WebDriverWait(driver, 20);

    public static void launchBrowser(String searchCriteria) {

        driver.get(searchCriteria);
        driver.manage().window().maximize();

    }

    public static String getTitle() {
        return driver.getTitle();
    }

    public static void waitforElementThenClick(WebDriver driver, WebElement element) throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
        }

    }

    public static void waitforElementToBeVisibleThenClick(WebDriver driver, String element) throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
        } catch (Exception e) {
        }

    }
}
