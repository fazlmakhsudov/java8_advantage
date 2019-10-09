package java8_advantage;

import java.awt.*;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

    @Override
    public String toString() {
        return "Main class";
    }

    public static void main(String[] args) {
        {
            //default interface method, lambda expression
            IConcat<Integer> iConcat1 = (a, b) -> {
                String concat = a + "" + b;
                return Integer.parseInt(concat);
            };
            System.out.println(iConcat1.concat(22, 55));
            System.out.println(iConcat1.defaultMethod(22, 55));
            IConcat<Double> iConcat2 = (a, b) -> (a + b);
            System.out.println(iConcat2.concat(22d, 55d));
            IConcat<Long> iConcat3 = (a, b) -> Long.parseLong(a + "" + b);
            System.out.println(iConcat3.concat(22l, 55l));
            //references to methods
            ClassA classA = new ClassA("classA");
            IConcat<String> iConcat4 = ClassA::staticMethod; // signature of methods should be the same
            System.out.println(iConcat4.concat("1", "2"));
            iConcat4 = classA::backValue;
            System.out.println(iConcat4.concat("1", "2"));
            //references to constructors
            IClassACreator<ClassA> iClassACreator = ClassA::new; // signature of constructor should be the same with interface method
            ClassA classA1 = iClassACreator.createClassA("classA1");//
            classA = iClassACreator.createClassAdefault("classA");
            //lambda scope
            final int i = 1;
            IConcat<Integer> iConcat5 = (a, b) -> (Integer.valueOf(i) + a + b);
            System.out.println(iConcat5.concat(3, 4));
            int i2 = 1;
            IConcat<Integer> iConcat6 = (a, b) -> (i2 + a + b);
            System.out.println(iConcat6.concat(3, 4));
            // i2 = 2; local variables referenced from a lambda expression must be final or effectively final
            // Access to fields and static fields
            classA.testScopes();
            //Predicate
            Predicate<Integer> predicate1 = (a) -> a.equals(1);
            System.out.println(predicate1.test(1));
            System.out.println(predicate1.negate().test(1));
            System.out.println(predicate1.negate().negate().test(1));
            Predicate<Boolean> nonNull = Objects::nonNull;
            System.out.println(nonNull.test(null));
            Predicate<Boolean> isNull = Objects::isNull;
            System.out.println(isNull.test(true));
            System.out.println(isNull.or(nonNull).test(true));
            //Function
            Function<String, Integer> function1 = (a) -> Integer.parseInt(a);
            Function<String, Integer> function2 = Integer::valueOf;
            System.out.println(function1.apply("2") + "  " + function2.apply("2"));
            Function<String, String> function3 = function2.andThen(String::valueOf);
            System.out.println(function3.apply("333"));
            //chain of functions
            Function<Integer, Double> function4 = Double::new;
            Function<Double, Long> function5 = Double::longValue;
            Function<Long, String> function6 = String::valueOf;
            function3 = function2.andThen(function4).andThen(function5).andThen(function6);
            System.out.println(function3.apply("333"));
            System.out.println("* " + function6.compose(function5).compose(function4).apply(555));
            System.out.println("* " + function6.compose(function5).compose(function4).compose(function2).apply("555"));
            //Supplier
            Supplier<Main> mainSupplier = Main::new;
            System.out.println(mainSupplier.get());
            //Consumer
            Consumer<ClassA> classAConsumer = ClassA::testScopes; // it uses certain class method
            classAConsumer.accept(new ClassA("leviafan"));
            classAConsumer = a -> System.out.println("Class hashcode is " + a.hashCode());
            classAConsumer.accept(new ClassA("leviafan"));
        }

    }
}
