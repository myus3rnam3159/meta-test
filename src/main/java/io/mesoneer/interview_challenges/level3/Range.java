package io.mesoneer.interview_challenges.level3;

import io.mesoneer.interview_challenges.level4.LeastMostRange;
import io.mesoneer.interview_challenges.level4.OpenEndedRange;

public class Range<T extends Comparable<? super T>>{
    protected final T lowerbnd;
    protected final T upperbnd;

    protected Range(T lower, T upper){
        this.lowerbnd = lower;
        this.upperbnd = upper;
    }

    public static <T extends Comparable<? super T>> Range<T> open(T lower, T upper) throws IllegalArgumentException{
        if(lower.compareTo(upper) >= 0){
            throw new IllegalArgumentException("Lower bound must be less than upper bound");
        }
        return new Range<>(lower, upper);
    }

    public static <T extends Comparable<? super T>> Range<T> closed(T lower, T upper) throws IllegalArgumentException{
        return open(lower, upper);
    }

    public static <T extends Comparable<? super T>> OpenEndedRange<T> lessThan(T upper){
        return new OpenEndedRange<>(null, upper);
    }

    public boolean contains(T value){
        return false;
    };

    public static <T extends Comparable<? super T>> LeastMostRange<T> atLeast(T lower){
        return new LeastMostRange<>(lower, null);
    }

    public static <T extends Comparable<? super T>> LeastMostRange<T> atMost(T upper){
        return new LeastMostRange<>(null, upper);
    }

    public static <T extends Comparable<? super T>> LeastMostRange<T> closed2(T lower, T upper){
        return new LeastMostRange<>(lower, upper);
    }

    public static <T extends Comparable<? super T>> LeastMostRange<T> all(){
        return new LeastMostRange<>(null, null);
    }

    public static <T extends Comparable<? super T>> OpenEndedRange<T> greaterThan(T lower){
        return new OpenEndedRange<>(lower, null);
    }
}
