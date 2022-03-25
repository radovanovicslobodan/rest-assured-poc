package tests;

import core.ReqRes;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.RequestUtils.*;
import static utils.ResponseUtils.*;

public class RestTests {

    @Test
    public void verifyCreateAuthToken() {
        Response response = makePostRequest("auth", "auth");
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test
    public void verifyAuthTokenIsNotNull() {
        Response response = makePostRequest("auth", "auth");
        assertTrue(checkResponseValue(response, "token"));
    }

    @Test
    public void verifyCreateBooking() {
        ReqRes reqRes = makePostReqRes("booking", "booking", "booking");
        assertTrue(checkResponse(reqRes));
    }

    @Test
    public void verifyCreateBookingId() {
        ReqRes reqRes = makePostReqRes("booking", "booking");
        assertTrue(checkResponseValue(reqRes.getResponse(), "bookingid"));
    }

    @Test
    public void verifyUpdatingBooking() {
        ReqRes reqRes = makePatchReqRes("booking", "partial-booking", "booking");
        assertTrue(checkResponse(reqRes));
    }

    @Test
    public void verifyCreateBookingWithRandomData() {
        ReqRes reqRes = makePostReqRes("parametrized-booking", "booking", "booking", true);
        assertTrue(checkResponse(reqRes));
    }

    @Test
    public void verifyGetBooking() {
        assertTrue(checkGetResponse("booking",1));
    }
}
