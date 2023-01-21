package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
// public class APIRunner

        features = "src/test/resources/features/",
        glue = "APISteps",

        dryRun = false,
        tags = "@api",

        monochrome = true,
        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json",
                "rerun:target/failed.txt"}

)






public class APIRunner {
}
