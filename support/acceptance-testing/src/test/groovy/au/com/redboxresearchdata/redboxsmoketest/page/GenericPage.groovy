package au.com.redboxresearchdata.redboxsmoketest.page

import geb.Page

/**
 * @author Matt Mulholland (matt@redboxresearchdata.com.au)
 * @date 19/7/17
 */
class GenericPage extends Page {
    static content = {
        branding {
            $("#branding")
        }
        redboxLink {
            branding.$("a", "href": "http://redbox:9000/redbox/verNum1.9.0.1-SNAPSHOT/default/home")
        }
        redboxIcon {
            redboxLink.$("img", "src": "http://redbox:9000/redbox/verNum1.9.0.1-SNAPSHOT/default/images/RedBox_Logo_Text.png")
        }
        navigationBar {
            $("ul.nav.main")
        }
        homeMenu {
            navigationBar.$("li a", "href": "http://redbox:9000/redbox/verNum1.9.0.1-SNAPSHOT/default/home")
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
            assert selector.$("h2")?.text()?.trim() ==~ /Options/
            return selector
        }
        searchMenu {
            def selector = $(".box.search")
            assert selector.$("h2")?.text()?.trim() ==~ /Search/
            return selector
        }
    }

    def assertLayoutIsVisible() {
        [redboxIcon, homeMenu, userInfo, pageHeading, optionsMenu, searchMenu].each {
            waitFor { it }.isDisplayed()
        }
    }
}
