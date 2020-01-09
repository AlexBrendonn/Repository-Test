package com.saa.web.dao.tillage;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.tillage.ContractType;

import javax.persistence.criteria.*;
import java.util.List;

public class ContractTypeDao extends MainDao {
    public void insert(ContractType object) {
        session.persist(object);
    }

    public void update(ContractType object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<ContractType> query = builder.createCriteriaUpdate(ContractType.class);
        Root root = query.from(ContractType.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public ContractType get(Long id) {
        return session.get(ContractType.class, id);
    }

    public ContractType get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ContractType> query = builder.createQuery(ContractType.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<ContractType> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ContractType> query = builder.createQuery(ContractType.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(ContractType object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<ContractType> query = builder.createCriteriaDelete(ContractType.class);
        Root root = query.from(ContractType.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
