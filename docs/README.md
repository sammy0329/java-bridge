# 3주차-다리 건너기

[기능 요구사항]

- 위아래 두 칸으로 이루어진 다리를 건너야 한다.
    - 다리는 왼쪽에서 오른쪽으로 건너야 한다.
    - 위아래 둘 중 하나의 칸만 건널 수 있다.
- 다리의 길이를 숫자로 입력받고 생성한다.
    - 다리를 생성할 때 위 칸과 아래 칸 중 건널 수 있는 칸은 0과 1 중 무작위 값을 이용해서 정한다.
    - 위 칸을 건널 수 있는 경우 U, 아래 칸을 건널 수 있는 경우 D값으로 나타낸다.
    - 무작위 값이 0인 경우 아래 칸, 1인 경우 위 칸이 건널 수 있는 칸이 된다.
- 다리가 생성되면 플레이어가 이동할 칸을 선택한다. (사용자 입력)
    - 이동할 때 위 칸은 대문자 U, 아래 칸은 대문자 D를 입력한다.
    - 이동한 칸을 건널 수 있다면 O로 표시한다. 건널 수 없다면 X로 표시한다.
- 다리를 끝까지 건너면 게임이 종료된다.
- 다리를 건너다 실패하면 게임을 재시작하거나 종료할 수 있다.
    - 재시작해도 처음에 만든 다리로 재사용한다.
    - 게임 결과의 총 시도한 횟수는 첫 시도를 포함해 게임을 종료할 때까지 시도한 횟수를 나타낸다.
- 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`를 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
    - `Exception`이 아닌 `IllegalArgumentException`, `IllegalStateException` 등과 같은 명확한 유형을 처리한다.

[구현 설계]

<Model>

- BridgeRandomNumberGenerator
    - `generate` : “U”, “D” 랜덤하게 입력받는 함수
    
- BridgeNumberGenerator
    - interface
    
- BridgeMaker
    - 멤버변수
        - `bridgeNumberGenerator` : `BridgeNumberGenerator` 객체
        
    - `makeBridge` : 입력받은 길이에 해당하는 다리 모양. 위 칸이면 "U", 아래 칸이면 "D"로 표현하여 정답 Bridge List 반환

- BridgeGame
    - 멤버 변수
        - 총 시도 횟수 (Int)
        - 정답 다리 상태 (char 배열)
        - 현재 다리 상태 (char 배열)
    - 함수
        - `retry` : 게임 재실행 여부 판단 함수
            - “Q” || “R”
            - 이외 예외처리
        - `resetBridgeState` : 현재 다리 상태를 리셋하는 함수
            - 현재 다리 []로 초기화
        - `move` : 사용자에게 이동할 칸(U,D)을 입력 받고 이동하는 함수
            - “U” || “D”
                - 움직일 수 있는 발판을 밟았으면,
                    - 움직인 후, return true;
                - 움직일 수 없는 발판을 밟았으면,
                    - return false;
            - 이외 예외처리
        - `getCurrentBridgeState` : 현재 다리 상태 반환하는 함수
        - `getTotalTryCount` : 총 시도 횟수를 반환하는 함수

- Validator
    - `validateBridgeCnt` : 생성할 다리 갯수 입력이 올바른지 판단하는 함수
        - 3이상 20 이하의 숫자인지 판단
            - 만약, 올바른 입력이 아니라면 예외처리
    - `validateCorrectMove` : 이동할 칸 입력이 올바른지 판단하는 함수
        - “U”, ”D” 둘 중 하나의 문자를 입력할 수 있음
            - 만약, 올바른 입력이 아니라면 예외처리
    - `validateCorrectRetry` : 게임 재시도 여부 입력이 올바른지 판단하는 함수
        - “R”, “Q” 둘 중 하나의 문자를 입력할 수 있음
            - 만약, 올바른 입력이 아니라면 예외 처리
    

<View>

- InputView
    - `readBridgeSize` : 생성할 다리 갯수 입력 받는 함수
    - `readMoving` : 이동할 칸 입력 받는 함수
    - `readGameCommand` : 게임 재시도 여부 입력 받는 함수
- OutputView
    - `printGameStart` : 게임 시작 알림 함수
        
        ```bash
        다리 건너기 게임을 시작합니다.
        ```
        
    - `printMap` : 다리 상태 출력 함수
        
        ```bash
        [ O ]
        [   ]
        
        [ O | X ]
        [   |   ]
        ```
        
    - `printResult` : 게임 결과 출력 함수
        
        ```bash
        최종 게임 결과
        [ O |   |   ]
        [   | O | O ]
        
        게임 성공 여부: 성공
        총 시도한 횟수: 2
        ```
        
- ErrorView
    - `printErrorMessage` : 에러 메시지 출력 함수

<Controller>

- gameController
    - 생성자로 inputView, outputView, ErrorView, Bridge 객체 생성
    - while(true)
        1. OutputView 객체를 통해 `printGameStart` 함수 호출
        2. InputView 객체를 통해 `readBridgeSize` 함수 호출
            - 입력을 받으면, Validator 객체의 `validateBridgeCnt` 함수로 검증 진행
                - 이상이 있다면, ErrorView 객체의 에러 메시지 출력 함수에 `IllegalArgumentException` 던지기
                - 이상 없다면, BridgeMaker 객체의 `makeBridge` 함수 호출
        3. InputView 객체를 통해 `readMoving` 함수 호출
            
            3-1. 입력을 받으면, Validator 객체의 `validateCorrectMove` 함수로 검증 진행
            
            - 이상이 있다면, ErrorView의 에러 메시지 출력 함수에 `IllegalArgumentException` 던지기
            - 이상 없다면, BridgeGame 객체의 `move` 함수 호출
                - true 반환시
                    - 모든 다리를 건넜을 경우 즉, 성공했을 때
                        - OutputView 객체의 `printResult` 함수 호출 후, 종료
                    - 남은 다리가 있을 경우
                        1. OutputView 객체의 `printMap` 함수 호출
                        2. 3번 다시 실행
                - false 반환시 즉, 실패했을 때
                    1. OutputView 객체의 `printResult` 함수 호출
                    2. InputView 객체의 `readGameCommand` 함수 호출
                        - 입력을 받으면, Validator 객체의 `validateCorrectRetry` 함수로 검증 진행
                            - 이상이 있다면 ErrorView의 에러 메시지 출력 함수에 `IllegalArgumentException` 던지기
                                - “Q”를 입력 받으면
                                    1. break 후, 게임 종료
                                - “R”을 입력 받으면,
                                    1. BridgeGame 객체의 `resetBridgeState` 함수 호출
                                    2. 다시 while문 초입 즉, 1번으로 돌아가 게임 재실행