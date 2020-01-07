package com.saa.web.resource;

import com.saa.web.resource.database.HibernateUtil;
import com.saa.web.utils.TokenJWT;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.logging.Logger;

public class ServerInitializer implements ServletContextListener {
    private Logger log = Logger.getLogger(ServerInitializer.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            new TokenJWT().createAlgorithm();
            HibernateUtil.connect();
        } catch (Exception ex) {
            log.severe(ex.toString());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            HibernateUtil.disconnect();
        } catch (Exception ex) {
            log.severe(ex.toString());
        }
    }
}
