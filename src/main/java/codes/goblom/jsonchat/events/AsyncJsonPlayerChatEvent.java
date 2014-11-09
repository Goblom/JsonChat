/*
 * Copyright (C) 2014 Goblom
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package codes.goblom.jsonchat.events;

import java.util.Set;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 *
 * @author Goblom
 */
public class AsyncJsonPlayerChatEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final Player who;
    private boolean cancel = false;
    private final Set<Player> recipients;
    private String message;
    
    @Override
    public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
    
    public AsyncJsonPlayerChatEvent(Player who, String message, Set<Player> recipients) {
        super(true);
        
        this.who = who;
        this.message = message;
        this.recipients = recipients;
    }
    
    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public Player getPlayer() {
        return this.who;
    }
    
    public String getMessage() {
        return message;
    }
    
    public Set<Player> getRecipients() {
        return recipients;
    }
}
