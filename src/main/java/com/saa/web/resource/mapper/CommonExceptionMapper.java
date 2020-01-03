package com.saa.web.resource.mapper;

import com.saa.web.resource.ResponseBodyBuilder;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.lang.Exception;

public class CommonExceptionMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {
        return new ResponseBodyBuilder(true, e.getMessage()).build(Response.Status.BAD_REQUEST);
    }
}
