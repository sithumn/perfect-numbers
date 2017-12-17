package org.perfectnumbers.service;

import org.perfectnumbers.util.PerfectNumbers;
import org.perfectnumbers.domain.PerfectNumberStatus;

import javax.ws.rs.core.Response;

public class CheckPerfectNumberService implements IRestService<Long> {

    @Override
    public Response execute(Long number) {
        boolean status = PerfectNumbers.isPerfectNumber(number);
        PerfectNumberStatus numberStatus = new PerfectNumberStatus(number, status);

        return Response.status(Response.Status.OK).entity(numberStatus).build();
    }
}
