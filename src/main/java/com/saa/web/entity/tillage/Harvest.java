package com.saa.web.entity.tillage;

import com.saa.web.entity.authentication.Organization;
import com.saa.web.utils.Converter;
import org.json.JSONObject;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity(name = "Harvest")
@Table(name = "harvest", schema = "tillage")
public class Harvest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description", length = 60, nullable = false)
    private String description;

    @Column(name = "mask", length = 60, nullable = false)
    private String mask;

    @Column(name = "init", nullable = false)
    private ZonedDateTime init = ZonedDateTime.now();

    @Column(name = "end", nullable = false)
    private ZonedDateTime end = ZonedDateTime.now();

    @Column(name = "activated")
    private Boolean activated;

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

    public ZonedDateTime getInit() {
        return init;
    }

    public void setInit(ZonedDateTime init) {
        this.init = init;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public static Harvest fromJSON(JSONObject json) {
        Harvest response = new Harvest();

        response.setId(json.optLong("id", 0));
        response.description = json.getString("description");
        response.mask = json.getString("mask");
        response.init = Converter.DateUtils.fromString(json.getString("init"));
        response.end = Converter.DateUtils.fromString(json.getString("end"));
        response.activated = json.getBoolean("activated");

        return response;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("id", this.id);
        object.put("description", this.description);
        object.put("mask", this.mask);
        object.put("init", this.init);
        object.put("end", this.end);
        object.put("activated", this.activated);

        return object;
    }
}
