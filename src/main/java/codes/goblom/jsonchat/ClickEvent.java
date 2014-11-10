package codes.goblom.jsonchat;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
 * Copyright 2014 Goblom.
 * 
 * All Rights Reserved unless otherwise explicitly stated.
 */
/**
 *
 * @author Goblom
 */
@RequiredArgsConstructor
public class ClickEvent {
    @Getter
    private final ClickAction clickAction;
    
    @Getter
    private final String value;
    
    public static enum ClickAction {
        RUN_COMMAND,
        SUGGEST_COMMAND,
        OPEN_URL,
        OPEN_FILE,
    }
}
