package Day8;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;

public class SpartanFlow {

    @BeforeClass
    public void beforeclass() {
        baseURI = ConfigurationReader.get("spartan_api_url");
    }


    @Test
    public void POSTNewSpartan() {

        Map<String,Object> requestMap = new HashMap<>();

        requestMap.put("name","HASAN KO");
        requestMap.put("gender","Male");
        requestMap.put("phone",17632946359l);

        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(requestMap)
        .when()
                .post("/api/spartans");

    }


    @Test
    public void PUTExistingSpartan() {

        Map<String,Object> putRequestMap = new HashMap<>();
        putRequestMap.put("name","Hasan Ko");
        putRequestMap.put("gender","Male");
        putRequestMap.put("phone",17632946458l);

        given()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id",122)
                .and().body(putRequestMap)
        .when()
                .put("/api/spartans/{id}")
        .then().log().all()
                .assertThat().statusCode(204);


    }

    @Test
    public void PATCHExistingSpartan() {

        Map<String,Object> putRequestMap = new HashMap<>();
        putRequestMap.put("name","Hasan PATCH");

        given()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id",122)
                .and().body(putRequestMap)
                .when()
                .patch("/api/spartans/{id}")
                .then().log().all()
                .assertThat().statusCode(204);
    }

    @Test
    public void GETNewSpartan() {

       given().accept(ContentType.JSON)
                .and().pathParam("id",122)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .and().assertThat().contentType("application/json")
                .and().assertThat().body("id",equalTo(122),
                        "name",equalTo("Hasan PATCH"),
                                             "gender",equalTo("Male"),
                                               "phone",equalTo(17632946458l));

    }


    @Test
    public void DELETEThatSpartan() {

       /* Random rd = new Random();
        int idToDelete = rd.nextInt(100)+1;
        System.out.println("This spartan id" + idToDelete + "will be deleted. Say goodbye!");
*/
        given()
                .pathParam("id",123)
                .when().delete("/api/spartans/{id}")
                .then()
                .statusCode(204).log().all();

    }


}