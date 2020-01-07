package com.saa.web.dao.authentication;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;

import javax.persistence.criteria.*;
import java.util.List;

public class CompanyDao extends MainDao {
    public void insert(Company object) {
        session.persist(object);
    }

    public void update(Company object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<Company> query = builder.createCriteriaUpdate(Company.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public Company get(Long id) {
        return session.get(Company.class, id);
    }

    public List<Company> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Company> query = builder.createQuery(Company.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));

        List<Company> data = session.createQuery(query).getResultList();
        return data;
    }

    public void delete(Company object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<Company> query = builder.createCriteriaDelete(Company.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));
        session.createQuery(query).executeUpdate();
    }
}
