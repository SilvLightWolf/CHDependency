package com.slw;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;
import com.slw.compatibility.BetonQuestManage;
import com.slw.compatibility.McMMOManage;
import com.slw.compatibility.PlaceHolderAPIManage;
import com.slw.compatibility.SkillAPIManage;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by User on 2017-08-03.
 */

@MSExtension("CHUltra")
public class LifeCycle extends AbstractExtension {

    private boolean isEnabled = false;

    @Override
    public void onStartup(){

        new BukkitRunnable() {
            public void run() {
                if(!isEnabled) {
                    SkillAPIManage.register();
                    BetonQuestManage.register();
                    McMMOManage.register();
                    PlaceHolderAPIManage.register();
                    isEnabled = !isEnabled;
                }
            }
        }.runTaskLater(CommandHelperPlugin.self, 0L);

        System.out.println("CH ULTRA " + getVersion() + " ENABLED!");

        CHUListener.BasicEventListener.register();


    }

    @Override
    public void onShutdown(){

        SkillAPIManage.unregister();
        BetonQuestManage.unregister();
        McMMOManage.unregister();

        System.out.println("CH ULTRA " + getVersion() + " DISABLED!");
        CHUListener.BasicEventListener.unregister();
    }

    public Version getVersion() {
        return new SimpleVersion(1, 0 , 0);
    }
}
