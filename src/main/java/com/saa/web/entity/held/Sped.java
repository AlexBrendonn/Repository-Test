package com.saa.web.entity.held;

import org.json.JSONObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Sped")
@Table(name = "sped", schema = "held")
public class Sped {
    @Id
    @Column(name = "code", length = 2)
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
