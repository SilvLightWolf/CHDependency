package com.slw.event.skillapi.interfaces;

import com.laytonsmith.core.events.BindableEvent;
import com.sucy.skill.api.player.PlayerData;
import com.sucy.skill.api.player.PlayerSkill;
import com.sucy.skill.api.skills.Skill;
import org.bukkit.entity.Player;

/**
 * Created by User on 2017-08-10.
 */
public interface CHUSkillCastInterface extends BindableEvent {

    public Player getPlayer();
    public PlayerData getPlayerData();
    public PlayerSkill getSkill();
    public boolean isAsynchronous();
    public void setCancelled(boolean b);
}
