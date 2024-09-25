package tests;


import api.reqres.registration.Register;
import api.reqres.registration.UnsuccessUserReg;
import api.reqres.specifications.Specifications;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@Epic(value = "reqres.in")
@Feature(value = "Тесты для reqres.in")
@Story(value = "POST api/register")

@Tag("@API")
@TmsLink("RecresTest_3")
@Link(name = "Ссылка на reqres.in", url = "https://reqres.in")
@Owner(value = "Калинина Карина Андреевна")
@Severity(value = SeverityLevel.NORMAL)
@DisplayName("(RecresTest_3) Не успешная регистрация (отсутствует пароль)")
public class Test_3 {
    private static String URL = "https://reqres.in/";

    @Test
    @Description("Проверка, что не успешная регистрация (отсутствует пароль)")
    @Step(value = "Не успешная регистрация пользователя, проверка, что получен ErrorText = \"Missing password\" ")
    public void unSuccessUserRegTest() {
        String ErrorText = "Missing password";
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecERROR400());
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
