package ua.opnu;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        // ===== Завдання 1: Перевірка простих чисел =====
        Predicate<Integer> isPrime = n -> {
            if (n < 2) return false;
            for (int i = 2; i * i <= n; i++) if (n % i == 0) return false;
            return true;
        };

        System.out.println("Прості числа від 1 до 20:");
        for (int i = 1; i <= 20; i++) if (isPrime.test(i)) System.out.print(i + " ");
        System.out.println("\n");

        // ===== Завдання 2: Масив студентів та фільтр =====
        Student1[] students = {
                new Student1("Іван", "CS-101", new int[]{80, 90, 75}),
                new Student1("Олена", "CS-102", new int[]{55, 60, 70}),
                new Student1("Петро", "CS-101", new int[]{40, 50, 65}),
                new Student1("Марія", "CS-102", new int[]{90, 95, 85})
        };

        Predicate<Student1> noDebts = s -> Arrays.stream(s.getMarks()).allMatch(m -> m >= 60);
        Student1[] passedStudents = filter(students, noDebts);
        System.out.println("Студенти без боргів:");
        for (Student1 s : passedStudents) System.out.println(s.getName());
        System.out.println();

        // ===== Завдання 3: Два предикати =====
        Predicate<Student1> cs101 = s -> s.getGroup().equals("CS-101");
        Student1[] cs101NoDebts = filter(students, noDebts.and(cs101));
        System.out.println("Студенти CS-101 без боргів:");
        for (Student1 s : cs101NoDebts) System.out.println(s.getName());
        System.out.println();

        // ===== Завдання 4: Consumer =====
        Consumer<Student1> printName = s -> System.out.println(s.getName());
        System.out.println("Вивід всіх студентів:");
        forEach(students, printName);
        System.out.println();

        // ===== Завдання 5: Predicate + Consumer =====
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Predicate<Integer> even = n -> n % 2 == 0;
        Consumer<Integer> printNum = n -> System.out.print(n + " ");
        System.out.print("Парні числа: ");
        forEachIf(numbers, even, printNum);
        System.out.println("\n");

        // ===== Завдання 6: Function 2^n =====
        Function<Integer, Integer> pow2 = n -> (int) Math.pow(2, n);
        int[] powers = {0,1,2,3,4,5,6,7,8,9};
        int[] pow2result = processArray(powers, pow2);
        System.out.println("2^n: " + Arrays.toString(pow2result));

        // ===== Завдання 7: Function stringify =====
        Function<Integer, String> numToStr = n -> {
            String[] words = {"нуль","один","два","три","чотири","п'ять","шість","сім","вісім","дев'ять"};
            return (n >= 0 && n <= 9) ? words[n] : "?";
        };
        int[] digits = {0,1,2,3,4,5,6,7,8,9};
        String[] strDigits = stringify(digits, numToStr);
        System.out.println("Цифри словами: " + Arrays.toString(strDigits));
    }

    // ===== Методи =====
    public static Student1[] filter(Student1[] input, Predicate<Student1> p) {
        return Arrays.stream(input).filter(p).toArray(Student1[]::new);
    }

    public static void forEach(Student1[] input, Consumer<Student1> c) {
        for (Student1 s : input) c.accept(s);
    }

    public static void forEachIf(int[] input, Predicate<Integer> p, Consumer<Integer> c) {
        for (int i : input) if (p.test(i)) c.accept(i);
    }

    public static int[] processArray(int[] input, Function<Integer, Integer> f) {
        int[] res = new int[input.length];
        for (int i = 0; i < input.length; i++) res[i] = f.apply(input[i]);
        return res;
    }

    public static String[] stringify(int[] input, Function<Integer,String> f) {
        String[] res = new String[input.length];
        for (int i = 0; i < input.length; i++) res[i] = f.apply(input[i]);
        return res;
    }
}
// ===== Клас Student1 =====
class Student1 {
    private String name;
    private String group;
    private int[] marks;

    public Student1(String name, String group, int[] marks) {
        this.name = name;
        this.group = group;
        this.marks = marks;
    }

    public String getName() { return name; }
    public String getGroup() { return group; }
    public int[] getMarks() { return marks; }

    public void setName(String name) { this.name = name; }
    public void setGroup(String group) { this.group = group; }
    public void setMarks(int[] marks) { this.marks = marks; }
}