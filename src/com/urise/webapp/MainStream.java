package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainStream {
    /**
     * Метод принимает массив цифр от 1 до 9,
     * надо выбрать уникальные и вернуть минимально возможное число, составленное из этих уникальных цифр.
     * Не использовать преобразование в строку и обратно. Например: {1,2,3,3,2,3} вернет 123, а {9,8} вернет 89
     */
    int minValue(int[] values) {
        int[] arr = Arrays.stream(values)
                .distinct()
                .sorted()
                .toArray();

        return IntStream.range(0, arr.length)
                .map(i -> (int) Math.pow(10, arr.length - i - 1) * arr[i])
                .reduce(0, Integer::sum);
    }

    /**
     * Если сумма всех чисел нечетная - удалить все нечетные, если четная - удалить все четные.
     * Сложность алгоритма должна быть O(N). Optional - решение в один стрим.
     */
    List<Integer> oddOrEven(List<Integer> integers) {
        List<Integer> odd = new ArrayList<>();// нечет
        List<Integer> even = new ArrayList<>();// чет
        int sum = 0;
        for (Integer num : integers) {
            sum += num;
            if (num % 2 == 0) {
                even.add(num);
            } else {
                odd.add(num);
            }
        }
        return sum % 2 == 0 ? even : odd;
    }

    List<Integer> oddOrEven2(List<Integer> integers) {
        List<Integer> even = integers.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        return even.size() % 2 == 0 ? even : integers.stream().filter(x -> x % 2 != 0).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] ar = {1, 2, 3, 3, 2, 3};
        System.out.println(new MainStream().minValue(ar));
    }
}
