package org.storm.clover.domain;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * Marker class that signals that there is a class attribute named 'id' of type long.
 */
public interface Identifiable {

    class Utils {
        public static long getId(Identifiable identifiable) {
            try {
                return (long) getIdField(identifiable).get(identifiable);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(identifiable.getClass() + " not accessible!");
            }
        }

        public static long getId(Identifiable identifiable, long fallback){
            if(identifiable == null) return fallback;
            try{
                return getId(identifiable);
            } catch (Exception e){
                return fallback;
            }
        }

        public static void setId(Identifiable identifiable, long id) {
            try {
                getIdField(identifiable).set(identifiable, id);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(identifiable.getClass() + " not accessible!");
            }
        }

        private static Field getIdField(Identifiable identifiable) {
            Objects.nonNull(identifiable);
            try {
                Field idField = identifiable.getClass().getDeclaredField("id");
                idField.setAccessible(true);
                return idField;
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(identifiable.getClass() + " does not have an attribute named 'id' of type long!");
            }
        }
    }
}
