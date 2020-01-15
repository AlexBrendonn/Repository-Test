package com.saa.web.entity.manager;

import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.register.Product;
import com.saa.web.entity.register.Unit;
import org.hibernate.annotations.ColumnDefault;
import org.json.JSONObject;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "DocumentItem")
@Table(name = "document_item", schema = "manager")
public class DocumentItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "has_operation")
    private Boolean hasOperation;

    @ColumnDefault("0.00")
    @Column(name = "amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @ColumnDefault("0.00")
    @Column(name = "value", precision = 10, scale = 2, nullable = false)
    private BigDecimal value;

    @ColumnDefault("0.00")
    @Column(name = "discount", precision = 10, scale = 2, nullable = false)
    private BigDecimal discount;

    @ColumnDefault("0.00")
    @Column(name = "increase", precision = 10, scale = 2, nullable = false)
    private BigDecimal increase;

    @ColumnDefault("0.00")
    @Column(name = "freight", precision = 10, scale = 2, nullable = false)
    private BigDecimal freight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit")
    private Unit unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document")
    private DocumentHeader document;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Organization.class)
    @JoinColumn(name = "organization", nullable = false, updatable = false)
    private Organization organization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getHasOperation() {
        return hasOperation;
    }

    public void setHasOperation(Boolean hasOperation) {
        this.hasOperation = hasOperation;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getIncrease() {
        return increase;
    }

    public void setIncrease(BigDecimal increase) {
        this.increase = increase;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public DocumentHeader getDocument() {
        return document;
    }

    public void setDocument(DocumentHeader document) {
        this.document = document;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public static DocumentItem fromJSON(JSONObject json) {
        DocumentItem object = new DocumentItem();

        BigDecimal _defaultValue = new BigDecimal(0);

        object.id = json.optLong("id", 0);
        object.amount = json.optBigDecimal("amount", _defaultValue);
        object.value = json.optBigDecimal("value", _defaultValue);
        object.discount = json.optBigDecimal("discount", _defaultValue);
        object.increase = json.optBigDecimal("increase", _defaultValue);
        object.freight = json.optBigDecimal("freight", _defaultValue);

        Product product = new Product();
        Unit unit = new Unit();
        DocumentHeader document = new DocumentHeader();
        product.setId(json.optLong("product", 0));
        unit.setId(json.optLong("unit", 0));
        document.setId(json.optLong("document", 0));
        object.product = product;
        object.unit = unit;
        object.document = document;

        object.hasOperation = false;

        return object;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("id", this.id);
        object.put("amount", this.amount);
        object.put("value", this.value);
        object.put("discount", this.discount);
        object.put("increase", this.increase);
        object.put("freight", this.freight);
        object.put("has_operation", this.hasOperation);
        object.put("product",this.product.getId());
        object.put("unit",this.unit.getId());
        object.put("document",this.document.getId());

        return object;
    }
}
