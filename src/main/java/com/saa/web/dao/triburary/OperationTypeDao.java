package com.saa.web.dao.triburary;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.tributary.OperationType;

import javax.persistence.criteria.*;
import java.util.List;

public class OperationTypeDao extends MainDao {
    public void insert(OperationType object) {
        session.persist(object);
    }

    public void update(OperationType object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<OperationType> query = builder.createCriteriaUpdate(OperationType.class);
        Root root = query.from(OperationType.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public OperationType get(Long id) {
        return session.get(OperationType.class, id);
    }

    public OperationType get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<OperationType> query = builder.createQuery(OperationType.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<OperationType> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<OperationType> query = builder.createQuery(OperationType.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(OperationType object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<OperationType> query = builder.createCriteriaDelete(OperationType.class);
        Root root = query.from(OperationType.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
