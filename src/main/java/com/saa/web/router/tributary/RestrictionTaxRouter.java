package com.saa.web.router.tributary;

import com.saa.web.control.held.CfopControl;
import com.saa.web.control.tributary.RestrictionTaxControl;
import com.saa.web.entity.authentication.AuthenticationToken;
import com.saa.web.entity.tributary.RestrictionTax;
import com.saa.web.enumerated.ESchema;
import com.saa.web.utils.JSONSchema;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tributary/restriction_tax")
public class RestrictionTaxRouter {
    @Context
    HttpServletRequest webRequest;
    RestrictionTaxControl control;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response PUT(String content) {
        this.control = new RestrictionTaxControl((AuthenticationToken) webRequest.getSession().getAttribute("authentication"));
        JSONObject body = new JSONSchema().valid(ESchema.RESTRICTION_TAX, content);
        return control.save(body);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GET() {
        this.control = new RestrictionTaxControl((AuthenticationToken) webRequest.getSession().getAttribute("authentication"));
        return control.list();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response PUT(@HeaderParam("id") @DefaultValue("0") Long id) {
        this.control = new RestrictionTaxControl((AuthenticationToken) webRequest.getSession().getAttribute("authentication"));
        return control.delete(id);
    }


}
