package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                .reduce((acc, num) -> acc * 10 + num).orElse(-1);
    }

    /**
     * Если сумма всех чисел нечетная - удалить все нечетные, если четная - удалить все четные.
     * Сложность алгоритма должна быть O(N). Optional - решение в один стрим.
     */
    List<Integer> oddOrEven3 (List<Integer> integers) {
        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();
        integers.forEach(x -> {
            if (x % 2 == 0) {
                even.add(x);
            } else{
                odd.add(x);
            }
        });
       return odd.size() % 2 == 0 ? even : odd;
    }

    public static void main(String[] args) {
        int[] ar = {1, 2, 3, 3, 2, 3};
        System.out.println(new MainStream().minValue(ar));
    }
}
