package edu.hw11.Task1;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import static net.bytebuddy.matcher.ElementMatchers.named;

@SuppressWarnings("HideUtilityClassConstructor")
public class ByteBuddyGenerator {
    public static Class<?> byteBuddy = new ByteBuddy()
        .subclass(Object.class)
        .method(named("toString"))
        .intercept(FixedValue.value("Hello, ByteBuddy!"))
        .make()
        .load(ByteBuddyGenerator.class.getClassLoader())
        .getLoaded();
}
