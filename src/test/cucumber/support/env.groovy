package support

import cucumber.api.Scenario
import geb.Browser
import geb.binding.BindingUpdater
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.remote.RemoteWebDriver

import static cucumber.api.groovy.Hooks.After
import static cucumber.api.groovy.Hooks.Before

def bindingUpdater
def currentBrowser

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
  bindingUpdater.browser.clearCookies()
  bindingUpdater.remove()
}