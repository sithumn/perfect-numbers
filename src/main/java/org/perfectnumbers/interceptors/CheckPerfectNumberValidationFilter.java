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
public class CheckPerfectNumberValidationFilter implements ContainerRequestFilter {

    private static final Logger logger = LogManager.getLogger(CheckPerfectNumberValidationFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) {
        MultivaluedMap<String, String> pathParameters = requestContext.getUriInfo().getPathParameters();

        if (pathParameters.size() > 0) {

            String number = pathParameters.getFirst("number");
            String pattern = "[\\d]*";

            logger.debug("Received path param number: {}", number);

            Response response = null;
            Response.ResponseBuilder responseBuilder = null;

            if (number == null || "".equals(number)) {
                ErrorMessage errorMessage = new ErrorMessage("Missing required path param [number]", Response.Status.BAD_REQUEST.getStatusCode());

                responseBuilder = Response.status(Response.Status.BAD_REQUEST);
                responseBuilder = responseBuilder.type(MediaType.APPLICATION_JSON);
                response = responseBuilder.entity(errorMessage).build();

                logger.warn("Path param validation failed. Missing required parameters. number: {}", number);

                requestContext.abortWith(response);
            } else if(!number.matches(pattern)) {
                ErrorMessage errorMessage = new ErrorMessage("Invalid parameters [number]. Required a number.", Response.Status.BAD_REQUEST.getStatusCode());

                responseBuilder = Response.status(Response.Status.BAD_REQUEST);
                responseBuilder = responseBuilder.type(MediaType.APPLICATION_JSON);
                response = responseBuilder.entity(errorMessage).build();

                logger.warn("Path param validation failed. Invalid parameters. number: ", number);

                requestContext.abortWith(response);
            } else {
                logger.info("Path param validation passed for number: {}", number);
            }
        }

    }

}
