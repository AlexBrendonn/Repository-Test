package com.saa.web.dao.triburary;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.tributary.OperationRule;

import javax.persistence.criteria.*;
import java.util.List;

public class OperationRuleDao extends MainDao {
    public void insert(OperationRule object) {
        session.persist(object);
    }

    public void update(OperationRule object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<OperationRule> query = builder.createCriteriaUpdate(OperationRule.class);
        Root root = query.from(OperationRule.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public OperationRule get(Long id) {
        return session.get(OperationRule.class, id);
    }

    public OperationRule get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<OperationRule> query = builder.createQuery(OperationRule.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<OperationRule> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<OperationRule> query = builder.createQuery(OperationRule.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(OperationRule object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<OperationRule> query = builder.createCriteriaDelete(OperationRule.class);
        Root root = query.from(OperationRule.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
