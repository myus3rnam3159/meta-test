package io.mesoneer.interview_challenges.level2;

import io.mesoneer.interview_challenges.Range;

public class OpenClosedRange extends Range {
    @Override
    public boolean contains(int value) {
        return value > this.lowerbound() && value <= this.upperbound();
    }

    public OpenClosedRange(int lowerbound, int upperbound) {
        super(lowerbound, upperbound);
    }
}
