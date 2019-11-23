@search
Feature:

  Background:
    Given I open the browser and go on "https://coinmarketcap.com/"

  Scenario: View All crypto currencies
    When I click on View All link
    Then I should see more than 100 results are displayed


  Scenario: Add few random currencies  and add them into watchlist and view

    When I Select 5 random crypto currencies
    And I click on 'Add to Watchlist' from the options menu in ellipses
    When I open the 'Watchlist' in another tab
    Then All the currencies added on the watchlist are validated

  Scenario Outline: Filtering on Coins Only full list of Cryptocurrencies

    When I click on the dropdown menu on the "Cryptocurrencies" tab
    And I click on 'Full List' option under Coins Only sub Menu
    When I select Marketcap as "<MarketCap>" and Price as "<Price>" and 24Hrs Volume as "<24Hrs Volume>"
    Then I see results based on my filtered criteria

    Examples:
      | MarketCap                 | Price     | 24Hrs Volume |
      | $1 Billion+               | $100+     | $10 Million+ |
      | $100 Million - $1 Billion | $1 - $100 | $1 Million+  |
      | $100 Million - $1 Billion | $1 - $100 | $100k+       |

