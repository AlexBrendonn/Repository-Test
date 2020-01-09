package com.saa.web.dao.tillage;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.tillage.Plot;

import javax.persistence.criteria.*;
import java.util.List;

public class PlotDao extends MainDao {
    public void insert(Plot object) {
        session.persist(object);
    }

    public void update(Plot object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<Plot> query = builder.createCriteriaUpdate(Plot.class);
        Root root = query.from(Plot.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public Plot get(Long id) {
        return session.get(Plot.class, id);
    }

    public Plot get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Plot> query = builder.createQuery(Plot.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<Plot> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Plot> query = builder.createQuery(Plot.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(Plot object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<Plot> query = builder.createCriteriaDelete(Plot.class);
        Root root = query.from(Plot.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
