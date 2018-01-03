package com.digital.stepdefs;

import org.junit.Assert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import com.digital.config.ConfigProvider;
import com.digital.headers.Headers;
import com.typesafe.config.Config;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static io.restassured.RestAssured.when;

public class ApiCallSteps {

	private Config config;
	private Response response;
	private String locale;
	private String word;

	public ApiCallSteps() {
		config = ConfigProvider.config();
	}

	@Given("^user has passed headers$")
	public void user_has_passed_headers() throws Throwable {
//		RestAssured
//		.given()
//		.headers(Headers.setHeaders()).log().all();
	}

	@Given("^user has passed \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_has_passed(String word, String locale) throws Throwable {
		this.locale = locale;
		this.word = word;

	}

	@When("^user access the \"([^\"]*)\" api$")
	public void user_access_the_api(String api) throws Throwable {
		this.config = ConfigProvider.config();
		//RestAssured.baseURI = this.config.getString("OxfordBaseURI");
		//RestAssured.basePath=this.config.getString("getWordMeaning");
//		RestAssured
//		.given()
//		.headers(Headers.setHeaders()).log().all().accept(ContentType.JSON)
//		.log().all();
		this.response = RestAssured
				.given()
				.headers(Headers.setHeaders()).log().all().accept(ContentType.JSON)
				.log().all()
				.get(this.config.getString("OxfordBaseURI")+this.config.getString("getWordMeaning"),this.word)
				.andReturn();
	}

	@Then("^user gets success$")
	public void user_gets_success() throws Throwable {
		Assert.assertTrue("HTTP Status is not correct",
				this.response.statusCode() == 200);
	}

	@Then("^user gets description$")
	public void user_gets_description() throws Throwable {
		String json = response.getBody().asString();
		JsonPath jsonPath = new JsonPath(json);
		System.out.println("Definition " + jsonPath
						.getString(
								"results[0].lexicalEntries[0].entries[0].senses[0].definitions[0]"));
		Assert.assertTrue(
				"Description is blank",
				!(jsonPath
						.getString(
								"results[0].lexicalEntries[0].entries[0].senses[0].definitions[0]")
						.length() == 0));
	}

	@Then("^user gets failure$")
	public void user_gets_failure() throws Throwable {
		Assert.assertTrue("HTTP Status is not correct",
				this.response.statusCode() == 404);
	}
}
