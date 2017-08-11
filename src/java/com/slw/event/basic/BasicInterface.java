package com.slw.event.basic;

import com.laytonsmith.core.events.BindableEvent;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.util.List;

/**
 * Created by User on 2017-08-11.
 */

public class BasicInterface {

    public interface CHUAreaEffectCloudApplyInterface extends BindableEvent {

        public List<LivingEntity> getEntities();
        public AreaEffectCloud getCloud();
        public EntityType getEntityType();

    }

}
