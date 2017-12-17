package org.perfectnumbers.resource;

import org.perfectnumbers.params.ParamsPair;
import org.perfectnumbers.service.IRestService;
import org.perfectnumbers.service.RestServiceFactory;

import javax.ws.rs.core.Response;


public class PerfectNumberResourceImpl implements PerfectNumberResource {

    private IRestService service;

    @Override
    public Response checkForPerfectPrime(Long number) {
        service = RestServiceFactory.getRestService(RestServiceFactory.ServiceType.CHECK_PERFECT_NUMBER_SERVICE);

        return service.execute(number);
    }

    @Override
    public Response getPerfectNumbersList(Long start, Long end) {
        service = RestServiceFactory.getRestService(RestServiceFactory.ServiceType.GET_PERFECT_NUMBERS_SERVICE);
        ParamsPair pair = new ParamsPair(start, end);

        return service.execute(pair);
    }

}
