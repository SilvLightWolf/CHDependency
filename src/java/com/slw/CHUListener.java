package com.slw;

import com.comphenix.protocol.events.PacketEvent;
import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.EventUtils;
import com.laytonsmith.libs.com.jcraft.jsch.Packet;
import com.slw.event.basic.BasicAPI;
import com.slw.event.skillapi.SkillapiAPI;
import com.sucy.skill.api.event.PhysicalDamageEvent;
import com.sucy.skill.api.event.PlayerAccountChangeEvent;
import com.sucy.skill.api.event.PlayerCastSkillEvent;
import com.sucy.skill.api.event.PlayerSkillDowngradeEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.AreaEffectCloudApplyEvent;

/**
 * Created by User on 2017-08-04.
 */
public class CHUListener implements Listener{

    private static CHUListener listener;

    public static void register(){
        if(listener == null)
            listener = new CHUListener();
        CommandHelperPlugin.self.registerEvents(listener);
    }

    public static void unregister(){
        AreaEffectCloudApplyEvent.getHandlerList().unregister(listener);
        PlayerCastSkillEvent.getHandlerList().unregister(listener);
        PhysicalDamageEvent.getHandlerList().unregister(listener);
        PlayerAccountChangeEvent.getHandlerList().unregister(listener);
        PlayerSkillDowngradeEvent.getHandlerList().unregister(listener);
    }

    @EventHandler
    public void onAreaEffectCloudApplyEvent(AreaEffectCloudApplyEvent e){
        EventUtils.TriggerListener(Driver.EXTENSION, "chu_area_effect_cloud_apply",
                new BasicAPI.CHUAreaEffectCloudApplyEvent(e));
    }

    @EventHandler
    public void onCastSkill(PlayerCastSkillEvent e){
        EventUtils.TriggerListener(Driver.EXTENSION, "chu_skillapi_cast_skill",
                new SkillapiAPI.CHUCastSkillEvent(e));
    }

    /*@EventHandler
    public void onPhysicalDamage(PhysicalDamageEvent e){
        EventUtils.TriggerListener(Driver.EXTENSION, "chu_skillapi_physical_damage",
                new SkillapiAPI.CHUPhysicalDamageEvent(e));
    }*/

    @EventHandler
    public void onPlayerAccountChange(PlayerAccountChangeEvent e){
        EventUtils.TriggerListener(Driver.EXTENSION, "chu_skillapi_player_account_change",
                new SkillapiAPI.CHUPlayerAccountChangeEvent(e));
    }

    @EventHandler
    public void onEvent(PlayerSkillDowngradeEvent e){
        EventUtils.TriggerListener(Driver.EXTENSION, "chu_skillapi_player_skill_downgrade",
                new SkillapiAPI.CHUPlayerSkillDowngradeEvent(e));
    }



}
