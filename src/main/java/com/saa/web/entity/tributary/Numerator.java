package com.saa.web.entity.tributary;

import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.held.Sped;
import com.saa.web.entity.register.Person;

import javax.persistence.*;

@Entity(name = "Numerator")
@Table(name = "numerator", schema = "tributary")
public class Numerator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person", nullable = false)
    private Person person;

    @Column(name = "series", nullable = false)
    private Integer series;

    @Column(name = "next_number", nullable = false)
    private Long nextNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sped", nullable = false)
    private Sped sped;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization", nullable = false, updatable = false)
    private Organization organization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Long getNextNumber() {
        return nextNumber;
    }

    public void setNextNumber(Long nextNumber) {
        this.nextNumber = nextNumber;
    }

    public Sped getSped() {
        return sped;
    }

    public void setSped(Sped sped) {
        this.sped = sped;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
