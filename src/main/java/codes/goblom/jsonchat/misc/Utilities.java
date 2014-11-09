/*
 * Copyright 2014 Goblom.
 * 
 * All Rights Reserved unless otherwise explicitly stated.
 */
package codes.goblom.jsonchat.misc;

import org.bukkit.ChatColor;

/**
 *
 * @author Goblom
 */
@Deprecated // move away from this in future releases
public class Utilities {
    
    public static class Color {
        
        public static String toColor(String str) {
            return ChatColor.translateAlternateColorCodes('&', str);
        }
        
        public static String toString(ChatColor color) {
            switch (color) {
                case MAGIC:
                    return "obfuscated";
                default:
                    return color.toString().toLowerCase();
            }
        }
        
        public static String COLOR_STRING = "§";
        public static char COLOR_CHAR = '§';
        
        public static ChatColor getFirstColor(String str) {
            boolean next = false;
            for (char c : str.toCharArray()) {
                if (next) {
                    return getByChar(c);
                } else if (COLOR_STRING.equals(String.valueOf(c)) || COLOR_CHAR == c) {
                    next = true;
                }
            }
            
            return ChatColor.WHITE;
        }
        
        public static ChatColor getByChar(char c) {
            ChatColor color = ChatColor.getByChar(c);
            
            return (color == null ? ChatColor.WHITE : color);
        }
        
        public static String strip(String str) {
            return ChatColor.stripColor(str);
        }
        
        public static boolean isColor(String s) {
            ChatColor color = ChatColor.getByChar(s);
            
            if (color != null) {
                return color.isColor();
            }
            
            return false;
        }
        
        public static boolean isColor(char c) {
            ChatColor color = ChatColor.getByChar(c);
            
            if (color != null) {
                return color.isColor();
            }
            
            return false;
        }
        
        public static boolean isStyle(String s) {
            ChatColor color = ChatColor.getByChar(s);
            
            if (color != null) {
                return color.isFormat();
            }
            
            return false;
        }
        
        public static boolean isStyle(char c) {
            ChatColor color = ChatColor.getByChar(c);
            
            if (color != null) {
                return color.isFormat();
            }
            
            return false;
        }
    }
}
