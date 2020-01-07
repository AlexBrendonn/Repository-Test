package com.saa.web.entity.register;

import com.saa.web.entity.authentication.Organization;
import com.saa.web.enumerated.EProductFiscalType;
import com.saa.web.enumerated.EProductType;

import javax.persistence.*;
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
    @JoinColumn(name = "group")
    private ProductGroup group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit")
    private Unit unit;

    @Column(name = "ncm", length = 8)
    private String ncm;

    @Column(name = "note", columnDefinition = "text")
    private String note;

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
}
