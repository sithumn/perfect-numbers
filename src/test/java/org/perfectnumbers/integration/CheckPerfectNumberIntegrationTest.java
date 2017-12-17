package org.perfectnumbers.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.junit.Test;
import org.perfectnumbers.domain.ErrorMessage;
import org.perfectnumbers.domain.PerfectNumberStatus;

import java.io.IOException;

import static org.junit.Assert.*;

public class CheckPerfectNumberIntegrationTest extends BaseIntegrationTest {

    @Test
    public void testPerfectNumberCheckService_IsPerfect() throws IOException {
        HttpGet get = new HttpGet(BASE_URL + "6");

        HttpResponse response = client.execute(get);

        ObjectMapper mapper = new ObjectMapper();
        PerfectNumberStatus status = mapper.readValue(response.getEntity().getContent(), PerfectNumberStatus.class);

        assertEquals(200, response.getStatusLine().getStatusCode());
        assertTrue(status.isValid());

    }

    @Test
    public void testPerfectNumberCheckService_IsNotPerfect() throws IOException {
        HttpGet get = new HttpGet(BASE_URL + "8");

        HttpResponse response = client.execute(get);

        ObjectMapper mapper = new ObjectMapper();
        PerfectNumberStatus status = mapper.readValue(response.getEntity().getContent(), PerfectNumberStatus.class);

        assertEquals(200, response.getStatusLine().getStatusCode());
        assertFalse(status.isValid());
    }

    @Test
    public void testPerfectNumberCheckService_Failed() throws IOException {
        HttpGet get = new HttpGet(BASE_URL + "tst");

        HttpResponse response = client.execute(get);

        ObjectMapper mapper = new ObjectMapper();
        ErrorMessage message = mapper.readValue(response.getEntity().getContent(), ErrorMessage.class);

        assertEquals(400, response.getStatusLine().getStatusCode());
        assertEquals(400, message.getStatus());
    }

}
