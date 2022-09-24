package xyz.daarkii.mojangapi.cache;

import kong.unirest.json.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.daarkii.mojangapi.MojangAPI;

/**
 * This class stores all values that have been loaded via the {@link MojangAPI} class for a certain time period
 */
public interface Cache {

    /**
     * Stores the given values with the {@link #getDefaultTimeOut()} value in the cache
     *
     * @param key   the key where the value is stored
     * @param node  the value which will be stored
     */
    void add(@NotNull String key, @NotNull JSONObject node);

    /**
     * Stores the given values in the cache
     *
     * @param key   the key where the value is stored
     * @param node  the value which will be stored
     * @param end   milliseconds until the value is ignored
     */
    void add(@NotNull String key, @NotNull JSONObject node, long end);

    /**
     * Removes a stored value from the cache
     *
     * @param key   the key where the value is stored
     */
    void remove(@NotNull String key);

    /**
     * Retrieves a {@link JSONObject} stored with the key from the cache
     *
     * @param key   the key where the value is stored
     * @return the {@link JSONObject} or null if it's not in the cache
     */
    @Nullable("if it's not in the cache")
    JSONObject get(@NotNull String key);

    /**
     * Checks if the cache contains a {@link JSONObject} and if the timeout is still valid
     *
     * @param key   the key where the value is stored
     * @return true if the {@link JSONObject} is still valid
     */
    boolean checkValidate(@NotNull String key);

    /**
     * Gets the currently set timeout for the validation of in cache stored objects
     *
     * @return the timeout
     */
    long getDefaultTimeOut();

    /**
     * Sets the timeout for the validation of in cache stored objects
     *
     * @param value the timeout
     */
    void setDefaultTimeOut(long value);

}
