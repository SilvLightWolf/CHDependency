package com.slw.event.basic;

import com.laytonsmith.abstraction.MCItemStack;
import com.laytonsmith.abstraction.bukkit.BukkitMCItemStack;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.AreaEffectCloudApplyEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.inventory.Inventory;

import java.util.List;

/**
 * Created by User on 2017-08-11.
 */

public class BasicAPI {

    public static class CHUAreaEffectCloudApplyEvent implements BasicInterface.CHUAreaEffectCloudApplyInterface {

        AreaEffectCloudApplyEvent e;

        public CHUAreaEffectCloudApplyEvent(AreaEffectCloudApplyEvent e) {
            this.e = e;
        }

        public List<LivingEntity> getEntities() {
            return e.getAffectedEntities();
        }

        public AreaEffectCloud getCloud() {
            return e.getEntity();
        }

        public EntityType getEntityType() {
            return e.getEntityType();
        }

        public Object _GetObject() {
            return null;
        }
    }

    public static class CHUInventoryMoveItemEvent implements BasicInterface.CHUInventoryMoveItemInterface {

        InventoryMoveItemEvent e;

        public CHUInventoryMoveItemEvent(InventoryMoveItemEvent e){
            this.e = e;
        }

        public Inventory getDestination() {
            return e.getDestination();
        }

        public Inventory getInitiator() {
            return e.getInitiator();
        }

        public Inventory getSource() {
            return e.getSource();
        }

        public MCItemStack getItem() {
            return new BukkitMCItemStack(e.getItem());
        }

        public void setItem(MCItemStack is) {
            e.setItem(((BukkitMCItemStack)is).__ItemStack());
        }

        public Object _GetObject() {
            return null;
        }
    }

    public static class CHUInventoryPickupItemEvent implements BasicInterface.CHUInventoryPickupInterface{

        InventoryPickupItemEvent e;

        public CHUInventoryPickupItemEvent(InventoryPickupItemEvent e){
            this.e = e;
        }

        public Inventory getInventory() {
            return e.getInventory();
        }

        public Item getItem() {
            return e.getItem();
        }

        public Object _GetObject() {
            return null;
        }
    }

}