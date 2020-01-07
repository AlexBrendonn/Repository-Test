package com.saa.web.dao.register;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.register.Person;

import javax.persistence.criteria.*;
import java.util.List;

public class PersonDao extends MainDao {
    public void insert(Person object) {
        session.persist(object);
    }

    public void update(Person object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<Person> query = builder.createCriteriaUpdate(Person.class);
        Root root = query.from(Person.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public Person get(Long id) {
        return session.get(Person.class, id);
    }

    public Person get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Person> query = builder.createQuery(Person.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<Person> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Person> query = builder.createQuery(Person.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(Person object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<Person> query = builder.createCriteriaDelete(Person.class);
        Root root = query.from(Person.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
