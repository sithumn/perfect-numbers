package org.perfectnumbers.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("perfectnumbers")
@Produces(MediaType.APPLICATION_JSON)
public interface PerfectNumberResource {

    @GET
    @Path("{number}")
    Response checkForPerfectPrime(@PathParam("number")Long number);

    @GET
    @Path("")
    Response getPerfectNumbersList(@QueryParam("start")Long start, @QueryParam("end")Long end);

}
