package com.saa.web.entity.tillage;

import com.saa.web.entity.authentication.Organization;
import com.saa.web.enumerated.EContractType;
import org.json.JSONObject;

import javax.persistence.*;

@Entity(name = "ContractType")
@Table(name = "contract_type", schema = "tillage")
public class ContractType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description",length = 60, nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "text", nullable = false)
    private EContractType type;

    @Column(name = "storage")
    private Boolean storage;

    @Column(name = "lease")
    private Boolean lease;

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

    public EContractType getType() {
        return type;
    }

    public void setType(EContractType type) {
        this.type = type;
    }

    public Boolean getStorage() {
        return storage;
    }

    public void setStorage(Boolean storage) {
        this.storage = storage;
    }

    public Boolean getLease() {
        return lease;
    }

    public void setLease(Boolean lease) {
        this.lease = lease;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public static ContractType fromJSON(JSONObject json) {
        ContractType response = new ContractType();

        response.setId(json.optLong("id", 0));
        response.description = json.getString("description");
        response.type = EContractType.valueOf(json.getString("type"));
        response.storage = json.optBoolean("storage", false);
        response.lease = json.optBoolean("lease", false);

        return response;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("id", this.id);
        object.put("description", this.description);
        object.put("type", this.type.name());
        object.put("storage", this.storage);
        object.put("lease", this.lease);

        return object;
    }
}
