package io.mesoneer.interview_challenges;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import io.mesoneer.interview_challenges.level3.Range;

public class GenericRangeTest {

    @Test
    public void isGenericRange(){
        Range<String> text = Range.open("abc", "xyz");

        Range<BigDecimal> decimals = Range.open(BigDecimal.valueOf(1.32432), BigDecimal.valueOf(1.324323423423423423423));

        Range<LocalDate> dates = Range.closed(LocalDate.of(2016, Month.SEPTEMBER, 11), LocalDate.of(2017, Month.JUNE, 30));
    }
}
