package tests;


import api.reqres.users.Create;
import api.reqres.specifications.Specifications;
import api.reqres.users.SuccessUserCreated;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import java.util.Date;

import static io.restassured.RestAssured.given;
@Epic(value = "reqres.in")
@Feature(value = "Тесты для reqres.in")
@Story(value = "POST api/users")

@Tag("@API")
@TmsLink("RecresTest_10")
@Link(name = "Ссылка на reqres.in", url = "https://reqres.in")
@Owner(value = "Калинина Карина Андреевна")
@Severity(value = SeverityLevel.CRITICAL)
@DisplayName("(RecresTest_10) Успешное создание юзера POST /api/users")
public class Test_10 {
    private static String Job = "leader";
    private static String Name = "morpheus";
    @Test
    @Description("Проверка POST /api/users")
    @Step(value = "Делаем POST /api/users и проверяем что юзер успешно создан")

    public void successUserCreateTest() {


        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK201());
        Create user = new Create(Name, Job);
        SuccessUserCreated successUserCreate = given()
                .body(user)
                .when()
                .post("/api/users")
                .then()
                .log().all()
                .extract().as(SuccessUserCreated.class);
        Assertions.assertNotNull(successUserCreate.getName());
        Assertions.assertNotNull(successUserCreate.getJob());
        Assertions.assertNotNull(successUserCreate.getId());
        Assertions.assertNotNull(successUserCreate.getCreatedAt());
        Assertions.assertTrue(successUserCreate.getId().matches("\\d+"));
        Assertions.assertEquals(Name, successUserCreate.getName());
        Assertions.assertEquals(Job, successUserCreate.getJob());
        String regex = "(.{11})$";
        Assertions.assertEquals(new Date().toString().replaceAll(regex,""), successUserCreate.getCreatedAt().toString().replaceAll(regex,""));
    }
}
