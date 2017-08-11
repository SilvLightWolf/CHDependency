package com.slw.event.basic;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.events.AbstractEvent;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.exceptions.EventException;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 2017-08-11.
 */
public class BasicDriver {

    @api
    public static class CHUAreaEffectCloudApplyAPI extends AbstractEvent {
        public String getName() {
            return "chu_area_effect_cloud_apply";
        }

        public String docs() {
            return "{}";
        }

        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        public boolean matches(Map<String, Construct> map, BindableEvent event) throws PrefilterNonMatchException {
            return true;
        }

        public BindableEvent convert(CArray cArray, Target target) {
            return null;
        }

        public Map<String, Construct> evaluate(BindableEvent event) throws EventException {

            if (event instanceof BasicInterface.CHUAreaEffectCloudApplyInterface) {

                BasicInterface.CHUAreaEffectCloudApplyInterface e = (BasicInterface.CHUAreaEffectCloudApplyInterface) event;
                Map<String, Construct> retv = new HashMap<String, Construct>();

                CArray entities = new CArray(Target.UNKNOWN);

                for (LivingEntity entity : e.getEntities())
                    entities.push(Construct.GetConstruct(entity.getUniqueId()), Target.UNKNOWN);

                retv.put("entities", entities);
                retv.put("entity_type", Construct.GetConstruct(e.getEntityType().name()));

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

