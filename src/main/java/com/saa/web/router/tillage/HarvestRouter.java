package com.saa.web.router.tillage;

import com.saa.web.control.tillage.HarvestControl;
import com.saa.web.entity.authentication.AuthenticationToken;
import com.saa.web.enumerated.ESchema;
import com.saa.web.utils.JSONSchema;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tillage/harvest")
public class HarvestRouter {
    @Context
    HttpServletRequest webRequest;
    HarvestControl control;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response GET() {
        this.control = new HarvestControl((AuthenticationToken) webRequest.getSession().getAttribute("authentication"));
        return control.list();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response PUT(String content){
        this.control = new HarvestControl((AuthenticationToken) webRequest.getSession().getAttribute("authentication"));
        JSONObject body = new JSONSchema().valid(ESchema.HARVEST, content);
        return control.save(body);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response PUT(@HeaderParam("id") @DefaultValue("0") Long id) {
        this.control = new HarvestControl((AuthenticationToken) webRequest.getSession().getAttribute("authentication"));
        return control.delete(id);
    }
}
