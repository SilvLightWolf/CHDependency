package com.slw.event.betonquest;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.bukkit.BukkitMCLocation;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.ObjectGenerator;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.events.AbstractEvent;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.exceptions.EventException;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;
import pl.betoncraft.betonquest.conversation.Conversation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 2017-08-19.
 */
public class BetonquestDriver {

    @api
    public static class CHUConversationStartAPI extends AbstractEvent{

        public String getName() {
            return "chu_betonquest_conversation_start";
        }

        public String docs() {
            return "{}";
        }

        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        public boolean matches(Map<String, Construct> map, BindableEvent bindableEvent) throws PrefilterNonMatchException {
            return true;
        }

        public BindableEvent convert(CArray cArray, Target target) {
            return null;
        }

        public Map<String, Construct> evaluate(BindableEvent event) throws EventException {

            if(event instanceof BetonquestInterface.CHUConversationStartInterface){
                BetonquestInterface.CHUConversationStartInterface e = (BetonquestInterface.CHUConversationStartInterface) event;
                Map<String, Construct> retv = new HashMap<String, Construct>();

                Conversation conv = e.getConversation();
                retv.put("event_type", new CString(getName(), Target.UNKNOWN));
                retv.put("convid", new CString(conv.getID(), Target.UNKNOWN));
                retv.put("package", new CString(conv.getPackage().getName(), Target.UNKNOWN));
                retv.put("npcname", new CString(conv.getData().getName(), Target.UNKNOWN));
                retv.put("location", ObjectGenerator.GetGenerator().location(new BukkitMCLocation(conv.getLocation())));

                return retv;
            }

            return null;
        }

        public Driver driver() {
            return Driver.EXTENSION;
        }

        public boolean modifyEvent(String s, Construct construct, BindableEvent bindableEvent) {
            return false;
        }
    }
}
