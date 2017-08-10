package com.slw.skillapi;

import com.laytonsmith.abstraction.MCLocation;
import com.laytonsmith.abstraction.MCWorld;
import com.laytonsmith.core.ObjectGenerator;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.player.PlayerAccounts;
import com.sucy.skill.api.player.PlayerData;
import com.sucy.skill.api.skills.Skill;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by User on 2017-08-10.
 */
public class SkillAPIManage {

    public static CArray getSkillArray(Skill sk){

        Target t = Target.UNKNOWN;

        CArray arr = new CArray(t);

        CArray costs = new CArray(t);
        CArray mcost = new CArray(t);
        CArray cools = new CArray(t);
        CArray descs = new CArray(t);
        CArray range = new CArray(t);
        CArray lreqs = new CArray(t);

        arr.set("combo", String.valueOf(sk.getCombo()));
        arr.set("skillReq", sk.getSkillReq());
        arr.set("maxLevel", String.valueOf(sk.getMaxLevel()));
        arr.set("message", sk.getMessage());
        arr.set("name", sk.getName());
        arr.set("key", sk.getKey());
        arr.set("type", sk.getType());

        for(String txt : sk.getDescription()){
            descs.push(new CString(txt, t), t);
        }

        for(int i = 0 ; i < sk.getMaxLevel() ; i++){
            costs.push(new CString(String.valueOf(sk.getCost(i)), t), t);
            mcost.push(new CString(String.valueOf(sk.getManaCost(i)), t), t);
            cools.push(new CString(String.valueOf(sk.getCooldown(i)), t), t);
            range.push(new CString(String.valueOf(sk.getRange(i)), t), t);
            lreqs.push(new CString(String.valueOf(sk.getLevelReq(i)), t), t);
        }

        arr.set("cost", costs, t);
        arr.set("manacost", mcost, t);
        arr.set("cooltime", cools, t);
        arr.set("description", descs, t);
        arr.set("range", range, t);
        arr.set("levelreq", lreqs, t);



        return arr;

    }

    public static boolean isExistentSkill(String n){
        for(Skill s : SkillAPI.getSkills().values())
            if(s.getName().equalsIgnoreCase(n)) return true;
        return false;
    }

    public static void refreshAccount(OfflinePlayer p){
        PlayerAccounts pa = SkillAPI.getPlayerAccountData(p);
        int cur = pa.getActiveId();
        if(cur == 1){ pa.setAccount(2); pa.setAccount(1); }
        else { pa.setAccount(1); pa.setAccount(cur); }
    }
}
