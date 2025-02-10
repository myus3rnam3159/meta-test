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

    @Override
    public String toString() {

        if(this.lowerbnd == null && this.upperbnd == null){
            return "[Infinity, Infinity]";
        }
        StringBuilder sb = new StringBuilder();

        if(this.lowerbnd != null && this.upperbnd != null) {
            sb.append("[").append(this.lowerbnd).append(", ").append(this.upperbnd).append("]");

            return sb.toString();
        }

        if (this.upperbnd != null) {
            sb.append("[Infinity, ").append(this.upperbnd).append("]");

            return sb.toString();
        }

        sb.append("[").append(this.lowerbnd).append(", Infinity]");

        return sb.toString();
    }
}
