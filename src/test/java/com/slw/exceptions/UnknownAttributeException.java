package com.slw.exceptions;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.typeof;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.exceptions.CRE.CREException;

/**
 * Created by User on 2017-08-03.
 */

@typeof("CastException")
public class UnknownAttributeException extends CREException{

    public UnknownAttributeException(String msg, Target t) {
        super(msg, t);
    }

    public UnknownAttributeException(String msg, Target t, Throwable cause){
        super(msg, t, cause);
    }

    @Override
    public String docs(){
        return "This exception is thrown if a value is not in the attributes type.";
    }

    @Override
    public Version since(){
        return new SimpleVersion(1, 0, 0);
    }

}
