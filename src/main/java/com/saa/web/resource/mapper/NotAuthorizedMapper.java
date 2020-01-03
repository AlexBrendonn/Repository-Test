package com.saa.web.resource.mapper;

import com.saa.web.resource.ResponseBodyBuilder;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NotAuthorizedMapper implements ExceptionMapper<NotAuthorizedException> {
    @Override
    public Response toResponse(NotAuthorizedException e) {
        return new ResponseBodyBuilder(true, e.getMessage()).build(Response.Status.UNAUTHORIZED);
    }
}
