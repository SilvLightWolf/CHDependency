package com.slw.event.skillapi;

import com.slw.event.skillapi.interfaces.CHUSkillCastInterface;
import com.sucy.skill.api.event.PlayerCastSkillEvent;
import com.sucy.skill.api.player.PlayerData;
import com.sucy.skill.api.player.PlayerSkill;
import com.sucy.skill.api.skills.Skill;
import org.bukkit.entity.Player;

/**
 * Created by User on 2017-08-10.
 */
public class CHUSkillCastEvent implements CHUSkillCastInterface {
    PlayerCastSkillEvent e;

    public CHUSkillCastEvent(PlayerCastSkillEvent e){
        this.e = e;
    }

    public Object _GetObject() {
        return null;
    }

    public Player getPlayer() {
        return e.getPlayer();
    }

    public PlayerData getPlayerData() {
        return e.getPlayerData();
    }

    public PlayerSkill getSkill() {
        return e.getSkill();
    }

    public boolean isAsynchronous() {
        return e.isAsynchronous();
    }

    public void setCancelled(boolean b) {
        e.setCancelled(b);
    }
}
