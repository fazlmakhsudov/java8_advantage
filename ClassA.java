package java8_advantage;

import java.security.spec.RSAOtherPrimeInfo;

public class ClassA {
    static int outerStaticNum;
    int outerNum;

    public ClassA(String name) {
        System.out.println(name + " is created");
    }

    public static String staticMethod(String a, String b) {
        return "Static method from ClassA";
    }

    public String backValue(String a, String b) {
        return a + "" + b;
    }

    public void testScopes() {
        IConcat<Integer> iConcat = (a, b) -> {
            outerNum = 1;
            System.out.println(outerNum+" instance field from lambda expression");
            return a+outerNum+b;
        };
        System.out.println(outerNum + " " + iConcat.concat(1, 3));
        iConcat = (a, b) -> {
            outerStaticNum = 11;
            System.out.println(outerStaticNum + " static field from lambda expression");
            return a+outerStaticNum+b;
        };
        System.out.println(outerStaticNum + " " + iConcat.concat(1, 2));
    }
}
