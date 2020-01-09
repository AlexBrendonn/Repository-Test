package com.saa.web.control.held;

import com.saa.web.control.MainControl;
import com.saa.web.dao.held.SpedDao;
import com.saa.web.entity.authentication.AuthenticationToken;
import com.saa.web.entity.held.Sped;
import com.saa.web.resource.ResponseBodyBuilder;
import com.saa.web.resource.database.HibernateUtil;
import org.hibernate.Session;
import org.json.JSONArray;

import javax.ws.rs.core.Response;
import java.util.List;

public class SpedControl extends MainControl {
    public SpedControl(AuthenticationToken token) {
        super(token);
    }

    public Response get() {
        ResponseBodyBuilder response = new ResponseBodyBuilder(false);
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            SpedDao dao = new SpedDao();

            List<Sped> list = dao.list();

            JSONArray array = new JSONArray();

            for (Sped object : list) {
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
