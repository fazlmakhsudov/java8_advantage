package java8_advantage;

public class Main {
    public static void main(String[] args) {
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
    }
}
