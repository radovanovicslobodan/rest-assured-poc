package utils;

import core.ReqRes;
import core.jsonparser.JsonParser;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static core.jsonparser.JsonParser.parseJson;
import static utils.ApiUtils.*;
import static utils.PropsUtils.getPayload;

public class RequestUtils {

    public static Response makePostRequest(String payload, String basePath) {
        String requestPayload = getPayload(payload);
        RequestSpecification requestSpec = createRequestSpec(requestPayload, basePath);
        return sendRequest(requestSpec, Method.POST);
    }

    public static ReqRes makePostReqRes(String payload, String basePath) {
        ReqRes reqRes = new ReqRes();
        String requestPayload = getPayload(payload);
        RequestSpecification requestSpec = createRequestSpec(requestPayload, basePath);
        Response response = sendRequest(requestSpec, Method.POST);
        reqRes.setRequest(requestPayload);
        reqRes.setResponse(response);
        return reqRes;
    }

    public static ReqRes makePostReqRes(String payload, String basePath, String wrap) {
        ReqRes reqRes = new ReqRes();
        String requestPayload = getPayload(payload);
        String wrappedPayload = wrapRequest(wrap, requestPayload);
        RequestSpecification requestSpec = createRequestSpec(requestPayload, basePath);
        Response response = sendRequest(requestSpec, Method.POST);
        reqRes.setRequest(wrappedPayload);
        reqRes.setResponse(response);
        return reqRes;
    }

    public static ReqRes makePostReqRes(String payload, String basePath, String wrap, Boolean parse) {
        ReqRes reqRes = new ReqRes();
        String requestPayload = getPayload(payload);
        if (parse) {
            requestPayload = parseJson(requestPayload);
        }
        String wrappedPayload = wrapRequest(wrap, requestPayload);
        RequestSpecification requestSpec = createRequestSpec(requestPayload, basePath);
        Response response = sendRequest(requestSpec, Method.POST);
        reqRes.setRequest(wrappedPayload);
        reqRes.setResponse(response);
        return reqRes;
    }

    public static ReqRes makePatchReqRes(String postPayload, String putPayload, String basePath) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("https://restful-booker.herokuapp.com");

        ReqRes reqRes = new ReqRes();
        Response postResponse = makePostRequest(postPayload, basePath);
        int id = postResponse.path("bookingid");
        String payloadContent = getPayload(putPayload);

        Response putResponse = RestAssured.given(builder.build())
                .auth().preemptive().basic("admin", "password123")
                .contentType(ContentType.JSON)
                .body(payloadContent)
                .patch(basePath + "/" + id);

        reqRes.setRequest(payloadContent);
        reqRes.setResponse(putResponse);
        return reqRes;
    }

    public static Response makeGetRequest(String basePath, int id) {
        RequestSpecification requestSpec = createGetRequestSpec(basePath, id);
        return ApiUtils.sendRequest(requestSpec, Method.GET);
    }
}
