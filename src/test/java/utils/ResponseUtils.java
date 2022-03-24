package utils;

import core.ReqRes;
import io.restassured.response.Response;
import core.jsoncompare.CustomJSONCompare;

public class ResponseUtils {
    public static boolean checkResponse(String request, Response response) {
        return CustomJSONCompare.assertEquals(request, response.asString());
    }

    public static boolean checkResponse(ReqRes reqRes) {
        return CustomJSONCompare.assertEquals(reqRes.getRequest(), reqRes.getResponse().asString());
    }

    public static boolean checkResponseValue(Response response, String key) {
        return response.path(key) != null;
    }

    public static boolean checkResponseValue(Response response, String key, String value) {
        return response.path(key).equals(value);
    }

    public static boolean checkResponseValue(Response response, String key, int value) {
        return response.path(key).equals(String.valueOf(value));
    }
}
