package com.saa.web.entity.register;

import com.saa.web.entity.authentication.Organization;
import org.json.JSONObject;

import javax.persistence.*;

@Entity(name = "Unit")
@Table(name = "unit", schema = "register")
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description", length = 60)
    private String description;

    @Column(name = "mask", length = 10, nullable = false)
    private String mask;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization", nullable = false, updatable = false)
    private Organization organization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public static Unit fromJSON(JSONObject json) {
        Unit unit = new Unit();

        unit.id = json.optLong("id", 0);
        unit.description = json.getString("description");
        unit.mask = json.getString("mask");

        return unit;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("id", this.id);
        object.put("description", this.description);
        object.put("mask", this.mask);

        return object;
    }
}
