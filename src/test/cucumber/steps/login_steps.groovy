package steps

import au.com.redboxresearchdata.page.HomePage

import static cucumber.api.groovy.EN.*

Given(~/^I am on the home page$/) { ->
    to HomePage
    at HomePage
}
//
//When(~/^<when statement goes here>$/) { ->
//    page.<method goes here>()
//}
//
//Then(~/^<then statement goes here>$/) { ->
//    page.<method goes here>()
//}
