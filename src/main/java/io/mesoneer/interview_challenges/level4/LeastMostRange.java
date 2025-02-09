package io.mesoneer.interview_challenges.level4;

import io.mesoneer.interview_challenges.level3.Range;

public class LeastMostRange <T extends Comparable<? super T>> extends Range<T> {

    public LeastMostRange(T lower, T upper) {
        super(lower, upper);
    }

    @Override
    public boolean contains(T value) {

        if(this.lowerbnd == null && this.upperbnd == null) {
            return true;
        }
        if(this.lowerbnd == null) {
            return value.compareTo(this.upperbnd) <= 0;
        }

        return  value.compareTo(this.lowerbnd) >= 0;
    }
}
