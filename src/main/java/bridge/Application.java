package bridge;

public class Application {

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        GameController gameController = GameController.getInstance();
        try {
            gameController.gameStart();
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
