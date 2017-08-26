package com.slw.compatibility;

import com.slw.CHUListener;
import org.bukkit.Bukkit;

/**
 * Created by User on 2017-08-24.
 */
public class McMMOManage {

    public static boolean mcmmoPlay = false;

    public static void register(){
        if(Bukkit.getServer().getPluginManager().isPluginEnabled("mcMMO")) {
            CHUListener.McMMOEventListener.register();
            System.out.println("[CHU Ultra] Success hooked MCMMO!");
            mcmmoPlay = true;
        }else{
            System.out.println("[CH Ultra] McMMO's Function and Events diabled.");
        }
    }

    public static void unregister(){
        if(mcmmoPlay)
            CHUListener.McMMOEventListener.unregister();
    }
}
