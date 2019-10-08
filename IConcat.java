package java8_advantage;

@FunctionalInterface
public interface IConcat<T> {
    T concat(T a, T b);

    default String defaultMethod(T a, T b) {
        return a + "" + b;
    }
}
