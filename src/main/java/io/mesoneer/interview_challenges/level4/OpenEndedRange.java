package io.mesoneer.interview_challenges.level4;

import io.mesoneer.interview_challenges.level3.Range;

public class OpenEndedRange<T extends Comparable<? super T>> extends Range<T> {

    public OpenEndedRange(T lower, T upper) {
        super(lower, upper);
    }

    @Override
    public boolean contains(T value) {

        if (this.lowerbnd == null && this.upperbnd != null)
            return value.compareTo(this.upperbnd) < 0;

        if (this.lowerbnd != null && this.upperbnd == null)
            return value.compareTo(this.lowerbnd) > 0;

        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if(this.lowerbnd == null && this.upperbnd == null){
            return "[Infinity, Infinity]";
        }
        
        // if(this.lowerbnd != null && this.upperbnd != null) {
        //     sb.append("(").append(this.lowerbnd).append(", ").append(this.upperbnd).append(")");

        //     return sb.toString();
        // }

        if (this.upperbnd != null) {
            sb.append("[Infinity, ").append(this.upperbnd).append(")");

            return sb.toString();
        }

        sb.append("(").append(this.lowerbnd).append(", Infinity]");

        return sb.toString();
    }
}
