package ru.spbau.bibaev.utils;

public class Pair<T1, T2> {
    public Pair(T1 fst, T2 snd) {
        first = fst;
        second = snd;
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    private T1 first;
    private T2 second;
}
