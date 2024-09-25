package hooks;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.Before;


public class Hooks {


    @Before
    public void configureLoggers() {

            RestAssured.given().filter(new AllureRestAssured());

    }
}
