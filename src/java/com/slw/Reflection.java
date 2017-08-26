package com.slw;

import com.laytonsmith.core.exceptions.CRE.CRECastException;
import org.bukkit.Bukkit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by User on 2017-08-03.
 */
public class Reflection {

    private static String BV_PREFIX = Bukkit.getServer().getClass().getPackage().getName();
    private static String NMSV_PREFIX = BV_PREFIX.replace("org.bukkit.craftbukkit", "net.minecraft.server");
    private static String VERSION = BV_PREFIX.replace("org.bukkit.craftbukkit", "").replace("", "");
    private static String CH = "com.laytonsmith";

    private static Class<?> getClassforName(String n){
        try{
            return Class.forName(n);
        } catch (ClassNotFoundException e){
            throw new IllegalArgumentException("Cannot find " + n, e);
        }
    }

    public static Class<?> getNMSClass(String name){
        return getClassforName(NMSV_PREFIX + "." + name);
    }

    public static Class<?> getBukkitClass(String name){
        return getClassforName(BV_PREFIX + "." + name);
    }

    public static Class<?> getCHClass(String name) { return getClassforName(CH+ "." + name); }

    public static Object invoke(String cn, String method, Object... args){
        return(invoke(getClassforName(cn), method, args));
    }

    public static Object invoke(Class<?> clazz, String method, Object... args){
        for(Method method1 : clazz.getDeclaredMethods()){
            if(method1.getName().equals(method)){
                try {
                    method1.setAccessible(true);
                    Object o = method1.invoke(clazz.newInstance(), args);
                    method1.setAccessible(false);
                    Bukkit.broadcastMessage(o.toString());
                    return o;
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
