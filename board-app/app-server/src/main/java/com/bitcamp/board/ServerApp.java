package com.bitcamp.board;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import com.bitcamp.board.handler.BoardHandler;
import com.bitcamp.board.handler.MemberHandler;
import com.bitcamp.handler.Handler;
import com.bitcamp.util.BreadCrumb;


//1.클라이언트 접속시 환영 메세지 전송
//2.여러 클라이언트를 순차적으로 접속 처리
//3.여러 클라이언트를 동시 접속 처리
//4.클라이언트가 보낸 요청 값을 받아서 
//5.요청/응답을 무한 반복한다.
//6.quit 명령을 보내면 연결 끊기
//7.환영 메시지 후에 메인 메뉴를 응답한다.
//8.사용자가 선택한 메뉴 번호의 유효성을 검증한다.
//9.메인 메뉴 선택에 따라 핸들러를 실행하여 클라이언트에게 하위 메뉴를 출력한다.
// ->handler 인터페이스 변경
// ->AbstractHanler 추상 클래스의 execute() 변경
//10.breadcrumb 기능을 객체로 분리한다.
//  -breadcramb 클래스를 정의한다. 
//11. 코드 리팩토링
//- execute() 메서드 정의하고 main() 메서드의 코드를 옮긴다.


public class ServerApp {


  //[메인 메뉴 목록 준비]] = 인스턴스필드
  private String[] menus = {"게시판", "회원"};
  private int port;
  ArrayList<Handler> handlers = new ArrayList<>();


  public static void main(String[] args) {
    ServerApp app = new ServerApp(8888);
    app.execute();
  }

  public ServerApp(int port) { //생성자에서 초기화
    this.port = port;

    handlers.add(new BoardHandler(null));
    handlers.add(new MemberHandler(null));
  }

  public void execute() { //client와 통신할 소켓 준비
    try(ServerSocket serverSocket = new ServerSocket(this.port)) {
      System.out.println("서버 실행중 ...");

      while(true) {
        new Thread(new ServiceProcessor(serverSocket.accept())).start();
        System.out.println("클라이언트 접속!");
      }

    }catch(Exception e) {
      System.out.println("서버 실행 중 오류 발생!");
      e.printStackTrace();
    }
  }




  /* =================== main2 메서드 통째로 주석처리==========================
  public static void main2(String[] args) {

    try (//DAO가 사용할 커넥션 객체 준비
        Connection con = DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/studydb","study","1111");
        ) {
      System.out.println("[게시글 관리 클라이언트]");

      welcome();

      //DAO 객체를 준비한다.
      MariaDBMemberDao memberDao = new MariaDBMemberDao(con);
      MariaDBBoardDao boardDao = new MariaDBBoardDao(con);


      //기존에는 생성자 내부에서 받았지만 보드핸들러는 외부에서 '주입' 받는다 = 교체가 용이함



      // "메인" 메뉴의 이름을 스택에 등록한다.
      breadcrumbMenu.push("메인");



      loop: while (true) {

        printTitle();
        printMenus(menus);
        System.out.println();
        //*특정 명령어를 실행했을때 발생하는 오류
        try {


          // 메뉴에 진입할 때 breadcrumb 메뉴바에 그 메뉴를 등록한다.
          breadcrumbMenu.push(menus[mainMenuNo - 1]);


          breadcrumbMenu.pop();

        } catch (Exception ex) {
          System.out.println("입력 값이 옳지 않습니다.");
        }


      } // while
      Prompt.close();

      System.out.println("종료!");

    } catch (Exception e) {
      System.out.println("시스템 오류 발생!");
      e.printStackTrace();
    }
  }
   */


  //출력 스트림을 받아서 출력한다.
  //PrintWriter을 사용하면 기존에 쓰던 print() 사용가능하다.
  static void welcome(DataOutputStream out) throws Exception {

    try (StringWriter strOut = new StringWriter();
        PrintWriter tempOut = new PrintWriter(strOut)) {
      //진짜 클라이언트와 연결된 스트림으로 출력한다.
      tempOut.println("[게시판 애플리케이션]");
      tempOut.println();
      tempOut.println("환영합니다!");
      tempOut.println();
      out.writeUTF(strOut.toString());
    }
  }
  static void error(DataOutputStream out, Exception e)  {

    try (StringWriter strOut = new StringWriter();
        PrintWriter tempOut = new PrintWriter(strOut)) {
      //진짜 클라이언트와 연결된 스트림으로 출력한다.

      tempOut.printf("실행 오류:%s\n",e.getMessage());
      out.writeUTF(strOut.toString());
    }catch(Exception e2) {
      e2.printStackTrace();
    }
  }

  private void printMainMenus(DataOutputStream out) throws Exception {
    try (StringWriter strOut = new StringWriter();
        PrintWriter tempOut = new PrintWriter(strOut)) {

      tempOut.println(BreadCrumb.getBreadCrumbOfCurrentThread().toString());

      for (int i = 0; i < menus.length; i++) {
        tempOut.printf("  %d: %s\n", i + 1, menus[i]);
      }
      tempOut.printf("메뉴를 선택하세요[1..%d](quit: 종료) ", menus.length);
      out.writeUTF(strOut.toString());
    }
  }

  void processMainMenu(DataInputStream in,DataOutputStream out, String request) {

    try {
      int menuNo = Integer.parseInt(request);
      if(menuNo <= 1 || menuNo > menus.length) {
        throw new Exception("메뉴 번호가 옳지 않습니다.");
      }

      //현재 스레드가 사용하는 breadcrumb을 꺼낸다.
      BreadCrumb breadcrumb = BreadCrumb.getBreadCrumbOfCurrentThread();

      //핸들러에 들어가기 전에 BreadCrumb 메뉴에 하위 메뉴 이름을 추가한다.
      breadcrumb.put(menus[menuNo-1]);

      handlers.get(menuNo - 1).execute(in, out); //숫자를 찾아서 실행하고

      //다시 메인 메뉴로 돌아 왔다면 breadcrumb 메뉴에서 한 단계 위로 올라간다.
      breadcrumb.pickUp();

    } catch(Exception e) {
      error(out,e);
    }
  }

  private class ServiceProcessor implements Runnable {

    Socket socket;

    public ServiceProcessor(Socket socket) {
      this.socket = socket;
    }
    @Override
    public void run() {
      try (Socket s = this.socket;
          DataOutputStream out = new DataOutputStream(s.getOutputStream());
          DataInputStream in = new DataInputStream(s.getInputStream())) {

        //스레드를 시작하는 순간, 별도의 실행흐름에서 병행으로 수행된다.
        // 접속한 클라이언트의 이동 경로를 보관할 breadcrumb 객체 준비
        BreadCrumb breadcrumb = new BreadCrumb(); // 현재 스레드 보관소에 저장된다.
        breadcrumb.put("메인");

        //클라이언트에게 환영 메세지를 보낸다.
        // 메인 메뉴를 출력한다.
        welcome(out);

        while(true) {
          //클라이언트가 보낸 요청 정보를 읽는다.

          String request = in.readUTF();

          if(request.equals("quit")) {
            break;

          }else if (request.equals("menu")) {
            printMainMenus(out);

          }else {
            processMainMenu(in, out, request);
          }
        }
        System.out.println("클라이언트와 접속 종료!");

      } catch (Exception e) {
        System.out.println("클라이언트와 통신하는 중 오류 발생");
        e.printStackTrace();
      }
    }
  }
}
