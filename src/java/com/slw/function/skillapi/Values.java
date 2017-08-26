package com.slw.function.skillapi;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCPlayer;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.environments.CommandHelperEnvironment;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CREException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.slw.DataHandling;
import com.slw.compatibility.SkillAPIManage;
import com.sucy.skill.dynamic.DynamicSkill;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by User on 2017-08-10.
 */
public class Values {

    @api
    public static class CHU_get_value extends AbstractFunction {

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

            if(!SkillAPIManage.skillapiPlay) return CVoid.VOID;

            Player p = ((BukkitMCPlayer) Static.GetPlayer(args[0].val(), t))._Player();
            Object o = null;
            for(String s : DynamicSkill.getCastData(p).keySet()){
                if(s.equalsIgnoreCase(args[1].val())) o = DynamicSkill.getCastData(p).get(s);
            }
            if(o == null){ o = -1; DynamicSkill.getCastData(p).put(args[1].val(), Double.parseDouble("-1")); }
            if(!DataHandling.isNumeric(o)) return CBoolean.FALSE;

            return new CDouble(o.toString(), t);
        }

        public Version since() {
            return new SimpleVersion(1, 0,0 );
        }

        public String getName() {
            return "chu_skillapi_get_value";
        }

        public Integer[] numArgs() {
            return new Integer[]{ 2 };
        }

        public String docs() {
            return "double (playerName, ID) get Player's Value. but if is null return false.";
        }

    }

    @api
    public static class CHU_set_value extends AbstractFunction {

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

            if(!SkillAPIManage.skillapiPlay) return CVoid.VOID;

            Player p = ((BukkitMCPlayer) Static.GetPlayer(args[0].val(), t))._Player();
            HashMap<String, Object> data = DynamicSkill.getCastData(p);

            if(!DataHandling.isNumeric(args[2].val())) return CBoolean.FALSE;
            data.put(args[1].val(), Double.valueOf(args[2].val()));

            return CBoolean.TRUE;
        }

        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        public String getName() {
            return "chu_skillapi_set_value";
        }

        public Integer[] numArgs() {
            return new Integer[]{ 3 };
        }

        public String docs() {
            return "boolean (playerName, ID, Value) set Player's Value.";
        }
    }
}
