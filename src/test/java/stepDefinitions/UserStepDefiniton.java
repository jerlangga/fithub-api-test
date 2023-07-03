package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import util.BaseTest;
import util.ConfigFileReader;

public class UserStepDefiniton extends BaseTest {
    ConfigFileReader configFileReader;

    @Given("User API")
    public void userAPI() {
        configFileReader= new ConfigFileReader();

        RestAssured.baseURI = configFileReader.getApplicationUrl()+"/api/users";
        httpRequest = RestAssured.given();
    }

    @When("Query params {string} is {string}")
    public void queryParamsIs(String var, String val) {
        switch (var) {
            case "page":
                response = httpRequest.request(Method.GET, "/users?"+var+"="+val);
                break;
            case "users":
                response = httpRequest.request(Method.GET, "/users/"+val);
                break;
        }
    }

    @When("Create new {string} with payload {string} and {string}")
    public void createNewWithPayloadAnd(String kind, String var1, String var2) {
        String payload = "{\n" +
                "    \"name\": \""+var1+"\",\n" +
                "    \"job\": \""+var2+"\"\n" +
                "}";

        String payloadReg = "{\n" +
                "    \"email\": \""+var1+"\",\n" +
                "    \"password\": \""+var2+"\"\n" +
                "}";

        switch (kind) {
            case "user":
                httpRequest.body(payload);
                response = httpRequest.request(Method.POST, "/users");
                break;
            case "account":
                httpRequest.body(payloadReg);
                response = httpRequest.request(Method.POST, "/register");
                break;
        }
    }

    @When("Update existing user with id {string} using payload {string} and {string}")
    public void updateExistingUserWithIdUsingPayloadAnd(String name, String job, String userId) {
        String payload = "{\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"job\": \""+job+"\"\n" +
                "}";

        httpRequest.body(payload);
        response = httpRequest.request(Method.PUT, "/users/"+userId);
    }

    @When("Delete existing user with id {string}")
    public void deleteExistingUserWithId(String userId) {
        response = httpRequest.request(Method.DELETE, "/users/"+userId);
    }

    @When("Login with payload {string} and {string}")
    public void loginWithPayloadAnd(String email, String password) {
        String payload = "{\n" +
                "    \"email\": \""+email+"\",\n" +
                "    \"password\": \""+password+"\"\n" +
                "}";

        httpRequest.body(payload);
        response = httpRequest.request(Method.POST, "/login");
    }
}
