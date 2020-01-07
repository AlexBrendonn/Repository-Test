package com.saa.web.dao.held;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.held.Cst;

import java.util.List;

public class CstDao extends MainDao {

    public List<Cst> list() {
        return this.session.createQuery("from Cst").getResultList();
    }
}
