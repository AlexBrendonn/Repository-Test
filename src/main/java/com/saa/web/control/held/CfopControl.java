package com.saa.web.control.held;

import com.saa.web.control.MainControl;
import com.saa.web.dao.held.CfopDao;
import com.saa.web.entity.authentication.AuthenticationToken;
import com.saa.web.entity.held.Cfop;
import com.saa.web.resource.ResponseBodyBuilder;
import com.saa.web.resource.database.HibernateUtil;
import org.hibernate.Session;
import org.json.JSONArray;

import javax.ws.rs.core.Response;
import java.util.List;

public class CfopControl extends MainControl {
    public CfopControl(AuthenticationToken token) {
        super(token);
    }

    public Response get() {
        ResponseBodyBuilder response = new ResponseBodyBuilder(false);
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            CfopDao dao = new CfopDao();

            List<Cfop> list = dao.list();

            JSONArray array = new JSONArray();

            for (Cfop object : list) {
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
