package petstore.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class PetStoreWithParametrs {
public final int petID;

@Parameters
public static ArrayList<Integer> data(){
ArrayList<Integer> list = new ArrayList<>();
list.add(500);
list.add(778);
list.add(779);
return list;
}

    public PetStoreWithParametrs(int petID) {
        this.petID = petID;
    }

    @Test
    public void firstTest(){
    String url = "https://petstore.swagger.io/v2";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        Response response = request.get(url + String.format("/pet/%d",petID));
            if (petID == 500){
                Assert.assertEquals(200, response.getStatusCode());
                response.then().body("name", equalTo("doggie"));
            }
            if (petID == 778){
                Assert.assertEquals("application/json", response.getContentType());
                response.then().body("category.name", equalTo("RpNDyYY8JQOCKLUM"));
            }if (petID == 779){
                Assert.assertEquals(404,response.getStatusCode());
        }
    }
}
