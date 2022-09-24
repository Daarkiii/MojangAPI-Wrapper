package xyz.daarkii.mojangapi.cache;

import kong.unirest.json.JSONObject;
import org.jetbrains.annotations.NotNull;

/** Represents an object which is stored in the {@link DefaultCache} */
public class CacheObject {

    private final JSONObject object;
    private final long end;

    public CacheObject(long end, @NotNull JSONObject object) {
        this.end = end;
        this.object = object;
    }

    public JSONObject getObject() {
        return this.object;
    }

    public long getEnd() {
        return this.end;
    }
}
