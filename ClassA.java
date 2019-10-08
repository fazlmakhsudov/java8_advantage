package java8_advantage;

public class ClassA {

    public ClassA(String name) {
        System.out.println(name + " is created");
    }

    public static String staticMethod(String a, String b) {
        return "Static method from ClassA";
    }

    public String backValue(String a, String b) {
        return a + "" + b;
    }
}
