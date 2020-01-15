package com.saa.web.entity.manager;

import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.held.Sped;
import com.saa.web.entity.register.Person;
import com.saa.web.entity.tributary.OperationType;
import com.saa.web.enumerated.EDocumentStatus;
import com.saa.web.utils.Converter;
import org.hibernate.annotations.ColumnDefault;
import org.json.JSONObject;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity(name = "DocumentHeader")
@Table(name = "document_header", schema = "manager")
public class DocumentHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number", nullable = false)
    private Long number;

    @Column(name = "series", nullable = false)
    private Integer series;

    @Column(name = "sub_serie", length = 60)
    private String subSerie;

    @Column(name = "emission", nullable = false)
    private ZonedDateTime emission = ZonedDateTime.now();

    @Column(name = "movement", nullable = false)
    private ZonedDateTime moviment = ZonedDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operation")
    private OperationType operation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sped")
    private Sped sped;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient")
    private Person recipient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "finance")
    private Person finance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issuer")
    private Person issuer;

    @ColumnDefault("0.00")
    @Column(name = "total_value", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalValue;

    @ColumnDefault("0.00")
    @Column(name = "total_discount", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalDiscount;

    @ColumnDefault("0.00")
    @Column(name = "total_increase", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalIncrease;

    @ColumnDefault("0.00")
    @Column(name = "total_freight", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalFreight;

    @Column(name = "locked")
    private Boolean locked;

    @Column(name = "invalid")
    private Boolean invalid;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "text", nullable = false)
    private EDocumentStatus status;

    @Column(name = "note", columnDefinition = "text")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Organization.class)
    @JoinColumn(name = "organization", nullable = false, updatable = false)
    private Organization organization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public ZonedDateTime getEmission() {
        return emission;
    }

    public void setEmission(ZonedDateTime emission) {
        this.emission = emission;
    }

    public ZonedDateTime getMoviment() {
        return moviment;
    }

    public void setMoviment(ZonedDateTime moviment) {
        this.moviment = moviment;
    }

    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }

    public Sped getSped() {
        return sped;
    }

    public void setSped(Sped sped) {
        this.sped = sped;
    }

    public Person getRecipient() {
        return recipient;
    }

    public void setRecipient(Person recipient) {
        this.recipient = recipient;
    }

    public Person getFinance() {
        return finance;
    }

    public void setFinance(Person finance) {
        this.finance = finance;
    }

    public Person getIssuer() {
        return issuer;
    }

    public void setIssuer(Person issuer) {
        this.issuer = issuer;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public BigDecimal getTotalIncrease() {
        return totalIncrease;
    }

    public void setTotalIncrease(BigDecimal totalIncrease) {
        this.totalIncrease = totalIncrease;
    }

    public BigDecimal getTotalFreight() {
        return totalFreight;
    }

    public void setTotalFreight(BigDecimal totalFreight) {
        this.totalFreight = totalFreight;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getInvalid() {
        return invalid;
    }

    public void setInvalid(Boolean invalid) {
        this.invalid = invalid;
    }

    public EDocumentStatus getStatus() {
        return status;
    }

    public void setStatus(EDocumentStatus status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public static DocumentHeader fromJSON(JSONObject json) {
        DocumentHeader object = new DocumentHeader();

        object.id = json.optLong("id", 0);
        object.number = json.optLong("number", 0);
        object.series = json.optInt("series", 0);
        object.subSerie = json.optString("sub_series", null);
        object.emission = Converter.DateUtils.fromString(json.getString("emission"));
        object.moviment = Converter.DateUtils.fromString(json.getString("moviment"));

        OperationType operationType = new OperationType();
        Person recipient = new Person();
        Person issuer = new Person();
        Person finance = new Person();
        Sped sped = new Sped();

        operationType.setId(json.optLong("operation", 0));
        recipient.setId(json.optLong("recipient", 0));
        issuer.setId(json.optLong("issuer", 0));
        finance.setId(json.optLong("finance", 0));
        sped.setCode(json.optString("sped"));

        object.operation = operationType;
        object.recipient = recipient;
        object.issuer = issuer;
        object.finance = finance;
        object.sped = sped;

        BigDecimal _defaultTotal = new BigDecimal(0);
        object.totalValue = _defaultTotal;
        object.totalDiscount = json.optBigDecimal("total_discount", _defaultTotal);
        object.totalIncrease = json.optBigDecimal("total_increase", _defaultTotal);
        object.totalFreight = json.optBigDecimal("total_freight", _defaultTotal);

        object.note = json.optString("note", null);

        object.locked = false;
        object.invalid = false;
        object.status = EDocumentStatus.OPENED;

        return object;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("id", this.id);
        object.put("number", this.number);
        object.put("series", this.series);
        object.put("sub_serie", this.subSerie);
        object.put("emission", Converter.DateUtils.fromLocalDateTime(this.emission));
        object.put("moviment", Converter.DateUtils.fromLocalDateTime(this.moviment));
        object.put("sped", this.sped.getCode());
        object.put("operation", this.operation.getId());
        object.put("recipient", this.recipient.getId());
        object.put("issuer", this.issuer.getId());
        object.put("finance", this.finance.getId());
        object.put("total_value", this.totalValue);
        object.put("total_discount", this.totalDiscount);
        object.put("total_increase", this.totalIncrease);
        object.put("total_freight", this.totalFreight);
        object.put("note", this.note);
        object.put("locked", this.locked);
        object.put("invalid", this.invalid);
        object.put("status", this.status);

        return object;
    }
}
