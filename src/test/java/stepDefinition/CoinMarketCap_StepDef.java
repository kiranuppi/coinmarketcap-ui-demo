package stepDefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObject.BasePage;
import pageObject.HomePage;
import supportMethods.NumberRange;
import supportMethods.Utils;

import java.util.Random;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CoinMarketCap_StepDef {
    HomePage homePage = new HomePage();
    private String marketCapValue;
    private String priceValue;
    private String volumeValue;

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    @Given("^I open the browser and go on \"([^\"]*)\"$")
    public void i_open_the_browser_an_go_on(String url) {
        BasePage.launchBrowser(url);
    }

    @When("^I click on View All link$")
    public void iClickOn() {
        homePage.clickOnViewAllLink();
    }

    @Then("^I should see more than (\\d+) results are displayed$")
    public void iShouldSeeMoreThanResultsAreDisplayed(int resultCount) {
        assertTrue(homePage.getTbl_ccys().size() > resultCount);
    }

    @When("^I Select (\\d+) random crypto currencies$")
    public void iSelectBetweenAndRandomCryptoCurrencies(int num) throws InterruptedException {

        while (num > 0) {
            int number = getRandomNumberInRange(1, 100);
            homePage.clickOnEllipsis(number);
            num--;
        }

    }

    @When("^I open the 'Watchlist' in another tab$")
    public void iOpenTheInAnotherTab() {
        homePage.clickOnWatchList();
    }

    @Then("^All the currencies added on the watchlist are validated$")
    public void allTheCurrenciesAddedOnTheWatchlistAreValidated() {
        assertEquals(homePage.getWatchListRowCount(), 5);
    }

    @When("^I click on the dropdown menu on the \"([^\"]*)\" tab$")
    public void iClickOnTheDropdownMenuOnTheTab(String arg0) throws InterruptedException {
        homePage.clickOnCryptocurrenciesDropdown();
    }

    @And("^I click on 'Full List' option under Coins Only sub Menu$")
    public void iClickOnOptionUnderCoinsOnlySubMenu() throws InterruptedException {
        homePage.clickOnFullListLink();
    }

    @When("^I select Marketcap as \"(.+)\" and Price as \"(.+)\" and 24Hrs Volume as \"(.+)\"$")
    public void iSelectMarketcapAsAndPriceAsAndHrsVolumeAs(String marketCap, String price, String volume) throws Throwable {
        marketCapValue = marketCap;
        priceValue = price;
        volumeValue = volume;
        homePage.selectMarketCapFilter(marketCapValue);
        homePage.selectPriceFilter(priceValue);
        homePage.selectVolumeFilter(volumeValue);
    }

    @And("^I click on 'Add to Watchlist' from the options menu in ellipses$")
    public void iClickOnAddToWatchlistFromTheOptionsMenuInEllipses() {
        // homePage.clickOnLinkName();

    }

    @Then("^I see results based on my filtered criteria$")
    public void iSeeResultsBasedOnMyFilteredCriteria() {
        int resultCount = homePage.getresultCount();


        System.out.println("resultCount: " + resultCount);

        for (int i = 0; i < resultCount; i++) {
            int rowNumber = i + 1;
            String marketCapDataFromPage = homePage.returnMarketCapData(rowNumber);
            String priceDataFromPage = homePage.returnPriceData(rowNumber);
            String volumeDataFromPage = homePage.returnVolumeData(rowNumber);

            System.out.printf(String.format("marketCapDataFromPage: %s, priceDataFromPage: %s, volumeDataFromPage: %s", marketCapDataFromPage, priceDataFromPage, volumeDataFromPage));

            assertThat(marketCapDataFromPage, not(emptyOrNullString()));
            assertThat(priceDataFromPage, not(emptyOrNullString()));
            assertThat(volumeDataFromPage, not(emptyOrNullString()));


            double marketCapValueFromPage = Utils.getNumberFromFormattedNumber(marketCapDataFromPage);
            double priceFromPage = Utils.getNumberFromFormattedNumber(priceDataFromPage);
            double volumeFromPage = Utils.getNumberFromFormattedNumber(volumeDataFromPage);

            System.out.println(String.format("marketCapValueFromPage: %s, priceFromPage: %s, volumeFromPage: %s", marketCapValueFromPage, priceFromPage, volumeFromPage));


            NumberRange expectedMarketCapValueRange = Utils.getMarketCapRange(marketCapValue);
            NumberRange expectedPriceValueRange = Utils.getPriceValueRange(priceValue);
            double expectedVolume = Utils.get24HrsVolumeValue(volumeValue);

            System.out.println(String.format("expectedMarketCapValueRange: %s, expectedPriceValueRange: %s, expectedVolume: %s", expectedMarketCapValueRange, expectedPriceValueRange, expectedVolume));

            assertThat(String.format("MarketCap Assertion Error: marketCapValueFromPage: %s, expectedMarketCapValueRange: %s", marketCapValueFromPage, expectedMarketCapValueRange), marketCapValueFromPage, is(both(greaterThanOrEqualTo(expectedMarketCapValueRange.getMin())).and(lessThanOrEqualTo(expectedMarketCapValueRange.getMax()))));
            assertThat(String.format("Price Assertion Error: priceFromPage: %s, expectedPriceValueRange: %s", priceFromPage, expectedPriceValueRange), priceFromPage, is(both(greaterThanOrEqualTo(expectedPriceValueRange.getMin())).and(lessThanOrEqualTo(expectedPriceValueRange.getMax()))));
            assertThat(volumeFromPage, is(greaterThanOrEqualTo(expectedVolume)));

        }


    }
}

