package com.saa.web.entity.register;

import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.held.City;
import com.saa.web.enumerated.EPersonProfile;
import com.saa.web.enumerated.EVehicleBodywork;
import com.saa.web.enumerated.EVehicleLoadType;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ElementCollection(targetClass = EVehicleLoadType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "vehicle_load_type", schema = "register", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "load_type", columnDefinition = "text")
    private List<EVehicleLoadType> loadType;

    @ElementCollection(targetClass = EVehicleBodywork.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "vehicle_bodywork", schema = "register", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "bodywork", columnDefinition = "text")
    private List<EVehicleBodywork> bodywork;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver", referencedColumnName = "id")
    private Person driver;

    @ManyToOne
    @JoinColumn(name = "city", referencedColumnName = "code", insertable = false, updatable = false)
    private City city;

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

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Person getDriver() {
        return driver;
    }

    public void setDriver(Person driver) {
        this.driver = driver;
    }

    public List<EVehicleLoadType> getLoadType() {
        return loadType;
    }

    public void setLoadType(List<EVehicleLoadType> loadType) {
        this.loadType = loadType;
    }

    public List<EVehicleBodywork> getBodywork() {
        return bodywork;
    }

    public void setBodywork(List<EVehicleBodywork> bodywork) {
        this.bodywork = bodywork;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public static Vehicle fromJSON(JSONObject object) {
        Vehicle vehicle = new Vehicle();

        vehicle.id = object.optLong("id", 0);
        vehicle.description = object.getString("description");
        vehicle.plate = object.getString("plate");

        City city = new City();
        Person person = new Person();

        List<EVehicleBodywork> bodyworks = new ArrayList<>();
        JSONArray arrayBodywork = object.getJSONArray("bodywork");

        for (int i = 0; i < arrayBodywork.length(); i++) {
            EVehicleBodywork bodywork = EVehicleBodywork.valueOf(arrayBodywork.getString(i));
            bodyworks.add(bodywork);
        }

        List<EVehicleLoadType> loadTypes = new ArrayList<>();
        JSONArray arrayLoadType = object.getJSONArray("loadType");

        for (int i = 0; i < arrayLoadType.length(); i++) {
            EVehicleLoadType loadType = EVehicleLoadType.valueOf(arrayLoadType.getString(i));
            loadTypes.add(loadType);
        }

        city.setCode(object.getString("city"));
        person.setId(object.getLong("driver"));

        vehicle.bodywork = bodyworks;
        vehicle.loadType = loadTypes;
        vehicle.city = city;
        vehicle.driver = person;

        return vehicle;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("id", this.id);
        object.put("description", this.description);
        object.put("plate", this.plate);
        object.put("city", this.city);
        object.put("load_type", new JSONObject(this.loadType));
        object.put("bodywork", new JSONObject(this.bodywork));

        return object;
    }
}
