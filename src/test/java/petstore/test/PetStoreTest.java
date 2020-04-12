package petstore.test;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import petstore.EndPointUrlPetStore;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PetStoreTest {


    private static final String URL_KEY = "https://petstore.swagger.io/v2";
    // тесты могут быть не актуальны на момент воспроизведения, т.к. пэтстор в общем доступе и постоянно изменяется;
    @Test
    public void firstGetTest() {
        RestAssured.baseURI = URL_KEY;

        given()

                .when()
                .get(EndPointUrlPetStore.PET.addPath(String.format("/findByStatus?status=%s", "sold")))
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void secondGetTest() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        given()

                .when()
                .get(EndPointUrlPetStore.PET.addPath("/3"))

                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(3),
                        "category.id", equalTo(0),
                        "name", equalTo("doggie"),
                        "tags[0].name", equalTo("string"),
                        "photoUrls", hasItems("string"),
                        "status", equalTo("available"));
    }

    @Test
    public void thirdGetTest() {
        RestAssured.baseURI = URL_KEY;
        given()
                .when()
                .get(EndPointUrlPetStore.PET.addPath(String.format("/findByStatus?status=%s", "sold")))
                .then()
                .statusCode(200)
                .body("[0].category.name", equalTo("cats"),
                        //"[8].name", equalTo("King Kong"),
                        "[8].photoUrls[0]", equalTo("string"),
                        "[31].name", equalTo("Fido"),
                        "[31].tags[0].id", equalTo(0));

    }
    @Test
    public void fourthGetTest() {
        RequestSpecification request = RestAssured.given();
        Response response = request.get(URL_KEY + EndPointUrlPetStore.PET.addPath("/779"));
        int statusCode = response.getStatusCode();

        // Assert.assertEquals(statusCode, 200);
        String answer = "{\"id\":779,\"name\":\"Dude333\",\"photoUrls\":[],\"tags\":[]}";
        Assert.assertEquals(answer,response.getBody().asString());

    }
    @Test
    public void fifthGetTest(){
        RestAssured.baseURI = URL_KEY;
        RequestSpecification request = RestAssured.given();
        Response response = request.get("/pet/780");
        request.then()
                .body("id",equalTo(780));
        System.out.println(response.getBody().asString());
    }
    @Test
    public void sixthGetTest(){
        RestAssured.baseURI = URL_KEY;
        String expectedBody = "{\"id\":781,\"name\":\"Dude335\",\"photoUrls\":[],\"tags\":[],\"status\":\"sold\"}";

        RequestSpecification request = RestAssured.given();
        Response response = request.get(EndPointUrlPetStore.PET.addPath("/781"));
        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals("application/json", response.contentType());
        Assert.assertEquals(expectedBody, response.getBody().asString());
        response.then()
                .body("name", equalTo("Dude335"));
    }


    @Test
    public void firstPostTest() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", 779);
        requestBody.put("name", "Dude333");

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        Response response = request.post("https://petstore.swagger.io/v2/pet");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        String successCode = response.jsonPath().get("SuccessCode");
        System.out.println(response.getBody().asString());
    }
    @Test
    public void secondPostTest(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", 780);
        requestBody.put("name", "Dude334");
        requestBody.put("status", "sold");

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type","application/json");
        request.body(requestBody.toString());
        Response response = request.post(URL_KEY + EndPointUrlPetStore.PET.addPath(""));
        String contentType = response.getContentType();
        int statusCode = response.getStatusCode();
        String answer = "{\"id\":780,\"name\":\"Dude334\",\"photoUrls\":[],\"tags\":[],\"status\":\"sold\"}";
        Assert.assertEquals("application/json", contentType);
        Assert.assertEquals(200,statusCode);
        Assert.assertEquals(answer,response.getBody().asString());
        System.out.println(response.getBody().asString());

    }
    @Test
    public void thirdPostTest(){
        RestAssured.baseURI = URL_KEY;
        JSONObject requesteBody = new JSONObject();
        requesteBody.put("id", 782);
        requesteBody.put("name", "Dude336");
        requesteBody.put("status","sold");

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requesteBody.toString());
        Response response = request.post(URL_KEY + EndPointUrlPetStore.PET.addPath(""));
        Assert.assertEquals(200,response.getStatusCode());
        System.out.println(requesteBody);
        System.out.println(response.getBody().asString());
    }
    //{"id":781,"category":{"id":0,"name":"string"},"name":"Sharik2","photoUrls":["string"],"tags":[{"id":0,"name":"string"}],"status":"available"}
    @Test
    public void firstPutTest(){
        String expectedBody = "{\"id\":782,\"name\":\"Sharik4\",\"photoUrls\":[],\"tags\":[],\"status\":\"available\"}";
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", 782);
        requestBody.put("name", "Sharik4");
        requestBody.put("status", "available");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type","application/json");
        request.body(requestBody.toString());
        Response response = request.put(URL_KEY + EndPointUrlPetStore.PET.addPath(""));
        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(expectedBody,response.getBody().asString());
        System.out.println(requestBody);
        System.out.println(response.getBody().asString());
    }


}
