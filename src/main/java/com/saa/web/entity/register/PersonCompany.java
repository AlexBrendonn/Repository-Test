package com.saa.web.entity.register;

import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.enumerated.EPersonCRT;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;

@Entity(name = "PersonCompany")
@Table(name = "person_company", schema = "register")
public class PersonCompany {
    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne
    @MapsId
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company", nullable = false)
    private Company company;

    @Column(name = "accountant_document", length = 14)
    private String accountantDocument;

    @Column(name = "accountant_crc", length = 60)
    private String accountantCrc;

    @Column(name = "cert_file", columnDefinition = "bytea")
    private byte[] certFile;

    @ColumnTransformer(write = "crypt(?, 'CERT_PASSWORD')", read = "crypt(cert_password,'CERT_PASSWORD')")
    @Column(name = "cert_password", columnDefinition = "bytea")
    private String certPassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "crt", columnDefinition = "text")
    private EPersonCRT crt;

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getAccountantDocument() {
        return accountantDocument;
    }

    public void setAccountantDocument(String accountantDocument) {
        this.accountantDocument = accountantDocument;
    }

    public String getAccountantCrc() {
        return accountantCrc;
    }

    public void setAccountantCrc(String accountantCrc) {
        this.accountantCrc = accountantCrc;
    }

    public byte[] getCertFile() {
        return certFile;
    }

    public void setCertFile(byte[] certFile) {
        this.certFile = certFile;
    }

    public String getCertPassword() {
        return certPassword;
    }

    public void setCertPassword(String certPassword) {
        this.certPassword = certPassword;
    }

    public EPersonCRT getCrt() {
        return crt;
    }

    public void setCrt(EPersonCRT crt) {
        this.crt = crt;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
