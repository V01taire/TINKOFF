package edu.hw10.Task1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"MultipleStringLiterals", "MagicNumber"})
public class RandomObjectGenerator {
    private final Random random = new Random();
    private static final Logger LOGGER = LogManager.getLogger(RandomObjectGenerator.class);

    public <T> T nextObject(Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

    public <T> T nextObject(Class<T> clazz, String factoryMethodName) {
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            T instance = constructor.newInstance();

            Method factoryMethod = clazz.getDeclaredMethod(factoryMethodName, String.class, int.class);
            factoryMethod.setAccessible(true);
            return (T) factoryMethod.invoke(instance, "RandomString", 25);
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }


    public Object generateValueWithAnnotations(Class<?> fieldType) {
        for (Field field : fieldType.getDeclaredFields()) {
            if (field.isAnnotationPresent(NotNull.class)) {
                return generateRandomValue(field.getType());
            }

            if (field.isAnnotationPresent(Min.class)) {
                Min minAnnotation = field.getAnnotation(Min.class);
                int minValue = minAnnotation.value();
                return Math.max(minValue, (int) generateRandomValue(field.getType()));
            }

            if (field.isAnnotationPresent(Max.class)) {
                Max maxAnnotation = field.getAnnotation(Max.class);
                int maxValue = maxAnnotation.value();
                return Math.min(maxValue, (int) generateRandomValue(field.getType()));
            }
        }

        return generateRandomValue(fieldType);
    }


    public Object generateRandomValue(Class<?> fieldType) {
        if (fieldType == int.class || fieldType == Integer.class) {
            return random.nextInt();
        } else if (fieldType == double.class || fieldType == Double.class) {
            return random.nextDouble();
        } else if (fieldType == String.class) {
            return "RandomString";
        } else {
            return null;
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface NotNull {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Min {
        int value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Max {
        int value();
    }
}

