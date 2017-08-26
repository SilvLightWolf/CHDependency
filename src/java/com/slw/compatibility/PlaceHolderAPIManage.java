package com.slw.compatibility;

import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.CHLog;
import com.laytonsmith.core.LogLevel;
import com.laytonsmith.core.Profiles;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CNull;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.environments.GlobalEnv;
import com.laytonsmith.core.exceptions.CRE.CREFormatException;
import com.laytonsmith.core.exceptions.CRE.CREIOException;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.exceptions.MarshalException;
import com.laytonsmith.core.functions.Persistence;
import com.laytonsmith.persistence.DataSourceException;
import com.slw.CHUListener;
import com.slw.Reflection;
import me.clip.placeholderapi.external.EZPlaceholderHook;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by User on 2017-08-26.
 */
public class PlaceHolderAPIManage {

    public static boolean placeholderapiPlay = false;

    public static void register(){
        if(Bukkit.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new PlaceHolderHook("ch").hook();
            System.out.println("[CHU Ultra] Success hooked PlaceHolderAPI!");
            placeholderapiPlay = true;
        }else{
            System.out.println("[CH Ultra] PlaceHolderAPI's Function and Events diabled.");
        }
    }

    public static void unregister(){ }


    public static class PlaceHolderHook extends EZPlaceholderHook{

        CommandHelperPlugin plugin = CommandHelperPlugin.self;

        public PlaceHolderHook( String identifier) {
            super(CommandHelperPlugin.self, identifier);
        }

        public String onPlaceholderRequest(Player player, String s) {

            Bukkit.broadcastMessage(((player!=null)?player.getName():"none")+" , "+ s);

            if(player == null) return "noneplayer";

            if(s.toLowerCase().startsWith("get_value_")) {

                Object o;
                String k;

                String key = s.replace("get_value_", "");
                String[] args = key.split("_");
                Construct[] cons = new Construct[args.length];
                for(int i = 0; i < args.length ; i++) {
                    cons[i] = new CString(args[i], Target.UNKNOWN);
                }
                Object invoked = Reflection.invoke(Reflection.getCHClass("core.functions.Persistence"), "GetNamespace", cons, null, "get_value", Target.UNKNOWN);
                if (invoked == null) return "NULL";

                String ns = invoked.toString();
                k = args[args.length - 1];
                CHLog.GetLogger().Log(CHLog.Tags.PERSISTENCE, LogLevel.DEBUG, "Getting value: " + ns, Target.UNKNOWN);

                try {
                    Object obj = null;
                    try {
                        obj = (Static.GenerateStandaloneEnvironment()).getEnv(GlobalEnv.class).GetPersistenceNetwork().get(("storage." + ns).split("\\."));
                    } catch (DataSourceException e) {
                        throw new CREIOException(e.getMessage(), Target.UNKNOWN, e);
                    } catch (IllegalArgumentException e) {
                        throw new CREFormatException(e.getMessage(), Target.UNKNOWN, e);
                    } catch (Exception ignored) {
                    }

                    if (obj == null) return "null";

                    o = Construct.json_decode(obj.toString(), Target.UNKNOWN);
                }catch(MarshalException e){
                    throw ConfigRuntimeException.CreateUncatchableException(e.getMessage(), Target.UNKNOWN);
                }

                return o.toString();

            }

            return null;
        }
    }
}
