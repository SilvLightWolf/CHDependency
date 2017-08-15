package com.slw.function.skillapi;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCPlayer;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.environments.CommandHelperEnvironment;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.slw.DataHandling;
import com.slw.skillapi.SkillAPIManage;
import com.sucy.skill.dynamic.DynamicSkill;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by User on 2017-08-10.
 */
public class Values {

    @api
    public static class CHU_get_value extends AbstractFunction {

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

            Player p = ((BukkitMCPlayer)env.getEnv(CommandHelperEnvironment.class).GetPlayer())._Player();
            Object o = DynamicSkill.getCastData(p).get(args[1].val());
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

            Player p = ((BukkitMCPlayer)env.getEnv(CommandHelperEnvironment.class).GetPlayer())._Player();
            HashMap<String, Object> data = DynamicSkill.getCastData(p);

            if(!data.keySet().contains(args[1].val())) return CBoolean.FALSE;

            if(!DataHandling.isNumeric(args[2].val())) return CBoolean.FALSE;
            data.put(args[1].val(), args[2].val());

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
