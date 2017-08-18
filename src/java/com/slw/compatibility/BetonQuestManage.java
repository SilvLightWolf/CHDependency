package com.slw.compatibility;

import com.slw.CHUListener;
import org.bukkit.Bukkit;

/**
 * Created by User on 2017-08-15.
 */
public class BetonQuestManage {

    public static boolean betonquestPlay = false;

    public static void register(){
        if(Bukkit.getServer().getPluginManager().isPluginEnabled("BetonQuest")) {
            CHUListener.SkillAPIEventListener.register();
            System.out.println("[CHU Ultra] Success hooked BetonQuest!");
            betonquestPlay = true;
        }else{
            System.out.println("[CH Ultra] Betonquest's Function and Events diabled.");
        }
    }

}
