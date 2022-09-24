package xyz.daarkii.mojangapi.util;

import kong.unirest.json.JSONObject;
import org.jetbrains.annotations.NotNull;

/**
 * This class is a copy of the property class from Mojang, it is used to decode objects from the retrieved {@link JSONObject} objects
 */
public class Property {

    private String name;
    private String value;
    private String signature;

    @NotNull
    public String getName() {
        return this.name;
    }

    @NotNull
    public String getValue() {
        return this.value;
    }

    @NotNull
    public String getSignature() {
        return this.signature;
    }
}
