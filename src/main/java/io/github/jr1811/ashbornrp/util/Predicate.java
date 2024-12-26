package io.github.jr1811.ashbornrp.util;

import com.google.gson.JsonObject;

public record Predicate(String key, Float value) {
    public void addToJson(JsonObject predicateJson) {
        predicateJson.addProperty(key, value);
    }
}