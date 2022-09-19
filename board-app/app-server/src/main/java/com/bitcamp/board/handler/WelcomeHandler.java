package com.bitcamp.board.handler;

import java.io.PrintWriter;
import java.util.Map;
import com.bitcamp.servlet.Servlet;
import com.bitcamp.servlet.annotation.WebServlet;

<<<<<<< HEAD
@WebServlet(value="/")
public class WelcomeHandler implements Servlet {

=======
@WebServlet(value="/")//범위를 지정해놨기 때문에 클래스 위에만 붙일 수 있다.
//(뽑아쓰기위함)
public class WelcomeHandler implements Servlet {


>>>>>>> f3a826ff53cf1d41a6a6b22c879dec163ed7daf7
  @Override
  public void service(Map<String,String> paramMap, PrintWriter out) {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset=\"UTF-8\">");
    out.println("<title>bitcamp</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>환영합니다!2</h1>");
    out.println("<p>비트캠프 게시판 관리 시스템 프로젝트입니다.</p>");
    out.println("<ul>");
    out.println("  <li><a href='/board/list'>게시글</a></li>");
    out.println("  <li><a href='/member/list'>회원</a></li>");
    out.println("</ul>");
    out.println("</body>");
    out.println("</html>");
  }
}
