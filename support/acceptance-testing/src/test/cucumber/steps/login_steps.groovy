package steps

import au.com.redboxresearchdata.redboxsmoketest.page.HomePage

import static cucumber.api.groovy.EN.*

When(~/^I click on login$/) { ->
    page.showLoginDialog()
}

Then(~/^I should see the login dialog$/) { ->
    page.loginForm.isDisplayed()
}
