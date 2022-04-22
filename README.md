# 웹 체스게임
Spark 기반의 웹 체스게임을 Spring으로 대체하는 프로젝트입니다.

## 🚀 1단계 - Spring 적용하기

### 요구사항
- Spring Framework를 활용하여 애플리케이션을 구동한다.
- Spark를 대체하여 Spring MVC를 활용하여 요청을 받고 응답을 한다.
- Spring JDBC를 활용하여 DB 접근하던 기존 로직을 대체한다.

### 프로그래밍 요구사항
- 스프링 애플리케이션으로 체스가 실행 가능 해야한다.
- @Controller나 @RestController를 활용하여 요청을 받아야 한다.
- Spring JDBC에서 제공하는 JdbcTemplate를 이용하여 Connection을 직접 만들어 주는 로직을 대체한다.
- JdbcTemplate는 매번 새로 생성하지 않고 빈 주입을 받아서 사용한다.

## 구현할 기능 목록
- `/` : 체스 게임 시작 UI
- `/reload` : 체스 이어하기
    - `Command` 테이블 데이터를 다 가져온다
    - 초기화된 체스판에 해당 command를 적용시킨다
    - 적용된 체스판을 웹으로 출력한다
- `/start` : 초기화면(체스판 출력)
    - 초기화된 체스판을 출력한다
- `/game` : 기물 이동 후 체스판 출력
    - 입력된 명령어를 체스판에 적용시킨다
    - 적용된 체스판을 웹으로 출력한다
    - 입력된 명령어를 Command 테이블에 저장한다
    - Command 테이블에 모든 명령어를 가져온다
    - 가져온 모든 명령어를 출력한다
    - 만약 게임이 종료된 경우 finish.html로 출력한다
- `/result` : 결과 출력
    - 체스판과 점수 결과를 출력한다