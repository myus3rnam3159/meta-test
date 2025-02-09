package io.mesoneer.interview_challenges.level2;

import io.mesoneer.interview_challenges.Range;

public final class ClosedOpenRange extends Range {
    @Override
    public boolean contains(int value) {
        return value >= this.lowerbound() && value < this.upperbound();
    }

    public ClosedOpenRange(int lowerbound, int upperbound) {
        super(lowerbound, upperbound);
    }
}
