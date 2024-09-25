package tests;


import api.reqres.colors.Colors;
import api.reqres.specifications.Specifications;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
@Epic(value = "reqres.in")
@Feature(value = "Тесты для reqres.in")
@Story(value = "GET api/unknown")

@Tag("@API")
@TmsLink("RecresTest_5")
@Link(name = "Ссылка на reqres.in", url = "https://reqres.in")
@Owner(value = "Калинина Карина Андреевна")
@Severity(value = SeverityLevel.MINOR)
@DisplayName("(RecresTest_5) Года правильно отсортированы")
public class Test_5 {

    @Test
    @Description("Проверка сортировки по годам")
    @Step(value = "Делаем GET /api/unknown и проверяем что записи отсортированы по годам")

    public void checkSortedYearsTest(){
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK200());
        List<Colors> data = given()
                .when()
                .get("/api/unknown")
                .then()
                .log().all()
                .extract().body().jsonPath().getList("data", Colors.class);

        List<Integer> dataYears = data.stream().map(Colors::getYear).collect(Collectors.toList());
        List<Integer> sortedDataYears = dataYears.stream().sorted().collect(Collectors.toList());
        Assertions.assertEquals(dataYears, sortedDataYears);
        System.out.println(dataYears);
        System.out.println(sortedDataYears);
    }
}
