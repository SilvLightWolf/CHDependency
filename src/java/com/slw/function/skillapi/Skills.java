package com.slw.function.skillapi;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCPlayer;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.slw.DataHandling;
import com.slw.compatibility.SkillAPIManage;
import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.player.PlayerData;
import com.sucy.skill.api.player.PlayerSkill;
import com.sucy.skill.api.skills.Skill;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

/**
 * Created by User on 2017-08-10.
 */
public class Skills {

    @api
    public static class CHU_get_skills extends AbstractFunction {

        @SuppressWarnings("unchecked")
        public Class<? extends CREThrowable>[] thrown() {
            return new Class[0];
        }

        public boolean isRestricted() {
            return false;
        }

        public Boolean runAsync() {
            return null;
        }

        public Construct exec(Target t, Environment env, Construct... args) throws ConfigRuntimeException {

            if(!SkillAPIManage.skillapiPlay) return CVoid.VOID;

            CArray retv = new CArray(t);

            if(args.length == 0)
                for(Skill s : SkillAPI.getSkills().values()) retv.push(SkillAPIManage.getSkillArray(s), t);
            else if(args.length == 1){
                OfflinePlayer p = ((BukkitMCPlayer) Static.GetPlayer(args[0].val(), t))._Player();
                for(PlayerSkill s : SkillAPI.getPlayerAccountData(p).getActiveData().getSkills()) {
                    CArray a = SkillAPIManage.getSkillArray(s.getData());
                    a.set("level", String.valueOf(s.getLevel()));
                    retv.push(a, t);
                }
            }

            return retv;
        }

        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        public String getName() {
            return "chu_skillapi_get_skills";
        }

        public Integer[] numArgs() {
            return new Integer[]{ 0, 1 };
        }

        public String docs() {
            return "Array ([playerName]) get All Skills or Player's current account skill";
        }

    }

    @api
    public static class CHU_set_skill_level extends AbstractFunction {

        @SuppressWarnings("unchecked")
        public Class<? extends CREThrowable>[] thrown() {
            return new Class[0];
        }

        public boolean isRestricted() {
            return false;
        }

        public Boolean runAsync() {
            return null;
        }

        public Construct exec(Target t, Environment env, Construct... args) throws ConfigRuntimeException {

            if(!SkillAPIManage.skillapiPlay) return CVoid.VOID;

            OfflinePlayer p = ((BukkitMCPlayer) Static.GetPlayer(args[0].val(), t))._Player();
            if(p == null) return CBoolean.FALSE;
            PlayerData pd = SkillAPI.getPlayerAccountData(p).getActiveData();

            if(!SkillAPIManage.isExistentSkill(args[1].val())) return CBoolean.FALSE;
            if(!DataHandling.isNumeric(args[2])) return CBoolean.FALSE;
            if(pd.getSkill(args[1].val()).getData().getMaxLevel() < Integer.parseInt(args[2].val())) return CBoolean.FALSE;

            pd.getSkill(args[1].val()).setLevel(Integer.parseInt(args[2].val()));
            SkillAPIManage.refreshAccount(p);

            return CBoolean.TRUE;
        }

        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        public String getName() {
            return "chu_skillapi_set_skill_level";
        }

        public Integer[] numArgs() {
            return new Integer[]{ 3 };
        }

        public String docs() {
            return "boolean (playerName, skillName, Level) set Player's skill level.";
        }
    }

    @api
    public static class CHU_give_skill extends AbstractFunction{

        @SuppressWarnings("unchecked")
        public Class<? extends CREThrowable>[] thrown() {
            return new Class[0];
        }

        public boolean isRestricted() {
            return false;
        }

        public Boolean runAsync() {
            return null;
        }

        public Construct exec(Target t, Environment env, Construct... args) throws ConfigRuntimeException {

            if(!SkillAPIManage.skillapiPlay) return CVoid.VOID;

            OfflinePlayer p = ((BukkitMCPlayer) Static.GetPlayer(args[0].val(), t))._Player();
            if(p == null) return CBoolean.FALSE;

            String n = args[1].val();
            if(!SkillAPIManage.isExistentSkill(n)) return CBoolean.FALSE;

            SkillAPI.getPlayerAccountData(p).getActiveData().giveSkill(SkillAPI.getSkill(n));
            if(args.length == 3){
                if(!DataHandling.isNumeric(args[2])) return CBoolean.FALSE;
                if(SkillAPI.getSkill(n).getMaxLevel() < Integer.parseInt(args[2].val())) return CBoolean.FALSE;
                SkillAPI.getPlayerAccountData(p).getActiveData().getSkill(n).setLevel(Integer.parseInt(args[2].val()));
            }

            return CBoolean.TRUE;
        }

        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        public String getName() {
            return "chu_skillapi_give_skill";
        }

        public Integer[] numArgs() {
            return new Integer[]{ 2, 3 };
        }

        public String docs() {
            return "boolean (playerName, skillName[, Level]) give Skill to player, but this skill is removed after reloading.";
        }
    }

    @api
    public static class CHU_refresh extends AbstractFunction {

        @SuppressWarnings("unchecked")
        public Class<? extends CREThrowable>[] thrown() {
            return new Class[0];
        }

        public boolean isRestricted() {
            return false;
        }

        public Boolean runAsync() {
            return null;
        }

        public Construct exec(Target t, Environment env, Construct... args) throws ConfigRuntimeException {

            if(!SkillAPIManage.skillapiPlay) return CVoid.VOID;

            if(args[0] instanceof CArray){
                CArray arr =(CArray) args[0];
                for(Construct c : arr.asList()){
                    Player p = ((BukkitMCPlayer)Static.GetPlayer(c.getValue(), t))._Player();
                    SkillAPIManage.refreshAccount(p);
                }
                return CBoolean.TRUE;
            }
            return CBoolean.FALSE;
        }

        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        public String getName() {
            return "chu_skillapi_refresh";
        }

        public Integer[] numArgs() {
            return new Integer[]{ 1 };
        }

        public String docs() {
            return "boolean (playerArray) refresh player's skill.";
        }
    }

}
