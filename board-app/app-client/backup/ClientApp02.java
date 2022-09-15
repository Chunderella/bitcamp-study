package com.bitcamp.board;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import com.bitcamp.util.Prompt;


public class ClientApp02 {

  public static void main(String[] args) {

    System.out.println("[게시글 관리 클라이언트]");

    //서버에 접속하기
    //1. 서버와 통신할 소켓 준비하기
    try(Socket socket = new Socket("localHost",8888);


        //2.주고 받는 값은 문자열로 주고 받아야함. 이떄 사용하는 것이 Scanner        
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream())){
      //입출력을 하는데 처음에 Scanner을 넣어서 인트값, 문자열 한줄 출력이 가능하나
      //한 덩어리의 문자열을 주고 받아야하기 때문에 
      //데코레이터(Scanner)로 기능을 설계하면 붙였다가 때고 수정하는 기능 확장이 가능하다.
      //Date(input/Out)Stream 으로 기능 확장 - 생성자에 붙인다.


      //서버가 보낸 문자열을 읽기(1줄씩)
      String line = null;

      line = in.readUTF();
      System.out.println(line);

    }catch (Exception e) {
      System.out.println("서버와 통신 중 오류 발생!");
      e.printStackTrace();

    }
    //


    //    loop: while (true) {
    //
    //      printTitle();
    //      printMenus(menus);
    //      System.out.println();
    //      //*특정 명령어를 실행했을때 발생하는 오류
    //      try {
    //        int mainMenuNo = Prompt.inputInt(String.format(
    //            "메뉴를 선택하세요[1..%d](0: 종료) ", handlers.size()));
    //
    //        if (mainMenuNo < 0 || mainMenuNo > menus.length) {
    //          System.out.println("메뉴 번호가 옳지 않습니다!");
    //          continue; // while 문의 조건 검사로 보낸다.
    //
    //        } else if (mainMenuNo == 0) {
    //          break loop;
    //        }
    //
    //        // 메뉴에 진입할 때 breadcrumb 메뉴바에 그 메뉴를 등록한다.
    //        breadcrumbMenu.push(menus[mainMenuNo - 1]);
    //
    //        // 메뉴 번호로 Handler 레퍼런스에 들어있는 객체를 찾아 실행한다.
    //        handlers.get(mainMenuNo - 1).execute();
    //
    //        breadcrumbMenu.pop();
    // } catch (Exception e) {
    //        System.out.println("입력 값이 옳지 않습니다.");
    //
    //
    //  } // while
    Prompt.close();

    System.out.println("종료!");


  }
}
