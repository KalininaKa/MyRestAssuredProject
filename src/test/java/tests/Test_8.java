package tests;


import api.reqres.colors.Colors;
import api.reqres.specifications.Specifications;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

@Epic(value = "reqres.in")
@Feature(value = "Тесты для reqres.in")
@Story(value = "GET api/unknown")

@Tag("@API")
@TmsLink("RecresTest_8")
@Link(name = "Ссылка на reqres.in", url = "https://reqres.in")
@Owner(value = "Калинина Карина Андреевна")
@Severity(value = SeverityLevel.NORMAL)
@DisplayName("(RecresTest_8) GET /api/unknown/{id}")
public class Test_8 {

    private static Integer Id = 2;
    private static String Name = "fuchsia rose";
    private static Integer Year = 2001;
    private static String Color = "#C74375";
    private static String PantoneValue = "17-2031";

    @Test
    @Description("Проверка GET /api/unknown/{id}")
    @Step(value = "Делаем GET /api/unknown/{id} и проверяем что получили")

    public void userFoundColorsTest() {
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK200());
        Colors colors = given()
                .when()
                .get("/api/unknown/" + Id)
                .then().log().all()
                .extract().body().jsonPath().getObject("data", Colors.class);

        Assertions.assertNotNull(colors.getId());
        Assertions.assertEquals(Id, colors.getId());

        Assertions.assertNotNull(colors.getName());
        Assertions.assertEquals(Name, colors.getName());

        Assertions.assertNotNull(colors.getYear());
        Assertions.assertEquals(Year, colors.getYear());

        Assertions.assertNotNull(colors.getColor());
        Assertions.assertEquals(Color, colors.getColor());

        Assertions.assertNotNull(colors.getPantone_value());
        Assertions.assertEquals(PantoneValue, colors.getPantone_value());
    }
}
