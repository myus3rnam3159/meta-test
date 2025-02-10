package io.mesoneer.interview_challenges;

import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static org.junit.jupiter.api.Assertions.*;

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
        assertFalse(lessThanFive.contains(5)); // false;
        assertTrue(lessThanFive.contains(-9000)); // true;
    }

    @Test
    public void isAtLeastRange() {

        Range<Integer> atLeastFive = Range.atLeast(5); // [5, Infinity]
        assertTrue(atLeastFive.contains(5)); // true;
        assertFalse(atLeastFive.contains(4)); // false;
    }

    @Test
    public void isAtMostRange() {

        Range<Integer> atMostFive = Range.atMost(5); // [Infinity, 5]
        assertTrue(atMostFive.contains(5)); // true
        assertTrue(atMostFive.contains(-234234)); // true;
        assertFalse(atMostFive.contains(6)); // false;
    }

    @Test
    public void isAllRange() {
        Range<String> all = Range.all(); // [Infinity, Infinity]
        assertTrue(all.contains("anything")); // true;
        assertTrue(all.contains("")); // true;
        assertTrue(all.contains(null)); // true;
    }

    @Test
    public void isGreaterRange() {

        Range<LocalDate> afterEpoch = Range.greaterThan(LocalDate.of(1900, JANUARY, 1)); // (1900-01-01, Infinity]
        assertTrue(afterEpoch.contains(LocalDate.of(2016, Month.JULY, 28))); // true;
        assertFalse(afterEpoch.contains(LocalDate.of(1750, JANUARY, 1))); // false;
    }

    @Test
    public void isOpenEndedRangeToString() {
        Range<Integer> lessThen100 = Range.lessThan(100);
        assertEquals("[Infinity, 100)", lessThen100.toString());
    }

    @Test
    public void isLeastMostRangeToString() {
        Range<LocalDate> within2020 = Range.closed2(
                LocalDate.of(2020, JANUARY, 1),
                LocalDate.of(2020, DECEMBER, 31));
        assertEquals("[2020-01-01, 2020-12-31]", within2020.toString());
    }
}