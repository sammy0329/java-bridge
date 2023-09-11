package bridge.View;

public class Validator {
    public static void validateBridgeCnt(String bridgeSize) throws IllegalArgumentException{
        int bridgeSizeInt = 0;

        try {
            bridgeSizeInt = Integer.parseInt(bridgeSize);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("[ERROR] 다리의 갯수를 숫자로 입력해주세요.");
        }

        if(bridgeSizeInt<3 || bridgeSizeInt>20){
            throw new IllegalArgumentException("[ERROR] 다리의 갯수는 3 이상 20 이하의 숫자로 입력해주세요.");
        }
    }

    public static void validateCorrectMove(String movingCommand){
        if(movingCommand.equals("U") || movingCommand.equals("D")){
            return;
        }
        throw new IllegalArgumentException("[ERROR] 이동할 칸은 U,D 둘 중 하나를 선택해주세요.");
    }

    public static void validateCorrectRetry(String gameRetryCommand){
        if(gameRetryCommand.equals("R") || gameRetryCommand.equals("Q")){
            return;
        }
        throw new IllegalArgumentException("[ERROR] 이동할 칸은 U,D 둘 중 하나를 선택해주세요.");
    }
}
