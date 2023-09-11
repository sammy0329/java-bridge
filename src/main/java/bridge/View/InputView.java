package bridge.View;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        String bridgeSize = camp.nextstep.edu.missionutils.Console.readLine();
        Validator.validateBridgeCnt(bridgeSize);

        return Integer.parseInt(bridgeSize);
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        String movingCommand = camp.nextstep.edu.missionutils.Console.readLine();
        Validator.validateCorrectMove(movingCommand);

        return movingCommand;
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        String gameRetryCommand = camp.nextstep.edu.missionutils.Console.readLine();
        Validator.validateCorrectRetry(gameRetryCommand);

        return gameRetryCommand;
    }
}
