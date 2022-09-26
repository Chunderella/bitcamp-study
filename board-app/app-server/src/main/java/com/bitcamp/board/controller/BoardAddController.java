package com.bitcamp.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.Board;


@WebServlet("/board/add")
public class BoardAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  //얘가 BoardList

  BoardDao boardDao;


  @Override
  public void init() throws ServletException {
    // TODO Auto-generated method stub
    boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
  }
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException { //자기가 받은 것을

    try {
      Board board = new Board();
      board.title = request.getParameter("title");
      board.content = request.getParameter("content");
      board.memberNo = Integer.parseInt(request.getParameter("writerNo"));

      if (boardDao.insert(board) == 0) {//인서트가 안됐다는 의미
        throw new Exception("게시글 등록 실패!");
      }

      //      Refresh
      // - 응답 프로토콜
      //      HTTP/1.1 200
      //      Content-Type: text/html;charset=UTF-8
      //      Content-Length: 2064
      //      Date: Mon, 26 Sep 2022 05:24:27 GMT
      //      Keep-Alive: timeout=20
      //      Connection: keep-alive

      //      <!DOCTYPE html>
      //      <html>
      //      <head>
      //      <meta charset="UTF-8">
      //      <title>bitcamp</title>
      //      <meta http-equiv='Refresh' content='1; url=list'> <==HTML에 refresh 삽입
      //      </head>
      //      <body>
      //      <h1>게시글 입력-JSP</h1>
      //          <p>게시글을 등록했습니다.</p>
      //      </body>
      //      </html>

      //        자바 코드:
      //      response.setHeader("Refresh","1;url=list");//응답 헤더에 refresh 삽입
      response.setContentType("text/html;charset=UTF-8"); 
      request.getRequestDispatcher("/board/add.jsp").include(request, response);

      //Redirect    
      // - 클라이언트에게 콘탠트를 보내지 않는다.
      // - 응답 프로토콜 
      //        Status Code: 302  <==응답 상태 코드
      //        Location: list
      //        Content-Length: 0 <==콘텐트는 보내지 않는다.
      //        Date: Mon, 26 Sep 2022 05:21:21 GMT
      //        Keep-Alive: timeout=20
      //        Connection: keep-alive
      //     "콘텐트없음"
      //
      //        자바 코드:
      //      response.sendRedirect("list");

    } catch (Exception e) { 
      request.setAttribute("exception",e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }
}
