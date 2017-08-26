package com.slw.function.basic;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.MCCommandSender;
import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCPlayer;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.environments.CommandHelperEnvironment;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CRECastException;
import com.laytonsmith.core.exceptions.CRE.CREException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.slw.DataHandling;
import com.slw.exceptions.UnknownAttributeException;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by User on 2017-08-03.
 */
public class Attributes {

    @api
    public static class CHU_get_entity_attr extends AbstractFunction {

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

            LivingEntity entity = null;
            if(args.length == 0) {
                MCCommandSender player1 = env.getEnv(CommandHelperEnvironment.class).GetCommandSender();
                entity = ((BukkitMCPlayer) ((MCPlayer) player1))._Player();
            }else{
                UUID uuid = Static.GetUUID(args[0].val(), t);
                if(Bukkit.getEntity(uuid) instanceof Player) entity = (LivingEntity) Bukkit.getOfflinePlayer(uuid);
                else entity = (LivingEntity) Bukkit.getEntity(uuid);
            }



            CArray retv = new CArray(t);

            if (args.length <= 1) {

                retv = attrArray(entity);

            } else if (args.length == 2) {

                if (checkAttr(args[1].val()))
                    retv = attrArray(entity, Attribute.valueOf(args[1].val()));


            }


            return retv;
        }

        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        public String getName() {
            return "chu_get_attr";
        }

        public Integer[] numArgs() {
            return new Integer[]{0, 1, 2};
        }

        public String docs() {
            return "Array (UUID[, AttrType]) Get Entity's attribute value";
        }


    }

    @api
    public static class CHU_set_entity_attr extends AbstractFunction{

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

            LivingEntity entity = null;
            Attribute attr = null;
            Double value = 0.0D;
            Double bef = 0.0D;

            if(args.length == 2){
                MCCommandSender player1 = env.getEnv(CommandHelperEnvironment.class).GetCommandSender();
                entity = ((BukkitMCPlayer) ((MCPlayer) player1))._Player();
                if(checkAttr(args[0].val())) attr = Attribute.valueOf(args[0].val());
                if(DataHandling.isNumeric(args[1])) value = Double.valueOf(args[1].val());
                else throw new CRECastException("Expecting an number, but recieved " + args[1].val(), t);
            }else if(args.length == 3){
                UUID uuid = Static.GetUUID(args[0].val(), t);
                if(Bukkit.getEntity(uuid) instanceof Player) entity = (LivingEntity) Bukkit.getOfflinePlayer(uuid);
                else entity = (LivingEntity) Bukkit.getEntity(uuid);
                if(checkAttr(args[1].val())) attr = Attribute.valueOf(args[1].val());
                if(DataHandling.isNumeric(args[2])) value = Double.valueOf(args[2].val());
                else throw new CRECastException("Expecting an number, but recieved " + args[2].val(), t);
            }

            AttributeInstance instance = entity.getAttribute(attr);
            bef = instance.getValue();
            instance.setBaseValue(value);

            return CVoid.VOID;
        }

        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        public String getName() {
            return "chu_set_attr";
        }

        public Integer[] numArgs() {
            return new Integer[]{ 2, 3 };
        }

        public String docs() {
            return "void ([UUID ,]Attribute, value) set Entity's attribute";
        }
    }

    private static boolean checkAttr(String name) {

        Attribute[] attrs = Attribute.values();
        for (int i = 0; i < attrs.length; i++) {
            if (attrs[i].toString().equalsIgnoreCase(name)) {
                return true;
            }
        }
        throw new UnknownAttributeException(name + " is not attribute type." +
                " Attribute : GENERIC_ARMOR, GENERIC_ARMOR_TOUGHNESS, GENERIC_ATTACK_DAMAGE," +
                " GENERIC_ATTACK_SPEED, GENERIC_FLYING_SPEED, GENERIC_FOLLOW_RANGE," +
                " GENERIC_KNOCKBACK_RESISTANCE, GENERIC_LUCK, GENERIC_MAX_HEALTH," +
                " GENERIC_MOVEMENT_SPEED, HORSE_JUMP_STRENGTH, ZOMBIE_SPAWN_REINFORCEMENTS", Target.UNKNOWN);
    }

    private static CArray attrArray(LivingEntity entity){
        CArray arr = new CArray(Target.UNKNOWN);
        for(int i = 0 ; i < Attribute.values().length ; i++)
            arr.push(attrArray(entity, Attribute.values()[i]), Target.UNKNOWN);
        return arr;
    }

    private static CArray attrArray(LivingEntity entity, Attribute attr){

        CArray arr = new CArray(Target.UNKNOWN);
        Target t = Target.UNKNOWN;

        AttributeInstance instance = entity.getAttribute(attr);
        if(instance == null) return arr;

        arr.set("value", new CDouble(instance.getValue(), t), t);
        arr.set("basevalue", new CDouble(instance.getBaseValue(), t), t);
        arr.set("defaultvaulue", new CDouble(instance.getDefaultValue(), t), t);
        arr.set("name", new CString(attr.name(), t), t);

        return arr;
    }


}
