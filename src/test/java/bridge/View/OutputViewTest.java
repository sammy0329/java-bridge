package bridge.View;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class OutputViewTest {
    OutputView outputview = new OutputView();

    @Test
    void 다리_상태_출력함수_테스트_O만_있는경우(){
        // 표준 출력을 임시로 캡처하기 위한 ByteArrayOutputStream 생성
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        // 표준 출력을 임시로 변경하여 outputStream으로 리디렉션
        System.setOut(new PrintStream(outputStream));

        // 실제 출력문 실행
        outputview.printMap(Arrays.asList("O"," ","O"), Arrays.asList(" ","O"," "));

        // outputStream에 캡처된 내용을 String으로 변환
        String printedText = outputStream.toString().trim();

        // 원래의 표준 출력으로 복원
        System.setOut(originalOut);

        // 출력문을 원하는 것과 비교
        assertEquals("[ O |   | O ]\n[   | O |   ]", printedText);
    }

    @Test
    void 다리_상태_출력함수_테스트_X포함(){
        // 표준 출력을 임시로 캡처하기 위한 ByteArrayOutputStream 생성
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        // 표준 출력을 임시로 변경하여 outputStream으로 리디렉션
        System.setOut(new PrintStream(outputStream));

        // 실제 출력문 실행
        outputview.printMap(Arrays.asList("O"," ","O","X"), Arrays.asList(" ","O"," "," "));

        // outputStream에 캡처된 내용을 String으로 변환
        String printedText = outputStream.toString().trim();

        // 원래의 표준 출력으로 복원
        System.setOut(originalOut);

        // 출력문을 원하는 것과 비교
        assertEquals("[ O |   | O | X ]\n[   | O |   |   ]", printedText);
    }

    @Test
    void 게임결과_출력함수_테스트_성공시(){
        // 표준 출력을 임시로 캡처하기 위한 ByteArrayOutputStream 생성
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        // 표준 출력을 임시로 변경하여 outputStream으로 리디렉션
        System.setOut(new PrintStream(outputStream));

        // 실제 출력문 실행
        outputview.printResult(Arrays.asList("O"," ","O","O"), Arrays.asList(" ","O"," "," "),"성공",2);

        // outputStream에 캡처된 내용을 String으로 변환
        String printedText = outputStream.toString().trim();

        // 원래의 표준 출력으로 복원
        System.setOut(originalOut);

        // 출력문을 원하는 것과 비교
        assertEquals("최종 게임 결과\n[ O |   | O | O ]\n[   | O |   |   ]\n\n게임 성공 여부: 성공\n총 시도한 횟수: 2", printedText);
    }

    @Test
    void 게임결과_출력함수_테스트_실패시(){
        // 표준 출력을 임시로 캡처하기 위한 ByteArrayOutputStream 생성
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        // 표준 출력을 임시로 변경하여 outputStream으로 리디렉션
        System.setOut(new PrintStream(outputStream));

        // 실제 출력문 실행
        outputview.printResult(Arrays.asList("O"," ","O","X"), Arrays.asList(" ","O"," "," "),"실패",1);

        // outputStream에 캡처된 내용을 String으로 변환
        String printedText = outputStream.toString().trim();

        // 원래의 표준 출력으로 복원
        System.setOut(originalOut);

        // 출력문을 원하는 것과 비교
        assertEquals("최종 게임 결과\n[ O |   | O | X ]\n[   | O |   |   ]\n\n게임 성공 여부: 실패\n총 시도한 횟수: 1", printedText);
    }

}