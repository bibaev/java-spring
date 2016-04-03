package ru.spbau.mit.bibaev.functional;

import org.junit.Test;
import ru.spbau.mit.bibaev.functional.func.Function1;
import ru.spbau.mit.bibaev.functional.func.Function2;
import ru.spbau.mit.bibaev.functional.func.Predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static java.util.Collections.emptyList;
import static org.junit.Assert.*;

public class CollectionsTest {

    @Test
    public void map() throws Exception {
        ArrayList<Integer> intList = new ArrayList<>(Arrays.asList(1, 2, 3));

        Function1<Integer, Integer> square = value -> value * value;

        Collection<Integer> result = Collections.map(square, intList);
        assertEquals(Arrays.asList(1, 4, 9), result);
    }

    @Test
    public void filter() throws Exception {
        ArrayList<Integer> numberList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Predicate<Integer> even = x -> x % 2 == 0;

        Collection<Integer> result = Collections.filter(even, numberList);
        assertEquals(Arrays.asList(2, 4, 6), result);
    }

    @Test
    public void takeWhile() throws Exception {
        ArrayList<Integer> numberList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Predicate<Integer> lessThenFive = x -> x < 5;

        Collection<Integer> result = Collections.takeWhile(lessThenFive, numberList);
        assertEquals(Arrays.asList(1, 2, 3, 4), result);
    }

    @Test
    public void takeUnless() throws Exception {
        ArrayList<Integer> numberList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Predicate<Integer> positive = x -> x > 0;

        Collection<Integer> result = Collections.takeUnless(positive, numberList);
        assertEquals(emptyList(), result);
    }

    @Test
    public void foldl() throws Exception {
        ArrayList<Integer> numberList = new ArrayList<>(Arrays.asList(1, 2, 3, 5));
        Function2<Double, Integer, Double> diff = (l, r) -> l - r;

        assertEquals(-11, Collections.foldl(diff, 0.0, numberList).intValue());
    }

    @Test
    public void foldr() throws Exception {
        ArrayList<Integer> numberList = new ArrayList<>(Arrays.asList(1, 2, 3, 5));
        Function2<Integer, Double, Double> diff = (l, r) -> l - r;
        assertEquals(-3, Collections.foldr(diff, 0.0, numberList).intValue());
    }
}
