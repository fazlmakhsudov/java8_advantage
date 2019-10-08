package java8_advantage;

@FunctionalInterface
public interface IClassACreator<C extends ClassA> {
    default ClassA createClassAdefault(String name) {
        return new ClassA(name);
    }

    C createClassA(String name);
}
