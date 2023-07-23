package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class Authentication {

    /*
        Bu yapıyı bize her seferinde token olustursun diye hazirladık
     */

    private static RequestSpecification spec;
    public static String generateToken(){

        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

        spec.pathParams("pp1","api","pp2","login");

        JSONObject requestBody = new JSONObject();

        requestBody.put("email", ConfigReader.getProperty("email"));
        requestBody.put("password",ConfigReader.getProperty("password"));

        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept","application/json")
                .when()
                .body(requestBody.toString())
                .post("/{pp1}/{pp2}");

        JsonPath responseJp = response.jsonPath();

        String token = responseJp.getString("token");

        return token;

    }

}
