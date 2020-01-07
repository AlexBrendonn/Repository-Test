package com.saa.web.entity.held;

import org.json.JSONObject;

import javax.persistence.*;

@Entity(name = "State")
@Table(name = "state", schema = "held")
public class State {

    @Id
    @Column(name = "code", length = 2)
    private String code;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column(name = "mask", length = 2, nullable = false)
    private String mask;

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

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("code", this.code);
        object.put("name", this.name);
        object.put("mask", this.mask);

        return object;
    }
}
