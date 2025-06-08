package br.com.scheiner.core.utils;

import java.time.temporal.Temporal;
import java.util.Objects;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AspectUtils {

	public static boolean isTipoSimples(Object obj) {
        if (Objects.isNull(obj)) {
            return true;
        }
        Class<?> clazz = obj.getClass();
        return clazz.isPrimitive() ||
               clazz == String.class ||
               Number.class.isAssignableFrom(clazz) ||
               clazz == Boolean.class ||
               clazz == Character.class ||
               clazz == UUID.class ||
               Temporal.class.isAssignableFrom(clazz);
    }
	
}
