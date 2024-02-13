package tests;


import api.reqres.registration.Register;
import api.reqres.registration.UnsuccessUserReg;
import api.reqres.specifications.Specifications;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class Test_3 {
    private static String URL = "https://reqres.in/";

    @Test
    @Tag("@RecresTest_3")
    @Tag("@API")
    @DisplayName("(RecresTest_3) Не успешная регистрация (отсутствует пароль)")
    public void unSuccessUserRegTest() {
        String ErrorText = "Missing password";
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecERROR400());
        Register user_2 = new Register("sydney@fife", "");
        UnsuccessUserReg unsuccessUserReg = given()
                .body(user_2)
                .when()
                .post("api/register")
                .then()
                .log().body()
                .extract().as(UnsuccessUserReg.class);
        assertNotNull(unsuccessUserReg.getError());
        assertEquals(ErrorText, unsuccessUserReg.getError());
    }
}
