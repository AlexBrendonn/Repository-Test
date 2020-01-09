package com.saa.web.entity.tillage;

import com.saa.web.entity.authentication.Organization;
import org.json.JSONObject;

import javax.persistence.*;

@Entity(name = "ServiceType")
@Table(name = "service_type", schema = "tillage")
public class ServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description",length = 60, nullable = false)
    private String description;

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

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public static ServiceType fromJSON(JSONObject json) {
        ServiceType response = new ServiceType();

        response.setId(json.optLong("id", 0));
        response.description = json.getString("description");

        return response;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("id", this.id);
        object.put("description", this.description);

        return object;
    }
}
