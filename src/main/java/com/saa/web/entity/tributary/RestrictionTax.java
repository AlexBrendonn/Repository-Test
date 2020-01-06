package com.saa.web.entity.tributary;

import com.saa.web.entity.authentication.Organization;

import javax.persistence.*;

@Entity(name = "RestrictionTax")
@Table(name = "restriction_tax", schema = "tributary")
public class RestrictionTax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description", length = 60, nullable = false)
    private String description;

    @Column(name = "enable", nullable = false, columnDefinition = "boolean")
    private Boolean enable;

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

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
