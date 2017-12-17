package org.logtrace.service;

import org.logtrace.math.PerfectNumbers;
import org.logtrace.pojo.CheckPerfectNumber;

import javax.ws.rs.core.Response;

public class CheckPerfectNumberService implements IRestService<Long> {

    @Override
    public Response execute(Long number) {
        boolean status = PerfectNumbers.isPerfectNumber(number);
        CheckPerfectNumber perfectNumberCheck = new CheckPerfectNumber(number, status);

        return Response.status(Response.Status.OK).entity(perfectNumberCheck).build();
    }
}
