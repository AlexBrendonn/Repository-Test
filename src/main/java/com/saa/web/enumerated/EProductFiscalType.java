package com.saa.web.enumerated;

public enum EProductFiscalType {
    FISCAL_TYPE_00("00"),
    FISCAL_TYPE_01("01"),
    FISCAL_TYPE_02("01"),
    FISCAL_TYPE_03("03"),
    FISCAL_TYPE_04("04"),
    FISCAL_TYPE_05("05"),
    FISCAL_TYPE_06("06"),
    FISCAL_TYPE_07("07"),
    FISCAL_TYPE_08("08"),
    FISCAL_TYPE_09("09"),
    FISCAL_TYPE_10("10"),
    FISCAL_TYPE_99("99");

    private final String code;

    EProductFiscalType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static EProductFiscalType get(String code) {
        for (EProductFiscalType fiscalType : EProductFiscalType.values()) {
            if (fiscalType.code.equals(code)) return fiscalType;
        }
        return null;
    }
}
