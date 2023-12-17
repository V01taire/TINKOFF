package edu.project5;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
public class BenchmarkMethod {

    private Student student;
    private Method method;
    private MethodHandles.Lookup lookup;
    private MethodHandle nameMethodHandle;
    private StudentGetName studentName;


    @SuppressWarnings({"UncommentedMain", "MagicNumber"})
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(BenchmarkMethod.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(5))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(5))
            .build();

        new Runner(options).run();
    }

    @SuppressWarnings("MultipleStringLiterals")
    @Setup
    public void setup() throws Throwable {
        student = new Student("Егор", "Михайлик");
        method = student.getClass().getMethod("name");
        lookup = MethodHandles.lookup();
        nameMethodHandle = lookup.findVirtual(Student.class, "name", MethodType.methodType(String.class));
        studentName = (StudentGetName) LambdaMetafactory.metafactory(
            lookup,
            "invoke",
            MethodType.methodType(StudentGetName.class),
            MethodType.methodType(String.class, Student.class),
            lookup.findVirtual(Student.class, "name", MethodType.methodType(String.class)),
            MethodType.methodType(String.class, Student.class)
        ).getTarget().invokeExact();
    }

    @Benchmark
    public void directAccess(Blackhole blackhole) {
        String name = student.name();
        blackhole.consume(name);
    }

    @Benchmark
    public void reflectionAccess(Blackhole blackhole) throws InvocationTargetException, IllegalAccessException {
        String name = (String) method.invoke(student);
        blackhole.consume(name);
    }

    @Benchmark
    public void methodHandlesAccess(Blackhole blackhole) throws Throwable {
        String name = (String) nameMethodHandle.invoke(student);
        blackhole.consume(name);
    }
}
