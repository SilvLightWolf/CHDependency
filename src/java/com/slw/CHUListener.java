package com.slw;

import com.comphenix.protocol.events.PacketEvent;
import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.EventUtils;
import com.slw.event.basic.BasicAPI;
import com.slw.event.skillapi.SkillapiAPI;
import com.sucy.skill.api.event.*;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.AreaEffectCloudApplyEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;

/**
 * Created by User on 2017-08-04.
 */
public class CHUListener {

    public static class BasicEventListener implements Listener {

        private static BasicEventListener listener;

        public static void register() {
            if (listener == null)
                listener = new BasicEventListener();
            CommandHelperPlugin.self.registerEvents(listener);
        }

        public static void unregister() {
            InventoryMoveItemEvent.getHandlerList().unregister(listener);
            InventoryPickupItemEvent.getHandlerList().unregister(listener);
        }

        @EventHandler
        public void onInventoryMoveItemEvent(InventoryMoveItemEvent e){
            EventUtils.TriggerListener(Driver.EXTENSION, "chu_inventory_move_item",
                    new BasicAPI.CHUInventoryMoveItemEvent(e));
        }

        @EventHandler
        public void onInventoryPickupItemEvent(InventoryPickupItemEvent e){
            EventUtils.TriggerListener(Driver.EXTENSION, "chu_inventory_pickup_item",
                    new BasicAPI.CHUInventoryPickupItemEvent(e));
        }

    }

    public static class SkillAPIEventListener implements Listener {

        private static SkillAPIEventListener listener;

        public static void register() {
            if (listener == null)
                listener = new SkillAPIEventListener();
            CommandHelperPlugin.self.registerEvents(listener);
        }

        public static void unregister() {

            PhysicalDamageEvent.getHandlerList().unregister(listener);
            PlayerAccountChangeEvent.getHandlerList().unregister(listener);
            PlayerCastSkillEvent.getHandlerList().unregister(listener);
            PlayerLandEvent.getHandlerList().unregister(listener);
            PlayerSkillDowngradeEvent.getHandlerList().unregister(listener);
            PlayerSkillUpgradeEvent.getHandlerList().unregister(listener);
            SkillDamageEvent.getHandlerList().unregister(listener);
            SkillHealEvent.getHandlerList().unregister(listener);
            TrueDamageEvent.getHandlerList().unregister(listener);

        }


        @EventHandler
        public void onPhysicalDamage(PhysicalDamageEvent e) {
            EventUtils.TriggerListener(Driver.EXTENSION, "chu_skillapi_physical_damage",
                    new SkillapiAPI.CHUPhysicalDamageEvent(e));
        }

        @EventHandler
        public void onPlayerAccountChange(PlayerAccountChangeEvent e) {
            EventUtils.TriggerListener(Driver.EXTENSION, "chu_skillapi_player_account_change",
                    new SkillapiAPI.CHUPlayerAccountChangeEvent(e));
        }

        @EventHandler
        public void onCastSkill(PlayerCastSkillEvent e) {
            EventUtils.TriggerListener(Driver.EXTENSION, "chu_skillapi_cast_skill",
                    new SkillapiAPI.CHUCastSkillEvent(e));
        }

        @EventHandler
        public void onPlayerLand(PlayerLandEvent e) {
            EventUtils.TriggerListener(Driver.EXTENSION, "chu_skillapi_player_land",
                    new SkillapiAPI.CHUPlayerLandEvent(e));
        }

        @EventHandler
        public void onPlayerSkillDowngrade(PlayerSkillDowngradeEvent e) {
            EventUtils.TriggerListener(Driver.EXTENSION, "chu_skillapi_player_skill_downgrade",
                    new SkillapiAPI.CHUPlayerSkillDowngradeEvent(e));
        }

        @EventHandler
        public void onPlayerSkillUpgrade(PlayerSkillUpgradeEvent e) {
            EventUtils.TriggerListener(Driver.EXTENSION, "chu_skillapi_player_skill_upgrade",
                    new SkillapiAPI.CHUPlayerSkillUpgradeEvent(e));
        }

        @EventHandler
        public void onSkillDamage(SkillDamageEvent e) {
            EventUtils.TriggerListener(Driver.EXTENSION, "chu_skillapi_skill_damage",
                    new SkillapiAPI.CHUSkillDamageEvent(e));
        }

        @EventHandler
        public void onSkillHeal(SkillHealEvent e) {
            EventUtils.TriggerListener(Driver.EXTENSION, "chu_skillapi_skill_heal",
                    new SkillapiAPI.CHUSkillHealEvent(e));
        }

        @EventHandler
        public void onTrueDamage(TrueDamageEvent e) {
            EventUtils.TriggerListener(Driver.EXTENSION, "chu_skillapi_true_damage",
                    new SkillapiAPI.CHUTrueDamageEvent(e));
        }
    }

    public static class BetonQuestEventListener implements Listener {

        private static BetonQuestEventListener listener;

        public static void register() {
            if (listener == null)
                listener = new BetonQuestEventListener();
            CommandHelperPlugin.self.registerEvents(listener);
        }

        public static void unregister() {

        }
    }


}
