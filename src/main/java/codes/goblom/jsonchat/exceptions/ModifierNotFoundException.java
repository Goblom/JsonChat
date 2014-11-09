/*
 * Copyright 2014 Goblom.
 * 
 * All Rights Reserved unless otherwise explicitly stated.
 */
package codes.goblom.jsonchat.exceptions;

/**
 *
 * @author Goblom
 */
public class ModifierNotFoundException extends Exception {
    
    public ModifierNotFoundException(String lookFor) {
        super("There is no Modifier looking for '" + lookFor + "'");
    }
}
