package com.slw;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;
import com.slw.compatibility.SkillAPIManage;

/**
 * Created by User on 2017-08-03.
 */

@MSExtension("CHUltra")
public class LifeCycle extends AbstractExtension {

    @Override
    public void onStartup(){

        SkillAPIManage.register();
        System.out.println("CH ULTRA " + getVersion() + " ENABLED!");

        CHUListener.BasicEventListener.register();


    }

    @Override
    public void onShutdown(){

        SkillAPIManage.unregister();

        System.out.println("CH ULTRA " + getVersion() + " DISABLED!");
        CHUListener.BasicEventListener.unregister();
    }

    public Version getVersion() {
        return new SimpleVersion(1, 0 , 0);
    }
}
