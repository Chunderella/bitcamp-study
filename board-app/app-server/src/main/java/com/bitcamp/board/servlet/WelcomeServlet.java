package com.bitcamp.board.servlet;

import java.io.PrintWriter;

public class WelcomeServlet {
  public void service(PrintWriter out) {

    out.println(" <!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println(" <meta charset=\"UTF-8\">");
    out.println("<title>bitcamp</title>");
    out.println(" </head>");
    out.println(" <body>");
    out.println(" <h1>환영합니다2!</h1>");
    out.println(" <p>비트캠프 게시판 관리 시스템 프로젝트 입니다.");
    out.println(" </body>");
    out.println(" </html>");

  }
}


//stateless 한번 연결하고 끊는 형태