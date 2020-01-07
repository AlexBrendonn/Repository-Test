package com.saa.web.dao.register;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.register.Vehicle;

import javax.persistence.criteria.*;
import java.util.List;

public class VehicleDao extends MainDao {
    public void insert(Vehicle object) {
        session.persist(object);
    }

    public void update(Vehicle object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<Vehicle> query = builder.createCriteriaUpdate(Vehicle.class);
        Root root = query.from(Vehicle.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public Vehicle get(Long id) {
        return session.get(Vehicle.class, id);
    }

    public Vehicle get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Vehicle> query = builder.createQuery(Vehicle.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<Vehicle> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Vehicle> query = builder.createQuery(Vehicle.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(Vehicle object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<Vehicle> query = builder.createCriteriaDelete(Vehicle.class);
        Root root = query.from(Vehicle.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
