import geb.driver.SauceLabsDriverFactory
import org.openqa.selenium.Platform
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.remote.BrowserType
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver

/*
 *
 *  * Copyright (C) 2017. Queensland Cyber Infrastructure Foundation (http://www.qcif.edu.au/)
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation; either version 2 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License along
 *  * with this program; if not, write to the Free Software Foundation, Inc.,
 *  * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 */

/*
	This is the Geb configuration file.
	See: http://www.gebish.org/manual/current/#configuration
*/

waiting {
  timeout = 10
  retryInterval = 1
}

environments {
    // local test drivers
    chrome {
        driver = { new ChromeDriver() }
    }

    firefox {
        driver = { new FirefoxDriver() }
    }

    phantomjs {
        driver = { new PhantomJSDriver() }
    }

    remotefirefox {
        driver = {
            //docker remote test drivers
            def remoteWebDriverServerUrl = new URL(System.getenv("DOCKER_HUB_URL"))
            new RemoteWebDriver(remoteWebDriverServerUrl, DesiredCapabilities.firefox())
        }
    }

    remotechrome {
        driver = {
            //docker remote test drivers
            def remoteWebDriverServerUrl = new URL(System.getenv("DOCKER_HUB_URL"))
            DesiredCapabilities capabilities = new DesiredCapabilities(BrowserType.CHROME, "", Platform.ANY)
            capabilities.setCapability(ChromeOptions.CAPABILITY, "--no-sandbox")
            new RemoteWebDriver(remoteWebDriverServerUrl, capabilities)
//            remote.manage().timeouts().
        }
    }

    //https://saucelabs.com - all of the following variables are created by the saucelabs plugin in build.gradle
    def sauceLabsBrowser = System.getProperty("geb.saucelabs.browser")
    if (sauceLabsBrowser) {
        driver = {
            def username = System.getenv("GEB_SAUCE_LABS_USER")
            assert username
            def accessKey = System.getenv("GEB_SAUCE_LABS_ACCESS_PASSWORD")
            assert accessKey
            new SauceLabsDriverFactory().create(sauceLabsBrowser, username, accessKey)
        }
    }
}
baseNavigatorWaiting = true
atCheckWaiting = true
