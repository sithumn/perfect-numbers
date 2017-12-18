package org.perfectnumbers.resource;

import javax.ws.rs.core.Response;

public class HealthResourceImpl implements HealthResource {

    @Override
    public Response getHealth() {
        return Response.status(Response.Status.OK).build();
    }

}
