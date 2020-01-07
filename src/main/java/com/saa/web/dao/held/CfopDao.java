package com.saa.web.dao.held;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.held.Cfop;

import java.util.List;

public class CfopDao extends MainDao<Cfop> {

    public List<Cfop> list() {
        return this.session.createQuery("from Cfop").getResultList();
    }
}
