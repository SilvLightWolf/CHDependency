package com.slw.event.skillapi;

import com.laytonsmith.core.events.BindableEvent;
import com.sucy.skill.api.event.*;
import com.sucy.skill.api.player.PlayerAccounts;
import com.sucy.skill.api.player.PlayerData;
import com.sucy.skill.api.player.PlayerSkill;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 * Created by User on 2017-08-11.
 */
public class SkillapiAPI {

    public static class CHUFlagApplyEvent implements SkillapiInterface.CHUFlagApplyInterface {

        public Object _GetObject() {
            return null;
        }
    }

    public static class CHUFlagExpireInterface implements SkillapiInterface.CHUFlagExpireInterface {

        public Object _GetObject() {
            return null;
        }
    }

    public static class CHUItemProjectileHitEvent implements SkillapiInterface.CHUItemProjectileHitInterface {

        public Object _GetObject() {
            return null;
        }
    }

    public static class CHUItemProjectileLandInterface implements SkillapiInterface.CHUItemProjectileLandInterface {

        public Object _GetObject() {
            return null;
        }
    }

    public static class CHUItemProjectileLaunchInterface implements SkillapiInterface.CHUItemProjectileLaunchInterface {

        public Object _GetObject() {
            return null;
        }
    }

    public static class CHUParticleProjectileExpireInterface implements SkillapiInterface.CHUParticleProjectileExpireInterface {

        public Object _GetObject() {
            return null;
        }
    }

    public static class CHUParticleProjectileHitInterface implements SkillapiInterface.CHUParticleProjectileHitInterface {

        public Object _GetObject() {
            return null;
        }
    }


    public static class CHUParticleProjectileLandInterface implements SkillapiInterface.CHUParticleProjectileLandInterface {

        public Object _GetObject() {
            return null;
        }
    }


    public static class CHUParticleProjectileLaunchInterface implements SkillapiInterface.CHUParticleProjectileLaunchInterface {

        public Object _GetObject() {
            return null;
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
    public static class CHUPlayerLandEvent implements SkillapiInterface.CHUPlayerLandInterface {
        PlayerLandEvent e;

        public CHUPlayerLandEvent(PlayerLandEvent e){
            this.e = e;
        }

        public Object _GetObject() {
            return null;
        }

        public double getDistance() {
            return e.getDistance();
        }

        public Player getPlayer() {
            return e.getPlayer();
        }

        public boolean isAsync() {
            return e.isAsynchronous();
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

    public static class CHUPlayerSkillUpgradeEvent implements SkillapiInterface.CHUPlayerSkillUpgradeInterface {
        PlayerSkillUpgradeEvent e;

        public CHUPlayerSkillUpgradeEvent(PlayerSkillUpgradeEvent e){
            this.e = e;
        }

        public Object _GetObject() {
            return null;
        }

        public PlayerSkill getUpgradedSkill() {
            return e.getUpgradedSkill();
        }

        public PlayerData getPlayerData() {
            return e.getPlayerData();
        }

        public int getCost() {
            return e.getCost();
        }

        public boolean isAsynchronous() {
            return e.isAsynchronous();
        }

        public void setCancelled(boolean b) {
            e.setCancelled(b);
        }

    }

    public static class CHUSkillDamageEvent implements SkillapiInterface.CHUSkillDamageInterface {
        SkillDamageEvent e;

        public CHUSkillDamageEvent(SkillDamageEvent e){
            this.e = e;
        }

        public Object _GetObject() {
            return null;
        }

        public boolean isAsynchronous() {
            return e.isAsynchronous();
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

    public static class CHUSkillHealEvent implements SkillapiInterface.CHUSkillHealInterface {

        SkillHealEvent e;

        public CHUSkillHealEvent(SkillHealEvent e){
            this.e = e;
        }

        public Object _GetObject() {
            return null;
        }

        public boolean isAsynchronous() {
            return e.isAsynchronous();
        }

        public double getAmount() {
            return e.getAmount();
        }

        public LivingEntity getHealer() {
            return e.getHealer();
        }

        public LivingEntity getTarget() {
            return e.getTarget();
        }

        public void setCancelled(boolean b) {
            e.setCancelled(b);
        }

        public void setAmount(double v) {
            e.setAmount(v);
        }

    }

    public static class CHUTrueDamageEvent implements SkillapiInterface.CHUTrueDamageInterface {
        TrueDamageEvent e;

        public CHUTrueDamageEvent(TrueDamageEvent e){
            this.e = e;
        }
        public Object _GetObject() {
            return null;
        }

        public boolean isAsynchronous() {
            return e.isAsynchronous();
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




}
