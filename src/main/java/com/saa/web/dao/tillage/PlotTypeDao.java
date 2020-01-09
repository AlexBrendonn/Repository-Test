package com.saa.web.dao.tillage;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.tillage.PlotType;

import javax.persistence.criteria.*;
import java.util.List;

public class PlotTypeDao extends MainDao {
    public void insert(PlotType object) {
        session.persist(object);
    }

    public void update(PlotType object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<PlotType> query = builder.createCriteriaUpdate(PlotType.class);
        Root root = query.from(PlotType.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public PlotType get(Long id) {
        return session.get(PlotType.class, id);
    }

    public PlotType get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PlotType> query = builder.createQuery(PlotType.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<PlotType> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PlotType> query = builder.createQuery(PlotType.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(PlotType object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<PlotType> query = builder.createCriteriaDelete(PlotType.class);
        Root root = query.from(PlotType.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
