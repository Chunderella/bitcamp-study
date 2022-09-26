package com.bitcamp.board.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.Board;


@WebServlet("/board/list")
public class BoardListController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  //얘가 BoardList

  BoardDao boardDao;


  @Override
  public void init() throws ServletException {
    // TODO Auto-generated method stub
    boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException { //자기가 받은 것을

    try {
      List<Board> boards = boardDao.findAll();

      //JSP 사용할 수 있도록 SelvletRequest 보관소에 저장한다.
      req.setAttribute("boards",boards);


      //JSP에서 UI생성을 위임한다.
      resp.setContentType("text/html;charset=UTF-8"); //JSP가 출력할 콘텐트의 MIME 타입 설정(서블릿이 설정)
      RequestDispatcher 요청배달자 = req.getRequestDispatcher("/board/list.jsp");  
      요청배달자.include(req, resp);//JSP를 실행한 후 리턴된다.

    }catch (Exception e) { //에러 JSP로 배달할 요청 배달자를 준비시킨다.
      //예외가 발생하면 예외를 처리하는 JSP에게 UI생성을 위임한다.++++++++++++

      RequestDispatcher 요청배달자 = req.getRequestDispatcher("/error.jsp"); //요청 배달자 준비

      //JSP를 실행하기 전에 ServletRequest 보관소에 오류 정보를 담는다.
      req.setAttribute("exception",e);

      //forward() :
      //    -예외가 발생하면 기존의 출력 내용을 모두 버린다.
      //    -JSP에게 처음부터 새로 출력하게 전권을 위임한다.
      요청배달자.forward(req, resp);//JSP를 실행한 후 리턴된다.
      //JSP를 수행하다가 오류가 뜨면 기존의 JSP가 출력하다가 만 것을 모두 버리고 JSP가 새로 출력됨.

      //서블릿컨테이너 : 톰캣서버 -> 이러한 이유로 언제든지 취소할 수 있다.
      // "PrintWriter" -> "StringWriter"에 쌓임 -> Tostring -> 바이트 배열생성 -> 출력 
      //"out.println" -> 쌓임 -> 변환 -> 배열생성 -> 츨력
    }
  }
}
