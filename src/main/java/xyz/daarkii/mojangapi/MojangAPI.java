package xyz.daarkii.mojangapi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.daarkii.mojangapi.cache.Cache;
import xyz.daarkii.mojangapi.cache.DefaultCache;
import xyz.daarkii.mojangapi.profile.Profile;
import xyz.daarkii.mojangapi.profile.Skin;

import java.util.UUID;

public class MojangAPI {

    private static final Mojang mojang = Mojang.getInstance();

    /**
     * Retrieves the uuid linked to a username
     *
     * @param name  the username of the player
     * @return the uuid or null if the username is not existing
     */
    @Nullable("if the username is not existing")
    public static UUID getUUID(@NotNull String name) {
        return mojang.getUUID(name);
    }

    /**
     * Retrieves the name linked to an uuid
     *
     * @param uuid  the uuid of the player
     * @return the name or null if the uuid is not existing
     */
    @Nullable("if the uuid is not existing")
    public static String getName(@NotNull UUID uuid) {
        return mojang.getName(uuid);
    }

    /**
     * Retrieves a {@link Skin} object with the data linked to a username
     *
     * @param name  the username of the player
     * @return a {@link Skin} object or null if the username is not existing
     */
    @Nullable("if the username is not existing")
    public static Skin getSkin(@NotNull String name) {
        return mojang.getSkin(name);
    }

    /**
     * Retrieves a {@link Skin} object with the data linked to an uuid
     *
     * @param uuid  the uuid of the player
     * @return a {@link Skin} object or null if the uuid is not existing
     */
    @Nullable("if the uuid is not existing")
    public static Skin getSkin(@NotNull UUID uuid) {
        return mojang.getSkin(uuid);
    }

    /**
     * Retrieves a {@link Profile} object with the data linked to a username
     *
     * @param name  the name of the player
     * @return a {@link Profile} object or null if the username is not existing
     */
    @Nullable("if the username is not existing")
    public static Profile getProfile(@NotNull String name) {
        return mojang.getProfile(name);
    }

    /**
     * Retrieves a {@link Profile} object with the data linked to an uuid
     *
     * @param uuid  the username of the player
     * @return a {@link Profile} object or null if the uuid is not existing
     */
    @Nullable("if the uuid is not existing")
    public static Profile getProfile(@NotNull UUID uuid) {
        return mojang.getProfile(uuid);
    }

    /**
     * Gets the currently used {@link Cache} object
     * Should be called after {@link #connect()} was called
     *
     * @return the {@link Cache} object
     */
    public static Cache getCache() {
        return mojang.getCache();
    }

    /**
     * Sets the currently used {@link Cache} object
     * Should be called before {@link #connect()} is called
     *
     * @param cache the new {@link Cache} object
     */
    public static void setCache(@NotNull Cache cache) {
        mojang.setCache(cache);
    }

    /**
     * Sets variables for the connection to the Mojang API,
     * If there is no {@link Cache} object set, we use the {@link DefaultCache} class as the cache object
     */
    public static void connect() {
        mojang.connect();
    }
}
