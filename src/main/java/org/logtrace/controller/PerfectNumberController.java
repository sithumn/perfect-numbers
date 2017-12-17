package org.logtrace.controller;

import org.logtrace.pojo.StartEndParamsPair;
import org.logtrace.service.IRestService;
import org.logtrace.service.RestServiceFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;


public class PerfectNumberController implements PerfectNumberControllerInterface {

    private IRestService service;

    @GET
    @Path("check/{number}")
    public Response checkForPerfectPrime(@PathParam("number")Long number) {
        service = RestServiceFactory.getRestService(RestServiceFactory.ServiceType.CHECK_PERFECT_NUMBER_SERVICE);

        return service.execute(number);
    }

    @GET
    @Path("list")
    public Response getPerfectNumbersList(@QueryParam("start")Long start, @QueryParam("end")Long end) {
        service = RestServiceFactory.getRestService(RestServiceFactory.ServiceType.GET_PERFECT_NUMBERS_SERVICE);
        StartEndParamsPair pair = new StartEndParamsPair(start, end);

        return service.execute(pair);
    }

}
