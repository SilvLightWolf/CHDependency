package com.slw.event.skillapi.driver;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.events.AbstractEvent;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.exceptions.EventException;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;
import com.slw.event.skillapi.interfaces.CHUSkillCastInterface;
import com.slw.skillapi.SkillAPIManage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 2017-08-10.
 */

@api
public class CHUSkillCastAPI extends AbstractEvent{
    public String getName() {
        return "chu_skillapi_cast_skill";
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

    public Map<String, Construct> evaluate(BindableEvent bindableEvent) throws EventException {

        if(bindableEvent instanceof CHUSkillCastInterface){

            CHUSkillCastInterface e = (CHUSkillCastInterface) bindableEvent;
            Map<String, Construct> retv = new HashMap<String, Construct>();
            Target t = Target.UNKNOWN;

            CArray sk = SkillAPIManage.getSkillArray(e.getSkill().getData());
            sk.set("level", String.valueOf((e.getSkill().getLevel())));

            retv.put("player", new CString(e.getPlayer().getName(), t));
            retv.put("event_type", new CString(getName(), t));
            retv.put("skill", sk);
            retv.put("isAsync", CBoolean.get(e.isAsynchronous()));

            return retv;
        }

        return null;
    }

    public Driver driver() {
        return Driver.EXTENSION;
    }

    public boolean modifyEvent(String s, Construct construct, BindableEvent bindableEvent) {

        if(s.equalsIgnoreCase("cancel")){
            CHUSkillCastInterface e = (CHUSkillCastInterface) bindableEvent;
            if(construct.getValue().equalsIgnoreCase("true"))e.setCancelled(true);
            else e.setCancelled(false);
        }

        return false;
    }
}
