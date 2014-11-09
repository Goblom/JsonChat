/*
 * Copyright 2014 Goblom.
 * 
 * All Rights Reserved unless otherwise explicitly stated.
 */
package codes.goblom.jsonchat;

import lombok.RequiredArgsConstructor;
import com.google.common.collect.Lists;
import java.util.List;
import org.bukkit.entity.Player;

/**
 *
 * @author Goblom
 */
@RequiredArgsConstructor
class ModifierOutput {
    final Player player;
    List<String> output = Lists.newArrayList();
    List<ChatModifier> usedModifiers = Lists.newArrayList();
    
    ModifierOutput set(List<String> list) {
        output = list;
        return this;
    }
    
    String getLine(int line) {
        return output.get(line);
    }
}
