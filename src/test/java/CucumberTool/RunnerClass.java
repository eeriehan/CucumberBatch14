package CucumberTool;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features =
                "src\\test\\java\\CucumberTool\\Login.feature"
)


public class RunnerClass {
}