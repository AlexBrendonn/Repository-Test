package com.saa.web.dao.system;

import com.saa.web.dao.MainDao;
import com.saa.web.entity.system.Setting;

public class SettingDao extends MainDao {
    public void save(Setting setting) {
        this.session.saveOrUpdate(setting);
    }
}
