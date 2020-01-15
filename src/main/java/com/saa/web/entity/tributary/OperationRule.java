package com.saa.web.entity.tributary;

import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.held.Cfop;
import com.saa.web.entity.held.Cst;
import com.saa.web.entity.held.State;
import com.saa.web.entity.register.Person;
import com.saa.web.entity.register.Product;
import com.saa.web.entity.register.ProductGroup;
import com.saa.web.enumerated.EOperationBaseModality;
import com.saa.web.enumerated.EOperationBaseType;
import com.saa.web.enumerated.EOperationICMSType;
import com.saa.web.enumerated.EOperationSearchType;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.json.JSONObject;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "OperationRule")
@Table(name = "operation_rule", schema = "tributary")
public class OperationRule {

    // <editor-fold defaultstate="collapsed" desc="Principal">
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description", nullable = false, length = 60)
    private String description;

    @ColumnDefault("0.00")
    @Column(name = "estimated_tax", precision = 10, scale = 2, nullable = false)
    private BigDecimal estimatedTax;

    @Enumerated(EnumType.STRING)
    @Column(name = "search_type", columnDefinition = "text", nullable = false)
    private EOperationSearchType searchType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "search_type_product")
    private Product searchTypeProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "search_type_product_group")
    private ProductGroup searchTypeProductGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_company")
    private State stateCompany;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_costumer")
    private State stateCostumer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cfop_inside_state")
    private Cfop cfopInsideState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cfop_outside_state")
    private Cfop cfopOutsideState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cfop_abroad")
    private Cfop cfopAbroad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restriction_tax")
    private RestrictionTax restrictionTax;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ICMS">
    @ColumnDefault("0.00")
    @Column(name = "base_value", precision = 10, scale = 2, nullable = false)
    private BigDecimal baseValue;

    @ColumnDefault("0.00")
    @Column(name = "rate_value", precision = 10, scale = 2, nullable = false)
    private BigDecimal rateValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "icms_type", columnDefinition = "text", nullable = false)
    private EOperationICMSType icmsType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cst")
    private Cst cst;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "csosn")
    private Cst csosn;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ICMS-ST">
    @ColumnDefault("0.00")
    @Column(name = "mva_base_value", precision = 10, scale = 2, nullable = false)
    private BigDecimal mvaBaseValue;

    @ColumnDefault("0.00")
    @Column(name = "mva_rate_value", precision = 10, scale = 2, nullable = false)
    private BigDecimal mvaRateValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "base_value_modality", columnDefinition = "text", nullable = false)
    private EOperationBaseModality baseValueModality;

    @Enumerated(EnumType.STRING)
    @Column(name = "base_value_type", columnDefinition = "text", nullable = false)
    private EOperationBaseType baseValueType;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="PIS">
    @ColumnDefault("0.00")
    @Column(name = "pis_base_value", precision = 10, scale = 2, nullable = false)
    private BigDecimal pisBaseValue;

    @ColumnDefault("0.00")
    @Column(name = "pis_rate_value", precision = 10, scale = 2, nullable = false)
    private BigDecimal pisRateValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pis_cst")
    private Cst pisCst;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="COFINS">
    @ColumnDefault("0.00")
    @Column(name = "cofins_base_value", precision = 10, scale = 2, nullable = false)
    private BigDecimal cofinsBaseValue;

    @ColumnDefault("0.00")
    @Column(name = "cofins_rate_value", precision = 10, scale = 2, nullable = false)
    private BigDecimal cofinsRateValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cofins_cst")
    private Cst cofinsCst;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Notes">
    @Column(name = "note_body", columnDefinition = "text")
    private String noteBody;

    @Column(name = "note_extra", columnDefinition = "text")
    private String noteExtra;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Foreign Keys">
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Company.class)
    @JoinColumn(name = "operation_type")
    @NotFound(action = NotFoundAction.IGNORE)
    private OperationType operationType;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Organization.class)
    @JoinColumn(name = "organization", nullable = false, updatable = false)
    private Organization organization;
    // </editor-fold>

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

    public BigDecimal getEstimatedTax() {
        return estimatedTax;
    }

    public void setEstimatedTax(BigDecimal estimatedTax) {
        this.estimatedTax = estimatedTax;
    }

    public EOperationSearchType getSearchType() {
        return searchType;
    }

    public void setSearchType(EOperationSearchType searchType) {
        this.searchType = searchType;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Product getSearchTypeProduct() {
        return searchTypeProduct;
    }

    public void setSearchTypeProduct(Product searchTypeProduct) {
        this.searchTypeProduct = searchTypeProduct;
    }

    public ProductGroup getSearchTypeProductGroup() {
        return searchTypeProductGroup;
    }

    public void setSearchTypeProductGroup(ProductGroup searchTypeProductGroup) {
        this.searchTypeProductGroup = searchTypeProductGroup;
    }

    public State getStateCompany() {
        return stateCompany;
    }

    public void setStateCompany(State stateCompany) {
        this.stateCompany = stateCompany;
    }

    public State getStateCostumer() {
        return stateCostumer;
    }

    public void setStateCostumer(State stateCostumer) {
        this.stateCostumer = stateCostumer;
    }

    public Cfop getCfopInsideState() {
        return cfopInsideState;
    }

    public void setCfopInsideState(Cfop cfopInsideState) {
        this.cfopInsideState = cfopInsideState;
    }

    public Cfop getCfopOutsideState() {
        return cfopOutsideState;
    }

    public void setCfopOutsideState(Cfop cfopOutsideState) {
        this.cfopOutsideState = cfopOutsideState;
    }

    public Cfop getCfopAbroad() {
        return cfopAbroad;
    }

    public void setCfopAbroad(Cfop cfopAbroad) {
        this.cfopAbroad = cfopAbroad;
    }

    public RestrictionTax getRestrictionTax() {
        return restrictionTax;
    }

    public void setRestrictionTax(RestrictionTax restrictionTax) {
        this.restrictionTax = restrictionTax;
    }

    public BigDecimal getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(BigDecimal baseValue) {
        this.baseValue = baseValue;
    }

    public BigDecimal getRateValue() {
        return rateValue;
    }

    public void setRateValue(BigDecimal rateValue) {
        this.rateValue = rateValue;
    }

    public EOperationICMSType getIcmsType() {
        return icmsType;
    }

    public void setIcmsType(EOperationICMSType icmsType) {
        this.icmsType = icmsType;
    }

    public Cst getCst() {
        return cst;
    }

    public void setCst(Cst cst) {
        this.cst = cst;
    }

    public Cst getCsosn() {
        return csosn;
    }

    public void setCsosn(Cst csosn) {
        this.csosn = csosn;
    }

    public BigDecimal getMvaBaseValue() {
        return mvaBaseValue;
    }

    public void setMvaBaseValue(BigDecimal mvaBaseValue) {
        this.mvaBaseValue = mvaBaseValue;
    }

    public BigDecimal getMvaRateValue() {
        return mvaRateValue;
    }

    public void setMvaRateValue(BigDecimal mvaRateValue) {
        this.mvaRateValue = mvaRateValue;
    }

    public EOperationBaseModality getBaseValueModality() {
        return baseValueModality;
    }

    public void setBaseValueModality(EOperationBaseModality baseValueModality) {
        this.baseValueModality = baseValueModality;
    }

    public EOperationBaseType getBaseValueType() {
        return baseValueType;
    }

    public void setBaseValueType(EOperationBaseType baseValueType) {
        this.baseValueType = baseValueType;
    }

    public BigDecimal getPisBaseValue() {
        return pisBaseValue;
    }

    public void setPisBaseValue(BigDecimal pisBaseValue) {
        this.pisBaseValue = pisBaseValue;
    }

    public BigDecimal getPisRateValue() {
        return pisRateValue;
    }

    public void setPisRateValue(BigDecimal pisRateValue) {
        this.pisRateValue = pisRateValue;
    }

    public Cst getPisCst() {
        return pisCst;
    }

    public void setPisCst(Cst pisCst) {
        this.pisCst = pisCst;
    }

    public BigDecimal getCofinsBaseValue() {
        return cofinsBaseValue;
    }

    public void setCofinsBaseValue(BigDecimal cofinsBaseValue) {
        this.cofinsBaseValue = cofinsBaseValue;
    }

    public BigDecimal getCofinsRateValue() {
        return cofinsRateValue;
    }

    public void setCofinsRateValue(BigDecimal cofinsRateValue) {
        this.cofinsRateValue = cofinsRateValue;
    }

    public Cst getCofinsCst() {
        return cofinsCst;
    }

    public void setCofinsCst(Cst cofinsCst) {
        this.cofinsCst = cofinsCst;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }

    public String getNoteExtra() {
        return noteExtra;
    }

    public void setNoteExtra(String noteExtra) {
        this.noteExtra = noteExtra;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public static OperationRule fromJSON(JSONObject json) {
        OperationRule response = new OperationRule();

        //Principal
        response.setId(json.optLong("id", 0));

        response.setSearchType(EOperationSearchType.valueOf(json.optString("search_type", "NONE")));

        response.description = json.getString("description");
        response.estimatedTax = json.getBigDecimal("estimated_tax");

        Person person = new Person();
        person.setId(json.optLong("person", 0));
        response.setPerson(person);

        Product product = new Product();
        product.setId(json.optLong("product", 0));
        response.setSearchTypeProduct(product);

        ProductGroup productGroup = new ProductGroup();
        productGroup.setId(json.optLong("product_group", 0));
        response.setSearchTypeProductGroup(productGroup);

        State stateCompany = new State();
        stateCompany.setCode(json.optString("state_company"));
        response.setStateCompany(stateCompany);

        State stateCostumer = new State();
        stateCostumer.setCode(json.optString("state_costumer"));
        response.setStateCostumer(stateCostumer);

        RestrictionTax restrictionTax = new RestrictionTax();
        restrictionTax.setId(json.optLong("restriction_tax", 0));
        response.setRestrictionTax(restrictionTax);

        Cfop cfopInsideState = new Cfop();
        cfopInsideState.setCode(json.optString("cfop_inside_state"));
        response.setCfopInsideState(cfopInsideState);

        Cfop cfopOutsideState = new Cfop();
        cfopOutsideState.setCode(json.optString("cfop_outside_state"));
        response.setCfopOutsideState(cfopOutsideState);

        Cfop cfopAbroad = new Cfop();
        cfopAbroad.setCode(json.optString("cfop_abroad"));
        response.setCfopAbroad(cfopAbroad);

        //ICMS
        Cst cst = new Cst();
        cst.setCode(json.optString("cst"));
        response.setCst(cst);

        Cst csosn = new Cst();
        csosn.setCode(json.optString("csosn"));
        response.setCsosn(csosn);

        response.setBaseValue(json.optBigDecimal("base_value", new BigDecimal(0.0)));
        response.setRateValue(json.optBigDecimal("rate_value", new BigDecimal(0.0)));
        response.setIcmsType(EOperationICMSType.valueOf(json.optString("icms_type", "FREE")));

        //ICMS-ST
        response.setBaseValueModality(EOperationBaseModality.getByCode(json.optString("base_value_modality", "0")));
        response.setBaseValueType(EOperationBaseType.valueOf(json.optString("base_value_type", "ICMS_PLUS_ICMS_ST")));
        response.setMvaBaseValue(json.optBigDecimal("mva_base_value", new BigDecimal(0.0)));
        response.setMvaRateValue(json.optBigDecimal("mva_rate_value", new BigDecimal(0.0)));

        //PIS
        Cst pisCst = new Cst();
        pisCst.setCode(json.optString("pis_cst"));
        response.setPisCst(pisCst);

        response.setPisBaseValue(json.optBigDecimal("pis_base_value", new BigDecimal(0.0)));
        response.setPisRateValue(json.optBigDecimal("pis_rate_value", new BigDecimal(0.0)));

        //COFINS
        Cst cofinsCst = new Cst();
        cofinsCst.setCode(json.optString("cofins_cst"));
        response.setCofinsCst(cofinsCst);

        response.setCofinsBaseValue(json.optBigDecimal("cofins_base_value", new BigDecimal(0.0)));
        response.setCofinsRateValue(json.optBigDecimal("cofins_rate_value", new BigDecimal(0.0)));

        //Observações
        response.setNoteBody(json.optString("note_body"));
        response.setNoteExtra(json.optString("note_extra"));

        //Foreign Keys
        OperationType operationType = new OperationType();
        operationType.setId(json.getLong("operation_type"));
        response.setOperationType(operationType);

        return response;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        //Principal
        object.put("id", this.id);
        object.put("description", this.description);
        object.put("estimated_tax", this.estimatedTax);
        object.put("search_type", this.searchType.name());
        if (this.person.getId() > 0) object.put("person", this.person.getId());
        if (this.searchTypeProduct.getId() > 0) object.put("product", this.searchTypeProduct.getId());
        if (this.searchTypeProductGroup.getId() > 0) object.put("product_group", this.searchTypeProductGroup.getId());
        if (this.stateCompany.getCode() != null) object.put("state_company", this.stateCompany.getCode());
        if (this.stateCostumer.getCode() != null) object.put("state_costumer", this.stateCostumer.getCode());
        if (this.restrictionTax.getId() > 0) object.put("restriction_tax", this.restrictionTax.getId());
        if (this.cfopInsideState.getCode() != null) object.put("cfop_inside_state", this.cfopInsideState.getCode());
        if (this.cfopOutsideState.getCode() != null) object.put("cfop_outside_state", this.cfopOutsideState.getCode());
        if (this.cfopAbroad.getCode() != null) object.put("cfop_abroad", this.cfopAbroad.getCode());

        //ICMS
        if (this.cst.getCode() != null) object.put("cst", this.cst.getCode());
        if (this.csosn.getCode() != null) object.put("csosn", this.csosn.getCode());
        object.put("base_value", this.baseValue);
        object.put("rate_value", this.rateValue);
        object.put("icms_type", this.icmsType.name());

        //ICMS-ST
        object.put("base_value_modality", this.baseValueModality.getCode());
        object.put("base_value_type", this.baseValueType.name());
        object.put("mva_base_value", this.mvaBaseValue);
        object.put("mva_rate_value", this.mvaRateValue);

        //PIS
        if (this.pisCst.getCode() != null) object.put("pis_cst", this.pisCst.getCode());
        object.put("pis_base_value", this.pisBaseValue);
        object.put("pis_rate_value", this.pisRateValue);

        //COFINS
        if (this.cofinsCst.getCode() != null) object.put("cofins_cst", this.cofinsCst.getCode());
        object.put("cofins_base_value", this.cofinsBaseValue);
        object.put("cofins_rate_value", this.cofinsRateValue);

        //Observações
        object.put("note_body", this.noteBody);
        object.put("note_extra", this.noteExtra);

        //Foreign Keys
        object.put("operation_type", this.operationType.getId());

        return object;
    }
}
