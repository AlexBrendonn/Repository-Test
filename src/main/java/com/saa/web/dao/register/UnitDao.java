package com.saa.web.dao.register;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.register.Unit;

import javax.persistence.criteria.*;
import java.util.List;

public class UnitDao extends MainDao {
    public void insert(Unit object) {
        session.persist(object);
    }

    public void update(Unit object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<Unit> query = builder.createCriteriaUpdate(Unit.class);
        Root root = query.from(Unit.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public Unit get(Long id) {
        return session.get(Unit.class, id);
    }

    public Unit get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Unit> query = builder.createQuery(Unit.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<Unit> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Unit> query = builder.createQuery(Unit.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(Unit object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<Unit> query = builder.createCriteriaDelete(Unit.class);
        Root root = query.from(Unit.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
