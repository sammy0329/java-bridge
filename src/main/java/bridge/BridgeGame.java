package bridge;

import java.util.ArrayList;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private int tryCnt = 1;

    private int currentBridgeIdx = -1;

    private List<String> upBridgeStates = new ArrayList<>();
    ;
    private List<String> downBridgeStates = new ArrayList<>();
    private List<String> answerBridge;


    private static final BridgeGame instance = new BridgeGame();

    private BridgeGame() {}

    public static BridgeGame getInstance() {
        return instance;
    }

    public void setAnswerBridge(List<String> getAnswerBridge){
        answerBridge = getAnswerBridge;
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     */
    public boolean move(String movingCommand) {
        currentBridgeIdx++;
        if (isCorrectMovingCommand(movingCommand)) {
            return true;
        }
        isNotCorrectMovingCommand(movingCommand);

        return false;
    }

    private boolean isCorrectMovingCommand(String movingCommand) {
        if (movingCommand.equals("U") && answerBridge.get(currentBridgeIdx).equals(movingCommand)) {
            upBridgeStates.add("O");
            downBridgeStates.add(" ");
            return true;
        } else if (movingCommand.equals("D") && answerBridge.get(currentBridgeIdx).equals(movingCommand)) {
            downBridgeStates.add("O");
            upBridgeStates.add(" ");
            return true;
        }
        return false;
    }

    private void isNotCorrectMovingCommand(String movingCommand) {
        if (movingCommand.equals("U") && !answerBridge.get(currentBridgeIdx).equals(movingCommand)) {
            upBridgeStates.add("X");
            downBridgeStates.add(" ");
        } else if (movingCommand.equals("D") && !answerBridge.get(currentBridgeIdx).equals(movingCommand)) {
            downBridgeStates.add("X");
            upBridgeStates.add(" ");
        }
    }

    public boolean isLastBridge(){
        return upBridgeStates.size() == answerBridge.size();
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 사용자가 "R" 입력시 true 반환, "Q" 입력시 false 반환
     * InputView에서 Validator 했기 때문에 넘어오는 값은 "R" or "Q"
     */
    public boolean retry(String gameRetryCommand) {
        if (gameRetryCommand.equals("R")) {
            tryCnt++;
            return true;
        }
        return false;
    }

    /**
     * 게임 시작을 위해 upBridgeStates, downBridgeStates 리스트를 초기화 해주는 메서드
     */
    public void resetBridgeState() {
        upBridgeStates.clear();
        downBridgeStates.clear();
        currentBridgeIdx = -1;
    }

    public int getTryCnt() {
        return tryCnt;
    }

    public List<String> getUpBridgeStates() {
        return upBridgeStates;
    }

    public List<String> getDownBridgeStates() {
        return downBridgeStates;
    }
}
