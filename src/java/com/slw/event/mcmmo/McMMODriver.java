package com.slw.event.mcmmo;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.events.AbstractEvent;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.exceptions.EventException;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;
import com.slw.DataHandling;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 2017-08-24.
 */
public class McMMODriver {

    @api
    public static class CHUMcMMOLevelUpAPI extends AbstractEvent{

        public String getName() {
            return "chu_mcmmo_levelup";
        }

        public String docs() {
            return "{}";
        }

        public Version since() {
            return new SimpleVersion(1, 1, 0);
        }

        public boolean matches(Map<String, Construct> map, BindableEvent bindableEvent) throws PrefilterNonMatchException {
            return true;
        }

        public BindableEvent convert(CArray cArray, Target target) {
            return null;
        }

        public Map<String, Construct> evaluate(BindableEvent event) throws EventException {
            if(event instanceof McMMOInterface.CHUMcMMOLevelUpInterface){

                McMMOInterface.CHUMcMMOLevelUpInterface e = (McMMOInterface.CHUMcMMOLevelUpInterface) event;
                Map<String, Construct> retv = new HashMap<String, Construct>();

                retv.put("event_type", new CString(getName(), Target.UNKNOWN));
                retv.put("sklevel", e.getSkillLevel());
                retv.put("lvlgain", e.getLevelsGained());
                retv.put("skname", new CString(e.getSkill().getName(), Target.UNKNOWN));
                retv.put("reason", new CString(e.getXpGainReason().name(), Target.UNKNOWN));
                retv.put("player", new CString(e.getPlayer().getName(), Target.UNKNOWN));

                return retv;
            }

            return null;
        }

        public Driver driver() {
            return Driver.EXTENSION;
        }

        public boolean modifyEvent(String k, Construct v, BindableEvent e) {
            if(k.equalsIgnoreCase("lvlgain")){
                if(DataHandling.isNumeric(v)){
                    ((McMMOInterface.CHUMcMMOLevelUpInterface) e).setLevelsGained(Integer.parseInt(v.val()));
                    return true;
                }
            }
            return false;
        }
    }
}
