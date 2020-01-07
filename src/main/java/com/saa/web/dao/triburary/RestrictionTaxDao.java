package com.saa.web.dao.triburary;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.tributary.RestrictionTax;

import javax.persistence.criteria.*;
import java.util.List;

public class RestrictionTaxDao extends MainDao {
    public void insert(RestrictionTax object) {
        session.persist(object);
    }

    public void update(RestrictionTax object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<RestrictionTax> query = builder.createCriteriaUpdate(RestrictionTax.class);
        Root root = query.from(RestrictionTax.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public RestrictionTax get(Long id) {
        return session.get(RestrictionTax.class, id);
    }

    public RestrictionTax get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<RestrictionTax> query = builder.createQuery(RestrictionTax.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<RestrictionTax> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<RestrictionTax> query = builder.createQuery(RestrictionTax.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(RestrictionTax object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<RestrictionTax> query = builder.createCriteriaDelete(RestrictionTax.class);
        Root root = query.from(RestrictionTax.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
