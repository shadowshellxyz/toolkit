package net.shadowdefender.toolkit;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author shadow
 */
public class Test4 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2");

        list.forEach(System.out::println);

    }
}