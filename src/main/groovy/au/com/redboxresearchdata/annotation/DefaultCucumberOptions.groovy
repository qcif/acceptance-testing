package au.com.redboxresearchdata.annotation

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
        features = ["src/test/cucumber/features"],
        glue = ["src/test/cucumber/steps", "src/test/cucumber/support"]
)
@AnnotationCollector(processor = "au.com.redboxresearchdata.annotation.transform.CompileDynamicProcessor")
@interface DefaultCucumberOptions {

}