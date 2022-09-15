package br.inatel.dragrace.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"classpath:features/"}, glue = {"br.inatel.dragrace.step", "br.inatel.dragrace.config"})
public class FunctionalTestRunner {
}
