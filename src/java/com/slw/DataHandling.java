package com.slw;

import com.laytonsmith.abstraction.MCHumanEntity;
import com.laytonsmith.abstraction.MCInventory;
import com.laytonsmith.abstraction.MCLocation;
import com.laytonsmith.abstraction.MCWorld;
import com.laytonsmith.abstraction.blocks.MCBlock;
import com.laytonsmith.abstraction.blocks.MCBlockState;
import com.laytonsmith.abstraction.bukkit.BukkitMCInventory;
import com.laytonsmith.abstraction.bukkit.BukkitMCInventoryHolder;
import com.laytonsmith.abstraction.bukkit.BukkitMCLocation;
import com.laytonsmith.core.ObjectGenerator;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.Inventory;

import java.util.Iterator;

/**
 * Created by User on 2017-08-04.
 */
public class DataHandling {

    public static boolean isNumeric(Object num){
        return isNumeric(new CString(num.toString(), Target.UNKNOWN));
    }

    public static boolean isNumeric(Construct num){
        boolean numeric = true;
        try{
            Static.getNumber(num, Target.UNKNOWN);
        } catch (ConfigRuntimeException e){
            numeric = false;
        }
        return numeric;
    }

    public static CArray inventoryToArray(Inventory inv){
        return(inventoryToArray(new BukkitMCInventory(inv)));
    }

    public static CArray inventoryToArray(MCInventory inv){

        CArray array = new CArray(Target.UNKNOWN);

        CArray inventory = new CArray(Target.UNKNOWN);
        for(int i = 0; i < inv.getSize(); i++)
            inventory.push(ObjectGenerator.GetGenerator().item(inv.getItem(i), Target.UNKNOWN), Target.UNKNOWN);

        CArray viewers = new CArray(Target.UNKNOWN);
        Iterator it = inv.getViewers().iterator();
        while(it.hasNext()){
            MCHumanEntity he = (MCHumanEntity)it.next();
            viewers.push(Construct.GetConstruct(he.getName()), Target.UNKNOWN);
        }


        array.set("viewers", viewers, Target.UNKNOWN);
        array.set("inventory", inventory, Target.UNKNOWN);
        array.set("type", inv.getType().name());
        array.set("inventorysize", String.valueOf(inv.getSize()));
        array.set("inventorytitle", inv.getTitle());
        Object holder = ((BukkitMCInventoryHolder)inv.getHolder()).getHandle();
        if(holder instanceof BlockState){
            BlockState bs = (BlockState)holder;
            array.set("block", ObjectGenerator.GetGenerator().location(new BukkitMCLocation(bs.getBlock().getLocation())), Target.UNKNOWN);
        }

        return array;
    }

}
