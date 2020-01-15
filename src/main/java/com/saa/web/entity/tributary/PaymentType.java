package com.saa.web.entity.tributary;

import com.saa.web.entity.authentication.Organization;
import org.json.JSONObject;

import javax.persistence.*;

@Entity(name = "PaymentType")
@Table(name = "payment_type", schema = "tributary")
public class PaymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description", length = 60, nullable = false)
    private String description;

    @Column(name = "mask", length = 60, nullable = false)
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

    public static PaymentType fromJSON(JSONObject object) {
        PaymentType paymentType = new PaymentType();

        paymentType.id = object.optLong("id", 0);
        paymentType.description = object.getString("description");
        paymentType.mask = object.getString("mask");

        return paymentType;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("id", this.id);
        object.put("description", this.description);
        object.put("mask", this.mask);

        return object;
    }
}
