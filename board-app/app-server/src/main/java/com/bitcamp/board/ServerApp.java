package com.bitcamp.board;

import java.net.ServerSocket;

public class ServerApp {
  public static void main(String[] args) {
    System.out.println("[게시글 데이터 관리 서버]");


    try {
      // 네트워크 준비
      // => 클라이언트 연결을 관리할 객체를 준비한다.
      ServerSocket serverSocket = new ServerSocket(8888);
      System.out.println("서버 소켓 준비 완료!");



      //네트워크 종료
      //=> 더이상 클라이언트와 연결하고 싶지 않다면, 네트워크를 종료한다.

      serverSocket.close();

    }catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("서버 종료!");
  }
}
