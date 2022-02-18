package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {
    /**
     * Метод принимает массив цифр от 1 до 9,
     * надо выбрать уникальные и вернуть минимально возможное число, составленное из этих уникальных цифр.
     * Не использовать преобразование в строку и обратно. Например: {1,2,3,3,2,3} вернет 123, а {9,8} вернет 89
     */
    int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce((acc, num) -> acc * 10 + num)
                .orElse(-1);
    }

    /**
     * Если сумма всех чисел нечетная - удалить все нечетные, если четная - удалить все четные.
     * Сложность алгоритма должна быть O(N). Optional - решение в один стрим.
     */
    List<Integer> oddOrEven3(List<Integer> integers) {
        List<Integer> odd = new ArrayList<>(); // неч
        List<Integer> even = new ArrayList<>();// чет
        integers.forEach(x -> {
            if (x % 2 == 0) {
                even.add(x);
            } else {
                odd.add(x);
            }
        });
        return odd.size() % 2 == 0 ? even : odd;
    }

    List<Integer> oddOrEven(List<Integer> integers) {
        return integers.stream().collect(Collectors.teeing(
                Collectors.filtering(x -> x % 2 == 0, Collectors.toList()),
                Collectors.filtering(x -> x % 2 != 0, Collectors.toList()),
                (List<Integer> even, List<Integer> odd) -> odd.size() % 2 == 0 ? even : odd));
    }

    List<Integer> oddOrEven2(List<Integer> integers) {
        var map = integers.stream().collect(Collectors.partitioningBy(x -> x % 2 == 0));
        return map.get(map.get(false).size() % 2 == 0);
    }

    public static void main(String[] args) {
        int[] ar = {1, 2, 3, 3, 2, 3};
        System.out.println(new MainStream().minValue(ar));

        List<Integer> list = List.of(9, 8, 1);
        System.out.println(new MainStream().oddOrEven2(list));

        List<Integer> list2 = List.of(1, 2);
        System.out.println(new MainStream().oddOrEven2(list2));
    }
}
