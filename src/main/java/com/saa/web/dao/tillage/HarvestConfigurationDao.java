package com.saa.web.dao.tillage;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.tillage.HarvestConfiguration;

import javax.persistence.criteria.*;
import java.util.List;

public class HarvestConfigurationDao extends MainDao {
    public void insert(HarvestConfiguration object) {
        session.persist(object);
    }

    public void update(HarvestConfiguration object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<HarvestConfiguration> query = builder.createCriteriaUpdate(HarvestConfiguration.class);
        Root root = query.from(HarvestConfiguration.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public HarvestConfiguration get(Long id) {
        return session.get(HarvestConfiguration.class, id);
    }

    public HarvestConfiguration get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<HarvestConfiguration> query = builder.createQuery(HarvestConfiguration.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<HarvestConfiguration> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<HarvestConfiguration> query = builder.createQuery(HarvestConfiguration.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(HarvestConfiguration object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<HarvestConfiguration> query = builder.createCriteriaDelete(HarvestConfiguration.class);
        Root root = query.from(HarvestConfiguration.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
