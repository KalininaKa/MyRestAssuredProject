package api.reqres.specifications;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {
    public static RequestSpecification requestSpec(String url){
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                //.addFilters(new AllureRestAssured())
                .build();
    }

    public static ResponseSpecification responseSpecOK200(){
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification responseSpecOK201(){
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(201)
                .build();
    }

    public static ResponseSpecification responseSpecOK204(){
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(204)
                .build();
    }

    public static ResponseSpecification responseSpecERROR400(){
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(400)
                .build();
    }

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

