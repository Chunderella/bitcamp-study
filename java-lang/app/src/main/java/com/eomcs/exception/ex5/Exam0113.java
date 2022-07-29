// 예외 처리 전 - 3단계 -try-with-resources를 이용하여 자원 해제를 자동화하기
package com.eomcs.exception.ex5;

import java.sql.Date;
import java.util.Scanner;

public class Exam0113 {



  static Board read() {

    try {Scanner keyScan = new Scanner(System.in) {
      Board board = new Board();

      System.out.print("번호> ");
      board.setNo(Integer.parseInt(keyScan.nextLine()));

      System.out.print("제목> ");
      board.setTitle(keyScan.nextLine());

      System.out.print("내용> ");
      board.setContent(keyScan.nextLine());

      System.out.print("등록일> ");
      board.setCreatedDate(Date.valueOf(keyScan.nextLine()));

      keyScan.close(); //개발자가 직접 자원을 해제시킨다.
      // "주의"
      // ==>close() 호출하기 전에 예외가 발생한다면,
      // Scanner 자원이 해제되지 못한다.
      // "해결책"
      // ==> 정상적으로 실행되든 예외가 발생하든지 간에
      // 무조건 close()가 실행되게 만들라!
      // "어떻게?"
      //finally{}에 자원 해제 시키는 코드를 담아라!

      return board;



    } 

    //read가 리턴한 board의 값을 받아서 출력한다.

    public static void main(String[] args) {
      Board board = read();
      System.out.println("---------------------");
      System.out.printf("번호: %d\n", board.getNo());
      System.out.printf("제목: %s\n", board.getTitle());
      System.out.printf("내용: %s\n", board.getContent());
      System.out.printf("등록일: %s\n", board.getCreatedDate());
    }
    }


