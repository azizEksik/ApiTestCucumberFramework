package stepDefinitions.api;


import hooks.api.HooksAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;

public class CommonAPI {

    String fullPath;
    JSONObject requestBody;

    @Given("Api kullanicisi {string} path parametreleri set eder")
    public void api_kullanicisi_path_parametreleri_set_eder(String endPoint) {

        String[] paths = endPoint.split("/");

        StringBuilder tempPath = new StringBuilder("/{");

        for (int i = 0; i < paths.length; i++) {

            String key = "pp"+(i+1);
            String value = paths[i].trim(); // trim() bosluklardan arındırma methodudur

            HooksAPI.spec.pathParam(key,value);

            tempPath.append(key + "}/{");

        }

        tempPath.deleteCharAt(tempPath.lastIndexOf("{")); // son karakteri siler
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));

        fullPath = tempPath.toString();


    }
    @Then("AllCountries icin get request gonderilir")
    public void all_countries_icin_get_request_gonderilir() {

        Response response = given()
                                .spec(HooksAPI.spec)
                                .contentType(ContentType.JSON)
                                .header("Accept","application/json")
                                .headers("Authorization","Bearer "+HooksAPI.token)
                            .when()
                            .get(fullPath);

        response.prettyPrint();

    }

    @Then("Login icin {string} ve {string} girilir")
    public void login_icin_ve_girilir(String email, String password) {

        /*
                {
                    "email" : "test@test.com",
                    "password" : "123123123"
                 }

         */

        requestBody = new JSONObject();

        requestBody.put("email", ConfigReader.getProperty(email));
        requestBody.put("password",ConfigReader.getProperty(password));



    }


    @Then("Login icin Post request Gonderilir")
    public void loginIcinPostRequestGonderilir() {

        Response response = given()
                                .spec(HooksAPI.spec)
                                .contentType(ContentType.JSON)
                            .when()
                                .body(requestBody.toString())
                            .post(fullPath);

        response.prettyPrint();

    }
}
