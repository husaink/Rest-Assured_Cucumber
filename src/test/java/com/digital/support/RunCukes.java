package com.digital.support;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin={"html:target/cucumber","json:target/cucumber.json"}, features = "src/test/resources/features", 
glue = {"com.digital.stepdefs"}, tags={"@OxfordWordMeaning"}) 
public class RunCukes { }
