package com.bitcamp.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bitcamp.board.dao.BoardDao;


@WebServlet("/board/delete")
public class BoardDeleteController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  BoardDao boardDao;

  @Override
  public void init() throws ServletException {
    boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
  }
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException { //자기가 받은 것을

    try {
      int no = Integer.parseInt(request.getParameter("no"));

      if (boardDao.delete(no) == 0) {
        throw new Exception("게시글 등록 실패!");
      }

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
      response.sendRedirect("list");

    } catch (Exception e) { 
      request.setAttribute("exception",e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }
}
