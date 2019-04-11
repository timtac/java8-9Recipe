package FunctionPackage;

public interface FunctionWithInterfaceException<T, R, E extends Exception> {
    R apply(T t) throws E;
}
