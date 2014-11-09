/*
 * Copyright 2014 Goblom.
 * 
 * All Rights Reserved unless otherwise explicitly stated.
 */
package codes.goblom.jsonchat;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 *
 * @author Goblom
 */
public class FormatablePlayer {
    @Getter
    private final UUID uniqueId;
    
    @Getter
    @Setter
    private String nameFormat;
    
    @Getter
    @Setter
    private List<String> tooltip;
    
    FormatablePlayer(Player player) {
        this.uniqueId = player.getUniqueId();
    }
    
    public Player getBukkit() {
        return Bukkit.getPlayer(uniqueId);
    }
    
    /**
     * A non-colored formatted name
     */
    public String getFormattedNameFormat() {
        if (getBukkit() == null) {
            return getNameFormat();
        }
        
        return modify(getBukkit(), Arrays.asList(getNameFormat())).getLine(0);
    }
    
    /**
     * A non-colored formatted tooltip
     */
    public List<String> getFormattedTooltip() {
        if (getBukkit() == null) {
            return getTooltip();
        }
        
        return modify(getBukkit(), tooltip).output;
    }
    
    // ********************************
    // This is required in order to not have to include JSONChatPlugin in the GitHub repo
    // ********************************
    
    private static Method modify;
    
    static {
        try {
            Class<?> JSONChatPlugin = Class.forName("codes.goblom.jsonchat.JSONChatPlugin");
            
            modify = JSONChatPlugin.getDeclaredMethod("modify", Player.class, List.class);
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    private static ModifierOutput modify(Player player, List<String> lines) {
        if (modify == null) {
            return new ModifierOutput(player).set(lines);
        }
        
        try {
            return (ModifierOutput) modify.invoke(null, player, lines);
        } catch (Exception e) { }
        return new ModifierOutput(player).set(lines);
    }
}
