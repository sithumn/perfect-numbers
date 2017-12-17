package org.perfectnumbers.service;

import org.perfectnumbers.exception.NumberRangeException;
import org.perfectnumbers.util.PerfectNumbers;
import org.perfectnumbers.domain.ErrorMessage;
import org.perfectnumbers.domain.PerfectNumbersList;
import org.perfectnumbers.params.ParamsPair;

import javax.ws.rs.core.Response;
import java.util.List;

public class GetPerfectNumberService implements IRestService<ParamsPair> {

    @Override
    public Response execute(ParamsPair paramsPair) {
        Response response = null;
        try {
            List<Long> list = PerfectNumbers.getPerfectNumbersBetween(paramsPair.getStart(), paramsPair.getEnd());
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
