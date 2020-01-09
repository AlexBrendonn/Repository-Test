package com.saa.web.dao.held;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.held.Sped;

import java.util.List;

public class SpedDao extends MainDao {
    public List<Sped> list() {
        return this.session.createQuery("from Sped").getResultList();
    }
}
