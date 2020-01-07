package com.saa.web.entity.held;

import org.json.JSONObject;

import javax.persistence.*;

@Entity(name = "Country")
@Table(name = "country", schema = "held")
public class Country {

    @Id
    @Column(name = "code", length = 4)
    private String code;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("code", this.code);
        object.put("name", this.name);

        return object;
    }
}
