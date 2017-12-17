package org.perfectnumbers.service;

import javax.ws.rs.core.Response;

public interface IRestService<T> {

    Response execute(T params);

}
