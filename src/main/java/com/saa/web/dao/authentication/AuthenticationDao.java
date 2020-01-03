package com.saa.web.dao.authentication;

import com.saa.web.entity.authentication.AuthenticationToken;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.authentication.User;
import com.saa.web.enumerated.EAuthPlatform;
import com.saa.web.resource.database.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import javax.persistence.NoResultException;
import javax.persistence.criteria.*;

public class AuthenticationDao {
    private Session session;

    public AuthenticationDao() {
        this.session = HibernateUtil.getFactory().getCurrentSession();
    }

    public User signIn(String organization, String nickname, String password) {
        Query query = session.createNativeQuery("SELECT UserObject.* FROM authentication.user AS UserObject INNER JOIN authentication.organization as OrganizationObject ON UserObject.organization = OrganizationObject.id WHERE UserObject.nickname = :nickname AND UserObject.password = crypt(:password,UserObject.password) AND OrganizationObject.nickname = :organization AND OrganizationObject.active = TRUE;", User.class);
        query.setParameter("nickname", nickname, new StringType());
        query.setParameter("password", password, new StringType());
        query.setParameter("organization", organization, new StringType());
        try {
            return (User) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public AuthenticationToken checkToken(String token, EAuthPlatform platform) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<AuthenticationToken> query = builder.createQuery(AuthenticationToken.class);
        Root<AuthenticationToken> root = query.from(AuthenticationToken.class);

        query.where(builder.equal(root.get("token"), token));
        query.where(builder.equal(root.get("platform"), platform));

        query.select(root);
        return session.createQuery(query).getSingleResult();
    }

    public AuthenticationToken getToken(User user, EAuthPlatform platform) {
        Query query = session.createQuery("FROM AuthenticationToken AS a WHERE a.platform = :platform AND a.user = :user", AuthenticationToken.class);
        query.setParameter("platform", platform.name(), new StringType());
        query.setParameter("user", user.getId(), new LongType());
        try {
            return (AuthenticationToken) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
