package com.saa.web.enumerated;

public enum EPersonCRT {
    CRT_1("1"),
    CRT_2("2"),
    CRT_3("3");

    private final String code;

    EPersonCRT(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static EPersonCRT get(String code) {
        for (EPersonCRT obj : EPersonCRT.values()) {
            if (obj.code.equals(code)) return obj;
        }
        return null;
    }
}
