package com.saa.web.control.authentication;

import com.saa.web.control.MainControl;
import com.saa.web.dao.authentication.CompanyDao;
import com.saa.web.entity.authentication.AuthenticationToken;
import com.saa.web.entity.authentication.Company;
import com.saa.web.resource.ResponseBodyBuilder;
import com.saa.web.resource.database.HibernateUtil;
import org.hibernate.Session;
import org.json.JSONArray;

import javax.ws.rs.core.Response;
import java.util.List;

public class CompanyControl extends MainControl {
    public CompanyControl(AuthenticationToken token) {
        super(token);
    }

    public Response get() {
        ResponseBodyBuilder response = new ResponseBodyBuilder(false);
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            CompanyDao dao = new CompanyDao();

            List<Company> list = dao.list(user.getOrganization());

            JSONArray array = new JSONArray();

            for (Company object : list) {
                array.put(object.toJSON());
            }

            response.setBody(array);
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw ex;
        }


        return response.build();
    }
}
