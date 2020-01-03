package com.saa.web.dao;

import com.saa.web.resource.database.HibernateUtil;
import org.hibernate.Session;

public class MainDao<T> {
    protected Session session;
    protected T entity;

    public MainDao() {
        this.session = HibernateUtil.getSession();
    }
}
