package core.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import core.ReqRes;
import io.restassured.response.Response;
import core.jsoncompare.CustomJSONCompare;

import java.util.HashMap;
import java.util.Map;

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

    public static boolean checkGetResponse(String payload, int id){
        Gson gson = new Gson();
        String payloadToCompare = PropsUtils.getPayload(payload);
        Response getResponse = RequestUtils.makeGetRequest("booking", id);

        Map<String, Object> payloadToCompareMap = gson
                .fromJson(payloadToCompare, new TypeToken<HashMap<String, Object>>() {
                }.getType());

        Map<String, Object> getResponseMap = gson
                .fromJson(getResponse.body().asString(), new TypeToken<HashMap<String, Object>>() {
                }.getType());

        return payloadToCompareMap.keySet().equals(getResponseMap.keySet());
    }
}
