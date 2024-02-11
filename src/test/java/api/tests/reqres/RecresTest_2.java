package api.tests.reqres;


import api.tests.reqres.registration.Register;
import api.tests.reqres.registration.SuccessUserReg;
import api.tests.reqres.specifications.Specifications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class RecresTest_2 {
    private static String URL = "https://reqres.in/";

    @Test
    @Tag("@RecresTest_2")
    @DisplayName("(RecresTest_2) Успешная регистрация")
    public void successUserRegTest() {
        Integer UserId = 4;
        String UserPassword = "QpwL5tke4Pnpja7X4";
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Register user = new Register("eve.holt@reqres.in", "pistol");
        SuccessUserReg successUserReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then()
                .log().all()
                .extract().as(SuccessUserReg.class);
        Assertions.assertNotNull(successUserReg.getId());
        Assertions.assertNotNull(successUserReg.getToken());
        Assertions.assertEquals(UserId, successUserReg.getId());
        Assertions.assertEquals(UserPassword, successUserReg.getToken());
    }
}
