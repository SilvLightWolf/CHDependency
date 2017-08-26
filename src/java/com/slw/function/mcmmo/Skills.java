package com.slw.function.mcmmo;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.datatypes.player.PlayerProfile;
import com.gmail.nossr50.datatypes.skills.SkillType;
import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCPlayer;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CREException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.slw.DataHandling;
import com.slw.compatibility.McMMOManage;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by User on 2017-08-24.
 */
public class Skills {

    @api
    public static class CHUMcMMOGetPlevel extends AbstractFunction{

        @SuppressWarnings("unchecked")
        public Class<? extends CREThrowable>[] thrown() {
            return new Class[]{CREException.class};
        }

        public boolean isRestricted() {
            return false;
        }

        public Boolean runAsync() {
            return null;
        }

        public Construct exec(Target t, Environment env, Construct... args) throws ConfigRuntimeException {

            if (!McMMOManage.mcmmoPlay) return CVoid.VOID;

            Player p = ((BukkitMCPlayer) Static.GetPlayer(args[0].val(), t))._Player();
            UUID uuid = p.getUniqueId();
            CArray arr = new CArray(t);

            for (SkillType st : SkillType.values()) {

                if(st.isChildSkill()) continue;

                CArray ar2 = new CArray(t);

                ar2.set("exp", new CInt(ExperienceAPI.getOfflineXP(uuid, st.getName()), t), t);
                ar2.set("rawexp", new CDouble(ExperienceAPI.getOfflineXPRaw(uuid, st.getName()), t), t);
                ar2.set("maxexp", new CInt(ExperienceAPI.getOfflineXPToNextLevel(uuid, st.getName()), t), t);
                ar2.set("level", new CInt(ExperienceAPI.getLevelOffline(uuid, st.getName()), t), t);
                ar2.set("rankoverall", new CInt(ExperienceAPI.getPlayerRankOverall(uuid), t), t);

                arr.set(st.getName().toLowerCase(), ar2, t);
            }
            arr.set("power", new CInt(ExperienceAPI.getPowerLevelOffline(uuid), t), t);

            if(args.length == 1) return arr;
            else return arr.get(args[1].val().toLowerCase(), t);

        }

        public Version since() {
            return new SimpleVersion(1, 1, 0);
        }

        public String getName() {
            return "chu_mcmmo_get_level";
        }

        public Integer[] numArgs() {
            return new Integer[]{ 1, 2 };
        }

        public String docs() {
            return "Array (playerName[, skillName])";
        }
    }

    @api
    public static class CHUMcMMOSetPlevel extends AbstractFunction{

        @SuppressWarnings("unchecked")
        public Class<? extends CREThrowable>[] thrown() {
            return new Class[]{CREException.class};
        }

        public boolean isRestricted() {
            return false;
        }

        public Boolean runAsync() {
            return null;
        }

        public Construct exec(Target t, Environment env, Construct... args) throws ConfigRuntimeException {

            if (!McMMOManage.mcmmoPlay) return CVoid.VOID;

            Player p = ((BukkitMCPlayer) Static.GetPlayer(args[0].val(), t))._Player();
            UUID uuid = p.getUniqueId();
            String sktype = "";

            for(SkillType st : SkillType.values())
                if(st.getName().equalsIgnoreCase(args[1].val())) sktype = st.getName();

            if(sktype.equals("")) return CBoolean.FALSE;
            if(!DataHandling.isNumeric(args[2])) return CBoolean.FALSE;

            ExperienceAPI.setLevelOffline(uuid, sktype, Integer.parseInt(args[2].val()));

            return CBoolean.TRUE;

        }

        public Version since() {
            return new SimpleVersion(1, 1, 0);
        }

        public String getName() {
            return "chu_mcmmo_set_level";
        }

        public Integer[] numArgs() {
            return new Integer[]{ 3 };
        }

        public String docs() {
            return "boolean (playerName, skillName, Value)";
        }
    }

    @api
    public static class CHUMcMMOSetXP extends AbstractFunction{

        @SuppressWarnings("unchecked")
        public Class<? extends CREThrowable>[] thrown() {
            return new Class[]{CREException.class};
        }

        public boolean isRestricted() {
            return false;
        }

        public Boolean runAsync() {
            return null;
        }

        public Construct exec(Target t, Environment env, Construct... args) throws ConfigRuntimeException {

            if (!McMMOManage.mcmmoPlay) return CVoid.VOID;

            Player p = ((BukkitMCPlayer) Static.GetPlayer(args[0].val(), t))._Player();
            UUID uuid = p.getUniqueId();
            String sktype = "";

            for(SkillType st : SkillType.values())
                if(st.getName().equalsIgnoreCase(args[1].val())) sktype = st.getName();

            if(sktype.equals("")) return CBoolean.FALSE;
            if(!DataHandling.isNumeric(args[2])) return CBoolean.FALSE;

            ExperienceAPI.setXPOffline(uuid, sktype, Integer.parseInt(args[2].val()));

            return CBoolean.TRUE;
        }

        public Version since() {
            return new SimpleVersion(1, 1, 0);
        }

        public String getName() {
            return "chu_mcmmo_set_xp";
        }

        public Integer[] numArgs() {
            return new Integer[]{ 3 };
        }

        public String docs() {
            return "boolean (playerName, skillName, Value)";
        }
    }

    @api
    public static class CHUMcMMOAddLevel extends AbstractFunction{

        @SuppressWarnings("unchecked")
        public Class<? extends CREThrowable>[] thrown() {
            return new Class[]{CREException.class};
        }

        public boolean isRestricted() {
            return false;
        }

        public Boolean runAsync() {
            return null;
        }

        public Construct exec(Target t, Environment env, Construct... args) throws ConfigRuntimeException {

            if (!McMMOManage.mcmmoPlay) return CVoid.VOID;

            Player p = ((BukkitMCPlayer) Static.GetPlayer(args[0].val(), t))._Player();
            UUID uuid = p.getUniqueId();
            String sktype = "";

            for(SkillType st : SkillType.values())
                if(st.getName().equalsIgnoreCase(args[1].val())) sktype = st.getName();

            if(sktype.equals("")) return CBoolean.FALSE;
            if(!DataHandling.isNumeric(args[2])) return CBoolean.FALSE;

            ExperienceAPI.addLevelOffline(uuid, sktype, Integer.parseInt(args[2].val()));

            return CBoolean.TRUE;
        }

        public Version since() {
            return new SimpleVersion(1, 1,0);
        }

        public String getName() {
            return "chu_mcmmo_add_level";
        }

        public Integer[] numArgs() {
            return new Integer[]{ 3 };
        }

        public String docs() {
            return "boolean (playerName, skillName, Value)";
        }
    }

    @api
    public static class CHUMcMMOAddXp extends AbstractFunction{

        @SuppressWarnings("unchecked")
        public Class<? extends CREThrowable>[] thrown() {
            return new Class[]{CREException.class};
        }


        public boolean isRestricted() {
            return false;
        }

        public Boolean runAsync() {
            return null;
        }

        public Construct exec(Target t, Environment env, Construct... args) throws ConfigRuntimeException {

            if (!McMMOManage.mcmmoPlay) return CVoid.VOID;

            Player p = ((BukkitMCPlayer) Static.GetPlayer(args[0].val(), t))._Player();
            UUID uuid = p.getUniqueId();
            String sktype = "";

            for(SkillType st : SkillType.values())
                if(st.getName().equalsIgnoreCase(args[1].val())) sktype = st.getName();

            if(sktype.equals("")) return CBoolean.FALSE;
            if(!DataHandling.isNumeric(args[2])) return CBoolean.FALSE;

            ExperienceAPI.addRawXPOffline(uuid, sktype, Integer.parseInt(args[2].val()));

            return CBoolean.TRUE;

        }

        public Version since() {
            return new SimpleVersion(1, 1, 0);
        }

        public String getName() {
            return "chu_mcmmo_add_xp";
        }

        public Integer[] numArgs() {
            return new Integer[]{ 3 };
        }

        public String docs() {
            return "boolean (playerName, skillName, Value)";
        }
    }
}
