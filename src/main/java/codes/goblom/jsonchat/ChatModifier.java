/*
 * Copyright 2014 Goblom.
 * 
 * All Rights Reserved unless otherwise explicitly stated.
 */
package codes.goblom.jsonchat;

import lombok.Getter;
import lombok.NonNull;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Goblom
 */
public abstract class ChatModifier {
    private final String lookingFor, description, providingPlugin;
    @Getter
    boolean disabled;
    
    public ChatModifier(@NonNull Plugin plugin, @NonNull String lookFor, @NonNull String description) {
        this.providingPlugin = plugin.getName().replace("_", " ");
        this.lookingFor = ChatColor.stripColor(lookFor);
        this.description = ChatColor.stripColor(description);
    }
    
    public final String getLookingFor() {
        return this.lookingFor;
    }
    
    public final String getDescription() {
        return this.description;
    }
    
    public final String getProvidingPlugin() {
        return this.providingPlugin;
    }
    
    public abstract String modify(Player speaker);
}
