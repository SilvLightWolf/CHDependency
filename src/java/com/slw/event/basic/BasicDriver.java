package com.slw.event.basic;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.MCItemStack;
import com.laytonsmith.abstraction.bukkit.BukkitMCItemStack;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.ObjectGenerator;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.events.AbstractEvent;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.exceptions.CRE.CRECastException;
import com.laytonsmith.core.exceptions.EventException;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;

import static com.slw.DataHandling.inventoryToArray;

/**
 * Created by User on 2017-08-11.
 */
public class BasicDriver {

    @api
    public static class CHUInventoryMoveItemAPI extends AbstractEvent {

        public String getName() {
            return "chu_inventory_move_item";
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

            if(event instanceof BasicInterface.CHUInventoryMoveItemInterface){
                BasicInterface.CHUInventoryMoveItemInterface e = (BasicInterface.CHUInventoryMoveItemInterface) event;
                Map<String, Construct> retv = new HashMap<String, Construct>();

                CArray dest = inventoryToArray(e.getDestination());
                CArray init = inventoryToArray(e.getInitiator());
                CArray source = inventoryToArray(e.getSource());

                retv.put("destination", dest);
                retv.put("initiator", init);
                retv.put("source", source);
                retv.put("item", ObjectGenerator.GetGenerator().item(e.getItem(), Target.UNKNOWN));

                return retv;

            }
            return null;
        }

        public Driver driver() {
            return Driver.EXTENSION;
        }

        public boolean modifyEvent(String s, Construct construct, BindableEvent bindableEvent) {
            if(s.equalsIgnoreCase("item")){
                if(!(construct instanceof CArray)) throw new CRECastException("Expecting an array, but received "+ construct.getValue() +" instead.", Target.UNKNOWN);
                MCItemStack is = ObjectGenerator.GetGenerator().item(construct, Target.UNKNOWN);
                ((BasicAPI.CHUInventoryMoveItemEvent) bindableEvent).setItem(is);
                return true;
            }
            return false;
        }
    }

    @api
    public static class CHUInventoryPickupItemAPI extends AbstractEvent {

        public String getName() {
            return "chu_inventory_pickup_item";
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
            if(event instanceof BasicInterface.CHUInventoryPickupInterface){
                BasicInterface.CHUInventoryPickupInterface e = (BasicInterface.CHUInventoryPickupInterface) event;
                Map<String, Construct> retv = new HashMap<String, Construct>();

                retv.put("inventory", inventoryToArray(e.getInventory()));
                retv.put("item", ObjectGenerator.GetGenerator().item(new BukkitMCItemStack(e.getItem().getItemStack()), Target.UNKNOWN));

                return retv;
            }
            return null;
        }

        public Driver driver() {
            return Driver.EXTENSION;
        }

        public boolean modifyEvent(String s, Construct construct, BindableEvent bindableEvent) {
            return false;
        }
    }
}

