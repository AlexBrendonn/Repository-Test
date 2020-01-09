package com.saa.web.entity.register;

import com.saa.web.entity.authentication.Organization;
import org.json.JSONObject;

import javax.persistence.*;

@Entity(name = "ProductGroup")
@Table(name = "product_group", schema = "register")
public class ProductGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description", length = 60)
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

    public static ProductGroup fromJSON(JSONObject object) {
        ProductGroup productGroup = new ProductGroup();

        productGroup.id = object.optLong("id", 0);
        productGroup.description = object.getString("description");

        return productGroup;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("id", this.id);
        object.put("description", this.description);

        return object;
    }
}
