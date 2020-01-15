package com.saa.web.entity.tributary;

import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.authentication.User;
import com.saa.web.entity.held.Sped;
import com.saa.web.enumerated.EOperationDocumentType;
import com.saa.web.enumerated.EOperationEmissionType;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Operation_Type")
@Table(name = "operation_type", schema = "tributary")
public class OperationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description", nullable = false, length = 60)
    private String description;

    @Column(name = "mask", nullable = false, length = 20)
    private String mask;

    @Column(name = "note", columnDefinition = "text")
    private String note;

    @Column(name = "limited_user")
    private Boolean limitedUser;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", columnDefinition = "text", nullable = false)
    private EOperationDocumentType documentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "emission_type", columnDefinition = "text", nullable = false)
    private EOperationEmissionType emissionType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "operation_type_user", schema = "tributary", joinColumns = @JoinColumn(name = "operation_type_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sped", nullable = false)
    private Sped sped;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Company.class)
    @JoinColumn(name = "company")
    @NotFound(action = NotFoundAction.IGNORE)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Organization.class)
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

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getLimitedUser() {
        return limitedUser;
    }

    public void setLimitedUser(Boolean limitedUser) {
        this.limitedUser = limitedUser;
    }

    public EOperationDocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(EOperationDocumentType documentType) {
        this.documentType = documentType;
    }

    public EOperationEmissionType getEmissionType() {
        return emissionType;
    }

    public void setEmissionType(EOperationEmissionType emissionType) {
        this.emissionType = emissionType;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Sped getSped() {
        return sped;
    }

    public void setSped(Sped sped) {
        this.sped = sped;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public static OperationType fromJSON(JSONObject json) {
        OperationType operation = new OperationType();

        operation.id = json.optLong("id", 0);
        operation.description = json.getString("description");
        operation.mask = json.getString("mask");
        operation.documentType = EOperationDocumentType.valueOf(json.getString("document_type"));
        operation.emissionType = EOperationEmissionType.OWN;
        operation.note = json.optString("note", "");
        operation.limitedUser = json.optBoolean("limit_user", false);

        Sped sped = new Sped();
        Company company = new Company();

        sped.setCode(json.optString("sped"));
        company.setId(json.getLong("company"));

        operation.sped = sped;
        operation.company = company;

        operation.users = new ArrayList<>();

        if (json.has("users")) {
            JSONArray array = json.getJSONArray("users");

            for (Object userId : array.toList()) {
                User user = new User();
                user.setId((Long) userId);
                operation.users.add(user);
            }
        }

        return operation;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();

        object.put("id", this.id);
        object.put("description", this.description);
        object.put("mask", this.mask);
        object.put("document_type", this.documentType.name());
        object.put("emission_type", this.emissionType.name());
        if (this.sped != null) object.put("sped", this.sped.getCode());
        object.put("limit_user", this.limitedUser);
        object.put("note", this.note);

        for (User user : this.users) {
            array.put(user.getId());
        }

        object.put("users", array);

        return object;
    }
}
