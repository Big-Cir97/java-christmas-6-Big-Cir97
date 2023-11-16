package christmas.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConverterTest {

    @Test
    @DisplayName("문자열을 숫자로 변환한다.")
    void testStringToInteger() {
        String input = "123";
        int expected = 123;

        assertThat(Converter.toInteger(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("문자열을 '-'로 자른다.")
    void testSplitMinusOperation() {
        String input = "나-너";
        String[] split = Converter.splitByMinusOperation(input);

        assertThat(split[0]).isEqualTo("나");
        assertThat(split[1]).isEqualTo("너");
    }

    @Test
    @DisplayName("원화 형식으로 변경한다.")
    void testTransThousandWon() {
        int won = 1_000;

        assertThat(Converter.toThousandWonFormmat(won)).isNotEqualTo("1000");
        assertThat(Converter.toThousandWonFormmat(won)).isEqualTo("1,000");
    }
}