package api.tests.reqres;


import api.tests.reqres.login.Login;
import api.tests.reqres.login.UnsuccessfulLogin;
import api.tests.reqres.specifications.Specifications;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Java6Assertions.assertThat;


public class RecresTest_15 {
    private static String URL = "https://reqres.in/";

    @Test
    @Tag("@RecresTest_15")
    @DisplayName("(RecresTest_15) LOGIN-UNSUCCESSFUL (отсутствует пароль)")
    public void LoginUnSuccessfulTest() {
        String Email = "peter@klaven";
        String ErrorText = "Missing password";
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecERROR400());
        Login request = new Login(Email);
        UnsuccessfulLogin loginUnSuccessful = given()
                .body(request)
                .when()
                .post("api/login")
                .then()
                .log().all()
                .extract().as(UnsuccessfulLogin.class);
        assertThat(loginUnSuccessful.getError())
                .isEqualTo(ErrorText);
    }
}
