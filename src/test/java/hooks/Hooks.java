package hooks;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;


public class Hooks {


    @Test
    public void configureLoggers() {

            RestAssured.given().filter(new AllureRestAssured());

    }
}
