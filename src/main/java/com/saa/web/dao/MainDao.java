package com.saa.web.dao;

import com.saa.web.resource.database.HibernateUtil;
import org.hibernate.Session;

public class MainDao {
    protected Session session;
    public MainDao() {
        this.session = HibernateUtil.getSession();
    }
}
