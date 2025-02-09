package io.mesoneer.interview_challenges.level4;

import io.mesoneer.interview_challenges.level3.Range;

public class OpenEndedRange <T extends Comparable<? super T>> extends Range<T> {

    public OpenEndedRange(T lower, T upper) {
        super(lower, upper);
    }

    @Override
    public boolean contains(T value) {

        if(this.lowerbnd == null && this.upperbnd != null) return value.compareTo(this.upperbnd) < 0;

        if(this.lowerbnd != null && this.upperbnd == null) return value.compareTo(this.lowerbnd) > 0;

        return false;
    }
}
