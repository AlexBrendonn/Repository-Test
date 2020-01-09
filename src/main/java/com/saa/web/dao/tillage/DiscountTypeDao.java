package com.saa.web.dao.tillage;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.tillage.DiscountType;

import javax.persistence.criteria.*;
import java.util.List;

public class DiscountTypeDao extends MainDao {
    public void insert(DiscountType object) {
        session.persist(object);
    }

    public void update(DiscountType object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<DiscountType> query = builder.createCriteriaUpdate(DiscountType.class);
        Root root = query.from(DiscountType.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public DiscountType get(Long id) {
        return session.get(DiscountType.class, id);
    }

    public DiscountType get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DiscountType> query = builder.createQuery(DiscountType.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<DiscountType> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DiscountType> query = builder.createQuery(DiscountType.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(DiscountType object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<DiscountType> query = builder.createCriteriaDelete(DiscountType.class);
        Root root = query.from(DiscountType.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
