package com.slw.event.betonquest;

import pl.betoncraft.betonquest.api.PlayerConversationStartEvent;
import pl.betoncraft.betonquest.conversation.Conversation;

/**
 * Created by User on 2017-08-19.
 */
public class BetonquestAPI {

    public static class CHUConversationStartEvent implements BetonquestInterface.CHUConversationStartInterface {
        PlayerConversationStartEvent e;

        public CHUConversationStartEvent(PlayerConversationStartEvent e){
            this.e = e;

        }

        public Object _GetObject() {
            return null;
        }

        public Conversation getConversation() {
            return e.getConversation();
        }
    }
}
