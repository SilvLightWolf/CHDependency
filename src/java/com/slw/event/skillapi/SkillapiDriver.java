package com.slw.event.skillapi;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.events.AbstractEvent;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.exceptions.EventException;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;
import com.slw.skillapi.SkillAPIManage;
import com.sucy.skill.api.player.PlayerAccounts;
import com.sucy.skill.api.player.PlayerData;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 2017-08-11.
 */
public class SkillapiDriver {

    @api
    public static class CHUCastSkillAPI extends AbstractEvent {

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

        public Map<String, Construct> evaluate(BindableEvent event) throws EventException {

            if (event instanceof SkillapiInterface.CHUCastSkillInterface) {

                SkillapiInterface.CHUCastSkillInterface e = (SkillapiInterface.CHUCastSkillInterface) event;
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

            if (s.equalsIgnoreCase("cancel")) {
                SkillapiInterface.CHUCastSkillInterface e = (SkillapiInterface.CHUCastSkillInterface) bindableEvent;
                if (construct.getValue().equalsIgnoreCase("true")) e.setCancelled(true);
                else e.setCancelled(false);
            }

            return false;
        }
    }

    @api
    public static class CHUPhysicalDamageAPI extends AbstractEvent {

        public String getName() {
            return "chu_skillapi_physical_damage";
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

            if(event instanceof SkillapiInterface.CHUPhysicalDamageInterface){

                SkillapiInterface.CHUPhysicalDamageInterface e = (SkillapiInterface.CHUPhysicalDamageInterface) event;
                Map<String, Construct> retv = new HashMap<String, Construct>();

                LivingEntity target = e.getTarget(); LivingEntity damager = e.getDamager();

                retv.put("isasync", CBoolean.get(e.isAsynchronous()));
                retv.put("isprojectile", CBoolean.get(e.isProjectile()));
                retv.put("damage", Construct.GetConstruct(e.getDamage()));
                retv.put("damager", Construct.GetConstruct(damager.getUniqueId().toString()));
                retv.put("target", Construct.GetConstruct(target.getUniqueId().toString()));
                retv.put("event_type", new CString(getName(), Target.UNKNOWN));

                return retv;

            }
            return null;
        }

        public Driver driver() {
            return Driver.EXTENSION;
        }

        public boolean modifyEvent(String s, Construct construct, BindableEvent bindableEvent) {

            if(bindableEvent instanceof SkillapiInterface.CHUPhysicalDamageInterface){

                SkillapiInterface.CHUPhysicalDamageInterface e = (SkillapiInterface.CHUPhysicalDamageInterface) bindableEvent;

                if(s.equalsIgnoreCase("cancel")){
                    if(construct.getValue().equalsIgnoreCase("true")) e.setCancelled(true);
                    else e.setCancelled(false);
                    return true;
                }
            }
            return false;
        }
    }

    @api
    public static class CHUPlayerAccountChangeAPI extends AbstractEvent {

        public String getName() {
            return "chu_skillapi_player_account_change";
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

            if(event instanceof SkillapiInterface.CHUPlayerAccountChangeInterface){

                SkillapiInterface.CHUPlayerAccountChangeInterface e = (SkillapiInterface.CHUPlayerAccountChangeInterface) event;
                Map<String, Construct> retv = new HashMap<String, Construct>();

                PlayerAccounts pa = e.getAccountData();

                retv.put("isAsync", CBoolean.get(e.isAsynchronous()));
                retv.put("player", Construct.GetConstruct(e.getPrevAccount().getPlayer().getName()));
                retv.put("newid", Construct.GetConstruct(e.getNewID()));
                retv.put("previd", Construct.GetConstruct(e.getPrevId()));
                retv.put("event_type", new CString(getName(), Target.UNKNOWN));

                return retv;
            }

            return null;
        }

        public Driver driver() {
            return Driver.EXTENSION;
        }

        public boolean modifyEvent(String s, Construct construct, BindableEvent bindableEvent) {
            if(bindableEvent instanceof SkillapiInterface.CHUPlayerAccountChangeInterface){
                SkillapiInterface.CHUPlayerAccountChangeInterface e = (SkillapiInterface.CHUPlayerAccountChangeInterface) bindableEvent;

                if(s.equalsIgnoreCase("cancel")){
                    if(construct.getValue().equalsIgnoreCase("true")) e.setCancelled(true);
                    else e.setCancelled(false);
                    return true;
                }
            }
            return false;
        }
    }

    @api
    public static class CHUPlayerSkillDowngrade extends AbstractEvent {

        public String getName() {
            return "chu_skillapi_player_skill_downgrade";
        }

        public String docs() {
            return "{}";
        }

        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        public boolean matches(Map<String, Construct> map, BindableEvent bindableEvent) throws PrefilterNonMatchException {
            return false;
        }

        public BindableEvent convert(CArray cArray, Target target) {
            return null;
        }

        public Map<String, Construct> evaluate(BindableEvent event) throws EventException {
            if(event instanceof SkillapiInterface.CHUPlayerSkillDowngradeInterface){
                SkillapiInterface.CHUPlayerSkillDowngradeInterface e = (SkillapiInterface.CHUPlayerSkillDowngradeInterface) event;
                Map<String, Construct> retv = new HashMap<String, Construct>();

                CArray skill = SkillAPIManage.getSkillArray(e.getDowngradedSkill().getData());
                skill.set("level", String.valueOf(e.getDowngradedSkill().getLevel()));

                retv.put("isAsync", CBoolean.get(e.isAsynchronous()));
                retv.put("skill", skill);
                retv.put("player", Construct.GetConstruct(e.getPlayerData().getPlayer().getName()));
                retv.put("event_type", Construct.GetConstruct(getName()));
                retv.put("refund", Construct.GetConstruct(e.getRefund()));

                return retv;
            }
            return null;
        }

        public Driver driver() {
            return Driver.EXTENSION;
        }

        public boolean modifyEvent(String s, Construct construct, BindableEvent bindableEvent) {
            if(bindableEvent instanceof SkillapiInterface.CHUPlayerSkillDowngradeInterface){
                SkillapiInterface.CHUPlayerSkillDowngradeInterface e = (SkillapiInterface.CHUPlayerSkillDowngradeInterface) bindableEvent;
                if(s.equalsIgnoreCase("cancel")){
                    if(construct.getValue().equalsIgnoreCase("true")) e.setCancelled(true);
                    else e.setCancelled(false);
                    return true;
                }
            }
            return false;
        }
    }


}
