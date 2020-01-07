package com.saa.web.dao.register;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.register.ProductGroup;

import javax.persistence.criteria.*;
import java.util.List;

public class ProductGroupDao extends MainDao {
    public void insert(ProductGroup object) {
        session.persist(object);
    }

    public void update(ProductGroup object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<ProductGroup> query = builder.createCriteriaUpdate(ProductGroup.class);
        Root root = query.from(ProductGroup.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }

    public ProductGroup get(Long id) {
        return session.get(ProductGroup.class, id);
    }

    public ProductGroup get(Long id, Organization organization) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ProductGroup> query = builder.createQuery(ProductGroup.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("id"), id));
        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getSingleResult();
    }

    public List<ProductGroup> list(Organization organization) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ProductGroup> query = builder.createQuery(ProductGroup.class);
        Root root = query.from(Company.class);

        query.where(builder.equal(root.get("organization"), organization.getId()));
        return session.createQuery(query).getResultList();
    }

    public void delete(ProductGroup object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<ProductGroup> query = builder.createCriteriaDelete(ProductGroup.class);
        Root root = query.from(ProductGroup.class);

        query.where(builder.equal(root.get("id"), object.getId()));
        query.where(builder.equal(root.get("organization"), object.getOrganization().getId()));

        session.createQuery(query).executeUpdate();
    }
}
