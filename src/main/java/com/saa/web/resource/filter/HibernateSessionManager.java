package com.saa.web.resource.filter;

import com.saa.web.resource.database.HibernateUtil;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class HibernateSessionManager {
    public static class OpenSession implements ContainerRequestFilter {
        @Override
        public void filter(@Context ContainerRequestContext requestContext) throws IOException {}
    }

    public static class CloseSession implements ContainerResponseFilter {
        @Override
        public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
            HibernateUtil.closeSession();
        }
    }
}
