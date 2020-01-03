package com.saa.web.resource.mapper;

import com.saa.web.resource.ResponseBodyBuilder;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class BadRequestMapper implements ExceptionMapper<BadRequestException> {
    @Override
    public Response toResponse(BadRequestException e) {
        return new ResponseBodyBuilder(true, e.getMessage()).build(Response.Status.BAD_REQUEST);
    }
}
