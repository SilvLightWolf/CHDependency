package com.slw.event.betonquest;

import com.laytonsmith.core.events.BindableEvent;
import pl.betoncraft.betonquest.conversation.Conversation;

/**
 * Created by User on 2017-08-19.
 */
public class BetonquestInterface {

    public interface CHUConversationStartInterface extends BindableEvent{

        public Conversation getConversation();
    }
}
