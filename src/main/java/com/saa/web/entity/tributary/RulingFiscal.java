package com.saa.web.entity.tributary;

import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.register.Product;
import com.saa.web.enumerated.ERulingFiscalFreight;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity(name = "RulingFiscal")
@Table(name = "ruling_fiscal", schema = "tributary")
public class RulingFiscal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product", nullable = false)
    private Product product;

    @Column(name = "date", nullable = false)
    private ZonedDateTime date;

    @ColumnDefault("0.00")
    @Column(name = "value", precision = 10, scale = 2, nullable = false)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(name = "freight_type", columnDefinition = "text")
    private ERulingFiscalFreight freightType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization", nullable = false, updatable = false)
    private Organization organization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public ERulingFiscalFreight getFreightType() {
        return freightType;
    }

    public void setFreightType(ERulingFiscalFreight freightType) {
        this.freightType = freightType;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
