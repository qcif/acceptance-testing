package au.com.redboxresearchdata.acceptance.redboxsmoketest.page

import geb.Page
import geb.navigator.Navigator
import groovy.util.logging.Slf4j

/**
 * @author Matt Mulholland (matt@redboxresearchdata.com.au)
 * @date 19/7/17
 */
@Slf4j
abstract class GenericPage extends Page {
    static content = {
        branding {
            $("#branding")
        }
        redboxLink {
            branding.$('a[href$="home"]')
        }
        redboxIcon {
            redboxLink.$("img", src: iEndsWith("RedBox_Logo_Text.png"))
        }
        navigationBar {
            $("ul.nav.main")
        }
        homeMenu {
            navigationBar.$("li a", text: "Home")
        }
        userInfo {
            navigationBar.$("#user-info")
        }
        pageHeading {
            def selector = $("#page-heading")
            assert selector?.text()?.trim() ==~ /Everything/
            return selector
        }
        optionsMenu {
            def selector = $(".box.menu")
            assert selector.$("h2")?.text()?.trim() ==~ /OPTIONS/
            return selector
        }
        searchMenu {
            def selector = $(".box.search")
            assert selector.$("h2")?.text()?.trim() ==~ /SEARCH/
            return selector
        }
    }

    def assertLayoutIsVisible() {
        [redboxLink,homeMenu, userInfo, pageHeading,optionsMenu,searchMenu].each { Navigator nav ->
            waitFor { nav}.isDisplayed()
        }
    }
}
