package com.slw.event.basic;

import com.laytonsmith.abstraction.MCItemStack;
import com.laytonsmith.core.events.BindableEvent;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.Inventory;

import java.util.List;

/**
 * Created by User on 2017-08-11.
 */

public class BasicInterface {

    public interface CHUInventoryMoveItemInterface extends BindableEvent {

        public Inventory getDestination();
        public Inventory getInitiator();
        public Inventory getSource();
        public MCItemStack getItem();
        public void setItem(MCItemStack is);

    }

    public interface CHUInventoryPickupInterface extends BindableEvent{

        public Inventory getInventory();
        public Item getItem();

    }

}
