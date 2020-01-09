package com.saa.web.enumerated;

public enum ERulingFiscalFreight {
    NONE(null),
    TYPE_0("0"),
    TYPE_1("1"),
    TYPE_2("2"),
    TYPE_3("3"),
    TYPE_4("4"),
    TYPE_9("9");


    private final String code;

    ERulingFiscalFreight(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static ERulingFiscalFreight get(String code) {

        for (ERulingFiscalFreight freightType : ERulingFiscalFreight.values()) {
            if (freightType.code.equals(code)) return freightType;
        }

        return null;
    }
}
