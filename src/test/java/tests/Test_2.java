package tests;


import api.reqres.registration.Register;
import api.reqres.registration.SuccessUserReg;
import api.reqres.specifications.Specifications;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class Test_2 {
    private static String URL = "https://reqres.in/";

    @Test
    @Tag("@API")
    @Tag("@POST")
    @Tag("@Recres")
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
       assertNotNull(successUserReg.getId());
       assertNotNull(successUserReg.getToken());
       assertEquals(UserId, successUserReg.getId());
       assertEquals(UserPassword, successUserReg.getToken());
    }
}
