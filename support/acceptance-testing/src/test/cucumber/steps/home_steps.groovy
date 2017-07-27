package au.com.redboxresearchdata.acceptance.redboxsmoketest.steps

import au.com.redboxresearchdata.acceptance.redboxsmoketest.page.HomePage

import static cucumber.api.groovy.EN.*

Given(~/^I (?:am on|go to) the home page$/) { ->
    to HomePage
    at HomePage
}

When(~/^I enter the home page url$/) { ->
    go HomePage.url
}

Then(~/^I am at the home page$/) { ->
    at HomePage
}