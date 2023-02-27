package com.urise.webapp;

import java.util.*;
import java.util.stream.Collectors;


public class StreamJava8API {
    public static void main(String[] args) {
        int[] array = fillArray();
        System.out.println("\nStart int[] array -> " + Arrays.toString(array) + " <-");
        int minValue = minValue(array);
        System.out.println("MinValue Number ->" + minValue + "<-");

        List<Integer> integers = new ArrayList<>();
        Collections.addAll(integers, Arrays.stream(array).boxed().toArray(Integer[]::new));
        System.out.println("\nStart List<Integer> -> " + integers + " <-");

        List<Integer> answer = oddOrEven(integers);
        System.out.println(answer.size() % 2 == 0 ? "Sum of ListValue is Odd" : "Sum of ListValue is Even");
        System.out.println("Answer List<Integer> -> " + answer + " <-");
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        Map<Boolean, List<Integer>> collect = integers.stream().collect(
                Collectors.partitioningBy(n -> n % 2 == 0));
        return collect.get(false).size() % 2 == 0 ? collect.get(true) : collect.get(false);
    }

    private static int minValue(int[] array) {
        return Arrays.stream(array)
                .sorted()
                .distinct()
                .reduce(0, (a, b) -> 10 * a + b);
    }

    private static int[] fillArray() {
        Random rd = new Random();
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = rd.nextInt(9);
        }
        return array;
    }
}
