package com.saa.web.dao.authentication;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.authentication.User;

import javax.persistence.criteria.*;
import java.util.List;

public class UserDao extends MainDao<User> {
    public void insert(User object) {
        session.persist(object);
    }

    public void update(User object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<User> query = builder.createCriteriaUpdate(User.class);
        Root root = query.from(User.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public User get(Long id) {
        return session.get(User.class, id);
    }

    public List<User> list() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        query.from(User.class);
        List<User> data = session.createQuery(query).getResultList();
        return data;
    }

    public void delete(User object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<User> query = builder.createCriteriaDelete(User.class);
        Root root = query.from(User.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
