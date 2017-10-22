package com.slw.function.libsdisguise;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.CBoolean;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CREException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.slw.DataHandling;
import com.slw.compatibility.LibsDisguiseManage;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.DisguiseConfig;
import me.libraryaddict.disguise.commands.DisguiseBaseCommand;
import me.libraryaddict.disguise.commands.LibsDisguisesCommand;
import me.libraryaddict.disguise.disguisetypes.watchers.LivingWatcher;
import me.libraryaddict.disguise.utilities.DisguiseParser;
import org.bukkit.Bukkit;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by User on 2017-10-22.
 */
public class Disguise {

    @api
    public static class LD_disguise extends AbstractFunction{

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

            if(!LibsDisguiseManage.libsDisguisePlay) return CBoolean.get(false);

            Entity entity;
            me.libraryaddict.disguise.disguisetypes.Disguise disguise;
            String[] arg = null;

            Bukkit.broadcastMessage("Errored by 1");
            if (DataHandling.getEntitybyUUID(args[0].val()) == null) return CBoolean.get(false);
            Bukkit.broadcastMessage("Errored by 2");
            entity = DataHandling.getEntitybyUUID(args[0].val());
            if (args.length == 2){ arg = new String[1]; arg[0] = args[1].val(); }
            else if(args.length == 3){ arg = new String[2]; arg[0] = args[1].val(); arg[1] = args[2].val(); }

            try {
                disguise = DisguiseParser.parseDisguise(entity, "disguise", arg, DisguiseParser.getPermissions(entity, "libsdisguises.disguise."));
            } catch (Exception ex) {
                Bukkit.broadcastMessage("Errored by 3");
                return CBoolean.get(false);
            }

            DisguiseAPI.disguiseToAll(entity, disguise);

            return CBoolean.get(disguise.isDisguiseInUse());
        }

        public Version since() {
            return new SimpleVersion(1, 1, 0);
        }

        public String getName() {
            return "chu_ld_disguise";
        }

        public Integer[] numArgs() {
            return new Integer[]{ 2, 3 };
        }

        public String docs() {
            return "boolean (UUID, DisguiseType[, DisguiseData]) disguise ";
        }

    }

    @api
    public static class LD_undisguise extends AbstractFunction {

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

            if(!LibsDisguiseManage.libsDisguisePlay) return CBoolean.get(false);

            Entity entity;
            if (DataHandling.getEntitybyUUID(args[0].val()) == null) return CBoolean.get(false);
            entity = DataHandling.getEntitybyUUID(args[0].val());
            if(DisguiseAPI.isDisguised(entity))
                DisguiseAPI.undisguiseToAll(entity);

            return CBoolean.TRUE;
        }

        public Version since() {
            return new SimpleVersion(1, 1, 0);
        }

        public String getName() {
            return "chu_ld_undisguise";
        }

        public Integer[] numArgs() {
            return new Integer[]{ 1 };
        }

        public String docs() {
            return "boolean (UUID) undisguise";
        }
    }
}
