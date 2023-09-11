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

    @Test
    void 이동할_칸_정상_입력_테스트1() {
        assertDoesNotThrow(() -> Validator.validateCorrectMove("U"));
    }

    @Test
    void 이동할_칸_정상_입력_테스트2() {
        assertDoesNotThrow(() -> Validator.validateCorrectMove("D"));
    }

    @Test
    void 이동할_칸_비정상_입력_테스트_숫자() {
        Throwable thrown = catchThrowable(() -> {
            Validator.validateCorrectMove("123");
        });
        assertThat(thrown).isInstanceOf(Exception.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 이동할_칸_비정상_입력_테스트_U와D_이외의_문자() {
        Throwable thrown = catchThrowable(() -> {
            Validator.validateCorrectMove("E");
        });
        assertThat(thrown).isInstanceOf(Exception.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 이동할_칸_비정상_입력_테스트_문자열() {
        Throwable thrown = catchThrowable(() -> {
            Validator.validateCorrectMove("UD");
        });
        assertThat(thrown).isInstanceOf(Exception.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 이동할_칸_비정상_입력_테스트_숫자와문자() {
        Throwable thrown = catchThrowable(() -> {
            Validator.validateCorrectMove("12d");
        });
        assertThat(thrown).isInstanceOf(Exception.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 재시작여부_정상_입력_테스트1() {
        assertDoesNotThrow(() -> Validator.validateCorrectRetry("R"));
    }

    @Test
    void 재시작여부_정상_입력_테스트2() {
        assertDoesNotThrow(() -> Validator.validateCorrectRetry("Q"));
    }

    @Test
    void 재시작여부_비정상_입력_테스트_숫자() {
        Throwable thrown = catchThrowable(() -> {
            Validator.validateCorrectRetry("123");
        });
        assertThat(thrown).isInstanceOf(Exception.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 재시작여부_비정상_입력_테스트_R와Q_이외의_문자() {
        Throwable thrown = catchThrowable(() -> {
            Validator.validateCorrectRetry("U");
        });
        assertThat(thrown).isInstanceOf(Exception.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 재시작여부_비정상_입력_테스트_문자열() {
        Throwable thrown = catchThrowable(() -> {
            Validator.validateCorrectRetry("RQ");
        });
        assertThat(thrown).isInstanceOf(Exception.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 재시작여부_비정상_입력_테스트_숫자와문자() {
        Throwable thrown = catchThrowable(() -> {
            Validator.validateCorrectRetry("12d");
        });
        assertThat(thrown).isInstanceOf(Exception.class)
                .hasMessageContaining("[ERROR]");
    }

}