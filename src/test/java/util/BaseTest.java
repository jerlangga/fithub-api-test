package util;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    public static Response response;
    public static RequestSpecification httpRequest;
    public WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }
}
