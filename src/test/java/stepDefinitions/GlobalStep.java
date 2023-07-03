package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.junit.Assert;
import util.BaseTest;
import util.ConfigFileReader;

public class GlobalStep extends BaseTest {
    ConfigFileReader configFileReader;

    @Then("Response status is {int}")
    public void responseStatusIs(int code) {
        int statusCode = response.getStatusCode();
        System.out.println("Response Status Code: "+statusCode);
        Assert.assertEquals("Status code returned should: ", code, statusCode);
    }

    @When("Content type is {string}")
    public void contentTypeIs(String kind) {
        if (kind.equalsIgnoreCase("json")) {
            httpRequest.header("Content-Type", "application/json");
        }
    }

    @Given("API Header")
    public void apiHeader() {
        configFileReader= new ConfigFileReader();

        RestAssured.baseURI = configFileReader.getApplicationUrl()+"/api";
        httpRequest = RestAssured.given();
    }
}
