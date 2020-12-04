package apitests.Day2;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

import static io.restassured.RestAssured.baseURI;

public class Homework1 {

    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("hr_api_url");
    }

    /*
    Q1:
- Given accept type is Json
- Path param value- US
- When users sends request to /countries
- Then status code is 200
- And Content - Type is Json
- And country_id is US
- And Country_name is United States of America
- And Region_id is
     */

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q","{\"country_id\":\"US\"}")
                .when().get("/countries");

        assertEquals(response.getStatusCode(),200);
        assertEquals(response.getContentType(),"application/json");

        JsonPath jsonPath = response.jsonPath();

        String country_id = jsonPath.getString("items.country_id[0]");
        System.out.println("country_id = " + country_id);
        assertEquals(country_id,"US");

        String country_name = jsonPath.getString("items.country_name[0]");
        System.out.println("country_name = " + country_name);
        assertEquals(country_name,"United States of America");

        //assertTrue(response.body().asString().contains("United States of America"));


        String region_id = jsonPath.getString("items.region_id[0]");
        System.out.println("region_id = " + region_id);
        assertEquals(region_id,"2");


    }


    /*
    Q2:
- Given accept type is Json
- Query param value - q={"department_id":80}
- When users sends request to /employees
- Then status code is 200
- And Content - Type is Json
- And all job_ids start with 'SA'
- And all department_ids are 80
- Count is 25
     */

    @Test
    public void Q2 (){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q","{\"department_id\":80}")
                .when().get("/employees");

        assertEquals(response.getStatusCode(),200);
        assertEquals(response.getContentType(),"application/json");

        List<String> job_id = response.path("items.job_id");
        int count = 0;
        for (String job_ids : job_id) {
            System.out.println(job_ids);
            assertTrue(job_ids.startsWith("SA"));

            count++;

        }

        List<Integer> department_id = response.path("items.department_id");

        for (int department_ids : department_id) {
            System.out.println(department_ids);
            assertEquals(department_ids,80);

        }

        System.out.println("count = " + count);
        assertEquals(count,25);


    }

    /*
    Q3:
- Given accept type is Json
-Query param value q= region_id 3
- When users sends request to /countries
- Then status code is 200
- And all regions_id is 3
- And count is 6
- And hasMore is false
- And Country_name are;
Australia,China,India,Japan,Malaysia,Singapore
     */

    @Test
    public void Q3 (){

        Response response = given().accept(ContentType.JSON)
                            .and().queryParam("q","{\"region_id\":3}")
                            .when().get("/countries");

        assertEquals(response.statusCode(),200);

        List<Integer> region_id = response.path("items.region_id");

        int count = 0;
        for (int region_ids : region_id) {
            System.out.println(region_ids);
            count++;
            assertEquals(region_ids,3);

        }

        assertEquals(count,6);
        
        String hasMore = response.jsonPath().getString("hasMore");
        assertEquals(hasMore,"false");

        List<String> country_name = response.path("items.country_name");

        for (String country_names : country_name) {
            System.out.println(country_names);

        }


    }



}
