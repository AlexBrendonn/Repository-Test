package com.saa.web.entity.tillage;

import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.authentication.User;
import com.saa.web.entity.register.Vehicle;
import org.json.JSONObject;

import javax.persistence.*;

@Entity(name = "ExchangeConfiguration")
@Table(name = "exchange_configuration", schema = "tillage")
public class ExchangeConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "type", nullable = false)
    private ApplianceVehicleType type;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "vehicle", nullable = false)
    private Vehicle vehicle;

    @Column(name = "lifetime", nullable = false)
    private Long lifetime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization", nullable = false, updatable = false)
    private Organization organization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApplianceVehicleType getType() {
        return type;
    }

    public void setType(ApplianceVehicleType type) {
        this.type = type;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Long getLifetime() {
        return lifetime;
    }

    public void setLifetime(Long lifetime) {
        this.lifetime = lifetime;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public static ExchangeConfiguration fromJSON(JSONObject json) {
        ExchangeConfiguration response = new ExchangeConfiguration();

        response.setId(json.optLong("id", 0));
        response.lifetime = json.getLong("lifetime");

        ApplianceVehicleType type = new ApplianceVehicleType();
        Vehicle vehicle = new Vehicle();

        type.setId(json.optLong("type", 0));
        vehicle.setId(json.optLong("vehicle", 0));

        response.type = type;
        response.vehicle = vehicle;

        return response;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("id", this.id);
        object.put("type", this.type.getId());
        object.put("vehicle", this.vehicle.getId());
        object.put("lifetime", this.lifetime);

        return object;
    }
}
