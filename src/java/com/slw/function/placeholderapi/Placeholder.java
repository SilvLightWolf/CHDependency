package com.slw.function.placeholderapi;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCPlayer;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.CommandHelperEnvironment;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CREException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

/**
 * Created by User on 2017-08-26.
 */
public class Placeholder {

    @api
    public static class CHU_get_placeholder extends AbstractFunction{

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

            Player p = ((BukkitMCPlayer)env.getEnv(CommandHelperEnvironment.class).GetPlayer())._Player();
            String s = args[0].val();
            if(args.length == 2){
                p = ((BukkitMCPlayer)Static.GetPlayer(args[0].val(), t))._Player();
                s = args[1].val();
            }

            return new CString(PlaceholderAPI.setPlaceholders(p, s), t);
        }

        public Version since() {
            return new SimpleVersion(1, 1, 0);
        }

        public String getName() {
            return "chu_papi_get_placeholder";
        }

        public Integer[] numArgs() {
            return new Integer[]{ 1, 2 };
        }

        public String docs() {
            return "string ([player,]string)";
        }
    }
}
