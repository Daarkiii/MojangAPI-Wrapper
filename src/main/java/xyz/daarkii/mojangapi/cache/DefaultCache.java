package xyz.daarkii.mojangapi.cache;

import kong.unirest.json.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * This is the default used {@link Cache}, it stores {@link CacheObject} objects in a default Java ConcurrentHashMap
 */
public class DefaultCache implements Cache {

    private final ConcurrentHashMap<@NotNull String, @NotNull CacheObject> cache;
    private long timeout;

    public DefaultCache() {
        this.cache = new ConcurrentHashMap<>();
        this.timeout = TimeUnit.MINUTES.toMillis(5);
    }

    @Override
    public void add(@NotNull String key, @NotNull JSONObject object) {
        this.cache.put(key, new CacheObject(System.currentTimeMillis() + timeout, object));
    }

    @Override
    public void add(@NotNull String key, @NotNull JSONObject object, long end) {
        this.cache.put(key, new CacheObject(end, object));
    }

    @Override
    public void remove(@NotNull String key) {
        this.cache.remove(key);
    }

    @Override
    @Nullable
    public JSONObject get(@NotNull String key) {
        CacheObject object = this.cache.get(key);

        if(object == null)
            return null;

        if(object.getEnd() < System.currentTimeMillis()) {
            this.remove(key);
            return null;
        }

        return object.getObject();
    }

    @Override
    public boolean checkValidate(@NotNull String key) {
        CacheObject object = this.cache.get(key);
        return object != null && object.getEnd() >= System.currentTimeMillis();
    }

    @Override
    public long getDefaultTimeOut() {
        return this.timeout;
    }

    @Override
    public void setDefaultTimeOut(long value) {
        this.timeout = value;
    }
}
