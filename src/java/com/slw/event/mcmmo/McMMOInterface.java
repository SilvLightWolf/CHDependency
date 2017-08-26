package com.slw.event.mcmmo;

import com.gmail.nossr50.datatypes.skills.SkillType;
import com.gmail.nossr50.datatypes.skills.XPGainReason;
import com.laytonsmith.core.constructs.CInt;
import com.laytonsmith.core.events.BindableEvent;
import org.bukkit.entity.Player;

/**
 * Created by User on 2017-08-24.
 */
public class McMMOInterface {

    public interface CHUMcMMOLevelUpInterface extends BindableEvent{

        public Player getPlayer();
        public CInt getSkillLevel();
        public CInt getLevelsGained();
        public SkillType getSkill();
        public XPGainReason getXpGainReason();
        public void setLevelsGained(int v);
    }
}
