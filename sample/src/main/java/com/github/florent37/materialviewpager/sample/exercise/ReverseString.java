package com.github.florent37.materialviewpager.sample.exercise;

import java.util.Scanner;

/**
 * Created by raian on 12/12/16.
 */

public class ReverseString {
    public static void main(String[] args) {
        System.out.print("Type a String: ");
        Scanner in = new Scanner(System.in);

        String input = in.nextLine();

        StringBuilder stringBuilder = new StringBuilder(input);
        System.out.print("Reverse String is: " + stringBuilder.reverse().toString());
    }

}
