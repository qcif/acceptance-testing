package au.com.redboxresearchdata.redboxsmoketest.page

import geb.Page

/**
 * @author <a href="matt@redboxresearchdata.com.au">Matt Mulholland</a>
 * Created on 25/05/2017.
 */
class HomePage extends Page {
    static URL = ""
    static at = { $("h2#page-heading").text() == "Eerything" }

    static content = {
        loginLink(required: false) { $("#user-info .login-now") }
        loginForm { $("form#login") }
    }

    def showLoginDialog() {
        if (!loginForm.isDisplayed()) {
            def result = loginLink.click()
            print result
        }
    }
}
