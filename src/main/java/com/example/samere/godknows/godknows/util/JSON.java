package com.example.samere.godknows.godknows.util;

import org.json.simple.JSONObject;

public class JSON {

    private JSONObject jsonObject;

    public JSON(String key, String value) {
        jsonObject = new JSONObject();
        jsonObject.put(key, value);
    }

    public JSONObject getJSON() {return jsonObject;}

}
