package support

import static cucumber.api.groovy.Hooks.*

import geb.Browser
import geb.binding.BindingUpdater


def bindingUpdater
def currentBrowser

Before() { scenario ->
    currentBrowser = new Browser()
    bindingUpdater = new BindingUpdater(binding, currentBrowser)
    bindingUpdater.initialize()
    //needed for listeners to pickup session id.
    println("sessionId:" + currentBrowser.properties.config.rawConfig.sessionId)
}

After() { scenario ->
    bindingUpdater.remove()
}