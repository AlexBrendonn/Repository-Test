package com.saa.web;

import com.saa.web.resource.database.HibernateUtil;
import com.saa.web.resource.filter.HeaderFilter;
import com.saa.web.resource.filter.HibernateSessionManager;
import com.saa.web.resource.mapper.*;
import com.saa.web.router.AuthenticationRouter;
import com.saa.web.router.StatusRouter;
import com.saa.web.utils.TokenJWT;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/v1")
public class Main extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet hash = new HashSet<Class<?>>();

        // <editor-fold defaultstate="collapsed" desc="Listener">
        hash.add(HeaderFilter.class);
        hash.add(HibernateSessionManager.OpenSession.class);
        hash.add(HibernateSessionManager.CloseSession.class);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Listener">

        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Mappers">
        hash.add(CommonExceptionMapper.class);
        hash.add(ForbiddenMapper.class);
        hash.add(BadRequestMapper.class);
        hash.add(EntityNotFoundMapper.class);
        hash.add(InternalServerErrorMapper.class);
        hash.add(NotAuthorizedMapper.class);
        hash.add(NotFoundMapper.class);
        hash.add(PersistenceMapper.class);
        // </editor-fold>

        try {
            HibernateUtil.connect();
            new TokenJWT().createAlgorithm();
        } catch (Exception e) {

        }

        // <editor-fold defaultstate="collapsed" desc="Routers">
        hash.add(StatusRouter.class);
        hash.add(AuthenticationRouter.class);
        // </editor-fold>

        return hash;
    }
}
