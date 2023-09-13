package bridge;

import bridge.View.InputView;
import bridge.View.OutputView;

import java.util.List;

public class GameController {
    private static final GameController instance = new GameController();
    private InputView inputView;
    private OutputView outputView;
    private BridgeGame bridgeGame;
    private BridgeNumberGenerator bridgeRandomNumberGenerator;
    private final BridgeMaker bridgeMaker;

    private GameController() {
        inputView = new InputView();
        outputView = new OutputView();
        bridgeGame = BridgeGame.getInstance();

        bridgeRandomNumberGenerator = new BridgeRandomNumberGenerator();
        bridgeMaker = new BridgeMaker(bridgeRandomNumberGenerator);
    }

    public static GameController getInstance() {
        return instance;
    }

    public List<String> makeBridge(int size) {
        return bridgeMaker.makeBridge(size);
    }

    public void gameSetting() throws IllegalArgumentException{
        outputView.printGameStart(); // 게임시작 알림
        bridgeGame.setAnswerBridge(makeBridge(inputView.readBridgeSize())); // 다리 길이 입력 받기
    }

    public boolean checkSuccessGame(boolean checkSuccessMove) {
        if (checkSuccessMove && bridgeGame.isLastBridge()) {
            outputView.printResult(bridgeGame.getUpBridgeStates(), bridgeGame.getDownBridgeStates(), "성공", bridgeGame.getTryCnt());
            return true;
        }
        return false;
    }

    public boolean checkRetryOrFail(boolean checkSuccessMove) throws IllegalArgumentException {
        if (!checkSuccessMove) {
            if (bridgeGame.retry(inputView.readGameCommand())) {
                bridgeGame.resetBridgeState();
                return false;
            }
            outputView.printResult(bridgeGame.getUpBridgeStates(), bridgeGame.getDownBridgeStates(), "실패", bridgeGame.getTryCnt());
            return true;
        }
        return false;
    }


    public void gameMain() throws IllegalArgumentException{
        while (true) {
            boolean checkSuccessMove = bridgeGame.move(inputView.readMoving());
            outputView.printMap(bridgeGame.getUpBridgeStates(), bridgeGame.getDownBridgeStates());
            if (checkSuccessGame(checkSuccessMove) || checkRetryOrFail(checkSuccessMove)) break;
        }
    }

    public void gameStart() throws IllegalArgumentException{
        gameSetting();
        gameMain();
    }


}
