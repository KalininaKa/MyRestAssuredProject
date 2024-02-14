package tests;


import api.reqres.users.Create;
import api.reqres.specifications.Specifications;
import api.reqres.users.SuccessUserCreated;
import org.junit.jupiter.api.*;

import java.util.Date;

import static io.restassured.RestAssured.given;

@Tags({
        @Tag("@API"),
        @Tag("@POST"),
        @Tag("@Recres")
})
@DisplayName("(RecresTest_10) Успешное создание юзера POST /api/users")
public class Test_10 {
    private static String URL = "https://reqres.in/";
    private static String Job = "leader";
    private static String Name = "morpheus";
    @Test
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
        String regex = "(.{11})$";
        Assertions.assertEquals(new Date().toString().replaceAll(regex,""), successUserCreate.getCreatedAt().toString().replaceAll(regex,""));
    }
}
