package com.saa.web.resource;

import org.json.JSONObject;

import javax.ws.rs.core.Response;

public class ResponseBodyBuilder {
    private Boolean error;
    private Object body;
    private String message;

    public ResponseBodyBuilder() {
        this(true);
    }

    public ResponseBodyBuilder(Boolean error) {
        this(error, "");
    }

    public ResponseBodyBuilder(Boolean error, String message) {
        this(error, message, null);
    }

    public ResponseBodyBuilder(Boolean error, String message, Object body) {
        this.error = error;
        this.message = message;
        this.body = body;
    }

    public Response build() {
        return this.build(Response.Status.OK);
    }

    public Response build(Response.Status status) {
        JSONObject json = new JSONObject();
        json.put("error", this.error);
        json.put("body", this.body);
        json.put("message", this.message);
        return Response.status(status).entity(json.toString()).build();
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
