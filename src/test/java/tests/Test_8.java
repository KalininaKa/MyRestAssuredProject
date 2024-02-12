package tests;


import api.reqres.colors.Colors;
import api.reqres.specifications.Specifications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class Test_8 {
    private static String URL = "https://reqres.in/";
    private static Integer Id = 2;
    private static String Name = "fuchsia rose";
    private static Integer Year = 2001;
    private static String Color = "#C74375";
    private static String PantoneValue = "17-2031";

    @Test
    @Tag("@RecresTest_8")
    @DisplayName("(RecresTest_8) GET /api/unknown/{id}")
    public void userFoundColorsTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Colors colors = given()
                .when()
                .get("api/unknown/" + Id)
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
