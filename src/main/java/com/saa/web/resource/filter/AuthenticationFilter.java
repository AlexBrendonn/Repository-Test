package com.saa.web.resource.filter;

import com.saa.web.dao.authentication.AuthenticationDao;
import com.saa.web.entity.authentication.AuthenticationToken;
import com.saa.web.enumerated.EAuthPlatform;
import com.saa.web.resource.ResponseBodyBuilder;
import com.saa.web.resource.database.HibernateUtil;
import com.saa.web.utils.TokenJWT;
import org.hibernate.Session;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.util.Optional;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    @Context
    HttpServletRequest webRequest;

    private Response abortRequest() {
        ResponseBodyBuilder response = new ResponseBodyBuilder();
        response.setError(true);
        response.setMessage("Invalid token authentication");
        return response.build(Response.Status.UNAUTHORIZED);
    }

    @Override
    public void filter(@Context ContainerRequestContext request) {
        String token = request.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (request.getMethod() == "OPTIONS") return;

        UriInfo info = request.getUriInfo();
        switch (info.getPath()) {
            case "status":
            case "login": {
                return;
            }
        }

        TokenJWT jwt = new TokenJWT();
        try {
            jwt.valid(token);
            AuthenticationDao dao = new AuthenticationDao();

            AuthenticationToken authenticationToken = dao.checkToken(token, EAuthPlatform.WEB);
            Optional.ofNullable(authenticationToken).orElseThrow(() -> new NotAuthorizedException(Response.Status.UNAUTHORIZED));

            webRequest.getSession().setAttribute("authentication", authenticationToken);
        } catch (Exception e) {
            request.abortWith(this.abortRequest());
        }
    }
}
