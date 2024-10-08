package api.reqres.specifications;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {
    @Step("Url - {0}")
    public static RequestSpecification requestSpec(String url){
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                //.addFilters(new AllureRestAssured())
                .build();
    }
    @Step("Проверяем, что статус 200 ОК")
    public static ResponseSpecification responseSpecOK200(){
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(200)
                .build();
    }
    @Step("Проверяем, что статус 201 ОК")
    public static ResponseSpecification responseSpecOK201(){
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(201)
                .build();
    }
    @Step("Проверяем, что статус 204 ОК")
    public static ResponseSpecification responseSpecOK204(){
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(204)
                .build();
    }
    @Step("Проверяем, что статус 400 ERROR")
    public static ResponseSpecification responseSpecERROR400(){
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(400)
                .build();
    }
    @Step("Проверяем, что статус 404 NOT_FOUND")
    public static ResponseSpecification responseSpecNOTFOUND404(){
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(404)
                .build();
    }

    public static void installSpecification (RequestSpecification request, ResponseSpecification response){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
}

