package com.saa.web.enumerated;

public enum ESchema {
    DOCUMENT_INSTALLMENT("/schemas/document_installment.schema.json"),
    DOCUMENT_ITEM("/schemas/document_item.schema.json"),
    DOCUMENT("/schemas/document.schema.json"),
    SETTING("/schemas/setting.schema.json"),
    NUMERATOR("/schemas/numerator.schema.json"),
    OPERATION_RULE("/schemas/operation_rule.schema.json"),
    OPERATION_TYPE("/schemas/operation_type.schema.json"),
    NEGATIVE_CERTIFICATE("/schemas/negative_certificate.schema.json"),
    VEHICLE("/schemas/vehicle.schema.json"),
    PERSON("/schemas/person.schema.json"),
    LOGIN("/schemas/login.schema.json"),
    COMPANY("/schemas/company.schema.json"),
    USER("/schemas/user.schema.json"),
    RESTRICTION_TAX("/schemas/restriction_tax.schema.json"),
    CITY("/schemas/city.schema.json"),
    STATE("/schemas/state.schema.json"),
    COUNTRY("/schemas/country.schema.json"),
    PRODUCT("/schemas/product.schema.json"),
    UNIT("/schemas/unit.schema.json"),
    PRODUCT_GROUP("/schemas/product_group.schema.json"),
    PERSON_GROUP("/schemas/person_group.schema.json"),
    CFOP("/schemas/cfop.schema.json"),
    OTHER_TAX("/schemas/other_tax.schema.json"),
    CST("/schemas/cst.schema.json"),
    PAYMENT_TYPE("/schemas/payment_type.schema.json"),
    CURRENCY("/schemas/currency.schema.json"),
    RULING_FISCAL("/schemas/ruling_fiscal.schema.json"),
    VALIDITY_OTHER_TAX("/schemas/duration_other_tax.schema.json"),
    VALIDITY_STATE_UPF("/schemas/validity_state_upf.schema.json"),
    NFE_REQUEST("/schemas/nfe_request.schema.json"),
    APPLIANCE_FIELD_TYPE("/schemas/appliance_field_type.schema.json"),
    APPLIANCE_VEHICLE_TYPE("/schemas/appliance_vehicle_type.schema.json"),
    PLOT_TYPE("/schemas/plot_type.schema.json"),
    SERVICE_TYPE("/schemas/service_type.schema.json"),
    HARVEST("/schemas/harvest.schema.json"),
    HARVEST_CONFIGURATION("/schemas/harvest_configuration.schema.json"),
    CONTRACT_TYPE("/schemas/contract_type.schema.json"),
    CYCLE("/schemas/cycle.schema.json"),
    PLOT("/schemas/plot.schema.json"),
    DISCOUNT_TYPE("/schemas/discount_type.schema.json"),
    EXCHANGE_CONFIGURATION("/schemas/exchange_configuration.schema.json"),
    WORK_ON_VEHICLE("/schemas/work_on_vehicle.schema.json");

    private final String schemaFile;

    ESchema(String schemaFile) {
        this.schemaFile = schemaFile;
    }

    public String getFile() {
        return this.schemaFile;
    }
}
