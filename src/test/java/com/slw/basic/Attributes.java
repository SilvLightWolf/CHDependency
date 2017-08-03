package com.slw.basic;

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
import com.slw.exceptions.UnknownAttributeException;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

/**
 * Created by User on 2017-08-03.
 */
public class Attributes {

    @api
    public static class CHU_get_player_attr extends AbstractFunction {

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

            Player p = ((BukkitMCPlayer) Static.GetPlayer(args[0], t))._Player();
            CArray retv = new CArray(t);

            if (args.length == 1) {

                CArray arr = new CArray(t);
                for(int i = 0 ; i < Attribute.values().length ; i++){

                    arr = new CArray(t);

                    AttributeInstance attr = p.getAttribute(Attribute.values()[i]);

                    arr.set("value", new CDouble(attr.getValue(), t), t);
                    arr.set("basevalue", new CDouble(attr.getBaseValue(), t), t);
                    arr.set("defaultvaulue", new CDouble(attr.getDefaultValue(), t), t);
                    arr.set("name", new CString(args[1].val(), t), t);

                    retv.push(arr, t);

                }

            } else if (args.length == 2) {

                if (checkAttr(args[1].val())) {

                    AttributeInstance attr = p.getAttribute(Attribute.valueOf(args[1].val()));

                    retv.set("value", new CDouble(attr.getValue(), t), t);
                    retv.set("basevalue", new CDouble(attr.getBaseValue(), t), t);
                    retv.set("defaultvaulue", new CDouble(attr.getDefaultValue(), t), t);
                    retv.set("name", new CString(args[1].val(), t), t);

                } else {
                    throw new UnknownAttributeException(args[1].val() + " is not attribute type." +
                            " Attribute : GENERIC_ARMOR, GENERIC_ARMOR_TOUGHNESS, GENERIC_ATTACK_DAMAGE," +
                            " GENERIC_ATTACK_SPEED, GENERIC_FLYING_SPEED, GENERIC_FOLLOW_RANGE," +
                            " GENERIC_KNOCKBACK_RESISTANCE, GENERIC_LUCK, GENERIC_MAX_HEALTH," +
                            " GENERIC_MOVEMENT_SPEED, HORSE_JUMP_STRENGTH, ZOMBIE_SPAWN_REINFORCEMENTS", t);
                }

            }


            return retv;
        }

        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        public String getName() {
            return "chu_get_pattr";
        }

        public Integer[] numArgs() {
            return new Integer[]{1, 2};
        }

        public String docs() {
            return "Array (Player[, AttrType]) Get Player's Attribute value";
        }

        private static boolean checkAttr(String name) {

            org.bukkit.attribute.Attribute[] attrs = org.bukkit.attribute.Attribute.values();
            for (int i = 0; i < attrs.length; i++) {
                if (attrs[i].toString().equalsIgnoreCase(name)) {
                    return true;
                }
            }
            return false;

        }
    }
}
