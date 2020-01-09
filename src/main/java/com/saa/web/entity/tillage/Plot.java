package com.saa.web.entity.tillage;

import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.authentication.User;
import com.saa.web.entity.register.Person;
import org.hibernate.annotations.ColumnDefault;
import org.json.JSONObject;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "Plot")
@Table(name = "plot", schema = "tillage")
public class Plot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description",length = 60, nullable = false)
    private String description;

    @ColumnDefault("0.00")
    @Column(name = "area", precision = 10, scale = 2, nullable = false)
    private BigDecimal area;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "farm", nullable = false)
    private Person farm;

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

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public Person getFarm() {
        return farm;
    }

    public void setFarm(Person farm) {
        this.farm = farm;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public static Plot fromJSON(JSONObject json) {
        Plot response = new Plot();

        response.setId(json.optLong("id", 0));
        response.description = json.getString("description");
        response.area = json.getBigDecimal("area");

        Person person = new Person();
        person.setId(json.optLong("farm", 0));
        response.farm = person;

        return response;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("id", this.id);
        object.put("description", this.description);
        object.put("area", this.area);
        object.put("farm", this.farm.getId());

        return object;
    }
}
