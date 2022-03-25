package core.utils;

import io.restassured.response.Response;

import static core.utils.RequestUtils.makePostRequest;

public class AuthUtils {

    public static String getToken() {
        Response response = makePostRequest("auth", "auth");
        return response.path("token");
    }
}
