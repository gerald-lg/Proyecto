package cl.ucn.disc.pdbp.tdd.utils;

import org.apache.commons.lang3.builder.MultilineRecursiveToStringStyle;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Entity/Domain utils
 * @author Gerald López
 */
public final class Entity {

    /**
     * @param object to convert to String
     * @return the String
     */
    public static String toString(Object object){
        return ReflectionToStringBuilder.toString(object, new MultilineRecursiveToStringStyle());
    }
}
