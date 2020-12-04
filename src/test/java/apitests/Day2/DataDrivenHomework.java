package apitests.Day2;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class DataDrivenHomework {

    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void test1(){

        Response response = when().get("https://my.api.mockaroo.com/eu3?key=1597cf70");

        response.prettyPrint();


    }


    //optional homework1
    //1- Create csv file from mackaroo website, which includes name,gender,phone
    //2- Download excel file
    //3- using testng data provider and apache poi create data driven posting from spartan



    //optional Homework2
    //Create one mackaroo api for name,gender,phone
    //send get request to retrieve random info from that api
    //use those info to send post request to spartan


}
