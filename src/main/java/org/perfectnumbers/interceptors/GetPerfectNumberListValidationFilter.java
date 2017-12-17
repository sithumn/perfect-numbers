package org.perfectnumbers.interceptors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.perfectnumbers.domain.ErrorMessage;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(value = 1)
public class GetPerfectNumberListValidationFilter implements ContainerRequestFilter {

    private static final Logger logger = LogManager.getLogger(GetPerfectNumberListValidationFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) {
        MultivaluedMap<String, String> queryParams = requestContext.getUriInfo().getQueryParameters();

        if (queryParams.size() > 0) {

            String start = queryParams.getFirst("start");
            String end = queryParams.getFirst("end");
            String pattern = "[\\d]*";

            logger.debug("Received query param start: {}", start);
            logger.debug("Received query param end: {}", end);

            Response response = null;
            Response.ResponseBuilder responseBuilder = null;

            if (start == null || "".equals(start) || end == null || "".equals(end)) {
                ErrorMessage errorMessage = new ErrorMessage("Missing required parameters [start, end]", Response.Status.BAD_REQUEST.getStatusCode());

                responseBuilder = Response.status(Response.Status.BAD_REQUEST);
                responseBuilder = responseBuilder.type(MediaType.APPLICATION_JSON);
                response = responseBuilder.entity(errorMessage).build();

                logger.warn("Query param validation failed. Missing required parameters. start: {}, end: {}", start, end);

                requestContext.abortWith(response);
            } else if(!start.matches(pattern) || !end.matches(pattern)) {
                ErrorMessage errorMessage = new ErrorMessage("Invalid parameters [start, end]. Required numbers.", Response.Status.BAD_REQUEST.getStatusCode());

                responseBuilder = Response.status(Response.Status.BAD_REQUEST);
                responseBuilder = responseBuilder.type(MediaType.APPLICATION_JSON);
                response = responseBuilder.entity(errorMessage).build();

                logger.warn("Query param validation failed. Invalid parameters. start: {}, end: {}", start, end);

                requestContext.abortWith(response);
            } else {
                logger.info("Query param validation passed for start: {}, end: {}", start, end);
            }
        }

    }
}
