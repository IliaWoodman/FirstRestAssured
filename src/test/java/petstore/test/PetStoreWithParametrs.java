package petstore.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.*;

import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class PetStoreWithParametrs {
    public final String petID;
    public final String petName;

    @Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"1234", "Sharik"},
                {"1235", "Bobik"},
                {"1236", "Buddy"}
		});
    }


    public PetStoreWithParametrs(String petID, String petName) {
        this.petID = petID;
        this.petName = petName;
    }

    @Test
    public void secondTest() {
        String url = "https://petstore.swagger.io/v2";
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", petID );
        requestBody.put("name", petName);
        RequestSpecification request = RestAssured.given();
        request.body(requestBody.toString());
        request.header("Content-Type", "application/json");
        Response response = request.post(url + "/pet");
        Assert.assertEquals(200, response.getStatusCode());
        System.out.println(response.getBody().asString());

    }

    @Test
    public void firstTest(){
    String url = "https://petstore.swagger.io/v2";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        Response response = request.get(url + String.format("/pet/%s",petID));
            if (petID.equals("1234")){
                Assert.assertEquals(200, response.getStatusCode());
                response.then().body("name", equalTo("Sharik"));
                System.out.println(response.getBody().asString());
            }
            if (petID.equals("1235")){
                Assert.assertEquals("application/json", response.getContentType());
                response.then().body("category.name", equalTo("Bobik"));
                System.out.println(response.getBody().asString());
            }if (petID.equals("1235")){
                Assert.assertEquals(200,response.getStatusCode());
            System.out.println(response.getBody().asString());
        }
    }
}
