package com.saa.web.entity.tillage;

import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.authentication.User;
import com.saa.web.entity.register.Product;
import com.saa.web.utils.Converter;
import org.hibernate.annotations.ColumnDefault;
import org.json.JSONObject;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity(name = "Cycle")
@Table(name = "cycle", schema = "tillage")
public class Cycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description",length = 60, nullable = false)
    private String description;

    @Column(name = "mask",length = 60, nullable = false)
    private String mask;

    @ColumnDefault("0.00")
    @Column(name = "value", precision = 10, scale = 2, nullable = false)
    private BigDecimal value;

    @Column(name = "init_date", nullable = false)
    private ZonedDateTime init_date = ZonedDateTime.now();

    @Column(name = "end_date", nullable = false)
    private ZonedDateTime end_date = ZonedDateTime.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "harvest", nullable = false)
    private Harvest harvest;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "culture", nullable = false)
    private Product culture;

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

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public ZonedDateTime getInit() {
        return init_date;
    }

    public void setInit(ZonedDateTime init_date) {
        this.init_date = init_date;
    }

    public ZonedDateTime getEnd() {
        return end_date;
    }

    public void setEnd(ZonedDateTime end_date) {
        this.end_date = end_date;
    }

    public Harvest getHarvest() {
        return harvest;
    }

    public void setHarvest(Harvest harvest) {
        this.harvest = harvest;
    }

    public Product getCulture() {
        return culture;
    }

    public void setCulture(Product culture) {
        this.culture = culture;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public static Cycle fromJSON(JSONObject json) {
        Cycle response = new Cycle();

        response.setId(json.optLong("id", 0));
        response.description = json.getString("description");
        response.mask = json.getString("mask");
        response.value = json.getBigDecimal("value");
        response.init_date = Converter.DateUtils.fromString(json.getString("init_date"));
        response.end_date = Converter.DateUtils.fromString(json.getString("end_date"));

        Harvest harvest = new Harvest();
        Product product = new Product();

        harvest.setId(json.optLong("harvest", 0));
        product.setId(json.optLong("culture", 0));

        response.harvest = harvest;
        response.culture = product;

        return response;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("id", this.id);
        object.put("harvest", this.harvest.getId());
        object.put("culture", this.culture.getId());
        object.put("description", this.description);
        object.put("mask", this.mask);
        object.put("init_date", Converter.DateUtils.fromLocalDateTime(this.init_date));
        object.put("end_date", Converter.DateUtils.fromLocalDateTime(this.end_date));
        object.put("value", this.value);

        return object;
    }
}
