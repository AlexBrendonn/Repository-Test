package com.saa.web.entity.authentication;

import org.json.JSONObject;

public class SignIn {
    private String organization;
    private String password;
    private String nickname;

    public static SignIn fromJSON(JSONObject json){
        SignIn object = new SignIn();

        object.organization = json.getString("organization");
        object.password = json.getString("password");
        object.nickname = json.getString("nickname");

        return object;
    }
}
