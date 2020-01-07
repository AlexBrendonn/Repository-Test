package com.saa.web.control.held;

import com.saa.web.control.MainControl;
import com.saa.web.dao.held.CstDao;
import com.saa.web.entity.authentication.AuthenticationToken;
import com.saa.web.entity.held.Cst;
import com.saa.web.resource.ResponseBodyBuilder;
import com.saa.web.resource.database.HibernateUtil;
import org.hibernate.Session;
import org.json.JSONArray;

import javax.ws.rs.core.Response;
import java.util.List;

public class CstControl extends MainControl {
    public CstControl(AuthenticationToken token) {
        super(token);
    }

    public Response get() {
        ResponseBodyBuilder response = new ResponseBodyBuilder(false);
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            CstDao dao = new CstDao();

            List<Cst> list = dao.list();

            JSONArray array = new JSONArray();

            for (Cst object : list) {
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
