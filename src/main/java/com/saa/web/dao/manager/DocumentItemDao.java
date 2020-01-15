package com.saa.web.dao.manager;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.manager.DocumentItem;

import javax.persistence.criteria.*;
import java.util.List;

public class DocumentItemDao extends MainDao {
    public void insert(DocumentItem object) {
        session.persist(object);
    }

    public void update(DocumentItem object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<DocumentItem> query = builder.createCriteriaUpdate(DocumentItem.class);
        Root root = query.from(DocumentItem.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public DocumentItem get(Long id) {
        return session.get(DocumentItem.class, id);
    }

    public DocumentItem get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DocumentItem> query = builder.createQuery(DocumentItem.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<DocumentItem> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DocumentItem> query = builder.createQuery(DocumentItem.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(DocumentItem object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<DocumentItem> query = builder.createCriteriaDelete(DocumentItem.class);
        Root root = query.from(DocumentItem.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
