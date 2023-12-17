package edu.hw11.Task3;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.jar.asm.Opcodes;

@SuppressWarnings("HideUtilityClassConstructor")
public class MakeFibonacci {

    public static Class<?> fibonacci = new ByteBuddy()
        .subclass(Object.class)
        .name("Fibonacci")
        .defineMethod("fib", long.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
        .withParameters(long.class)
        .intercept(FibonacciImplementation.INSTANCE)
        .make()
        .load(MakeFibonacci.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
        .getLoaded();
}
