/*
 * Copyright 2014 Goblom.
 * 
 * All Rights Reserved unless otherwise explicitly stated.
 */
package codes.goblom.jsonchat;

import codes.goblom.jsonchat.exceptions.InvalidModifierException;
import codes.goblom.jsonchat.exceptions.ModifierNotFoundException;
import java.util.Map;
import com.google.common.collect.Maps;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Goblom
 */
public class JSONChat {
    static final Map<String, ChatModifier> MODIFIERS = Maps.newConcurrentMap();
    static final Map<UUID, FormatablePlayer> PLAYERS = Maps.newConcurrentMap();
    
    protected static JavaPlugin PLUGIN;
    
    public static ChatModifier getModifier(String lookingFor) throws ModifierNotFoundException {
        lookingFor = ChatColor.stripColor(lookingFor);
        if (MODIFIERS.containsKey(lookingFor)) {
            return MODIFIERS.get(lookingFor);
        }
        
        throw new ModifierNotFoundException(lookingFor);
    }
    
    public static ChatModifier removeModifier(String key) {
        return MODIFIERS.remove(key);
    }
    
    public static void registerModifier(ChatModifier mod) throws InvalidModifierException {
        if (mod.getLookingFor().isEmpty()) {
            throw new InvalidModifierException("Invalid search pattern for Modifier " + mod.getClass().getSimpleName());
        }
        
        if (mod.getDescription().isEmpty()) {
            throw new InvalidModifierException("Description for '" + mod.getLookingFor() + "' is empty");
        }
        
        if (!MODIFIERS.containsKey(mod.getLookingFor())) {
            MODIFIERS.put(mod.getLookingFor(), mod);
            PLUGIN.getLogger().info("Registered Modifier from plugin '" + mod.getProvidingPlugin() +"' with key: {" + mod.getLookingFor() + "}");
        } else {
            throw new InvalidModifierException("Modifier '" + mod.getLookingFor() + "' is already registered");
        }
    }
    
    public static Collection<ChatModifier> getRegisteredModifiers() {
        return Collections.unmodifiableCollection(MODIFIERS.values());
    }
    
    public static String getDefaultNameFormat() {
        return getConfigOption("Format.Name", "<{name}> ");
    }
    
    public static List<String> getDefaultTooltipFormat() {
        return getConfigOption("Format.Tooltip", Arrays.asList("UUID: {uuid}"));
    }
    
    public static boolean getSetting(String name) {
        return getConfigOption("Setting." + name, true);
    }
    
    protected static <T> T getConfigOption(String path, T def) {
        FileConfiguration config = PLUGIN.getConfig();
        
        if (!config.contains(path)) {
            config.set(path, def);
            PLUGIN.saveConfig();
        }
        
        return (T) config.get(path);
    }
    
    public static FormatablePlayer getFormatablePlayer(Player player) {
        if (PLAYERS.containsKey(player.getUniqueId())) {
            return PLAYERS.get(player.getUniqueId());
        }
        
        FormatablePlayer fp = new FormatablePlayer(player);
                         fp.setNameFormat(getDefaultNameFormat());
                         fp.setTooltip(getDefaultTooltipFormat());
                         
        PLAYERS.put(player.getUniqueId(), fp);
        return fp;
    }
}
