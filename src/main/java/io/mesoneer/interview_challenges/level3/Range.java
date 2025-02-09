package io.mesoneer.interview_challenges.level3;

public final class Range<T extends Comparable<? super T>>{
    private final T lowerbnd;
    private final T upperbnd;

    private Range(T lower, T upper){
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
}
