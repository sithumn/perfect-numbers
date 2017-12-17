package org.perfectnumbers.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.junit.Test;
import org.perfectnumbers.domain.ErrorMessage;
import org.perfectnumbers.domain.PerfectNumbersList;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetPerfectNumbersIntegrationTest extends BaseIntegrationTest {

    @Test
    public void testGetPerfectNumberService_Success() throws IOException {
        HttpGet get = new HttpGet(BASE_URL + "?start=0&end=1000");

        HttpResponse response = client.execute(get);

        ObjectMapper mapper = new ObjectMapper();
        PerfectNumbersList list = mapper.readValue(response.getEntity().getContent(), PerfectNumbersList.class);

        assertEquals(200, response.getStatusLine().getStatusCode());
        assertEquals(3, list.getPerfectNumbers().size());
        assertEquals(Long.valueOf(6), list.getPerfectNumbers().get(0));

    }

    @Test
    public void testGetPerfectNumberService_Error_MissingParam() throws IOException {
        HttpGet get = new HttpGet(BASE_URL + "?start=0");

        HttpResponse response = client.execute(get);

        ObjectMapper mapper = new ObjectMapper();
        ErrorMessage message = mapper.readValue(response.getEntity().getContent(), ErrorMessage.class);

        assertEquals(400, response.getStatusLine().getStatusCode());
        assertTrue(message.getMessage().contains("Missing required parameters"));
    }

    @Test
    public void testGetPerfectNumberService_Error_InvalidParam() throws IOException {
        HttpGet get = new HttpGet(BASE_URL + "?start=0&end=test");

        HttpResponse response = client.execute(get);

        ObjectMapper mapper = new ObjectMapper();
        ErrorMessage message = mapper.readValue(response.getEntity().getContent(), ErrorMessage.class);

        assertEquals(400, response.getStatusLine().getStatusCode());
        assertTrue(message.getMessage().contains("Invalid parameters"));
    }
}
