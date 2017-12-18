package org.perfectnumbers.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

public interface HealthResource {

    @GET
    @Path("health")
    Response getHealth();

}
