package steps;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import params.UrlParameters;

import java.util.Random;

public class GetMethods {

    static final String BASE_URL = "https://fakerapi.it/api/v1";
    static final String BOOK_PATH = "/books";
    static final String TEXT_PATH = "/texts";
    static final String CUSTOM_PATH = "/custom";

    public static void quantityNumberCheck() {
        RestAssured.baseURI = BASE_URL;

        // control of quantity with random number
        Random random = new Random();
        int quantityNumber = 1 + random.nextInt(9);

        System.out.println(quantityNumber);

        Response response = RestAssured
                .given()
                .queryParam(UrlParameters.Quantity, quantityNumber)
                .get(BOOK_PATH);

        JsonPath responseBody = response.jsonPath();

        Assert.assertEquals((int) responseBody.get("total"), quantityNumber);

        response.then()
                .statusCode(200)
                .log().all();
    }

    public static void dataFieldsAreChecked() {
        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured
                .given()
                .queryParam(UrlParameters.Quantity, 1)
                .get(TEXT_PATH);

        JsonPath responseBody = response.jsonPath();

        Assert.assertNotNull(responseBody.get("data"));
        Assert.assertNotNull(responseBody.get("data[0].author"));
        Assert.assertNotNull(responseBody.get("data[0].genre"));
        Assert.assertNotNull(responseBody.get("data[0].content"));

        response.then()
                .statusCode(200)
                .log().all();
    }

    public static void characterFieldsAreChecked(int characterLength) {
        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured
                .given()
                .queryParam(UrlParameters.Quantity, 1)
                .queryParam(UrlParameters.Characters, characterLength)
                .get(TEXT_PATH);

        JsonPath responseBody = response.jsonPath();

        int dataSize = responseBody.get("data[0].content.size()");

        System.out.println(dataSize);

        //I analyzed the Faker Api and realized that. When any parameter takes the value 0, the result is returned
        // according to the default value of that parameter. For the character parameter, this value is 200

        if (characterLength == 0)
            characterLength = 200;

        Assert.assertTrue(dataSize < characterLength);
        Assert.assertTrue(dataSize >= characterLength - 10);

        response.then()
                .statusCode(200)
                .log().all();

    }

    public static void specialDataFieldsAreChecked() {


        RestAssured.baseURI = BASE_URL;
        Response response = RestAssured
                .given()
                .queryParam(UrlParameters.Quantity, 1)
                .queryParam(UrlParameters.Name, "name")
                .queryParam(UrlParameters.Datetime, "dateTime")
                .queryParam(UrlParameters.PhoneNumber, "phone")
                .queryParam(UrlParameters.Text, "text")
                .get(CUSTOM_PATH);

        JsonPath responseBody = response.jsonPath();

        Assert.assertNotNull(responseBody.get("data"));
        Assert.assertNotNull(responseBody.get("data[0].name"));
        Assert.assertNotNull(responseBody.get("data[0].lmd.date"));
        Assert.assertNotNull(responseBody.get("data[0].lmd.timezone_type"));
        Assert.assertNotNull(responseBody.get("data[0].lmd.timezone"));
        Assert.assertNotNull(responseBody.get("data[0].phoneNumber"));
        Assert.assertNotNull(responseBody.get("data[0].description"));

    }
}
