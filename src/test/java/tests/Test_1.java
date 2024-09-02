package tests;



import api.reqres.specifications.Specifications;
import api.reqres.users.UserData;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static io.restassured.RestAssured.given;
@Epic(value = "reqres.in")
@Feature(value = "Тесты для reqres.in")
@Story(value = "GET api/users")

@Tag("@API")
@TmsLink("RecresTest_1")
@Link(name = "Ссылка на reqres.in", url = "https://reqres.in")
@Owner(value = "Калинина Карина Андреевна")
@Severity(value = SeverityLevel.NORMAL)
@Issue(value = "UT-4627")
@DisplayName("(RecresTest_1) Аватары содержат айди пользователей")
public class Test_1 {
    private static String URL = "https://reqres.in/";


    @Test
    @Description("Проверка, что аватары содержат айди пользователей")
    @Step(value = "Делаем GET api/users?page=2 и прокеряем что аватары содержат айди пользователей")
    public void checkAvatarAndIdTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        List<UserData> users = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        users.forEach(x -> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));


    }
}
