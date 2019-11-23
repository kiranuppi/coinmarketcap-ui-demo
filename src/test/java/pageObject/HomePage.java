package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

//    JavascriptExecutor js = (JavascriptExecutor) driver;

    @FindBy(how = How.LINK_TEXT, using = "View All")
    private WebElement lnk_ViewAll;

    @FindBy(how = How.XPATH, using = "//table[@id='currencies']//tr")
    private List<WebElement> tbl_ccys;


    @FindBy(how = How.XPATH, using = "//table[@id='currencies-all']//tr")
    private List<WebElement> tbl_all_ccys;


    @FindBy(how = How.LINK_TEXT, using = "Add to Watchlist")
    private WebElement lnk_AddToWatchList;

    @FindBy(how = How.LINK_TEXT, using = "Watchlist")
    private WebElement lnk_Watchlist;

    @FindBy(how = How.XPATH, using = "//table/tbody/tr")
    private List<WebElement> tbl_watchList;

    @FindBy(how = How.XPATH, using = "//a[text()='Cryptocurrencies ']/span[@class='caret']")
    private WebElement lnk_Cryptocurrencies;

    @FindBy(how = How.LINK_TEXT, using = "Full List")
    private WebElement lnk_FullList;


    @FindBy(how = How.ID, using = "filter_marketcap")
    private WebElement sel_MarketCapFilter;

    @FindBy(how = How.ID, using = "filter_price")
    private WebElement sel_PriceFilter;

    @FindBy(how = How.ID, using = "filter_volume")
    private WebElement sel_volumeFilter;

    @FindBy(how = How.XPATH, using = "//table[@id='currencies-all']//tbody/tr")
    private List<WebElement> tbl_filtered_ccys;

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public void clickOnViewAllLink() {
        lnk_ViewAll.click();
    }

    public void clickOnEllipsis(int number) throws InterruptedException {

        String variable = "//table[@id='currencies']//tr[" + number + "]/td[@class='dropdown']//button";
        WebElement element = driver.findElement(By.xpath(variable));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        clickOnLinkName();
    }

    public void clickOnLinkName() {
        lnk_AddToWatchList.click();
        System.out.println("Added to WatchList");
    }

    public void clickOnWatchList() {

        Actions newTab = new Actions(driver);
        newTab.keyDown(Keys.CONTROL).click(lnk_Watchlist).keyUp(Keys.CONTROL).build().perform();

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public List<WebElement> getTbl_ccys() {
        return tbl_all_ccys;
    }

    public void clickOnCryptocurrenciesDropdown() throws InterruptedException {
        lnk_Cryptocurrencies.click();
    }

    public void clickOnFullListLink() throws InterruptedException {
        lnk_FullList.click();
    }

    public void selectMarketCapFilter(String marketCap) {
        Select dropdown = new Select(sel_MarketCapFilter);
        dropdown.selectByVisibleText(marketCap);
    }


    public void selectPriceFilter(String price) {
        Select dropdown = new Select(sel_PriceFilter);
        dropdown.selectByVisibleText(price);
    }

    public void selectVolumeFilter(String volume) {
        Select dropdown = new Select(sel_volumeFilter);
        dropdown.selectByVisibleText(volume);
    }

    public String returnMarketCapData(int rowNumber) {
        String marketCapData = "//table[@id='currencies-all']//tbody/tr[" + rowNumber + "]/td[4]";
        return driver.findElement(By.xpath(marketCapData)).getText();
    }


    public String returnPriceData(int rowNumber) {
        String priceData = "//table[@id='currencies-all']//tbody/tr[" + rowNumber + "]/td[5]";
        return driver.findElement(By.xpath(priceData)).getText();
    }

    public String returnVolumeData(int rowNumber) {
        String volumeData = "//table[@id='currencies-all']//tbody/tr[" + rowNumber + "]/td[7]";
        return driver.findElement(By.xpath(volumeData)).getText();
    }

    public int getresultCount() {
        return tbl_filtered_ccys.size();
    }

    public int getWatchListRowCount() {
        return tbl_watchList.size();
    }
}
