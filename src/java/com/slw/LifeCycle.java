package com.slw;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.events.PacketListener;
import com.comphenix.protocol.injector.packet.PacketRegistry;
import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.environments.CommandHelperEnvironment;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 2017-08-03.
 */

@MSExtension("CHUltra")
public class LifeCycle extends AbstractExtension {

    @Override
    public void onStartup(){

        System.out.println("CH ULTRA " + getVersion() + " ENABLED!");

        CHUListener.register();


    }

    @Override
    public void onShutdown(){
        System.out.println("CH ULTRA " + getVersion() + " DISABLED!");
        CHUListener.unregister();
    }

    public Version getVersion() {
        return new SimpleVersion(1, 0 , 0);
    }
}
