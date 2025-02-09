package io.mesoneer.interview_challenges;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class RangeTest {

  @Test
  public void should_create_range() {
    Range range = Range.of(5, 50);
    assertThat(range.lowerbound()).isEqualTo(5);
    assertThat(range.upperbound()).isEqualTo(50);
  }

  @Test
  public void should_throw_error__when_create_with_lowerbound_bigger_than_upperbound() {
    try {
      Range.of(500, 1);
      fail("Should not allow creating a invalid Range");
    } catch(IllegalArgumentException e) {
      // pass
    }
  }

  @Test
  public void closed_range_should_contain_both_bounds_and_all_elements_in_between() {
    Range closedRange = Range.of(5, 50);

    assertThat(closedRange.contains(Integer.MIN_VALUE)).isEqualTo( false);
    assertThat(closedRange.contains(4)).isEqualTo( false);

    assertThat(closedRange.contains(5)).isEqualTo( true);

    assertThat(closedRange.contains(42)).isEqualTo( true);

    assertThat(closedRange.contains(50)).isEqualTo( true);

    assertThat(closedRange.contains(10000)).isEqualTo( false);
    assertThat(closedRange.contains(Integer.MAX_VALUE)).isEqualTo( false);
  }

  @Test
  public void range_should_be_state_independent() {
    Range range1 = Range.of(5, 10);
    Range range2 = Range.of(11, 20);

    assertThat(range1.contains(10)).isEqualTo( true);
    assertThat(range2.contains(10)).isEqualTo( false);
  }

  @Test
  public void isImmutable() throws Exception {

    // Test that fields are final and cannot be modified via reflection
    final Range range = Range.of(5, 10);

    int checksum = range.hashCode();
    String seri = range.toString();

    range.lowerbound();
    range.upperbound();
    range.contains(7);

    assertEquals(checksum, range.hashCode());
    assertEquals(seri, range.toString());

    assertEquals(5, range.lowerbound());
    assertEquals(10, range.upperbound());

  }

  @Test
  public void isOpenRange() {

    Range open = Range.open(5, 7);
    assertEquals(open.contains(5), false);
  }

  @Test
  public void isOpenClosedRange() {

    Range open = Range.openClosed(5, 7);
    assertEquals(open.contains(5), false);
    assertEquals(open.contains(7), true);
  }

  @Test 
  public void isClosedOpenRange() {

    Range open = Range.closedOpen(5, 7);
    assertEquals(open.contains(5), true);
    assertEquals(open.contains(7), false);
  }
}
