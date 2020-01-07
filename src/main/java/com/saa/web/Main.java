package com.saa.web;

import com.saa.web.resource.database.HibernateUtil;
import com.saa.web.resource.filter.AuthenticationFilter;
import com.saa.web.resource.filter.HeaderFilter;
import com.saa.web.resource.filter.HibernateSessionManager;
import com.saa.web.resource.mapper.*;
import com.saa.web.router.authentication.AuthenticationRouter;
import com.saa.web.router.authentication.CompanyRouter;
import com.saa.web.router.authentication.StatusRouter;
import com.saa.web.router.held.*;
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
        hash.add(AuthenticationFilter.class);
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

        // <editor-fold defaultstate="collapsed" desc="Routers">
        hash.add(StatusRouter.class);
        hash.add(AuthenticationRouter.class);
        hash.add(CompanyRouter.class);
        hash.add(CstRouter.class);
        hash.add(CfopRouter.class);
        hash.add(CityRouter.class);
        hash.add(StateRouter.class);
        hash.add(CountryRouter.class);
        // </editor-fold>

        return hash;
    }
}
