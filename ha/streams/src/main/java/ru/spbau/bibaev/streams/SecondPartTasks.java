package ru.spbau.bibaev.streams;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import ru.spbau.bibaev.utils.Pair;

@SuppressWarnings("WeakerAccess")
public final class SecondPartTasks {

    public static final int ATTEMPT_COUNT = 1000000;

    private SecondPartTasks() {
    }

    // Найти строки из переданных файлов, в которых встречается указанная подстрока.
    public static List<String> findQuotes(List<String> paths, CharSequence sequence) throws UncheckedIOException {
        return paths.stream().flatMap(s -> {
            try {
                return Files.lines(Paths.get(s)).filter(l -> !l.isEmpty());
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        })
                .filter(s -> s.contains(sequence))
                .collect(Collectors.toList());
    }

    // В квадрат с длиной стороны 1 вписана мишень.
    // Стрелок атакует мишень и каждый раз попадает в произвольную точку квадрата.
    // Надо промоделировать этот процесс с помощью класса java.util.Random и посчитать, какова вероятность попасть в мишень.
    public static double piDividedBy4() {
        Random rand = new Random(2016);
        int success = 0;
        for (int i = 0; i < ATTEMPT_COUNT; i++) {
            final double x = rand.nextDouble() - 0.5;
            final double y = rand.nextDouble() - 0.5;
            if (x * x + y * y <= 0.25) {
                success++;
            }
        }

        return (double) success / ATTEMPT_COUNT;
    }

    // Дано отображение из имени автора в список с содержанием его произведений.
    // Надо вычислить, чья общая длина произведений наибольшая.
    public static String findPrinter(Map<String, List<String>> compositions) {
        return compositions.entrySet()
                .stream()
                .map(stringListEntry ->
                        new Pair<>(stringListEntry.getKey(), stringListEntry.getValue()
                                .stream()
                                .mapToInt(String::length)
                                .sum()))
                .max(Comparator.comparing(Pair::getSecond))
                .map(Pair::getFirst)
                .orElse(null);
    }

    // Вы крупный поставщик продуктов. Каждая торговая сеть делает вам заказ в виде Map<Товар, Количество>.
    // Необходимо вычислить, какой товар и в каком количестве надо поставить.
    public static Map<String, Integer> calculateGlobalOrder(List<Map<String, Integer>> orders) {
        return orders
                .stream()
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Integer::sum));
    }

}
