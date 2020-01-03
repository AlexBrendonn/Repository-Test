package com.saa.web.router;

import com.saa.web.control.authentication.AuthenticationControl;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authentication")
public class AuthenticationRouter {
    @Context
    HttpServletRequest webRequest;

    AuthenticationControl control;

    public AuthenticationRouter() {
        this.control = new AuthenticationControl();
    }

    @Path("/signIn")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signIn(final String content) {
        JSONObject json = new JSONObject(content);
        return control.signIn(json);
    }

    @Path("/register")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(final String content) {
        JSONObject json = new JSONObject(content);
        return control.register(json);
    }

    @Path("/session")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response session(final @HeaderParam("Authorization") String token) {
        return control.session(token);
    }
}
