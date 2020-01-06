package com.saa.web.control.authentication;

import com.saa.web.dao.authentication.AuthenticationDao;
import com.saa.web.dao.authentication.CompanyDao;
import com.saa.web.dao.authentication.OrganizationDao;
import com.saa.web.dao.authentication.UserDao;
import com.saa.web.entity.authentication.AuthenticationToken;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.authentication.User;
import com.saa.web.enumerated.EAuthPlatform;
import com.saa.web.enumerated.EUserType;
import com.saa.web.resource.ResponseBodyBuilder;
import com.saa.web.resource.database.HibernateUtil;
import com.saa.web.utils.ManagerException;
import com.saa.web.utils.TokenJWT;
import org.hibernate.Session;
import org.json.JSONObject;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import java.time.ZonedDateTime;
import java.util.Optional;

public class AuthenticationControl {
    public Response signIn(JSONObject json) {
        ResponseBodyBuilder response = new ResponseBodyBuilder(false);
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();

            AuthenticationDao dao = new AuthenticationDao();
            CompanyDao companyDao = new CompanyDao();

            String organization = json.getString("organization");
            String nickname = json.getString("nickname");
            String password = json.getString("password");

            User user = dao.signIn(organization, nickname, password);

            Optional.ofNullable(user).orElseThrow(() -> new NotAuthorizedException("Usuário ou Senha incorretos", Response.Status.UNAUTHORIZED));

            Company company = Optional.ofNullable(user.getCompany()).orElse(companyDao.list(user.getOrganization()).get(0));
            user.setCompany(company);

            TokenJWT tokenJWT = new TokenJWT();

            String token = tokenJWT.create(user);

            AuthenticationToken authenticationToken = Optional
                    .ofNullable(dao.getToken(user, EAuthPlatform.WEB))
                    .orElse(new AuthenticationToken());

            authenticationToken.setToken(token);
            authenticationToken.setPlatform(EAuthPlatform.WEB);
            authenticationToken.setUser(user);
            authenticationToken.setOrganization(user.getOrganization());
            authenticationToken.setCreatedAt(ZonedDateTime.now());

            session.saveOrUpdate(authenticationToken);

            response.setBody(token);

            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw ex;
        }
        return response.build();
    }

    public Response register(JSONObject json) {
        ResponseBodyBuilder response = new ResponseBodyBuilder(false);
        Session session = HibernateUtil.getSession();

        try {
            session.beginTransaction();

            UserDao userDao = new UserDao();
            OrganizationDao organizationDao = new OrganizationDao();

            User user = new User();

            Organization organization = organizationDao.get(json.getLong("organization"));

            user.setOrganization(organization);
            user.setType(EUserType.OWNER);
            user.setPassword(json.getString("password"));
            user.setNickname(json.getString("nickname"));
            user.setEmail(json.getString("email"));

            userDao.insert(user);

            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw ex;
        }

        return response.build();
    }

    public Response session(String token) {
        ResponseBodyBuilder response = new ResponseBodyBuilder(false);
        Session session = HibernateUtil.getSession();

        try {
            session.beginTransaction();
            AuthenticationDao dao = new AuthenticationDao();

            TokenJWT tokenJWT = new TokenJWT();
            tokenJWT.valid(token);

            AuthenticationToken authenticationToken = dao.checkToken(token, EAuthPlatform.WEB);

            Optional.ofNullable(authenticationToken).orElseThrow(() -> new NotAuthorizedException("Token inválido", Response.Status.UNAUTHORIZED));

            JSONObject object = new JSONObject();

            object.put("token", authenticationToken.getToken());
            object.put("user", authenticationToken.getUser().getNickname());
            object.put("platform", authenticationToken.getPlatform().name());

            response.setBody(object);

            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw ex;
        }

        return response.build();
    }
}
