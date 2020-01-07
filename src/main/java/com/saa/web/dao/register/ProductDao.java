package com.saa.web.dao.register;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.register.Product;

import javax.persistence.criteria.*;
import java.util.List;

public class ProductDao extends MainDao {
    public void insert(Product object) {
        session.persist(object);
    }

    public void update(Product object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<Product> query = builder.createCriteriaUpdate(Product.class);
        Root root = query.from(Product.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public Product get(Long id) {
        return session.get(Product.class, id);
    }

    public Product get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<Product> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(Product object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<Product> query = builder.createCriteriaDelete(Product.class);
        Root root = query.from(Product.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
