package com.saa.web.entity.manager;

import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.tributary.Currency;
import com.saa.web.entity.tributary.PaymentType;
import com.saa.web.utils.Converter;
import org.hibernate.annotations.ColumnDefault;
import org.json.JSONObject;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity(name = "DocumentInstallment")
@Table(name = "document_installment", schema = "manager")
public class DocumentInstallment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "due", nullable = false)
    private ZonedDateTime due = ZonedDateTime.now();

    @ColumnDefault("0.00")
    @Column(name = "value", precision = 10, scale = 2, nullable = false)
    private BigDecimal value;

    @ColumnDefault("0.00")
    @Column(name = "alternative_value", precision = 10, scale = 2, nullable = false)
    private BigDecimal alternativeValue;

    @Column(name = "note", columnDefinition = "text")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment")
    private PaymentType payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alternative_currency")
    private Currency alternativeCurrency;

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

    public ZonedDateTime getDue() {
        return due;
    }

    public void setDue(ZonedDateTime due) {
        this.due = due;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getAlternativeValue() {
        return alternativeValue;
    }

    public void setAlternativeValue(BigDecimal alternativeValue) {
        this.alternativeValue = alternativeValue;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public PaymentType getPayment() {
        return payment;
    }

    public void setPayment(PaymentType payment) {
        this.payment = payment;
    }

    public Currency getAlternativeCurrency() {
        return alternativeCurrency;
    }

    public void setAlternativeCurrency(Currency alternativeCurrency) {
        this.alternativeCurrency = alternativeCurrency;
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

    public static DocumentInstallment fromJSON(JSONObject json) {
        DocumentInstallment object = new DocumentInstallment();

        BigDecimal _defaultValue = new BigDecimal(0);

        object.id = json.optLong("id", 0);
        object.due = Converter.DateUtils.fromString(json.getString("due"));
        object.note = json.optString("note", null);
        object.value = json.optBigDecimal("value", _defaultValue);
        object.alternativeValue = json.optBigDecimal("alternative_value", _defaultValue);

        Currency alternativeCurrency = new Currency();
        PaymentType payment = new PaymentType();
        DocumentHeader document = new DocumentHeader();
        alternativeCurrency.setMask(json.optString("alternative_currency"));
        payment.setMask(json.optString("payment"));
        document.setId(json.optLong("document", 0));
        object.alternativeCurrency = alternativeCurrency;
        object.payment = payment;
        object.document = document;

        return object;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("id", this.id);
        object.put("due", Converter.DateUtils.fromLocalDateTime(this.due));
        object.put("value", this.value);
        object.put("alternative_value", this.alternativeValue);
        object.put("note", this.note);
        object.put("payment", this.payment.getMask());
        object.put("alternative_currency", this.alternativeCurrency.getMask());
        object.put("document", this.document.getId());

        return object;
    }
}
