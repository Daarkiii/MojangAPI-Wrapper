package xyz.daarkii.mojangapi.profile;

import org.jetbrains.annotations.NotNull;
import xyz.daarkii.mojangapi.util.Property;

import java.util.Set;
import java.util.UUID;

/**
 * Holds metadata of players
 */
public class Profile {

    private final String uuid;
    private final String name;
    private final Skin skin;
    private final Set<Property> properties;

    public Profile(String uuid, String name, Set<Property> properties) {
        this.uuid = uuid;
        this.name = name;
        this.properties = properties;
        this.skin = this.properties.stream().filter(property -> property.getName().equalsIgnoreCase("textures")).map(Skin::fromProperty).findAny().orElse(null);
    }

    /**
     * Gets the {@link UUID} of the player
     *
     * @return the uuid
     */
    @NotNull
    public UUID getUUID() {
        return UUID.fromString(
                this.uuid.replaceFirst(
                "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5"));
    }

    /**
     * Gets the username of the player as {@link String}
     *
     * @return the username
     */
    @NotNull
    public String getName() {
        return this.name;
    }

    /**
     * Gets a {@link Skin} object from the player skin data
     *
     * @return the skin object
     */
    @NotNull
    public Skin getSkin() {
        return this.skin;
    }

    /**
     * Gets a {@link Set} Set with the loaded properties from the player
     *
     * @return the properties
     */
    @NotNull
    public Set<@NotNull Property> getProperties() {
        return this.properties;
    }

}
