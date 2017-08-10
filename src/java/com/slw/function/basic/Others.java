package com.slw.function.basic;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.MCLocation;
import com.laytonsmith.abstraction.MCWorld;
import com.laytonsmith.abstraction.bukkit.BukkitMCLocation;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCEntity;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCPlayer;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.ObjectGenerator;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CBoolean;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.CommandHelperEnvironment;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.slw.DataHandling;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;


/**
 * Created by User on 2017-08-10.
 */
public class Others {

    @api
    public static class CHU_push_entities extends AbstractFunction{

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

            try {

                if(!DataHandling.isNumeric(args[2])) return CBoolean.FALSE;
                double speed = Double.parseDouble(args[2].val());

                MCWorld w = Static.getWorld("world", t);
                Location center = ((BukkitMCLocation) ObjectGenerator.GetGenerator().location(args[0], w, t))._Location();
                CArray entityids = Static.getArray(args[1], t);
                for (Construct c : entityids.asList()){
                    Entity e = ((BukkitMCEntity) Static.getEntity(c, t)).getHandle();
                    Location loc = e.getLocation();
                    Vector v = loc.toVector().subtract(center.toVector()).normalize();
                    e.setVelocity(v.multiply(speed));
                }

            }catch(Exception e){ return CBoolean.FALSE; }

            return CBoolean.TRUE;
        }

        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        public String getName() {
            return "chu_push_entities";
        }

        public Integer[] numArgs() {
            return new Integer[]{ 3 };
        }

        public String docs() {
            return "boolean (Center, EntityIDs, Speed) Center location to push entities speed.";
        }
    }
}
