package org.logtrace.service;

import javax.ws.rs.core.Response;

public interface IRestService<Params> {

    Response execute(Params params);

}
