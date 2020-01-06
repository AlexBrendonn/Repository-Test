package com.saa.web.entity.authentication;

import com.saa.web.enumerated.EOrganizationService;
import com.saa.web.enumerated.EUserPermission;
import com.saa.web.enumerated.EUserType;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity(name = "User")
@Table(name = "user", schema = "authentication")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nickname", length = 30, nullable = false)
    private String nickname;

    @Column(name = "password", columnDefinition = "bytea", nullable = false)
    @ColumnTransformer(write = "crypt(?, gen_salt('bf'))")
    private String password;

    @Column(name = "email", length = 60)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 5)
    private EUserType type;

    @ElementCollection(targetClass = EUserPermission.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_permission", schema = "authentication", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "permission", columnDefinition = "text")
    private List<EUserPermission> permissions;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EUserType getType() {
        return type;
    }

    public void setType(EUserType type) {
        this.type = type;
    }

    public List<EUserPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<EUserPermission> permissions) {
        this.permissions = permissions;
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
}
