package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class SpecialDataCheck {

    @Given("^I perform Get operations for Custom$")
    public void iPerformGetOperationsForCustom() {
        given().contentType(ContentType.JSON);
    }

    @Then("^Special Data fields are checked$")
    public void specialDataFieldsAreChecked() {
        GetMethods.specialDataFieldsAreChecked();
    }

}
