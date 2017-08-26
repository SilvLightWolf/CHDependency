package com.slw.function.betonquest;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.MCCommandSender;
import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCPlayer;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CVoid;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.CommandHelperEnvironment;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CREException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.slw.compatibility.BetonQuestManage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import pl.betoncraft.betonquest.BetonQuest;
import pl.betoncraft.betonquest.compatibility.placeholderapi.BetonQuestPlaceholder;
import pl.betoncraft.betonquest.conversation.Conversation;
import pl.betoncraft.betonquest.conversation.ConversationResumer;
import pl.betoncraft.betonquest.conversation.InventoryConvIO;
import pl.betoncraft.betonquest.utils.PlayerConverter;

/**
 * Created by User on 2017-08-19.
 */
public class Quest {

    @api
    public static class chu_betonquest_open_gui extends AbstractFunction {

        @SuppressWarnings("unchecked")
        public Class<? extends CREThrowable>[] thrown() {
            return new Class[]{CREException.class};
        }

        public boolean isRestricted() {
            return false;
        }

        public Boolean runAsync() {
            return null;
        }

        public Construct exec(Target t, Environment env, Construct... args) throws ConfigRuntimeException {

            if(!BetonQuestManage.betonquestPlay) return CVoid.VOID;

            MCPlayer mccs = env.getEnv(CommandHelperEnvironment.class).GetPlayer();
            Player p = ((BukkitMCPlayer) mccs)._Player();
            String convid = args[0].val();

            if(args.length == 2) {
                p = ((BukkitMCPlayer) Static.GetPlayer(args[0].val(), t))._Player();
                convid = args[1].val();
            }

            Conversation conv = new Conversation(PlayerConverter.getID(p), convid, p.getLocation());
            InventoryConvIO convio = new InventoryConvIO(conv, PlayerConverter.getID(p));
            convio.display();

            return CVoid.VOID;
        }

        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        public String getName() {
            return "chu_betonquest_open_gui";
        }

        public Integer[] numArgs() {
            return new Integer[]{ 1, 2 };
        }

        public String docs() {
            return "Void (Player, Quest) open quest";
        }
    }
}
