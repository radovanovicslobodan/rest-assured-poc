package core;

import io.restassured.response.Response;

public class ReqRes {

    String request;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    Response response;
    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
