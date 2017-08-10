package com.slw.event.basic.interfaces;

import com.laytonsmith.core.events.BindableEvent;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.util.List;

/**
 * Created by User on 2017-08-05.
 */
public interface CHUAreaEffectCloudApplyInterface extends BindableEvent{

    public List<LivingEntity> getEntities();
    public AreaEffectCloud getCloud();
    public EntityType getEntityType();

}
