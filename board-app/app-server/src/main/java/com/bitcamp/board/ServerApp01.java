package com.bitcamp.board;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Stack;


//클라이언트 접속시 환영메세지
public class ServerApp01 {

  // breadcrumb 메뉴를 저장할 스택을 준비
  public static Stack<String> breadcrumbMenu = new Stack<>();

  public static void main(String[] args) {
    //client와 통신할 소켓 준비
    try(ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 실행중 ...");



      //클라이언트와 통신하기 위한 입출력 
      //한 덩어리의 문자열을 출력하기 위해 데코레이터 변경
      try(Socket socket = serverSocket.accept();
          DataOutputStream out = new DataOutputStream(socket.getOutputStream());
          DataInputStream in = new DataInputStream(socket.getInputStream())) {

        System.out.println("클라이언트 접속!");

        StringWriter strOut = new StringWriter();
        PrintWriter tempOut = new PrintWriter(strOut);

        //스트링 라이터를 프린트 라이터와 연결하다.
        //프린트 라아티를 이용해 스프링 라이터를 출력하려면
        //스프링 라이터는 내부 버퍼를 이용해 출력한다.
        //스트링 라이터의 버퍼에 welcome이 들어가있고
        //아직 응답한 상태가 아님

        welcome(tempOut);

        //클라이언트로 출력하기 
        //스트링라이터객체의 버퍼에 들어가 있음.
        out.writeUTF(strOut.toString());
        //스트링라이터에 있는 것을 문자열로 불러냄

        System.out.println("클라이언트에게 응답!");

      }catch (Exception e) {
        System.out.println("클라이언트와 통신하는 중 오류 발생");
        e.printStackTrace();
      }


      System.out.println("서버 종료");

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

      // 핸들러를 담을 컬렉션을 준비한다.
      ArrayList<Handler> handlers = new ArrayList<>();
      handlers.add(new BoardHandler(boardDao));
      handlers.add(new MemberHandler(memberDao));

      // "메인" 메뉴의 이름을 스택에 등록한다.
      breadcrumbMenu.push("메인");

      // 메뉴명을 저장할 배열을 준비한다.
      String[] menus = {"게시판", "회원"};

      loop: while (true) {

        printTitle();
        printMenus(menus);
        System.out.println();
        //*특정 명령어를 실행했을때 발생하는 오류
        try {
          int mainMenuNo = Prompt.inputInt(String.format(
              "메뉴를 선택하세요[1..%d](0: 종료) ", handlers.size()));

          if (mainMenuNo < 0 || mainMenuNo > menus.length) {
            System.out.println("메뉴 번호가 옳지 않습니다!");
            continue; // while 문의 조건 검사로 보낸다.

          } else if (mainMenuNo == 0) {
            break loop;
          }

          // 메뉴에 진입할 때 breadcrumb 메뉴바에 그 메뉴를 등록한다.
          breadcrumbMenu.push(menus[mainMenuNo - 1]);

          // 메뉴 번호로 Handler 레퍼런스에 들어있는 객체를 찾아 실행한다.
          handlers.get(mainMenuNo - 1).execute();

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
  static void welcome(PrintWriter out) throws Exception {
    out.println("[게시판 애플리케이션]");
    out.println();
    out.println("환영합니다!");
    out.println();
  }

  static void printMenus(String[] menus) {
    for (int i = 0; i < menus.length; i++) {
      System.out.printf("  %d: %s\n", i + 1, menus[i]);
    }
  }

  protected static void printTitle() {
    StringBuilder builder = new StringBuilder();
    for (String title : breadcrumbMenu) {
      if (!builder.isEmpty()) {
        builder.append(" > ");
      }
      builder.append(title);
    }
    System.out.printf("%s:\n", builder.toString());
  }
}
