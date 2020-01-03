package com.saa.web.dao.authentication;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Organization;

import javax.persistence.criteria.*;
import java.util.List;

public class OrganizationDao extends MainDao {

    public void insert(Organization object) {
        session.persist(object);
    }

    public void update(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<Organization> query = builder.createCriteriaUpdate(Organization.class);
        Root root = query.from(Organization.class);
        query.where(builder.equal(root.get("id"), organization.getId()));
        session.createQuery(query).executeUpdate();
    }

    public Organization get(Long id) {
        return session.get(Organization.class, id);
    }

    public List<Organization> list() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Organization> query = builder.createQuery(Organization.class);
        query.from(Organization.class);
        List<Organization> data = session.createQuery(query).getResultList();
        return data;
    }

    public void delete(Long id) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<Organization> query = builder.createCriteriaDelete(Organization.class);
        Root root = query.from(Organization.class);
        query.where(builder.equal(root.get("id"), id));
        session.createQuery(query).executeUpdate();
    }
}
