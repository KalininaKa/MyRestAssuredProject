package tests;


import api.reqres.specifications.Specifications;
import api.reqres.users.UserUpd;
import api.reqres.users.UserUpdResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Clock;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Test_11 {
    private static String URL = "https://reqres.in/";
    private static Integer UserId = 2;
    private static String Job = "zion resident";
    private static String Name = "morpheus";

    @Test
    @Tag("@RecresTest_11")
    @DisplayName("(RecresTest_11) Изменение существующего юзера PUT api/users/{id}")
    public void updatedUserTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        UserUpd newUser = new UserUpd(Name, Job);
        UserUpdResponse newUserResponse = given()
                .body(newUser)
                .when()
                .put("api/users/" + UserId)
                .then()
                .log().all()
                .extract().as(UserUpdResponse.class);

        assertThat(newUserResponse.getName())
                .isEqualTo(Name);
        assertThat(newUserResponse.getJob())
                .isEqualTo(Job);
        String regex = "(.{5})$";
        String regex_2 = "(.{8})$";
        assertEquals(newUserResponse.getUpdatedAt().toString().replaceAll(regex,""),Clock.systemUTC().instant().toString().replaceAll(regex_2,""));
    }
}
