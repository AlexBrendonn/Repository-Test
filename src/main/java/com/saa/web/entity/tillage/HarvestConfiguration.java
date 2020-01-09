package com.saa.web.entity.tillage;

import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.authentication.User;
import com.saa.web.entity.register.Person;
import com.saa.web.entity.register.Product;
import com.saa.web.utils.Converter;
import org.json.JSONObject;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity(name = "HarvestConfiguration")
@Table(name = "harvest_configuration", schema = "tillage")
public class HarvestConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "harvest", nullable = false)
    private Harvest harvest;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "farm", nullable = false)
    private Person farm;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "culture", nullable = false)
    private Product culture;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "plant", nullable = false)
    private Product plant;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "cycle", nullable = false)
    private Cycle cycle;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "plot", nullable = false)
    private Plot plot;

    @Column(name = "harvest_area")
    private Long harvestArea;

    @Column(name = "harvest_expected")
    private Long harvestExpected;

    @Column(name = "planting")
    private ZonedDateTime planting = ZonedDateTime.now();

    @Column(name = "harvesting")
    private ZonedDateTime harvesting = ZonedDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization", nullable = false, updatable = false)
    private Organization organization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Harvest getHarvest() {
        return harvest;
    }

    public void setHarvest(Harvest harvest) {
        this.harvest = harvest;
    }

    public Person getFarm() {
        return farm;
    }

    public void setFarm(Person farm) {
        this.farm = farm;
    }

    public Product getCulture() {
        return culture;
    }

    public void setCulture(Product culture) {
        this.culture = culture;
    }

    public Product getPlant() {
        return plant;
    }

    public void setPlant(Product plant) {
        this.plant = plant;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }

    public Plot getPlot() {
        return plot;
    }

    public void setPlot(Plot plot) {
        this.plot = plot;
    }

    public Long getHarvestArea() {
        return harvestArea;
    }

    public void setHarvestArea(Long harvestArea) {
        this.harvestArea = harvestArea;
    }

    public Long getHarvestExpected() {
        return harvestExpected;
    }

    public void setHarvestExpected(Long harvestExpected) {
        this.harvestExpected = harvestExpected;
    }

    public ZonedDateTime getPlanting() {
        return planting;
    }

    public void setPlanting(ZonedDateTime planting) {
        this.planting = planting;
    }

    public ZonedDateTime getHarvesting() {
        return harvesting;
    }

    public void setHarvesting(ZonedDateTime harvesting) {
        this.harvesting = harvesting;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public static HarvestConfiguration fromJSON(JSONObject json) {
        HarvestConfiguration response = new HarvestConfiguration();

        response.setId(json.optLong("id", 0));
        response.harvestExpected = json.optLong("harvest_expected");
        response.harvestArea = json.optLong("harvest_area");
        response.planting = Converter.DateUtils.fromString(json.getString("planting"));
        response.harvesting = Converter.DateUtils.fromString(json.getString("harvesting"));

        Harvest harvest = new Harvest();
        Person farm = new Person();
        Cycle cycle = new Cycle();
        Plot plot = new Plot();
        Product culture = new Product();
        Product plant = new Product();

        harvest.setId(json.optLong("harvest", 0));
        farm.setId(json.optLong("farm", 0));
        cycle.setId(json.optLong("cycle", 0));
        plot.setId(json.optLong("plot", 0));
        culture.setId(json.optLong("culture", 0));
        plant.setId(json.optLong("plant", 0));

        response.harvest = harvest;
        response.farm = farm;
        response.cycle = cycle;
        response.plot = plot;
        response.culture = culture;
        response.plant = plant;

        return response;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("id", this.id);
        object.put("harvest_area", this.harvestArea);
        object.put("harvest_expected", this.harvestExpected);
        object.put("harvest", this.harvest.getId());
        object.put("farm", this.farm.getId());
        object.put("cycle", this.cycle.getId());
        object.put("plot", this.plot.getId());
        object.put("culture", this.culture.getId());
        object.put("plant", this.plant.getId());
        object.put("planting", Converter.DateUtils.fromLocalDateTime(this.planting));
        object.put("harvesting", Converter.DateUtils.fromLocalDateTime(this.harvesting));

        return object;
    }
}
