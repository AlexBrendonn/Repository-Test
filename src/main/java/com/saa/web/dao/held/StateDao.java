package com.saa.web.dao.held;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.held.State;

import java.util.List;

public class StateDao extends MainDao<State> {

    public List<State> list() {
        return this.session.createQuery("from State").getResultList();
    }
}
