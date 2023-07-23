package hooks.api;

import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import utilities.Authentication;
import utilities.ConfigReader;


public class HooksAPI {

    public static RequestSpecification spec;
    public static String token;

    @Before (order = 0)
    public void setUp(){

        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

    }

    @Before (order = 1)
    public void beforeGenerateToke(){

        /*
            Bu yapıyı olusuturdugumuz Authentication classından bize her defasında token olusutursun diye hazirladık
            ve heryerden kullanabilmek icin public static olarak class level variable'a atadik
         */

        token = Authentication.generateToken();

    }

}
