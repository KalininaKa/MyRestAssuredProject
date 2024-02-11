package api.tests.reqres;


import api.tests.reqres.users.Create;
import api.tests.reqres.specifications.Specifications;
import api.tests.reqres.users.SuccessUserCreated;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static io.restassured.RestAssured.given;


public class RecresTest_10 {
    private static String URL = "https://reqres.in/";
    private static String Job = "leader";
    private static String Name = "morpheus";
    @Test
    @Tag("@RecresTest_10")
    @DisplayName("(RecresTest_10) Успешное создание юзера POST /api/users")
    public void successUserCreateTest() {


        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK201());
        Create user = new Create(Name, Job);
        SuccessUserCreated successUserCreate = given()
                .body(user)
                .when()
                .post("api/users")
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
        String regex = "(.{5})$";
        Assertions.assertEquals(new Date().toString().replaceAll(regex,""), successUserCreate.getCreatedAt().toString().replaceAll(regex,""));
    }
}
