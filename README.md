

To run the tests on Google Chrome:
* [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/)
* Set a system variable of "webdriver.chrome.driver" to point to the file downloaded above, either permanently or as part of running Maven: `mvn test -Dwebdriver.chrome.driver=path/to/chromedriver`

To run the tests on Internet Explorer:
* [InternetExplorerDriver](https://github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver)
* Set a system variable of "webdriver.ie.driver" to point to the file downloaded above, either permanently or as part of running Maven: `mvn test -Dwebdriver.ie.driver=path/to/internetexplorerdriver`



You can also add configurations to the `mvn test` command:
* Passing through any of the config.properties values as system properties, e.g. `mvn test -Dbrowser=Firefox -DseleniumEnvironment=grid`
* Specifying a tag to run from the feature files, e.g. `mvn test -Dcucumber.tags=@search`

### Browser choices
* IE
* Chrome

Additonal browser choices can be added to the `browser.enum` file within the tests. The format is based on the style used by Browserstack, and your Selenium Grid must use the same stylings.
