package com.saa.web.dao.manager;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.manager.DocumentInstallment;

import javax.persistence.criteria.*;
import java.util.List;

public class DocumentInstallmentDao extends MainDao {
    public void insert(DocumentInstallment object) {
        session.persist(object);
    }

    public void update(DocumentInstallment object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<DocumentInstallment> query = builder.createCriteriaUpdate(DocumentInstallment.class);
        Root root = query.from(DocumentInstallment.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public DocumentInstallment get(Long id) {
        return session.get(DocumentInstallment.class, id);
    }

    public DocumentInstallment get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DocumentInstallment> query = builder.createQuery(DocumentInstallment.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<DocumentInstallment> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DocumentInstallment> query = builder.createQuery(DocumentInstallment.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(DocumentInstallment object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<DocumentInstallment> query = builder.createCriteriaDelete(DocumentInstallment.class);
        Root root = query.from(DocumentInstallment.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
