package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;


public class DataFieldsCheck {

    @Given("^I perform Get operations for Text$")
    public void iPerformGetOperationsForText() {
        given().contentType(ContentType.JSON);
    }

    @Then("^Data fields are checked$")
    public void dataFieldsAreChecked() {
        GetMethods.dataFieldsAreChecked();
    }

}
