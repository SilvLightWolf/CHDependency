package com.slw;

import com.laytonsmith.abstraction.MCLocation;
import com.laytonsmith.abstraction.MCWorld;
import com.laytonsmith.core.ObjectGenerator;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CBoolean;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;

/**
 * Created by User on 2017-08-04.
 */
public class DataHandling {

    public static boolean isNumeric(Object num){
        return isNumeric(new CString(num.toString(), Target.UNKNOWN));
    }

    public static boolean isNumeric(Construct num){
        boolean numeric = true;
        try{
            Static.getNumber(num, Target.UNKNOWN);
        } catch (ConfigRuntimeException e){
            numeric = false;
        }
        return numeric;
    }

}
