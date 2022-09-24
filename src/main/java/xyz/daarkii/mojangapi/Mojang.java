package xyz.daarkii.mojangapi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.daarkii.mojangapi.cache.Cache;
import xyz.daarkii.mojangapi.cache.DefaultCache;
import xyz.daarkii.mojangapi.profile.Profile;
import xyz.daarkii.mojangapi.profile.Skin;
import xyz.daarkii.mojangapi.util.Property;

import java.util.Set;
import java.util.UUID;

@ApiStatus.Internal
public class Mojang {

    private static Mojang instance;

    private boolean connected = false;
    private Cache cache;

    public void connect() {
        this.connected = true;
        this.cache = this.cache != null ? this.cache : new DefaultCache();
    }

    @Nullable
    public UUID getUUID(@NotNull String name) {
        this.checkConnection();

        String key = "name-" + name;
        JSONObject object = null;

        if(this.cache.checkValidate(key))
            object = this.cache.get(key);

        if(object == null) {
            object = Unirest.get("https://api.mojang.com/users/profiles/minecraft/" + name)
                    .asJson()
                    .getBody()
                    .getObject();

            this.cache.add(key, object);
        }

        return !object.has("id") ? null : UUID.fromString(
                object.getString("id").replaceFirst(
                        "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5"));
    }

    @Nullable
    public String getName(@NotNull UUID uuid) {
        Profile profile = this.getProfile(uuid);
        return profile == null ? null : profile.getName();
    }

    @Nullable
    public Skin getSkin(@NotNull String name) {
        UUID uuid = this.getUUID(name);
        return uuid != null ? this.getSkin(uuid) : null;
    }

    @Nullable
    public Skin getSkin(@NotNull UUID uuid) {
        Profile profile = this.getProfile(uuid);
        return profile == null ? null : profile.getSkin();
    }

    @Nullable
    public Profile getProfile(@NotNull String name) {
        UUID uuid = this.getUUID(name);
        return uuid != null ? this.getProfile(uuid) : null;
    }

    @Nullable
    public Profile getProfile(@NotNull UUID uuid) {
        this.checkConnection();

        JSONObject object = null;
        String key = "profile-" + uuid;

        //check if the request is cached
        if(this.cache.checkValidate(key))
            object = this.cache.get(key);

        if(object == null) {
            //not cached so we need to request Mojang
            object = Unirest.get("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false")
                    .asJson()
                    .getBody()
                    .getObject();

            this.cache.add(key, object);
        }

        if(!object.has("id"))
            return null;

        String id = object.getString("id");
        String name = object.getString("name");
        Set<Property> properties = new Gson().fromJson(object.getJSONArray("properties").toString(), new TypeToken<Set<Property>>(){}.getType());

        return new Profile(id, name, properties);
    }

    @NotNull
    public Cache getCache() {
        return this.cache;
    }

    public void setCache(@NotNull Cache cache) {
        this.cache = cache;
    }

    private boolean isConnected() {
        return this.connected;
    }

    private void checkConnection() {
        if(!isConnected())
            this.connect();
    }

    /**
     * Checks if the class is initialized, if not it will create a new instance of the class
     *
     * @return an instance for this class
     */
    @NotNull
    public static Mojang getInstance() {

        if(instance == null)
            instance = new Mojang();



        return instance;
    }

}
