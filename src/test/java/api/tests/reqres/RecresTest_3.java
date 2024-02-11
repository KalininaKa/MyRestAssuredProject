package api.tests.reqres;


import api.tests.reqres.registration.Register;
import api.tests.reqres.registration.UnsuccessUserReg;
import api.tests.reqres.specifications.Specifications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class RecresTest_3 {
    private static String URL = "https://reqres.in/";

    @Test
    @Tag("@RecresTest_3")
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
        Assertions.assertNotNull(unsuccessUserReg.getError());
        Assertions.assertEquals(ErrorText, unsuccessUserReg.getError());
    }
}
