package com.saa.web.dao.tillage;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.tillage.ApplianceVehicleType;

import javax.persistence.criteria.*;
import java.util.List;

public class ApplianceVehicleTypeDao extends MainDao {
    public void insert(ApplianceVehicleType object) {
        session.persist(object);
    }

    public void update(ApplianceVehicleType object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<ApplianceVehicleType> query = builder.createCriteriaUpdate(ApplianceVehicleType.class);
        Root root = query.from(ApplianceVehicleType.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public ApplianceVehicleType get(Long id) {
        return session.get(ApplianceVehicleType.class, id);
    }

    public ApplianceVehicleType get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ApplianceVehicleType> query = builder.createQuery(ApplianceVehicleType.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<ApplianceVehicleType> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ApplianceVehicleType> query = builder.createQuery(ApplianceVehicleType.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(ApplianceVehicleType object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<ApplianceVehicleType> query = builder.createCriteriaDelete(ApplianceVehicleType.class);
        Root root = query.from(ApplianceVehicleType.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
