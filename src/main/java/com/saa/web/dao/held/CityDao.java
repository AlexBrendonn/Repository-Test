package com.saa.web.dao.held;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.held.City;

import java.util.List;

public class CityDao extends MainDao {

    public List<City> list() {
        return this.session.createQuery("from City").getResultList();
    }
}
