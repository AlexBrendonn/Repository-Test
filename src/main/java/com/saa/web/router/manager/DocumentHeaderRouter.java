package com.saa.web.router.manager;

import com.saa.web.control.manager.DocumentHeaderControl;
import com.saa.web.entity.authentication.AuthenticationToken;
import com.saa.web.enumerated.ESchema;
import com.saa.web.utils.JSONSchema;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/manager/document_header")
public class DocumentHeaderRouter {
    @Context
    HttpServletRequest webRequest;
    DocumentHeaderControl control;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response GET() {
        this.control = new DocumentHeaderControl((AuthenticationToken) webRequest.getSession().getAttribute("authentication"));
        return control.list();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response PUT(String content){
        this.control = new DocumentHeaderControl((AuthenticationToken) webRequest.getSession().getAttribute("authentication"));
        JSONObject body = new JSONSchema().valid(ESchema.UNIT, content);
        return control.save(body);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response PUT(@HeaderParam("id") @DefaultValue("0") Long id) {
        this.control = new DocumentHeaderControl((AuthenticationToken) webRequest.getSession().getAttribute("authentication"));
        return control.delete(id);
    }
}
