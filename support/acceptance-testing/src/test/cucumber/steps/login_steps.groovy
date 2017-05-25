package steps

import au.com.redboxresearchdata.redboxdistro.page.HomePage

import static cucumber.api.groovy.EN.*

Given(~/^I am on the home page$/) { ->
    to HomePage
    at HomePage
}

When(~/^I click on login$/) { ->
    page.showLoginDialog()
}

Then(~/^I should see the login dialog$/) { ->
    page.loginForm.isDisplayed()
}
