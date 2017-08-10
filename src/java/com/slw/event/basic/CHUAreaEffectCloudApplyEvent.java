package com.slw.event.basic;

import com.slw.event.basic.interfaces.CHUAreaEffectCloudApplyInterface;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.AreaEffectCloudApplyEvent;

import java.util.List;

/**
 * Created by User on 2017-08-05.
 */
public class CHUAreaEffectCloudApplyEvent implements CHUAreaEffectCloudApplyInterface {

    AreaEffectCloudApplyEvent e;

    public CHUAreaEffectCloudApplyEvent(AreaEffectCloudApplyEvent e){
        this.e = e;
    }

    public List<LivingEntity> getEntities() {
        return e.getAffectedEntities();
    }

    public AreaEffectCloud getCloud() {
        return e.getEntity();
    }

    public EntityType getEntityType() {
        return e.getEntityType();
    }

    public Object _GetObject() {
        return null;
    }
}
