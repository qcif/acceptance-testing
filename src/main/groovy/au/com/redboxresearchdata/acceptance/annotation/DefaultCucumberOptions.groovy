package au.com.redboxresearchdata.acceptance.annotation

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import groovy.transform.AnnotationCollector
import org.junit.runner.RunWith

/**
 * @author Matt Mulholland (matt@redboxresearchdata.com.au)
 * @date 23/7/17
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        format = ["pretty", "html:build/reports/cucumber"],
        features = ["src/test/cucumber/au/com/redboxresearchdata/acceptance/features"],
        glue = ["src/test/cucumber/au/com/redboxresearchdata/acceptance/steps",
                "src/test/cucumber/au/com/redboxresearchdata/acceptance/support"]
)
@AnnotationCollector(processor = "CompileDynamicProcessor")
@interface DefaultCucumberOptions {

}