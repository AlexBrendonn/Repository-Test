package com.saa.web.entity.register;

import com.saa.web.entity.authentication.Organization;

import javax.persistence.*;

@Entity(name = "Vehicle")
@Table(name = "vehicle", schema = "register")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description", length = 60, nullable = false)
    private String description;

    @Column(name = "plate", length = 7)
    private String plate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver")
    private Person driver;

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
}
