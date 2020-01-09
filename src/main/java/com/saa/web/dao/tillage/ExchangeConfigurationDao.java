package com.saa.web.dao.tillage;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.tillage.ExchangeConfiguration;

import javax.persistence.criteria.*;
import java.util.List;

public class ExchangeConfigurationDao extends MainDao {
    public void insert(ExchangeConfiguration object) {
        session.persist(object);
    }

    public void update(ExchangeConfiguration object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<ExchangeConfiguration> query = builder.createCriteriaUpdate(ExchangeConfiguration.class);
        Root root = query.from(ExchangeConfiguration.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public ExchangeConfiguration get(Long id) {
        return session.get(ExchangeConfiguration.class, id);
    }

    public ExchangeConfiguration get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ExchangeConfiguration> query = builder.createQuery(ExchangeConfiguration.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<ExchangeConfiguration> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ExchangeConfiguration> query = builder.createQuery(ExchangeConfiguration.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(ExchangeConfiguration object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<ExchangeConfiguration> query = builder.createCriteriaDelete(ExchangeConfiguration.class);
        Root root = query.from(ExchangeConfiguration.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
