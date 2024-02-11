package api.tests.reqres;



import api.tests.reqres.specifications.Specifications;
import api.tests.reqres.users.UserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static io.restassured.RestAssured.given;


public class RecresTest_1 {
    private static String URL = "https://reqres.in/";

    @Test
    @Tag("@RecresTest_1")
    @DisplayName("(RecresTest_1) Аватары содержат айди пользователей")
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
