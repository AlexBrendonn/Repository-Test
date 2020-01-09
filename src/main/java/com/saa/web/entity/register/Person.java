package com.saa.web.entity.register;

import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.held.City;
import com.saa.web.entity.held.State;
import com.saa.web.entity.tributary.RestrictionTax;
import com.saa.web.enumerated.EPersonProfile;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Base64;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state", nullable = false)
    private State state;

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

    public static Person fromJSON(JSONObject json) throws Exception {
        Person person = new Person();

        person.id = json.optLong("id", 0);
        person.name = json.getString("name");
        person.nickname = json.getString("nickname");
        person.cpfCnpj = json.getString("cpfCnpj");
        person.ie = json.optString("ie", null);
        person.email = json.optString("email", null);
        person.phone = json.optString("phone");
        person.address = json.getString("address");
        person.neighborhood = json.getString("neighborhood");
        person.number = json.optString("number", null);
        person.extra = json.optString("extra", null);
        person.cep = json.getString("cep");

        City city = new City();
        State state = new State();
        PersonGroup group = new PersonGroup();
        RestrictionTax restrictionTax = new RestrictionTax();
        PersonCompany company = new PersonCompany();

        List<EPersonProfile> profiles = new ArrayList<>();
        JSONArray array = json.getJSONArray("profiles");

        for (int i = 0; i < array.length(); i++) {
            EPersonProfile profile = EPersonProfile.valueOf(array.getString(i));
            profiles.add(profile);
        }

        city.setCode(json.getString("city"));
        state.setCode(json.getString("state"));
        group.setId(json.optLong("group", 0));
        restrictionTax.setId(json.optLong("restrictionTax", 0));
        company.setId(json.optLong("company", 0));

        person.profiles = profiles;
        person.city = city;
        person.state = state;
        person.group = group;
        person.note = json.optString("note", null);
        //person.enable = json.optBoolean("enable", false);
        person.restrictionTax = restrictionTax;
        person.company = company;


        return person;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("id", this.id);
        object.put("name", this.name);
        object.put("nickname", this.nickname);
        object.put("cpfCnpj", this.cpfCnpj);
        object.put("ie", this.ie);
        object.put("email", this.email);
        object.put("phone", this.phone);
        object.put("address", this.address);
        object.put("neighborhood", this.neighborhood);
        object.put("number", this.number);
        object.put("extra", this.extra);
        object.put("cep", this.cep);
        object.put("profiles", new JSONObject(this.profiles));
        object.put("city", this.city.getCode());
        object.put("state", this.state.getCode());
        object.put("note", this.note);

        if (this.group.getId() > 0) object.put("group", this.group.getId());
        if (this.restrictionTax.getId() > 0) object.put("restrictionTax", this.restrictionTax.getId());

        if (this.profiles.contains(EPersonProfile.COMPANY)) {
            object.put("accountantCRC", this.company.getAccountantCrc());
            object.put("accountantDocument", this.company.getAccountantDocument());
            object.put("company", this.company.getCompany().getId());
            object.put("certPassword", this.company.getCertPassword());
            object.put("crt", this.company.getCrt().getCode());
            if (this.company.getCertFile() != null) {
                String buildBase64 = "data:application/x-pkcs12;base64,";
                object.put("certFile", buildBase64 + Base64.getEncoder().encodeToString(this.company.getCertFile()));
            }
        }

        return object;
    }

}
