package com.saa.web.entity.register;

import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.held.City;
import com.saa.web.entity.tributary.RestrictionTax;
import com.saa.web.enumerated.EPersonProfile;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Person")
@Table(name = "person", schema = "register")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "nickname", nullable = false, length = 60)
    private String nickname;

    @Column(name = "cpf_cnpj", length = 20)
    private String cpfCnpj;

    @Column(name = "ie", length = 14)
    private String ie;

    @Column(name = "email", length = 60)
    private String email;

    @Column(name = "phone", length = 60)
    private String phone;

    @Column(name = "address", length = 60)
    private String address;

    @Column(name = "neighborhood", length = 60)
    private String neighborhood;

    @Column(name = "number", length = 60)
    private String number;

    @Column(name = "extra", length = 60)
    private String extra;

    @Column(name = "cep", length = 8)
    private String cep;

    @ElementCollection(targetClass = EPersonProfile.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "person_profile", schema = "register", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "profile", columnDefinition = "text")
    private List<EPersonProfile> profiles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city", nullable = false)
    private City city;

    @Column(name = "note", columnDefinition = "text")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restriction_tax")
    private RestrictionTax restrictionTax;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "person")
    @PrimaryKeyJoinColumn
    private PersonCompany company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_group")
    private PersonGroup group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization", nullable = false, updatable = false)
    private Organization organization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public List<EPersonProfile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<EPersonProfile> profiles) {
        this.profiles = profiles;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public RestrictionTax getRestrictionTax() {
        return restrictionTax;
    }

    public void setRestrictionTax(RestrictionTax restrictionTax) {
        this.restrictionTax = restrictionTax;
    }

    public PersonCompany getCompany() {
        return company;
    }

    public void setCompany(PersonCompany company) {
        this.company = company;
    }

    public PersonGroup getGroup() {
        return group;
    }

    public void setGroup(PersonGroup group) {
        this.group = group;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public static Person fromJSON(JSONObject json) {
        Person object = new Person();


        return object;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();

        return json;
    }

}
