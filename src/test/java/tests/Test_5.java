package tests;


import api.reqres.colors.Colors;
import api.reqres.specifications.Specifications;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;


@Tag("@API")
@DisplayName("(RecresTest_5) Года правильно отсортированы")
public class Test_5 {
    private static String URL = "https://reqres.in/";

    @Test
    public void checkSortedYearsTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
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
