package xyz.daarkii.mojangapi.profile;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.codec.binary.Base64;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.daarkii.mojangapi.util.Property;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

/**
 * Holds metadata of the modal of a player
 */
public class Skin {

    @Nullable
    private final URL url;

    @Nullable
    private final URL cape;

    @NotNull
    private final String value;

    @NotNull
    private final String signature;

    public Skin(@NotNull String value, @NotNull String signature, @Nullable URL url, @Nullable URL cape) {
        this.url = url;
        this.cape = cape;
        this.value = value;
        this.signature = signature;
    }

    /**
     * Gets the encoded {@link Base64} value string of a
     * player modal which is used by Mojang
     *
     * @return the value as {@link String}
     */
    @NotNull
    public String getValue() {
        return this.value;
    }

    /**
     * Gets the encoded {@link Base64} signature string of a
     * player modal which is used by Mojang
     *
     * @return the signature as {@link String}
     */
    @NotNull
    public String getSignature() {
        return this.signature;
    }

    /**
     * Gets an {@link Optional<URL>} object which holds the url to the players skin picture
     *
     * @return the optional can be null if the player never set a custom skin
     */
    public Optional<URL> getURL() {
        return Optional.ofNullable(this.url);
    }

    /**
     * Gets an {@link Optional<URL>} object which holds the url to the players cape picture
     *
     * @return the optional can be null if the player has not a cape
     */
    public Optional<URL> getCape() {
        return Optional.ofNullable(this.cape);
    }

    /**
     * Wraps the given {@link Property} to a {@link Skin} object
     *
     * @param property  the texture property from the player
     * @return the skin object
     */
    public static Skin fromProperty(Property property) {

        String value = property.getValue();
        String signature = property.getSignature();

        URL skin = null;
        URL cape = null;

        JsonObject object = JsonParser.parseString(new String(Base64.decodeBase64(value))).getAsJsonObject();
        JsonObject textures = object.getAsJsonObject("textures");

        if(textures.has("SKIN")) {
            System.out.println("has skin");
            JsonObject skinObject = textures.getAsJsonObject("SKIN");
            try {
                skin = new URL(skinObject.get("url").getAsString());
            } catch (MalformedURLException e) {
                //ignore
            }
        }

        if(textures.has("CAPE")) {
            System.out.println("has cape");
            JsonObject skinObject = textures.getAsJsonObject("CAPE");
            try {
                cape = new URL(skinObject.get("url").getAsString());
            } catch (MalformedURLException e) {
                //ignore
            }
        }

        return new Skin(value, signature, skin, cape);
    }
}
