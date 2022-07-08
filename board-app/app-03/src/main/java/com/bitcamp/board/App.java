/*
 * 게시판 관리 애플리케이션
 * 비트캠프-20200704 */
package com.bitcamp.board;

public class App {

    public static void main(String[] args) {
        System.out.println("[게시판 애플리케이션]");
        System.out.println();
        System.out.println("환영합니다!");
        // System.out.println("\n환영합니다!\n");
        System.out.println();

        System.out.println("메뉴:");
        System.out.println("  1:게시글 목록");
        System.out.println("  2:게시글 상세보기");
        System.out.println();
        System.out.print("메뉴를 선택하세요[1..2] ");

        java.util.Scanner keyboardInput = new java.util.Scanner(System.in);

        int menuNo = keyboardInput.nextInt();

        keyboardInput.close();
        
        if (menuNo == 1) {
            System.out.println("번호 제목 조회수 작성자 등록일");
            System.out.print("[게시글 목록]");
            System.out.print(1);
            System.out.print("\t");
            System.out.print("제목입니다1");
            System.out.print("\t");
            System.out.print(20 + "\t");
            System.out.print("홍길동\t");
            System.out.print("2022-07-08\r\n");

            System.out.print(2 + "\t" + "제목입니다3\t" +
                    11 + "\t" + "홍길동\t" + "2022-07-08\n");

            System.out.println(3 + "\t" + "제목입니다3\t" + 31 + "\t" + "임꺽정\t2022-07-08");

            System.out.printf("%d\t%s\t%d\t%s\t%s\n",
                    4, "제목입니다4", 45, "유관순", "2022-07-08");

        } else if (menuNo == 2) {
            System.out.println("게시판 상세보기");

            System.out.printf("번호:%d\n", 1);
            System.out.printf("제목:%s\n", "제목입니다.");
            System.out.printf("내용:%s\n", "내용입니다.");
            System.out.printf("조회수:%d\n", 100);
            System.out.printf("작성자:%s\n", "홍길동");
            System.out.printf("등록일:%s\n", "2022-07-08");
        } else {
            System.out.println("메뉴 번호가 옳지 않습니다!");
        }
    }
}

// 흐름제어문 or 제어문 (명령문의 실행 순서를 정함) ~가 아니면 ~하고 ~면 ~ 라

// || or (둘중에 하나라도 참이면 이 조건은 참이다)
// 만약 메뉴 넘버가 1보다 작거나 2를 초과할 경우/
// 카멜작성법 소문자대문자
// nextInt 정수값을 읽음 a라는 이름을 가진 메모리에 저장하라. 키보드가 숫자를 읽으면
// expretion 결과를 리턴하는 명령문
// (keyboardInput)로 expretion 입력한 숫자를 입력
// java.lang 패키지 (println)
// java.util 패키지 (Scanner)

/*
 * 
 * }
 * 
 * if (menuNo < 1 || menuNo > 2 ) {
 * System.out.println("1에서 2사이의 메뉴 번호를 입력하세요:");
 * } else {
 * System.out.println("메뉴 번호 ==> " + menuNo);
 */