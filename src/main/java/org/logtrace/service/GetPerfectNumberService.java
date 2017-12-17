package org.logtrace.service;

import org.logtrace.exception.NumberRangeException;
import org.logtrace.math.PerfectNumbers;
import org.logtrace.pojo.ErrorMessage;
import org.logtrace.pojo.PerfectNumbersList;
import org.logtrace.pojo.StartEndParamsPair;

import javax.ws.rs.core.Response;
import java.util.List;

public class GetPerfectNumberService implements IRestService<StartEndParamsPair> {

    @Override
    public Response execute(StartEndParamsPair startEndParamsPair) {
        Response response = null;
        try {
            List<Long> list = PerfectNumbers.getPerfectNumbersBetween(startEndParamsPair.getStart(), startEndParamsPair.getEnd());
            PerfectNumbersList perfectNumbersList = new PerfectNumbersList();
            perfectNumbersList.setPerfectNumbers(list);

            response = Response.status(Response.Status.OK).entity(perfectNumbersList).build();
        } catch (NumberRangeException e) {
            ErrorMessage error = new ErrorMessage(e.getMessage(), Response.Status.BAD_REQUEST.getStatusCode());

            response = Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }

        return response;
    }
}
