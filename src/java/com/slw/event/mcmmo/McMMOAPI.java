package com.slw.event.mcmmo;

import com.gmail.nossr50.datatypes.skills.SkillType;
import com.gmail.nossr50.datatypes.skills.XPGainReason;
import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;
import com.laytonsmith.core.constructs.CInt;
import com.laytonsmith.core.constructs.Target;
import org.bukkit.entity.Player;

/**
 * Created by User on 2017-08-24.
 */
public class McMMOAPI {

    public static class CHUMcMMOLevelUpEvent implements McMMOInterface.CHUMcMMOLevelUpInterface{

        McMMOPlayerLevelUpEvent e;

        public CHUMcMMOLevelUpEvent(McMMOPlayerLevelUpEvent e){
            this.e = e;
        }

        public Object _GetObject() {
            return null;
        }

        public Player getPlayer() {
            return e.getPlayer();
        }

        public CInt getSkillLevel() {
            return new CInt(e.getSkillLevel(), Target.UNKNOWN);
        }

        public CInt getLevelsGained() {
            return new CInt(e.getLevelsGained(),  Target.UNKNOWN);
        }

        public SkillType getSkill() {
            return e.getSkill();
        }

        public XPGainReason getXpGainReason() {
            return e.getXpGainReason();
        }

        public void setLevelsGained(int v) {
            e.setLevelsGained(v);
        }
    }
}
