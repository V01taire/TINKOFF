package edu.hw11.Task3;

import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;

enum FibonacciImplementation implements Implementation {
    INSTANCE;

    @Override
    public ByteCodeAppender appender(Target target) {
        return FibonacciGenerator.INSTANCE;
    }

    @Override
    public InstrumentedType prepare(InstrumentedType instrumentedType) {
        return instrumentedType;
    }
}
