package au.com.redboxresearchdata.redboxsmoketest.page
/**
 * @author <a href="matt@redboxresearchdata.com.au">Matt Mulholland</a>
 * Created on 25/05/2017.
 */
class HomePage extends GenericPage {
    static url = ""
    static at = { assertLayoutIsVisible() }

    static content = {
        loginLink(required: false) { userInfo.$("login-now") }
        loginForm { $("form#login") }
    }

    def showLoginDialog() {
        if (!loginForm.isDisplayed()) {
            def result = loginLink.click()
            print result
        }
    }

}
