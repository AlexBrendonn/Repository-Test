package com.saa.web.utils;

import com.saa.web.enumerated.ESchema;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.ws.rs.BadRequestException;
import java.io.InputStream;

public class JSONSchema {
    public void valid(ESchema schema, JSONObject json) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(schema.getFile());
            JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
            Schema instance = SchemaLoader.load(rawSchema);
            instance.validate(json);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public JSONObject valid(ESchema schema, String content) {
        try {
            JSONObject json = new JSONObject(content);
            InputStream inputStream = getClass().getResourceAsStream(schema.getFile());
            JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
            Schema instance = SchemaLoader.load(rawSchema);
            instance.validate(json);
            return json;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
