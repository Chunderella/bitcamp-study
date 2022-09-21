package com.bitcamp.board.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(value="/welcome")
public class WelcomeServlet extends HttpServlet { //GenericServlet를 상속받는다.

  private static final long serialVersionUID = 1L;

  //수퍼클래스의 매서드를 서브클래스에 역할에 맞춰서 다른 코드를 집어넣는것 : 오버라이딩

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {

    //  콘텐트를 출력하는 출력 스트림을 준비하기 전에
    //  어떤 인코딩으로 콘텐트를 출력할 것인지 먼저 설정해야 한다.
    res.setContentType("text/html; charset=UTF-8");    //(암기1)
    PrintWriter out = res.getWriter();                 //(암기2)


    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset=\"UTF-8\">");
    out.println("<title>bitcamp</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>환영합니다!3</h1>");
    out.println("<p>비트캠프 게시판 관리 시스템 프로젝트입니다.</p>"); 
    out.println("<ul>");
    out.println("  <li><a href='board/list'>게시글</a></li>");
    out.println("  <li><a href='member/list'>회원</a></li>");
    out.println("</ul>");
    out.println("</body>");
    out.println("</html>");
  }
}
