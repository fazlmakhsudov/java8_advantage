package java8_advantage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    static List<String> sStringCollection = new ArrayList<String>();
    static List<String> sValues;

    static {
        sStringCollection.add("ddd2");
        sStringCollection.add("ddd1");
        sStringCollection.add("aaa2");
        sStringCollection.add("ggg2");
        sStringCollection.add("ccc2");
        sStringCollection.add("hhh1");
        sStringCollection.add("aaa1");
        sStringCollection.add("eee1");
        sStringCollection.add("eee1");
        sStringCollection.add("bbb1");
        sStringCollection.add("bbb1");
        sStringCollection.add("kkk1");
        sValues = new ArrayList<>();
        for (int i = 0; i < 1000_000; i++) {
            UUID uuid = UUID.randomUUID();
            sValues.add(uuid.toString());
        }
    }

    @Override
    public String toString() {
        return "Main class";
    }

    public static void main(String[] args) {
        {
//            //default interface method, lambda expression
//            IConcat<Integer> iConcat1 = (a, b) -> {
//                String concat = a + "" + b;
//                return Integer.parseInt(concat);
//            };
//            System.out.println(iConcat1.concat(22, 55));
//            System.out.println(iConcat1.defaultMethod(22, 55));
//            IConcat<Double> iConcat2 = (a, b) -> (a + b);
//            System.out.println(iConcat2.concat(22d, 55d));
//            IConcat<Long> iConcat3 = (a, b) -> Long.parseLong(a + "" + b);
//            System.out.println(iConcat3.concat(22l, 55l));
//            //references to methods
//            ClassA classA = new ClassA("classA");
//            IConcat<String> iConcat4 = ClassA::staticMethod; // signature of methods should be the same
//            System.out.println(iConcat4.concat("1", "2"));
//            iConcat4 = classA::backValue;
//            System.out.println(iConcat4.concat("1", "2"));
//            //references to constructors
//            IClassACreator<ClassA> iClassACreator = ClassA::new; // signature of constructor should be the same with interface method
//            ClassA classA1 = iClassACreator.createClassA("classA1");//
//            classA = iClassACreator.createClassAdefault("classA");
//            //lambda scope
//            final int i = 1;
//            IConcat<Integer> iConcat5 = (a, b) -> (Integer.valueOf(i) + a + b);
//            System.out.println(iConcat5.concat(3, 4));
//            int i2 = 1;
//            IConcat<Integer> iConcat6 = (a, b) -> (i2 + a + b);
//            System.out.println(iConcat6.concat(3, 4));
//            // i2 = 2; local variables referenced from a lambda expression must be final or effectively final
//            // Access to fields and static fields
//            classA.testScopes();
//            //Predicate
//            Predicate<Integer> predicate1 = (a) -> a.equals(1);
//            System.out.println(predicate1.test(1));
//            System.out.println(predicate1.negate().test(1));
//            System.out.println(predicate1.negate().negate().test(1));
//            Predicate<Boolean> nonNull = Objects::nonNull;
//            System.out.println(nonNull.test(null));
//            Predicate<Boolean> isNull = Objects::isNull;
//            System.out.println(isNull.test(true));
//            System.out.println(isNull.or(nonNull).test(true));
//            //Function
//            Function<String, Integer> function1 = (a) -> Integer.parseInt(a);
//            Function<String, Integer> function2 = Integer::valueOf;
//            System.out.println(function1.apply("2") + "  " + function2.apply("2"));
//            Function<String, String> function3 = function2.andThen(String::valueOf);
//            System.out.println(function3.apply("333"));
//            //chain of functions
//            Function<Integer, Double> function4 = Double::new;
//            Function<Double, Long> function5 = Double::longValue;
//            Function<Long, String> function6 = String::valueOf;
//            function3 = function2.andThen(function4).andThen(function5).andThen(function6);
//            System.out.println(function3.apply("333"));
//            System.out.println("* " + function6.compose(function5).compose(function4).apply(555));
//            System.out.println("* " + function6.compose(function5).compose(function4).compose(function2).apply("555"));
//            //Supplier
//            Supplier<Main> mainSupplier = Main::new;
//            System.out.println(mainSupplier.get());
//            //Consumer
//            Consumer<ClassA> classAConsumer = ClassA::testScopes; // it uses certain class method
//            classAConsumer.accept(new ClassA("leviafan"));
//            classAConsumer = a -> System.out.println("Class hashcode is " + a.hashCode());
//            classAConsumer.accept(new ClassA("leviafan"));
//            //Comparetor
//            Comparator<ClassA> comparator = (a, b) -> {
//                if (a.mOuterNum > b.mOuterNum) return 1;
//                else if (a.mOuterNum < b.mOuterNum) return -1;
//                else return 0;
//            };
//            List<ClassA> list = Arrays.asList(new ClassA("class1"), new ClassA("class2"), new ClassA("class3"));
//            list.get(0).setmOuterNum(12);
//            list.get(1).setmOuterNum(1);
//            list.get(2).setmOuterNum(4);
//            System.out.println(list);
//            Collections.sort(list, comparator);
//            System.out.println(list);
//            //Optional class
//            String str = "bam";
//            Optional<String> optional = Optional.of(str);
//            System.out.println(optional.isPresent());
//            System.out.println(optional.get());
//            System.out.println(optional.orElse("hello from java"));
//            optional.ifPresent(s -> System.out.println("there is " + s));
//        //        //Filter
//        sStringCollection
//                .stream()
//                .filter((s) -> s.startsWith("a"))
//                .forEach(System.out::println);
//        //Sorted
//        sStringCollection
//                .stream()
//                .sorted()
//                .filter((s) -> s.startsWith("a"))
//                .forEach(System.out::println);
//        //Map
//        sStringCollection
//                .stream()
//                .map(String::toUpperCase)
//                .sorted((a, b) -> b.compareTo(a))
//                .forEach(System.out::println);
//        //Match
//        boolean anyStartWithA =
//                sStringCollection
//                        .stream()
//                        .anyMatch((s) -> s.startsWith("a"));
//        System.out.println(anyStartWithA);
//        boolean noneStartWithZ =
//                sStringCollection
//                        .stream()
//                        .noneMatch((s) -> s.startsWith("z"));
//        System.out.println(noneStartWithZ);
//        boolean allStartWithA =
//                sStringCollection
//                        .stream()
//                        .allMatch((s) -> s.startsWith("a"));
//        System.out.println(allStartWithA);
//        //Count
//        long startsWithB =
//                sStringCollection
//                    .stream()
//                    .filter((s) -> s.startsWith("b"))
//                    .count();
//        System.out.println(startsWithB);
//        //Reduce
//        Optional<String> reduced =
//                sStringCollection
//                    .stream()
//                    .sorted()
//                    .reduce((s1, s2) -> s1 + "#" + s2);
//        reduced.ifPresent(System.out::println);+
//            //Parralel stream
//            long t0 = System.nanoTime();
//            long count = sValues.stream().sorted().count();
//            System.out.println(count);
//            long t1 = System.nanoTime();
//            long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
//            System.out.println(String.format("sequential sort took: %d ms", (t1 - t0)));
//            t0 = System.nanoTime();
//            count = sValues.parallelStream().sorted().count();
//            System.out.println(count);
//            t1 = System.nanoTime();
//            millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
//            System.out.println(String.format("parallel sort took: %d ms", (t1 - t0)));
//            //Assosiative array
//            Map<Integer, String> map = new HashMap<>();
//            for (int i = 0; i < 10; i++) {
//                map.putIfAbsent(i, "val " + i);
//            }
//            //   map.forEach((id, val) -> System.out.println(val));
//            map.computeIfPresent(3, (num, val) -> val + num);
//            System.out.println(map.get(3));
//            map.computeIfPresent(9, (num, val) -> null);
//            System.out.println(map.containsKey(9));
//            map.computeIfAbsent(23, num -> "val" + num);
//            System.out.println(map.containsKey(23));
//            map.computeIfAbsent(3, num -> "bam");
//            System.out.println(map.get(3));
//            map.remove(3, "val 3");
//            System.out.println(map.get(3));
//            map.remove(3, "val 33");
//            System.out.println(map.get(3));
//            map.getOrDefault(42, "not found");
//            System.out.println(map.get(42));
//            map.merge(9, "val 9", (value, newValue) -> value.concat(newValue));
//            System.out.println(map.get(9));
//            map.merge(9, "val 9", (value, newValue) -> value.concat(newValue));
//            System.out.println(map.get(9));
            //Clock
//        Clock clock = Clock.systemDefaultZone();
//        long millis = clock.millis();
//        Instant instant = clock.instant();
//        Date legacyDate = Date.from(instant);
//        System.out.println(legacyDate);
//        //LocalTime
//        ZoneId zoneId = ZoneId.of("Europe/London");
//        LocalTime now1 = LocalTime.now();
//        LocalTime now2 = LocalTime.now(zoneId);
//        System.out.println(now1 + "  " + now2);
//        System.out.println(now1.isBefore(now2));
//        long minutesBetweenZones = ChronoUnit.MINUTES.between(now1, now2);
//        System.out.println(minutesBetweenZones);
//        LocalTime late = LocalTime.of(23, 56, 59);
//        System.out.println(late);
//        DateTimeFormatter germanFormatter =
//                DateTimeFormatter
//                        .ofLocalizedTime(FormatStyle.SHORT)
//                        .withLocale(Locale.GERMAN);
//        LocalTime lateTime = LocalTime.parse("13:25", germanFormatter);
//        System.out.println(lateTime);
        }
    }

}
