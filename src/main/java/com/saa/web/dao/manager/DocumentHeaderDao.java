package com.saa.web.dao.manager;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.manager.DocumentHeader;

import javax.persistence.criteria.*;
import java.util.List;

public class DocumentHeaderDao extends MainDao {
    public void insert(DocumentHeader object) {
        session.persist(object);
    }

    public void update(DocumentHeader object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<DocumentHeader> query = builder.createCriteriaUpdate(DocumentHeader.class);
        Root root = query.from(DocumentHeader.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public DocumentHeader get(Long id) {
        return session.get(DocumentHeader.class, id);
    }

    public DocumentHeader get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DocumentHeader> query = builder.createQuery(DocumentHeader.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<DocumentHeader> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DocumentHeader> query = builder.createQuery(DocumentHeader.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(DocumentHeader object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<DocumentHeader> query = builder.createCriteriaDelete(DocumentHeader.class);
        Root root = query.from(DocumentHeader.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
