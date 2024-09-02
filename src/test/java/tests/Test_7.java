package tests;


import api.reqres.specifications.Specifications;
import api.reqres.users.UserData;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
@Epic(value = "reqres.in")
@Feature(value = "Тесты для reqres.in")
@Story(value = "GET api/users")

@Tag("@API")
@TmsLink("RecresTest_7")
@Link(name = "Ссылка на reqres.in", url = "https://reqres.in")
@Owner(value = "Калинина Карина Андреевна")
@Severity(value = SeverityLevel.NORMAL)
@DisplayName("(RecresTest_7) Получение существующего юзера")
public class Test_7 {
    private static String URL = "https://reqres.in/";
    private static Integer UserId = 2;
    private static String FirstName = "Janet";
    private static String LastName = "Weaver";
    private static String Email = "janet.weaver@reqres.in";
    private static String Avatar = "https://reqres.in/img/faces/2-image.jpg";

    @Test
    @Description("Проверка что пполучаем существующего юзера")
    @Step(value = "Делаем GET api/users/{id} и проверяем что получили существующего юзера")

    public void singleUserFoundTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        UserData user = given()
                .when()
                .get("api/users/" + UserId)
                .then().log().all()
                .extract().body().jsonPath().getObject("data", UserData.class);


        Assertions.assertNotNull(user.getId());
        Assertions.assertEquals(UserId, user.getId());

        Assertions.assertNotNull(user.getFirst_name());
        Assertions.assertEquals(FirstName, user.getFirst_name());

        Assertions.assertNotNull(user.getLast_name());
        Assertions.assertEquals(LastName, user.getLast_name());

        Assertions.assertNotNull(user.getAvatar());
        Assertions.assertEquals(Avatar, user.getAvatar());

        Assertions.assertNotNull(user.getEmail());
        Assertions.assertEquals(Email, user.getEmail());
        Assertions.assertEquals((FirstName + "." + LastName + "@reqres.in").toLowerCase(), user.getEmail());
    }
}
