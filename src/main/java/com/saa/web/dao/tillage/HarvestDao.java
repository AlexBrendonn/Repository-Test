package com.saa.web.dao.tillage;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.tillage.Harvest;

import javax.persistence.criteria.*;
import java.util.List;

public class HarvestDao extends MainDao {
    public void insert(Harvest object) {
        session.persist(object);
    }

    public void update(Harvest object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<Harvest> query = builder.createCriteriaUpdate(Harvest.class);
        Root root = query.from(Harvest.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public Harvest get(Long id) {
        return session.get(Harvest.class, id);
    }

    public Harvest get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Harvest> query = builder.createQuery(Harvest.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<Harvest> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Harvest> query = builder.createQuery(Harvest.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(Harvest object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<Harvest> query = builder.createCriteriaDelete(Harvest.class);
        Root root = query.from(Harvest.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
