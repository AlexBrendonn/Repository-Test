package com.saa.web.resource.mapper;

import com.saa.web.resource.ResponseBodyBuilder;

import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class PersistenceMapper implements ExceptionMapper<PersistenceException> {
    @Override
    public Response toResponse(PersistenceException e) {
        return new ResponseBodyBuilder(true, e.getMessage()).build(Response.Status.INTERNAL_SERVER_ERROR);
    }
}
