package support

import cucumber.api.Scenario
import cucumber.api.groovy.Hooks
import geb.Browser
import geb.binding.BindingUpdater
import groovy.util.logging.Slf4j
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.remote.RemoteWebDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static cucumber.api.groovy.Hooks.After
import static cucumber.api.groovy.Hooks.Before

def bindingUpdater
def currentBrowser

final Logger logger = LoggerFactory.getLogger(Hooks.class)

Before() { scenario ->
    currentBrowser = new Browser()
    bindingUpdater = new BindingUpdater(binding, currentBrowser)
    bindingUpdater.initialize()
    if (currentBrowser.driver instanceof RemoteWebDriver) {
        //needed for test listener(s)
        println("sessionId:" + ((RemoteWebDriver) currentBrowser.driver).getSessionId())
    }
}

After() { Scenario scenario ->
    if (scenario.isFailed()) {
        final byte[] screenshot = ((TakesScreenshot) currentBrowser.driver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
        //stick it in the report - go to build/reports/cucumber/index.html to see image(s) in place in html report
    }
    // ensure that session starts again - no need for explicit logout
    // - some caps e.g.. Edge will throw exception when cookies cleared, so hide these so test passes

    bindingUpdater.browser.clearCookiesQuietly()
    bindingUpdater.remove()
}