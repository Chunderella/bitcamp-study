package com.bitcamp.board.servlet;

import java.io.PrintWriter;

public class ErrorServlet {
  public void error(PrintWriter out) {

    out.println(" <!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println(" <meta charset=\"UTF-8\">");
    out.println("<title>bitcamp</title>");
    out.println(" </head>");
    out.println(" <body>");
    out.println(" <h1>요청 오류!</h1>");
    out.println(" <p>요청한 자원을 찾을 수 없습니다.</p>");
    out.println(" </body>");
    out.println(" </html>");

  }
}


//stateless 한번 연결하고 끊는 형태