package com.digital.services;

import org.apache.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import com.digital.config.ConfigProvider;
import com.digital.headers.Headers;
import com.typesafe.config.Config;
import static org.hamcrest.Matchers.*;
//import static org.hamcrest.CoreMatchers.*;
public class OxfordServiceHandler {

	private Headers header;
	private Config config;

	public OxfordServiceHandler() {
		header = new Headers();
		config = ConfigProvider.config();
	}

	public void getMeaningForTheWord(String word,String locale)
	{
		
		RestAssured.baseURI = this.config.getString("OxfordBaseURI");
		RestAssured.basePath =  this.config.getString("getWordMeaning");
		
		given()
		.headers(this.header.setHeaders())
		.accept(ContentType.JSON)
		.pathParam("word", word)
		.pathParam("locale", locale)
		.log()
		.all()
		.when()
		.get()
		.then()
		.log()
		.all()
		.assertThat()
		.statusCode(is(HttpStatus.SC_OK))
		.and()
		.body("results[0].lexicalEntries[0].entries[0].senses[0].definitions[0]", is(not(nullValue())));
		
	}
}
