package api.tests.reqres;


import api.tests.reqres.specifications.Specifications;
import api.tests.reqres.users.UserUpd;
import api.tests.reqres.users.UserUpdResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Clock;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Java6Assertions.assertThat;


public class RecresTest_12 {
    private static String URL = "https://reqres.in/";
    private static Integer UserId = 2;
    private static String Job = "zion resident";
    private static String Name = "morpheus";

    @Test
    @Tag("@RecresTest_12")
    @DisplayName("(RecresTest_12) Patch существующего юзера PATCH api/users/{id}")
    public void patchUserTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        UserUpd newUser = new UserUpd(Name, Job);
        UserUpdResponse newUserResponse = given()
                .body(newUser)
                .when()
                .patch("api/users/" + UserId)
                .then()
                .log().all()
                .extract().as(UserUpdResponse.class);

        assertThat(newUserResponse.getName())
                .isEqualTo(Name);
        assertThat(newUserResponse.getJob())
                .isEqualTo(Job);
        String regex = "(.{7})$";
        assertThat(newUserResponse.getUpdatedAt().toString().replaceAll(regex,""))
                .isEqualTo(Clock.systemUTC().instant().toString().replaceAll(regex,""));
    }
}
