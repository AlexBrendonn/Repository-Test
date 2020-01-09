package com.saa.web.entity.tributary;

import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.register.Person;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity(name = "NegativeCertificate")
@Table(name = "negative_certificate", schema = "tributary")
public class NegativeCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person",nullable = false)
    private Person person;

    @Column(name = "due", nullable = false)
    private ZonedDateTime due;

    @Column(name = "cnd", nullable = false)
    private String cnd;

    @Column(name = "authentication_code", nullable = false)
    private String authenticationCode;

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

    public ZonedDateTime getDue() {
        return due;
    }

    public void setDue(ZonedDateTime due) {
        this.due = due;
    }

    public String getCnd() {
        return cnd;
    }

    public void setCnd(String cnd) {
        this.cnd = cnd;
    }

    public String getAuthenticationCode() {
        return authenticationCode;
    }

    public void setAuthenticationCode(String authenticationCode) {
        this.authenticationCode = authenticationCode;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
