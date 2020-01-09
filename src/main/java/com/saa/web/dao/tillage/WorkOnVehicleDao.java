package com.saa.web.dao.tillage;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.tillage.WorkOnVehicle;

import javax.persistence.criteria.*;
import java.util.List;

public class WorkOnVehicleDao extends MainDao {
    public void insert(WorkOnVehicle object) {
        session.persist(object);
    }

    public void update(WorkOnVehicle object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<WorkOnVehicle> query = builder.createCriteriaUpdate(WorkOnVehicle.class);
        Root root = query.from(WorkOnVehicle.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public WorkOnVehicle get(Long id) {
        return session.get(WorkOnVehicle.class, id);
    }

    public WorkOnVehicle get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<WorkOnVehicle> query = builder.createQuery(WorkOnVehicle.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<WorkOnVehicle> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<WorkOnVehicle> query = builder.createQuery(WorkOnVehicle.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(WorkOnVehicle object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<WorkOnVehicle> query = builder.createCriteriaDelete(WorkOnVehicle.class);
        Root root = query.from(WorkOnVehicle.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
