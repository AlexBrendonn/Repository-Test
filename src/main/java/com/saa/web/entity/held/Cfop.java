package com.saa.web.entity.held;

import com.saa.web.enumerated.ECfopDestination;
import com.saa.web.enumerated.ECfopType;
import org.json.JSONObject;

import javax.persistence.*;

@Entity(name = "Cfop")
@Table(name = "cfop", schema = "held")
public class Cfop {

    @Id
    @Column(name = "code", length = 4)
    private String code;

    @Column(name = "description", columnDefinition = "text", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "text", nullable = false)
    private ECfopType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "destination", columnDefinition = "text", nullable = false)
    private ECfopDestination destination;

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

    public ECfopType getType() {
        return type;
    }

    public void setType(ECfopType type) {
        this.type = type;
    }

    public ECfopDestination getDestination() {
        return destination;
    }

    public void setDestination(ECfopDestination destination) {
        this.destination = destination;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();

        json.put("code", this.code);
        json.put("description", this.description);
        json.put("type", this.type.name());
        json.put("destination", this.destination.name());

        return json;
    }
}
