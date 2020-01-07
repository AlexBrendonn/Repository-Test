package com.saa.web.entity.held;

import org.json.JSONObject;

import javax.persistence.*;

@Entity(name = "Cst")
@Table(name = "cst", schema = "held")
public class Cst {

    @Id
    @Column(name = "code", length = 4)
    private String code;

    @Column(name = "description", columnDefinition = "Text", nullable = false)
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();

        json.put("code", this.code);
        json.put("description", this.description);

        return json;
    }
}
