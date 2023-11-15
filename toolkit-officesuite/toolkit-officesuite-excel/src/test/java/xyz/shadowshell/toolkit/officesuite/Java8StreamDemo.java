//package xyz.shadowshell.toolkit.officesuite;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.junit.Test;
//
///**
// * Java8StreamDemo
// *
// * @author shadow
// */
//public class Java8StreamDemo {
//
//    @net.shadowdefender.toolkit.Test
//    public void forEach() {
//
//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
//        numbers.stream().forEach(System.out::println);
//    }
//
//    @net.shadowdefender.toolkit.Test
//    public void map() {
//
//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
//
//        List<Integer> squaresList = numbers.stream().map(i -> i * 100).collect(Collectors.toList());
//
//        squaresList.stream().forEach(System.out::println);
//    }
//
//    @net.shadowdefender.toolkit.Test
//    public void filter() {
//
//        List<Integer> numbers = Arrays.asList(-1, 0, 1, 2, 3, 4, 5);
//
//        numbers.stream().filter(item -> item <= 0).forEach(System.out::println);
//    }
//
//    @net.shadowdefender.toolkit.Test
//    public void limit() {
//
//        List<Integer> numbers = Arrays.asList(-1, 0, 1, 2, 3, 4, 5);
//
//        numbers.stream().limit(1).forEach(System.out::println);
//    }
//
//    @Test
//    public void sorted() {
//
//        List<Integer> numbers = Arrays.asList(-1, 0, 1, 2, 3, 4, 5);
//
//        numbers.stream().sorted().forEach(System.out::println);
//    }
//
//}