package com.saa.web.dao.tillage;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.tillage.Cycle;

import javax.persistence.criteria.*;
import java.util.List;

public class CycleDao extends MainDao {
    public void insert(Cycle object) {
        session.persist(object);
    }

    public void update(Cycle object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<Cycle> query = builder.createCriteriaUpdate(Cycle.class);
        Root root = query.from(Cycle.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public Cycle get(Long id) {
        return session.get(Cycle.class, id);
    }

    public Cycle get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Cycle> query = builder.createQuery(Cycle.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<Cycle> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Cycle> query = builder.createQuery(Cycle.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(Cycle object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<Cycle> query = builder.createCriteriaDelete(Cycle.class);
        Root root = query.from(Cycle.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
