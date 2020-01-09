package com.saa.web.dao.tillage;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.tillage.ApplianceFieldType;

import javax.persistence.criteria.*;
import java.util.List;

public class ApplianceFieldTypeDao extends MainDao {
    public void insert(ApplianceFieldType object) {
        session.persist(object);
    }

    public void update(ApplianceFieldType object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<ApplianceFieldType> query = builder.createCriteriaUpdate(ApplianceFieldType.class);
        Root root = query.from(ApplianceFieldType.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public ApplianceFieldType get(Long id) {
        return session.get(ApplianceFieldType.class, id);
    }

    public ApplianceFieldType get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ApplianceFieldType> query = builder.createQuery(ApplianceFieldType.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<ApplianceFieldType> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ApplianceFieldType> query = builder.createQuery(ApplianceFieldType.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(ApplianceFieldType object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<ApplianceFieldType> query = builder.createCriteriaDelete(ApplianceFieldType.class);
        Root root = query.from(ApplianceFieldType.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
