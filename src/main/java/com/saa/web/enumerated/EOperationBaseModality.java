package com.saa.web.enumerated;

public enum EOperationBaseModality {
    BASE_VALUE_MODALITY_0("0"),
    BASE_VALUE_MODALITY_1("1"),
    BASE_VALUE_MODALITY_2("2"),
    BASE_VALUE_MODALITY_3("3"),
    BASE_VALUE_MODALITY_4("4"),
    BASE_VALUE_MODALITY_5("5");

    private final String code;

    EOperationBaseModality(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static EOperationBaseModality getByCode(String code) {

        for (EOperationBaseModality object : EOperationBaseModality.values()) {
            if (object.code.equals(code)) return object;
        }

        return null;
    }
}
