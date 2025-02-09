package io.mesoneer.interview_challenges;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;
import io.mesoneer.interview_challenges.level3.Range;

public class GenericRangeTest {

    @Test
    public void isGenericRange() {
        Range<String> text = Range.open("abc", "xyz");

        Range<BigDecimal> decimals = Range.open(BigDecimal.valueOf(1.32432),
                BigDecimal.valueOf(1.324323423423423423423));

        Range<LocalDate> dates = Range.closed(LocalDate.of(2016, Month.SEPTEMBER, 11),
                LocalDate.of(2017, Month.JUNE, 30));
    }

    @Test
    public void isOpenEndedRange() {
        Range<Integer> lessThanFive = Range.lessThan(5); // [Infinity, 5)
        assertEquals(lessThanFive.contains(5), false); // false;
        assertEquals(lessThanFive.contains(-9000), true); // true;
    }

    @Test
    public void isAtLeastRange() {

        Range<Integer> atLeastFive = Range.atLeast(5); // [5, Infinity]
        assertEquals(atLeastFive.contains(5), true); // true;
        assertEquals(atLeastFive.contains(4), false); // false;
    }

    @Test
    public void isAtMostRange() {

        Range<Integer> atMostFive = Range.atMost(5); // [Infinity, 5]
        assertEquals(atMostFive.contains(5), true); // true
        assertEquals(atMostFive.contains(-234234), true); // true;
        assertEquals(atMostFive.contains(6), false); // false;
    }

    @Test
    public void isAllRange() {
        Range<String> all = Range.all(); // [Infinity, Infinity]
        assertEquals(all.contains("anything"), true); // true;
        assertEquals(all.contains(""), true); // true;
        assertEquals(all.contains(null), true); // true;
    }

    @Test
    public void isGreaterRange() {

        Range<LocalDate> afterEpoch = Range.greaterThan(LocalDate.of(1900, Month.JANUARY, 1)); // (1900-01-01, Infinity]
        assertEquals(afterEpoch.contains(LocalDate.of(2016, Month.JULY, 28)), true); // true;
        assertEquals(afterEpoch.contains(LocalDate.of(1750, Month.JANUARY, 1)), false); // false;
    }
}
