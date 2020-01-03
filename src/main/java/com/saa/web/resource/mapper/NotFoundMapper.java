package com.saa.web.resource.mapper;

import com.saa.web.resource.ResponseBodyBuilder;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NotFoundMapper implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException e) {
        return new ResponseBodyBuilder(true, e.getMessage()).build(Response.Status.NOT_FOUND);
    }
}
