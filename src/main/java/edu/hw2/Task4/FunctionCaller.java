package edu.hw2.Task4;

public class FunctionCaller {

    public static CallingInfo callingInfo() {

        Throwable throwable = new Throwable();

        return new CallingInfo(throwable.getStackTrace()[1].getClassName(),
            throwable.getStackTrace()[1].getMethodName());
    }

    private FunctionCaller() {}

    public record CallingInfo(String className, String methodName) {}
}
