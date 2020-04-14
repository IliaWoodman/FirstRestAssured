package petstore.test;

import categoriesMarker.GetTests;
import categoriesMarker.PostTests;
import categoriesMarker.PutTests;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.*;

import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class PetStoreWithParametrs {
    public final String petID;
    public final String petName;
    public final String newPetName;

    @Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"1234", "Sharik", "Jack"},
                {"1235", "Bobik", "Pes"},
                {"1236", "Buddy", "Barbos"}
        });
    }


    public PetStoreWithParametrs(String petID, String petName, String newPetName) {
        this.petID = petID;
        this.petName = petName;
        this.newPetName = newPetName;
    }

    @Test
    @Category(value = {PostTests.class})
    public void firstPostTest() {
        String url = "https://petstore.swagger.io/v2";
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", petID);
        requestBody.put("name", petName);
        RequestSpecification request = RestAssured.given();
        request.body(requestBody.toString());
        request.header("Content-Type", "application/json");
        Response response = request.post(url + "/pet");
        Assert.assertEquals(200, response.getStatusCode());
        System.out.println(response.getBody().asString());

    }

    @Test
    @Category(value = {GetTests.class})
    public void firstGetTest() {
        String url = "https://petstore.swagger.io/v2";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        Response response = request.get(url + String.format("/pet/%s", petID));
        if (petID.equals("1234")) {
            Assert.assertEquals(200, response.getStatusCode());
            response.then().body("name", equalTo("Sharik"));
            System.out.println(response.getBody().asString());
        }
        if (petID.equals("1235")) {
            Assert.assertEquals("application/json", response.getContentType());
            System.out.println(response.getBody().asString());
            response.then().body("name", equalTo("Bobik"));
        }
        if (petID.equals("1236")) {
            Assert.assertEquals(200, response.getStatusCode());
            response.then().body("name", equalTo("Buddy"));
            System.out.println(response.getBody().asString());
        }
    }

    @Test
    @Category(value = {PutTests.class})
    public void firstPutTest() {
        String url = "https://petstore.swagger.io/v2";
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", petID);
        requestBody.put("name", newPetName);
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        Response response = request.put(url + "/pet");

        Assert.assertEquals(200, response.getStatusCode());

    }

    @Test
    @Category(value = {GetTests.class})
    public void secondGetTest() {
        String url = "https://petstore.swagger.io/v2";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        Response response = request.get(url + String.format("/pet/%s",petID));

        if (petID.equals("1234")) {
            Assert.assertEquals(200, response.getStatusCode());
            response.then().body("name", equalTo("Jack"));
            System.out.println(response.getBody().asString());
        }
        if (petID.equals("1235")) {
            Assert.assertEquals(200, response.getStatusCode());
            response.then().body("name", equalTo("Pes"));
            System.out.println(response.getBody().asString());
        }
        if (petID.equals("1236")) {
            Assert.assertEquals(200, response.getStatusCode());
            response.then().body("name", equalTo("Barbos"));
            System.out.println(response.getBody().asString());
        }
    }
}
