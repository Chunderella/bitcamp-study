package com.bitcamp.board.handler;

import java.io.PrintWriter;
import java.util.Map;
import com.bitcamp.servlet.Servlet;
import com.bitcamp.servlet.annotation.WebServlet;

@WebServlet(value="/calc")
public class CalculatorHandler implements Servlet {

  //클래스에 생성자가 없으면 컴파일러는 파라미터가 없는 기본 생성자을 자동으로 만든다.

  @Override
  public void service(Map<String,String> paramMap, PrintWriter out) {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset=\"UTF-8\">");
    out.println("<title>bitcamp</title>");
    out.println("</head>");
    out.println("<body>");

    int a = Integer.parseInt(paramMap.get("a"));
    int b = Integer.parseInt(paramMap.get("b"));
    String op = paramMap.get("op");

    switch (op) {
      case "+": out.println(a + b); break;
      case "-": out.println(a - b); break;
      case "*": out.println(a * b); break;
      case "/": out.println(a / b); break;
      default: out.println("해당 연산자를 지원하지 않습니다.");
    }

    out.println("</body>");
    out.println("</html>");
  }
}
