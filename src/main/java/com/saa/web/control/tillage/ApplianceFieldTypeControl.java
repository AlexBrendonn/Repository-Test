package com.saa.web.control.tillage;

import com.saa.web.control.MainControl;
import com.saa.web.dao.tillage.ApplianceFieldTypeDao;
import com.saa.web.entity.authentication.AuthenticationToken;
import com.saa.web.entity.tillage.ApplianceFieldType;
import com.saa.web.resource.ResponseBodyBuilder;
import com.saa.web.resource.database.HibernateUtil;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

public class ApplianceFieldTypeControl extends MainControl {

    public ApplianceFieldTypeControl(AuthenticationToken token) {
        super(token);
    }

    public Response save(JSONObject body){
        ResponseBodyBuilder response = new ResponseBodyBuilder(false);
        Session session = HibernateUtil.getSession();

        try {
            session.beginTransaction();

            ApplianceFieldTypeDao dao = new ApplianceFieldTypeDao();

            ApplianceFieldType object = ApplianceFieldType.fromJSON(body);
            object.setOrganization(this.organization);

            if (Optional.ofNullable(object.getId()).isPresent()) {
                dao.update(object);
            } else {
                dao.insert(object);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }

        return response.build();
    }

    public Response list() {
        ResponseBodyBuilder response = new ResponseBodyBuilder(false);
        Session session = HibernateUtil.getSession();

        try {
            session.beginTransaction();

            ApplianceFieldTypeDao dao = new ApplianceFieldTypeDao();

            List<ApplianceFieldType> list = dao.list(this.organization);
            JSONArray array = new JSONArray();

            for (ApplianceFieldType object : list) {
                array.put(object.toJSON());
            }

            response.setBody(array);

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }

        return response.build();
    }

    public Response delete(Long id) {
        ResponseBodyBuilder response = new ResponseBodyBuilder(false);
        Session session = HibernateUtil.getSession();

        try {
            session.beginTransaction();

            ApplianceFieldTypeDao dao = new ApplianceFieldTypeDao();

            ApplianceFieldType object = dao.get(id, this.organization);

            Optional.ofNullable(object).orElseThrow(() -> new EntityNotFoundException("Object [" + id + "] not found"));

            dao.delete(object);

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }

        return response.build();
    }
}
