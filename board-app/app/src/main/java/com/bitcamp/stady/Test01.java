package com.bitcamp.stady;

import com.bitcamp.board.domain.Board;
import com.google.gson.Gson;

public class Test01 {

  public static void main(String[] args) {
    Board b = new Board();
    b.no = 100;
    b.title = "제목입니다.";
    b.content = "내용입니다.";
    b.writer = "홍길동";
    b.password = "1111";
    b.viewCount = 11;
    b.createdDate = System.currentTimeMillis();

    //object --> JSON 문자열
    Gson gson = new Gson();
    String json = gson.toJson(b);
    System.out.println(json);

    Board b2 = gson.fromJson(json, Board.class); //class = 스태틱 변수 (자바에 모든 클래스에 내장된 스태틱 변수 이름)
    //이 fromJson 에서 나온 설계도(Board.class)에 따라 인스턴스
    System.out.println(b2);
    System.out.println(b == b2);
  }

}