package com.slw.event.skillapi;

import com.laytonsmith.core.events.BindableEvent;
import com.sucy.skill.api.player.PlayerAccounts;
import com.sucy.skill.api.player.PlayerData;
import com.sucy.skill.api.player.PlayerSkill;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 * Created by User on 2017-08-11.
 */
public class SkillapiInterface {

    /*public interface CHUFlagApplyInterface extends BindableEvent { }

    public interface CHUFlagExpireInterface extends BindableEvent { }

    public interface CHUItemProjectileHitInterface extends BindableEvent { }

    public interface CHUItemProjectileLandInterface extends BindableEvent { }

    public interface CHUItemProjectileLaunchInterface extends BindableEvent { }

    public interface CHUParticleProjectileExpireInterface extends BindableEvent { }

    public interface CHUParticleProjectileHitInterface extends BindableEvent { }

    public interface CHUParticleProjectileLandInterface extends BindableEvent { }

    public interface CHUParticleProjectileLaunchInterface extends BindableEvent { }*/

    public interface CHUPhysicalDamageInterface extends BindableEvent {

        public boolean isAsynchronous();
        public boolean isProjectile();
        public double getDamage();
        public LivingEntity getDamager();
        public LivingEntity getTarget();
        public void setCancelled(boolean b);
        public void setDamage(double v);

    }

    public interface CHUPlayerAccountChangeInterface extends BindableEvent {

        public boolean isAsynchronous();
        public PlayerAccounts getAccountData();
        public PlayerData getNewAccount();
        public PlayerData getPrevAccount();
        public int getNewID();
        public int getPrevId();
        public void setCancelled(boolean b);

    }

    public interface CHUCastSkillInterface extends BindableEvent {

        public Player getPlayer();
        public PlayerData getPlayerData();
        public PlayerSkill getSkill();
        public boolean isAsynchronous();
        public void setCancelled(boolean b);

    }

    /*public interface CHUPlayerClassChangeInterface extends BindableEvent { }

    public interface CHUComboFinishInterface extends BindableEvent { }

    public interface CHUPlayerExperienceGainInterface extends BindableEvent { }

    public interface CHUPlayerExperienceLostInterface extends BindableEvent { }

    public interface CHUPlayerGainSkillPointsInterface extends BindableEvent { } //¡Ú

    public interface CHUPlayerLandInterface extends BindableEvent { } //¡Ú

    public interface CHUPlayerLevelUpInterface extends BindableEvent { }

    public interface CHUPlayerManaGainInterface extends BindableEvent { }

    public interface CHUPlayerManaLostInterface extends BindableEvent { }

    public interface CHUPlayerRefundAttributeInterface extends BindableEvent { }*/

    public interface CHUPlayerSkillDowngradeInterface extends BindableEvent {

        public PlayerSkill getDowngradedSkill();
        public PlayerData getPlayerData();
        public int getRefund();
        public boolean isAsynchronous();
        public void setCancelled(boolean b);

    }

    /*public interface CHUPlayerSkillUnlockInterface extends BindableEvent { }

    public interface CHUPlayerSkillUpgradeInterface extends BindableEvent { } //¡Ú

    public interface CHUPlayerUpAttributeInterface extends BindableEvent { }

    public interface CHUSkillDamageInterface extends BindableEvent { } //¡Ú

    public interface CHUSkillHealInterface extends BindableEvent { } //¡Ú

    public interface CHUTrueDamageIntrerface extends BindableEvent { } //¡Ú*/

}
