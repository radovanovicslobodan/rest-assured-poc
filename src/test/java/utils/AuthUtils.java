package utils;

import io.restassured.response.Response;

import static utils.RequestUtils.makePostRequest;

public class AuthUtils {

    public static String getToken() {
        Response response = makePostRequest("auth", "auth");
        return response.path("token");
    }
}
