package com.saa.web.entity.held;

import org.json.JSONObject;

import javax.persistence.*;

@Entity(name = "City")
@Table(name = "city", schema = "held")
public class City {

    @Id
    @Column(name = "code", length = 7)
    private String code;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = State.class)
    @JoinColumn(name = "state", nullable = false, updatable = false)
    private State state;

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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("code", this.code);
        object.put("name", this.name);
        object.put("state", this.state.getCode());

        return object;
    }
}
