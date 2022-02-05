package com.urise.webapp;

import java.util.Objects;

public class MainUtil {
    public static void main(String[] args) {
        System.out.println(Integer.valueOf(-1) == Integer.valueOf(-1));
        System.out.println(Objects.equals(Integer.valueOf(-1), Integer.valueOf(-1)));
        int result = getInt();
        System.out.println(result);
    }

    private static Integer getInt() {
        return null;
    }
}