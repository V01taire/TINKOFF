package edu.hw10.Task2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CacheProxy implements InvocationHandler {
    private final Object target;
    private final Map<String, Object> cache;
    private static final Logger LOGGER = LogManager.getLogger(CacheProxy.class);

    private CacheProxy(Object target) {
        this.target = target;
        this.cache = new ConcurrentHashMap<>();
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(Object target, Class<?> interfaceClass) {
        return (T) Proxy.newProxyInstance(
            interfaceClass.getClassLoader(),
            new Class<?>[]{interfaceClass},
            new CacheProxy(target)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            String key = generateKey(method, args);

            if (cache.containsKey(key)) {
                LOGGER.info("Cache hit!");
                return cache.get(key);
            } else {
                LOGGER.info("Cache miss!");
                Object result = method.invoke(target, args);
                cache.put(key, result);

                if (method.getAnnotation(Cache.class).persist()) {
                    persistToDisk(key, result);
                }

                return result;
            }
        } else {
            return method.invoke(target, args);
        }
    }

    private String generateKey(Method method, Object[] args) {
        return method.getName() + Arrays.hashCode(args);
    }

    private void persistToDisk(String key, Object value) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(key))) {
            oos.writeObject(value);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    private Object restoreFromDisk(String key) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(key))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error(e);
            return null;
        }
    }
}



