package com.saa.web.dao.held;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.held.Country;

import java.util.List;

public class CountryDao extends MainDao<Country> {

    public List<Country> list() {
        return this.session.createQuery("from Country").getResultList();
    }
}
