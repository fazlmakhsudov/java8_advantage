package java8_advantage;

@Hints({@Hint("hint1"), @Hint("hint2")})
public class ClassA {
    static int sOuterStaticNum;
    int mOuterNum;

    public ClassA(String name) {
        System.out.println(name + " is created");
    }

    public void setmOuterNum(int mOuterNum) {
        this.mOuterNum = mOuterNum;
    }

    public static String staticMethod(String a, String b) {
        return "Static method from ClassA";
    }

    public String backValue(String a, String b) {
        return a + "" + b;
    }

    public void testScopes() {
        IConcat<Integer> iConcat = (a, b) -> {
            mOuterNum = 1;
            System.out.println(mOuterNum + " instance field from lambda expression");
            return a + mOuterNum + b;
        };
        System.out.println(mOuterNum + " " + iConcat.concat(1, 3));
        iConcat = (a, b) -> {
            sOuterStaticNum = 11;
            System.out.println(sOuterStaticNum + " static field from lambda expression");
            return a + sOuterStaticNum + b;
        };
        System.out.println(sOuterStaticNum + " " + iConcat.concat(1, 2));
    }

    @Override
    public String toString() {
        return "ClassA{" +
                "mOuterNum=" + mOuterNum +
                '}';
    }
}
