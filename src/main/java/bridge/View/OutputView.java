package bridge.View;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */

    // 게임 시작 알림 함수
    public void printGameStart() {
        System.out.println("다리 건너기 게임을 시작합니다.");
    }

    // 다리 상태 출력 함수
    public void printMap(String[] upBridgeStates, String[] downBridgeStates) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ").append(String.join(" | ", upBridgeStates)).append(" ]").append('\n');
        sb.append("[ ").append(String.join(" | ", downBridgeStates)).append(" ]").append('\n');
        System.out.println(sb);
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */

    /**
     * 게임 결과 출력 함수
     * <p>
     * parms
     * upBridgeStates : 위쪽 다리 상태 String 배열
     * downBridgeStates : 아래쪽 다리 상태 String 배열
     * result : 게임 결과
     */

    public void printResult(String[] upBridgeStates, String[] downBridgeStates, String result, int tryCnt) {
        System.out.println("최종 게임 결과");
        printMap(upBridgeStates,downBridgeStates);
        System.out.println("게임 성공 여부: " + result);
        System.out.println("총 시도한 횟수: " + tryCnt);
    }
}
