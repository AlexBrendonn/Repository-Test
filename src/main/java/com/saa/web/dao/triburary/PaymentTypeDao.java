package com.saa.web.dao.triburary;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.tributary.PaymentType;

import javax.persistence.criteria.*;
import java.util.List;

public class PaymentTypeDao extends MainDao {
    public void insert(PaymentType object) {
        session.persist(object);
    }

    public void update(PaymentType object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<PaymentType> query = builder.createCriteriaUpdate(PaymentType.class);
        Root root = query.from(PaymentType.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public PaymentType get(Long id) {
        return session.get(PaymentType.class, id);
    }

    public PaymentType get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PaymentType> query = builder.createQuery(PaymentType.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<PaymentType> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PaymentType> query = builder.createQuery(PaymentType.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(PaymentType object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<PaymentType> query = builder.createCriteriaDelete(PaymentType.class);
        Root root = query.from(PaymentType.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
