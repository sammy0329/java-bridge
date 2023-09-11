package bridge.View;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    @Test
    void 다리갯수_문자열로_입력시_예외처리_테스트1() {
        Throwable thrown = catchThrowable(() -> {
            Validator.validateBridgeCnt("d");
        });
        assertThat(thrown).isInstanceOf(Exception.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 다리갯수_문자열로_입력시_예외처리_테스트2() {
        Throwable thrown = catchThrowable(() -> {
            Validator.validateBridgeCnt("1d");
        });
        assertThat(thrown).isInstanceOf(Exception.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 다리갯수_3미만_숫자_입력시_예외처리_테스트() {
        Throwable thrown = catchThrowable(() -> {
            Validator.validateBridgeCnt("2");
        });
        assertThat(thrown).isInstanceOf(Exception.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 다리갯수_20초과_숫자_입력시_예외처리_테스트() {
        Throwable thrown = catchThrowable(() -> {
            Validator.validateBridgeCnt("21");
        });
        assertThat(thrown).isInstanceOf(Exception.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 다리갯수_정상입력_테스트() {
            assertDoesNotThrow(() -> Validator.validateBridgeCnt("14"));
    }

}