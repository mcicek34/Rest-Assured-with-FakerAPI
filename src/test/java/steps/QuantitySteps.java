package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;


public class QuantitySteps {

    @Given("^I perform Get operations for Books$")
    public void iPerformGetOperationsForFakerAPI() {
        given().contentType(ContentType.JSON);
    }

    @Then("^I perform GET for the quantity number$")
    public void iPerformGETForTheQuantityNumber() {
        GetMethods.quantityNumberCheck();
    }
}
