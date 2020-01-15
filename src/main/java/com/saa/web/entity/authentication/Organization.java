package com.saa.web.entity.authentication;

import com.saa.web.enumerated.EOrganizationService;
import com.saa.web.enumerated.EOrganizationType;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Organization")
@Table(name = "organization", schema = "authentication")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", columnDefinition = "text", unique = true)
    private String name;

    @Column(name = "nickname", columnDefinition = "text", unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "text")
    private EOrganizationType type;

    @ElementCollection(targetClass = EOrganizationService.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "organization_service", schema = "authentication", joinColumns = @JoinColumn(name = "organization_id"))
    @Column(name = "services", columnDefinition = "text")
    private List<EOrganizationService> services;

    @Column(name = "active")
    private Boolean active;

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

    public EOrganizationType getType() {
        return type;
    }

    public void setType(EOrganizationType type) {
        this.type = type;
    }

    public List<EOrganizationService> getServices() {
        return services;
    }

    public void setServices(List<EOrganizationService> services) {
        this.services = services;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
