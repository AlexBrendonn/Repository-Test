package com.saa.web.entity.tillage;

import com.saa.web.entity.authentication.Organization;
import com.saa.web.enumerated.EDiscountTypeField;
import org.json.JSONObject;

import javax.persistence.*;

@Entity(name = "DiscountType")
@Table(name = "discount_type", schema = "tillage")
public class DiscountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description",length = 60, nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "field", columnDefinition = "Text", nullable = false)
    private EDiscountTypeField field;

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

    public EDiscountTypeField getField() {
        return field;
    }

    public void setField(EDiscountTypeField field) {
        this.field = field;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public static DiscountType fromJSON(JSONObject json) {
        DiscountType response = new DiscountType();

        response.setId(json.optLong("id", 0));
        response.description = json.getString("description");
        response.field = EDiscountTypeField.valueOf(json.getString("field"));

        return response;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("id", this.id);
        object.put("description", this.description);
        object.put("field", this.field.name());

        return object;
    }
}
