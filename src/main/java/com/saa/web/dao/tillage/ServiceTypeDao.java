package com.saa.web.dao.tillage;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.tillage.ServiceType;

import javax.persistence.criteria.*;
import java.util.List;

public class ServiceTypeDao extends MainDao {
    public void insert(ServiceType object) {
        session.persist(object);
    }

    public void update(ServiceType object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<ServiceType> query = builder.createCriteriaUpdate(ServiceType.class);
        Root root = query.from(ServiceType.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public ServiceType get(Long id) {
        return session.get(ServiceType.class, id);
    }

    public ServiceType get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ServiceType> query = builder.createQuery(ServiceType.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<ServiceType> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ServiceType> query = builder.createQuery(ServiceType.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(ServiceType object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<ServiceType> query = builder.createCriteriaDelete(ServiceType.class);
        Root root = query.from(ServiceType.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
