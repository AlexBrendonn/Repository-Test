package com.saa.web.router.held;

import com.saa.web.control.held.CityControl;
import com.saa.web.entity.authentication.AuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/held/city")
public class CityRouter {
    @Context
    HttpServletRequest webRequest;
    CityControl control;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        this.control = new CityControl((AuthenticationToken) webRequest.getSession().getAttribute("authentication"));
        return control.get();
    }
}
