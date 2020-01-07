package com.saa.web.dao.register;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.register.PersonGroup;

import javax.persistence.criteria.*;
import java.util.List;

public class PersonGroupDao extends MainDao {
    public void insert(PersonGroup object) {
        session.persist(object);
    }

    public void update(PersonGroup object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<PersonGroup> query = builder.createCriteriaUpdate(PersonGroup.class);
        Root root = query.from(PersonGroup.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public PersonGroup get(Long id) {
        return session.get(PersonGroup.class, id);
    }

    public PersonGroup get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PersonGroup> query = builder.createQuery(PersonGroup.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<PersonGroup> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PersonGroup> query = builder.createQuery(PersonGroup.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(PersonGroup object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<PersonGroup> query = builder.createCriteriaDelete(PersonGroup.class);
        Root root = query.from(PersonGroup.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
