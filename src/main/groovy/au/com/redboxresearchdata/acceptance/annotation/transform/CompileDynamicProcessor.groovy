package au.com.redboxresearchdata.acceptance.annotation.transform

import org.codehaus.groovy.ast.AnnotatedNode
import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.ast.expr.Expression
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.AnnotationCollectorTransform

/**
 * @author Matt Mulholland (matt@redboxresearchdata.com.au)
 * @date 23/7/17
 */
class CompileDynamicProcessor extends AnnotationCollectorTransform {
    List<AnnotationNode> visit(AnnotationNode collector,
                               AnnotationNode aliasAnnotationUsage,
                               AnnotatedNode aliasAnnotated,
                               SourceUnit source) {

        // Get attributes and attribute value for feature.
        Map<String, Expression> attributes = aliasAnnotationUsage.getMembers()
        Expression feature = attributes.get('feature')
        // Remove original placeholder
        attributes.remove('feature')

        // add the plugin for nice reporting and the tags for parallel match
        if (feature) {
            String featureText = feature.getText()
            // Assign value of feature to excludes attributes.
            aliasAnnotationUsage.addMember("plugin", new ConstantExpression("com.cucumber.listener.ExtentCucumberFormatter:build/reports/extent/${featureText}.html" as String))
            aliasAnnotationUsage.addMember("tags", new ConstantExpression("@${featureText}" as String))
        }
        super.visit(collector, aliasAnnotationUsage, aliasAnnotated, source)
    }
}
