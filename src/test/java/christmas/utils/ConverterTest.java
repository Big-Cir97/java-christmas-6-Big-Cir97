package christmas.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
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
}