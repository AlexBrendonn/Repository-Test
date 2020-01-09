package com.saa.web.entity.register;

import com.saa.web.entity.authentication.Organization;
import com.saa.web.enumerated.EProductFiscalType;
import com.saa.web.enumerated.EProductType;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Product")
@Table(name = "product", schema = "register")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description", length = 120, nullable = false)
    private String description;

    @Column(name = "reference", length = 60)
    private String reference;

    @Enumerated(EnumType.STRING)
    @Column(name = "fiscal_type", length = 2, nullable = false)
    private EProductFiscalType fiscalType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_group")
    private ProductGroup group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit")
    private Unit unit;

    @Column(name = "ncm", length = 8)
    private String ncm;

    @Column(name = "note", columnDefinition = "text")
    private String note;

    @Column(name = "enable")
    private Boolean enable;

    @ElementCollection(targetClass = EProductType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "product_type", schema = "register", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "type", columnDefinition = "text")
    private List<EProductType> types;

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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public EProductFiscalType getFiscalType() {
        return fiscalType;
    }

    public void setFiscalType(EProductFiscalType fiscalType) {
        this.fiscalType = fiscalType;
    }

    public ProductGroup getGroup() {
        return group;
    }

    public void setGroup(ProductGroup group) {
        this.group = group;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public List<EProductType> getTypes() {
        return types;
    }

    public void setTypes(List<EProductType> types) {
        this.types = types;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public static Product fromJSON(JSONObject object) {
        Product product = new Product();

        product.id = object.optLong("id", 0);
        product.description = object.getString("description");
        product.reference = object.optString("reference", null);
        product.fiscalType = EProductFiscalType.valueOf(object.getString("fiscal_type"));
        product.ncm = object.optString("ncm", null);
        product.note = object.optString("note", null);
        product.enable = object.optBoolean("enable", true);

        ProductGroup group = new ProductGroup();
        Unit unit = new Unit();

        List<EProductType> types = new ArrayList<>();
        JSONArray array = object.getJSONArray("profiles");

        for (int i = 0; i < array.length(); i++) {
            EProductType type = EProductType.valueOf(array.getString(i));
            types.add(type);
        }

        group.setId(object.getLong("group"));
        unit.setId(object.getLong("unit"));

        product.types = types;
        product.group = group;
        product.unit = unit;

        return product;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("id", this.id);
        object.put("description", this.description);
        object.put("reference", this.reference);
        object.put("fiscal_type", this.fiscalType.getCode());
        object.put("group", this.group.getId());
        object.put("unit", this.unit.getId());
        object.put("ncm", this.ncm);
        object.put("note", this.note);
        object.put("enable", this.enable);
        object.put("types", new JSONObject(this.types));

        return object;
    }
}
