package bridge;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BridgeGameTest {
    BridgeGame bridgeGame = BridgeGame.getInstance();

    @Test
    void move_테스트_1단계_통과(){
        bridgeGame.setAnswerBridge(Arrays.asList("U","U","D","D"));
        boolean isSucessOrFailMove = bridgeGame.move("U");
        assertEquals(isSucessOrFailMove, true);
        assertEquals(Arrays.asList("O"), bridgeGame.getUpBridgeStates());
        assertEquals(Arrays.asList(" "), bridgeGame.getDownBridgeStates());
    }

    @Test
    void move_테스트_2단계_통과(){
        boolean isSucessOrFailMove = bridgeGame.move("U");
        assertEquals(isSucessOrFailMove, true);
        assertEquals(Arrays.asList("O","O"), bridgeGame.getUpBridgeStates());
        assertEquals(Arrays.asList(" "," "), bridgeGame.getDownBridgeStates());
    }

    @Test
    void move_테스트_3단계_실패(){
        boolean isSucessOrFailMove = bridgeGame.move("U");
        assertEquals(isSucessOrFailMove, false);
        assertEquals(Arrays.asList("O","O","X"), bridgeGame.getUpBridgeStates());
        assertEquals(Arrays.asList(" "," "," "), bridgeGame.getDownBridgeStates());
    }
}