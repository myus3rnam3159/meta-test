package io.mesoneer.interview_challenges;

public final class Range {
  private final int lower;
  private final int upper;
  /**
   * Constructor is private BY DESIGN.
   *
   * TODO: Change the constructor to meet your requirements.
   */
  private Range(int lowerbound, int upperbound) {

    if(lowerbound > upperbound){
      throw new IllegalArgumentException();
    }

    this.lower = lowerbound;
    this.upper = upperbound;
  }

  /**
   * Creates a new <b>closed</b> {@code Range} that includes both bounds.
   */
  public static Range of(int lowerbound, int upperbound) {
    // TODO: Implement here!
    return new Range(lowerbound, upperbound);
  }

  /**
   * Returns {@code true} on if the given {@code value} is contained in this
   * {@code Range}.
   */
  public boolean contains(int value) {
    // TODO: Implement here!
    return value >= this.lower && value <= this.upper;
  }

  /**
   * Returns the {@code lowerbound} of this {@code Range}.
   */
  public int lowerbound() {
    // TODO: Implement here!
    return this.lower;
  }

  /**
   * Returns the {@code upperbound} of this {@code Range}.
   */
  public int upperbound() {
    // TODO: Implement here!
    return this.upper;
  }

}
