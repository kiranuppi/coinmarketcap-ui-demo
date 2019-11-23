package supportFactory;

import org.openqa.selenium.WebDriver;
import webDriver.Driver;

public class WebdriverFactory {

    public static WebDriver createWebdriver() {

        Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
        return BrowserFactory.selectLocalBrowser();

    }

    private static class BrowserCleanup implements Runnable {
        public void run() {
            System.out.println("Cleaning up the browser");
            try {
                Driver.webdriver.quit();
            } catch (NullPointerException e) {
                System.out.println("Browser already shut down.");
            }
        }
    }

}
