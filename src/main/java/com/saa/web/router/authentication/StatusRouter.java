package com.saa.web.router.authentication;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/status")
public class StatusRouter {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return "Server is running!";
    }
}
