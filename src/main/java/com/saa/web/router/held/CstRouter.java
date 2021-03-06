package com.saa.web.router.held;

import com.saa.web.control.held.CstControl;
import com.saa.web.entity.authentication.AuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/held/cst")
public class CstRouter {
    @Context
    HttpServletRequest webRequest;
    CstControl control;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        this.control = new CstControl((AuthenticationToken) webRequest.getSession().getAttribute("authentication"));
        return control.get();
    }
}
