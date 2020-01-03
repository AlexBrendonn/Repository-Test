package com.saa.web.resource.mapper;

import com.saa.web.resource.ResponseBodyBuilder;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InternalServerErrorMapper implements ExceptionMapper<InternalServerErrorException> {
    @Override
    public Response toResponse(InternalServerErrorException e) {
        return new ResponseBodyBuilder(true, e.getMessage()).build(Response.Status.INTERNAL_SERVER_ERROR);
    }
}
