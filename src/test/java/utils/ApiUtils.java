package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    public static Response sendRequest(RequestSpecification requestSpec, Method requestMethod) {

        Response response;

        switch (requestMethod) {
            case GET:
                response = given().spec(requestSpec).log().all().relaxedHTTPSValidation().when()
                        .get().then().log().all().extract().response();
                break;

            case POST:
                response = given().spec(requestSpec).log().all().relaxedHTTPSValidation().when()
                        .post().then().log().all().extract().response();
                break;

            case PUT:
                response = given().spec(requestSpec).log().all().relaxedHTTPSValidation().when()
                        .put().then().log().all().extract().response();
                break;

            case PATCH:
                response = given().spec(requestSpec).log().all().relaxedHTTPSValidation().when()
                        .patch().then().log().all().extract().response();
                break;

            case DELETE:
                response = given().spec(requestSpec).log().all().relaxedHTTPSValidation().when()
                        .delete().then().log().all().extract().response();
                break;

            default:
                throw new IllegalStateException(
                        "Unexpected value: " + requestMethod);

        }
        return response;
    }

    public static RequestSpecification createRequestSpec(String payload, String basePath) {
        RequestSpecBuilder builder = new RequestSpecBuilder();

        builder.setBaseUri("https://restful-booker.herokuapp.com");
        builder.setBasePath(basePath);
        builder.setContentType(ContentType.JSON);
        builder.setBody(payload);
        return builder.build();
    }

    public static RequestSpecification createGetRequestSpec(String basePath,int id) {
        RequestSpecBuilder builder = new RequestSpecBuilder();

        builder.setBaseUri("https://restful-booker.herokuapp.com");
        builder.setBasePath(basePath+"/"+id);
        builder.setContentType(ContentType.JSON);
        return builder.build();
    }


    public static String wrapRequest(String innerKey, String request) {
        String newValue = String.format("{\"%s\":%s}", innerKey, request);
        return newValue;
    }
}

