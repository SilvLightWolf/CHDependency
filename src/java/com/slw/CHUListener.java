package com.slw;

import com.laytonsmith.commandhelper.CommandHelperPlugin;
import org.bukkit.event.Listener;

/**
 * Created by User on 2017-08-04.
 */
public class CHUListener implements Listener{

    private static CHUListener listener;

    public static void register(){
        if(listener == null)
            listener = new CHUListener();
        CommandHelperPlugin.self.registerEvents(listener);
    }

    public static void unregister(){

    }


}
