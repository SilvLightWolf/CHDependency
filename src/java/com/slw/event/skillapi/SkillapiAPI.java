package com.slw.event.skillapi;

import com.sucy.skill.api.event.PhysicalDamageEvent;
import com.sucy.skill.api.event.PlayerAccountChangeEvent;
import com.sucy.skill.api.event.PlayerCastSkillEvent;
import com.sucy.skill.api.event.PlayerSkillDowngradeEvent;
import com.sucy.skill.api.player.PlayerAccounts;
import com.sucy.skill.api.player.PlayerData;
import com.sucy.skill.api.player.PlayerSkill;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 * Created by User on 2017-08-11.
 */
public class SkillapiAPI {

    public static class CHUCastSkillEvent implements SkillapiInterface.CHUCastSkillInterface {
        PlayerCastSkillEvent e;

        public CHUCastSkillEvent(PlayerCastSkillEvent e) {
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

    public static class CHUPhysicalDamageEvent implements SkillapiInterface.CHUPhysicalDamageInterface {
        PhysicalDamageEvent e;

        public CHUPhysicalDamageEvent(PhysicalDamageEvent e){
            this.e = e;
        }

        public Object _GetObject() {
            return null;
        }

        public boolean isAsynchronous() {
            return e.isAsynchronous();
        }

        public boolean isProjectile() {
            return e.isProjectile();
        }

        public double getDamage() {
            return e.getDamage();
        }

        public LivingEntity getDamager() {
            return e.getDamager();
        }

        public LivingEntity getTarget() {
            return e.getTarget();
        }

        public void setCancelled(boolean b) {
            e.setCancelled(b);
        }

        public void setDamage(double v) {
            e.setDamage(v);
        }
    }

    public static class CHUPlayerAccountChangeEvent implements SkillapiInterface.CHUPlayerAccountChangeInterface {
        PlayerAccountChangeEvent e;

        public CHUPlayerAccountChangeEvent(PlayerAccountChangeEvent e) {
            this.e = e;
        }

        public Object _GetObject() {
            return null;
        }

        public boolean isAsynchronous() {
            return e.isAsynchronous();
        }

        public PlayerAccounts getAccountData() {
            return e.getAccountData();
        }

        public PlayerData getNewAccount() {
            return e.getNewAccount();
        }

        public PlayerData getPrevAccount() {
            return e.getPreviousAccount();
        }

        public int getNewID() {
            return e.getNewID();
        }

        public int getPrevId() {
            return e.getPreviousId();
        }

        public void setCancelled(boolean b) {
            e.setCancelled(b);
        }

    }

    public static class CHUPlayerSkillDowngradeEvent implements SkillapiInterface.CHUPlayerSkillDowngradeInterface {

        PlayerSkillDowngradeEvent e;

        public CHUPlayerSkillDowngradeEvent(PlayerSkillDowngradeEvent e){
            this.e = e;
        }

        public Object _GetObject() {
            return null;
        }

        public PlayerSkill getDowngradedSkill() {
            return e.getDowngradedSkill();
        }

        public PlayerData getPlayerData() {
            return e.getPlayerData();
        }

        public int getRefund() {
            return e.getRefund();
        }

        public boolean isAsynchronous() {
            return e.isAsynchronous();
        }

        public void setCancelled(boolean b) {
            e.setCancelled(b);
        }
    }




}
