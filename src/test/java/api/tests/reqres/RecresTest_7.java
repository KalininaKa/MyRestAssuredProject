package api.tests.reqres;


import api.tests.reqres.specifications.Specifications;
import api.tests.reqres.users.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class RecresTest_7 {
    private static String URL = "https://reqres.in/";
    private static Integer UserId = 2;
    private static String FirstName = "Janet";
    private static String LastName = "Weaver";
    private static String Email = "janet.weaver@reqres.in";
    private static String Avatar = "https://reqres.in/img/faces/2-image.jpg";

    @Test
    @Tag("@RecresTest_7")
    @DisplayName("(RecresTest_7) Получение существующего юзера")
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
