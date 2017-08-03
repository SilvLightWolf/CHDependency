package com.slw;

import org.bukkit.Bukkit;

/**
 * Created by User on 2017-08-03.
 */
public class Reflection {

    private static String BV_PREFIX = Bukkit.getServer().getClass().getPackage().getName();
    private static String NMSV_PREFIX = BV_PREFIX.replace("org.bukkit.craftbukki", "net.minecraft.server");
    private static String VERSION = BV_PREFIX.replace("org.bukkit.craftbukkit", "").replace(".", "");

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

}
